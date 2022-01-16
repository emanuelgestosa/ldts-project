import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Table {
    private Deck deck;

    public Player getPlayer() {
        return player;
    }

    private Player player;
    private Dealer dealer;

    //Methods----------------------------------------------------------------Constructor, Getters, Setters
    public Table(String playerName, int money, int deckNum) throws IOException {
        deck = new Deck(deckNum);
        player = new Player(playerName, money);
        dealer = new Dealer();
    }
    public boolean play(GUI gui) throws IOException {
        deck.shuffle();
        while(!this.gameIsOver()){
            this.round(gui);
            // TEMPORARY
            break;
        }
        return playerWonGame(player);
    }
    public boolean gameIsOver(){
        if(player.getMoney() <= 0 || deck.getDeck().size() <= 20)
            return true;
        return false;
    }
    public void round(GUI gui) throws IOException {
        deck.GiveCardTo(dealer.getHand());
        deck.GiveCardTo(player.getHand());
        deck.GiveCardTo(player.getHand());
        draw(gui, dealer, player);
        player.turn(gui, dealer,deck, player.getHand());
        deck.GiveCardTo(dealer.getHand());
        draw(gui, dealer, player);
        //TODO Waste some time here
        dealer.turn(deck); //Takes the dealer's turn
        draw(gui, dealer, player);
        //TODO Waste some time here
        prepareForNewRound(); //Calculates who won, removes/adds money to player, empties hands
    }

    private void prepareForNewRound(){
        float multiple = calculateWhoWon(player.getHand(), player.getSplitHand().getHand().size());
        float newMoney = this.player.getMoney();
        newMoney += this.player.getBet()*multiple;
        this.player.setMoney(newMoney);
        player.reset();
        dealer.reset();
    }
    public boolean playerWonGame(Player player){
        if (player.getMoney() > player.getInitialMoney())
            return true;
        return false;
    }

    public float calculateWhoWon(Hand hand, int split){
        float multiple = 1;
        if(split >= 2){
            multiple += calculateWhoWon(player.getSplitHand(),1);
        }
        if(dealer.getHand().getValue() == 21)
            multiple -= 1.5;
        if(hand.getValue() == 21) {
            multiple += 1.5;
            if(split >= 1)
                multiple -=0.5;
        }
        if(hand.getValue()==21 || dealer.getHand().getValue() == 21)
            return multiple;
        if(hand.getValue() > 21)
            multiple --;
        else if(dealer.getHand().getValue() > 21)
            multiple ++;
        else if(hand.getValue() > dealer.getHand().getValue())
            multiple ++;
        else if(hand.getValue() < dealer.getHand().getValue())
            multiple --;
        return multiple;
    }

    public static void draw(GUI gui, Dealer dealer, Player player) throws IOException {
        gui.drawTable();
        gui.drawHand(dealer);
        gui.drawHand(player);
        gui.refresh();
    }

    public int getPlayerHandValue(){
        return getPlayer().getHand().getValue();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
