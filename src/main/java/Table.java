import java.util.ArrayList;

public class Table {
    private Deck deck;
    private Player player;
    private Dealer dealer;

    //Methods----------------------------------------------------------------Constructor, Getters, Setters
    Table(String playerName, int money, int deckNum){
        deck = new Deck(deckNum);
        player = new Player(playerName, money);
        dealer = new Dealer();
    }
    public boolean play(){
        deck.shuffle();
        while(!this.gameIsOver()){
            this.round();
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
        drawGame(dealer, player); //Draws cards on the screen
        player.turn(deck, dealer.getHand()); //Takes the player's turn
        deck.GiveCardTo(dealer);
        drawGame(dealer, player);
        dealer.turn(deck); //Takes the dealer's turn
        drawGame(dealer, player);
        prepareForNewRound(); //Calculates who won, removes/adds money to player, empties hands
    }
    private void prepareForNewRound(){
        //TODO
    }
    public void drawGame(Dealer dealer, Player player){
        if (dealer.getHand().getCards().size() ==1)
            System.out.print("##" + "  ");
        for(Card card : dealer.getHand().getCards()){
            System.out.print(card.getSuit() + card.getSymbol() + "  ");
        }
        System.out.print("\n\n");
        for(Card card : player.getHand().getCards()){
            System.out.print(card.getSuit() + card.getSymbol() + "  ");
        }
    }
    public boolean playerWon(Player player){
        if (player.getMoney() > player.getInitialMoney())
            return true;
        return false;
    }
}