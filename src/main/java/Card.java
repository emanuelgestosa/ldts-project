public class Card {
    private String naipe;
    private String symbol;
    public Card(String naipe, String symbol){
        this.naipe = naipe;
        this.symbol = symbol;
    }

    public String getNaipe() {
        return naipe;
    }

    public void setNaipe(String naipe) {
        this.naipe = naipe;
    }
}
