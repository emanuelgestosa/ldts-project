public class Player extends CardHolder{
    private int initialMoney;
    private String name;
    private int money;

    public Player(String name, int money){
        super();
        this.name = name;
        this.money = money;
        this.initialMoney = money;
    }

    public int getMoney(){
        return money;
    }

    public int getInitialMoney() {
        return initialMoney;
    }

    public void setMoney(int newMoney) {money = newMoney;}
}
