public class Player extends CardHolder {

    private int money;

    public Player(String name, int money){
        super(name);
        this.money = money;
    }

    public void play(){
        // TO DO
    }

    public int getMoney(){
        return money;
    }

    public void setMoney(int newMoney) {money = newMoney;}
}
