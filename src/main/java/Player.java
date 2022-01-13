
import com.googlecode.lanterna.input.KeyStroke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Player extends CardHolder{
    private int initialMoney;
    private String name;
    private int money;
    private Hand splitHand;

    public Player(String name, int money){
        super();
        this.name = name;
        this.money = money;
        this.initialMoney = money;
    }

    public char getChar(KeyStroke key){
        return key.getCharacter();
    }

    public boolean processKey(GUI gui, Dealer dealer, Deck deck, char choice, Hand hand) throws IOException{
        if(choice == 'd') {
            if (hand.getHand().size() == 2 && hand.getHand().get(0).getSymbol() == hand.getHand().get(1).getSymbol() && hand.getValue() < 21) {
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
        if(choice == 'w' && splitHand.getHand().size() == 0)
            split(gui, dealer, deck);
        return true;
    }

    public void turn(GUI gui, Dealer dealer, Deck deck, Hand hand) throws IOException {
        boolean inTurn = true;
        while(hand.getValue() < 21 && inTurn) {
            KeyStroke key = gui.getKey();
            char choice = getChar(key);
            inTurn = processKey(gui, dealer, deck, choice, hand); //Takes the player's turn
            Table.draw(gui, dealer, this);
            gui.refresh();
        }
    }

    public int getMoney(){
        return money;
    }

    public int getInitialMoney() {
        return initialMoney;
    }

    public void setMoney(int newMoney) {money = newMoney;}

    public void hit(Deck deck, Hand hand) {
        deck.GiveCardTo(hand);
    }

    private void double_down(Deck deck, Hand hand) {
        deck.GiveCardTo(hand);
        //duplicate bet
    }


    private void split(GUI gui, Dealer dealer, Deck deck) throws IOException{
        List<Card> original = new ArrayList<Card>();
        List<Card> split = new ArrayList<Card>();
        original.add(hand.getCardAt(0));
        split.add(hand.getCardAt(1));
        hand.setHand(original);
        splitHand = new Hand();
        deck.GiveCardTo(this.getHand());
        splitHand.setHand(split);
        deck.GiveCardTo(this.getSplitHand());
        Table.draw(gui, dealer, this);
        gui.refresh();
        //remove bet
        turn(gui, dealer, deck, hand);
        turn(gui, dealer, deck, splitHand);
    }
    public Hand getSplitHand() {
        return splitHand;
    }
    public void reset(){
        super.reset();
        splitHand.getHand().clear();
        //TODO empty player bet
    }

    public void setSplitHand(Hand splitHand) {
        this.splitHand = splitHand;
    }
}


