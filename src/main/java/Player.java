public class Player extends CardHolder{
    private int initialMoney;
    private String name;
    private int money;
    private int bet;

    public Player(String name, int money, int bet){
        super();
        this.name = name;
        this.money = money;
        this.initialMoney = money;
        this.bet = bet;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public void turn(Deck deck, Hand dealerHand){
    }

    public int getMoney(){
        return money;
    }

    public int getInitialMoney() {
        return initialMoney;
    }

    public void setMoney(int newMoney) {money = newMoney;}
}
