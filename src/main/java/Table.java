import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.util.ArrayList;

public class Table {
    private Deck deck;
    private Player player;
    private Dealer dealer;

    //Methods----------------------------------------------------------------Constructor, Getters, Setters
    public Table(String playerName, int money, int deckNum){
        deck = new Deck(deckNum);
        player = new Player(playerName, money);
        dealer = new Dealer();
    }
    public boolean play(){
        deck.shuffle();
        while(!this.gameIsOver()){
            this.round();
            // TEMPORARY
            break;
        }
        return playerWon(player);
    }
    public boolean gameIsOver(){
        if(player.getMoney() <= 0 || deck.isEmpty())
            return true;
        return false;
    }
    public void round(){
        deck.GiveCardTo(dealer);
        deck.GiveCardTo(player);
        deck.GiveCardTo(player);
        // player.turn(deck, dealer.getHand()); //Takes the player's turn
        // deck.GiveCardTo(dealer);
        // dealer.turn(deck); //Takes the dealer's turn
        // prepareForNewRound(); //Calculates who won, removes/adds money to player, empties hands
    }


    private void turn(KeyStroke key){
        processKey(key);
    }

    private int processKey(KeyStroke key) {
        if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'w')
            split(this.player);
        if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'd')
            double_down(this.player);
        if(key.getKeyType() == KeyType.Character && key.getCharacter() == 's')
            stand(this.player);
        if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'a') {
            if(this.player.hand.getValue() <21) {
                hit(this.player);
                return 1;
            }
            return 0;
        }
        return 0;
    }

    private void hit(Player player) {
        this.deck.GiveCardTo(player);
    }

    private void stand(Player player) {
        return;
    }

    private void double_down(Player player) {
        if(player.hand.getCards().size() == 2){
            // player.setBet(player.getBet()*2);
            deck.GiveCardTo(player);
        }
    }

    private void split(Player player) {
    }

    private void prepareForNewRound(){
        //TODO
    }
    public boolean playerWon(Player player){
        if (player.getMoney() > player.getInitialMoney())
            return true;
        return false;
    }

    public void draw(GUI gui) throws IOException {
        gui.drawTable();
        gui.drawHand(dealer);
        gui.drawHand(player);
    }
}
