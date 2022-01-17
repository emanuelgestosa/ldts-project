
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

public class Player extends CardHolder{
    private float initialMoney;
    private String name;
    private float money;
    private Hand splitHand;
    private float insurance =0;
    Scanner scanner = new Scanner(System.in);

    public Player(String name, float money) throws IOException {
        super();
        this.name = name;
        this.money = money;
        this.initialMoney = money;
        splitHand = new Hand();
    }

    public char getChar(KeyStroke key){
        return toLowerCase(key.getCharacter());
    }

    public boolean processKey(GUI gui, Dealer dealer, Deck deck, char choice, Hand hand) throws IOException{
        if(choice == 'd') {
            if (hand.getHand().size() == 2 && hand.getValue() < 21) {
                double_down(deck, hand);
                return false;
            }
        }
        if(choice == 's') {
            return false;
        }
        if(choice == 'a') {
            if(hand.getValue() < 21)
                hit(deck, hand);
            if(hand.getValue() < 21)
                return true;
            return false;
        }
        if(choice == 'w') {
            split(gui, dealer, deck);
            return false;
        }
        if(choice == 'e') {
            insurance(gui, dealer, deck);
            return false;
        }
        return true;
    }

    public void turn(GUI gui, Dealer dealer, Deck deck, Hand hand) throws IOException {
        boolean inTurn = true;
        Table.draw(gui, dealer, this, 1);
        gui.refresh();
        while(hand.getValue() < 21 && inTurn) {
            KeyStroke key = gui.getKey();
            char choice = getChar(key);
            inTurn = processKey(gui, dealer, deck, choice, hand); //Takes the player's turn
            Table.draw(gui, dealer, this, 1);
            gui.refresh();
        }
    }

    public void scanInput(GUI gui) throws IOException {
        int bet=0;
        gui.drawBet(bet);
        gui.refresh();
        while(true) {
            KeyStroke key= gui.getKey();
            if (key.getKeyType() == KeyType.Enter) {
                if (bet != 0 || bet <= this.money)
                    break;
            }
            else if (key.getKeyType() == KeyType.Character && (int) key.getCharacter() >= 48 && (int) key.getCharacter() <= 57) {
                if (this.money >= (bet * 10) + (int) key.getCharacter() - 48) {
                    bet = (bet * 10) + (int)(key.getCharacter()) - 48;
                    gui.drawBet(bet);
                    gui.refresh();
                }
            }
        }
        money -= bet;
        this.getHand().setBet(bet);
    }

    public float getMoney(){
        return money;
    }

    public float getInitialMoney() {
        return initialMoney;
    }

    public int getBet(Hand hand) {
        return hand.getBet();
    }

    public void setBet(Hand hand, int bet) {
        hand.setBet(bet);
    }

    public void setMoney(float newMoney) {money = newMoney;}

    public void hit(Deck deck, Hand hand) {
        deck.GiveCardTo(hand);
    }

    private void double_down(Deck deck, Hand hand) {
        deck.GiveCardTo(hand);
        money-=hand.getBet();
        hand.setBet(hand.getBet()*2);
    }

    private void split(GUI gui, Dealer dealer, Deck deck) throws IOException{
        if(splitHand.getHand().size() != 0 || hand.getCardAt(0).getSymbol() != hand.getCardAt(1).getSymbol())
            return;
        List<Card> original = new ArrayList<Card>();
        List<Card> split = new ArrayList<Card>();
        original.add(hand.getCardAt(0));
        split.add(hand.getCardAt(1));
        hand.setHand(original);
        splitHand = new Hand();
        deck.GiveCardTo(this.getHand());
        splitHand.setHand(split);
        deck.GiveCardTo(this.getSplitHand());
        Table.draw(gui, dealer, this, 1);
        gui.refresh();
        money-= hand.getBet();
        splitHand.setBet(hand.getBet());
        turn(gui, dealer, deck, hand);
        turn(gui, dealer, deck, splitHand);
    }
    public Hand getSplitHand() {
        return splitHand;
    }
    public void reset(){
        super.reset();
        splitHand.getHand().clear();
        splitHand = new Hand();
    }

    public void setSplitHand(Hand splitHand) {
        this.splitHand = splitHand;
    }

    public void insurance(GUI gui, Dealer dealer, Deck deck){
        if(dealer.getHand().getHand().get(0).getSymbol() != "A")
            return;
        //TODO ask for insurance
    }

    public float getInsurance() {
        return insurance;
    }
}



