public class Card {
    private final String suit;
    private final String symbol;
    public Card(String suit, String symbol){
        this.suit = suit;
        this.symbol = symbol;
    }

    public String getSuit() {
        return suit;
    }

    public String getSymbol() { return symbol; }
}
