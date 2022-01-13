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

    public int getValue(){
        if (symbol.equals("2"))
            return 2;
        if (symbol.equals("3"))
            return 3;
        if (symbol.equals("4"))
            return 4;
        if (symbol.equals("5"))
            return 5;
        if (symbol.equals("6"))
            return 6;
        if (symbol.equals("7"))
            return 7;
        if (symbol.equals("8"))
            return 8;
        if (symbol.equals("9"))
            return 9;
        if (symbol.equals("10") || symbol.equals("J") || symbol.equals("Q") || symbol.equals("K") )
            return 10;
        else return 11;
    }
}
