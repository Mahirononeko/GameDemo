import java.util.Random;

public class Ship {
    private String name; //set name var
    private int blood; //set blood var
    private int attackPower; //set attack var
    private int defensePower; //set defense var
    public Ship(){
    }
    public Ship(String name, int blood, int attackPower, int defensePower){
        this.name = name;
        this.blood = blood;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
    }
    //get and set method
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getBlood(){
        return blood;
    }
    public void setBlood(int blood) {
        this.blood = blood;
    }
    public int getAttackPower() {
        return attackPower;
    }
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }
    public int getDefensePower() {
        return defensePower;
    }
    public void setDefensePower(int defensePower) {
        this.defensePower = defensePower;
    }
    ///////////////////////////////////////////////////////////////////////////////
    //attack method
    public void attack(Ship ship){
        //define hurt
        Random h = new Random();
        int hurt = h.nextInt(3) + 2;
        int damage = hurt - ship.getDefensePower();
        //define remain blood
        int remainBlood = ship.getBlood() + ship.getDefensePower() - hurt;
        remainBlood = remainBlood < 0 ? 0 : remainBlood;
        ship.setBlood(remainBlood);
        System.out.println(this.getName() + " attacks " + ship.getName());
        System.out.println("causing " + damage + " damage, ");
        System.out.println(ship.getName() + " remains " + remainBlood + "hp.");
    }

    public String toString() {
        return getName()
                + "-" + getBlood() + "hp"
                + " " + getDefensePower() + "defense";
    }
}