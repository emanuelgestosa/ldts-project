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
    public boolean play(GUI gui) throws IOException, InterruptedException {
        deck.shuffle();
        while(!this.gameIsOver()){
            this.round(gui);
        }
        return playerWonGame(player);
    }
    public boolean gameIsOver(){
        if(player.getMoney() <= 0 || deck.getDeck().size() <= 20)
            return true;
        return false;
    }
    public void round(GUI gui) throws IOException, InterruptedException {
        draw(gui, dealer, player, 0);
        gui.refresh();
        player.scanInput(gui);
        deck.GiveCardTo(dealer.getHand());
        deck.GiveCardTo(player.getHand());
        deck.GiveCardTo(player.getHand());
        draw(gui, dealer, player, 1);
        gui.refresh();
        player.turn(gui, dealer,deck, player.getHand());
        deck.GiveCardTo(dealer.getHand());
        draw(gui, dealer, player, 1);
        gui.refresh();
        Thread.sleep(1000);
        dealer.turn(deck); //Takes the dealer's turn
        draw(gui, dealer, player, 1);
        gui.refresh();
        Thread.sleep(1000);
        //TODO Waste some time here
        prepareForNewRound(); //Calculates who won, removes/adds money to player, empties hands
    }

    private void prepareForNewRound(){
        float multipleHand = calculateWhoWon(player.getHand());
        float multipleSplit = calculateWhoWon(player.getSplitHand());
        player.setMoney(player.getMoney() + (player.getHand().getBet()*multipleHand) + (player.getSplitHand().getBet()*multipleSplit));
        if(dealer.getHand().getValue()==21) {
            //TODO balance += insurance*2
        }
        player.reset();
        dealer.reset();
    }

    public boolean playerWonGame(Player player){
        if (player.getMoney() > player.getInitialMoney())
            return true;
        return false;
    }

    public float calculateWhoWon(Hand hand){
        float multiple = 1;
        if(dealer.getHand().getValue() == 21) {
            multiple -= 1.5;
        }
        if(hand.getValue() == 21) {
            multiple += 1.5;
            if(player.getSplitHand().getHand().size() >= 1)
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

    public static void draw(GUI gui, Dealer dealer, Player player, int phase) throws IOException {
        gui.drawTable((int)player.getMoney(), phase);
        gui.drawHand(dealer, 0);
        gui.drawHand(player, player.getSplitHand().getHand().size());
        if(player.getSplitHand().getHand().size() != 0)
            gui.drawSplitHand(player.getSplitHand());
        if(phase == 1)
            gui.drawChip(player, player.getSplitHand().getHand().size());
        gui.refresh();
    }

    public int getPlayerHandValue(){
        return getPlayer().getHand().getValue();
    }

    public Dealer getDealer() {
        return dealer;
    }
}
