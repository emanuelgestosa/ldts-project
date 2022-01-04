public class Player extends CardHolder {

    private int money;

    public Player(String name, int money){
        super(name);
        this.money = money;
    }

    public void play(){
        // TO DO
    }

    public String getName(){
        return this.name;
    }
}
