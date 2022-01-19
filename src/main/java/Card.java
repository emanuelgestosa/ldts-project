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

    public int getValue() {
        switch (symbol) {
            case "2": return 2;
            case "3": return 3;
            case "4": return 4;
            case "5": return 5;
            case "6": return 6;
            case "7": return 7;
            case "8": return 8;
            case "9": return 9;
            case "#": case "K": case "Q": case "J": return 10;
            default: return 11;
        }
    }
}
