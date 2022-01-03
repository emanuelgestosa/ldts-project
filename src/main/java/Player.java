public class Player {
    public int ID = 0;
    public String name;

    public Player(String name, int ID){
        this.name = name;
        this.ID = ID;
    }

    public void play(){
        // TO DO
    }

    public int getID(){
        return this.ID;
    }

    public String getName(){
        return this.name;
    }
}
