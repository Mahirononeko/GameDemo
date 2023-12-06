import java.util.Random;

public class Ship {
    //设置私有化成员变量
    private String name; //设置名字变量
    private int blood; //设置生命值变量
    private int defense; //设置防御值变量
    //空参
    public Ship(){
    }
    //全参
    public Ship(String name, int blood , int defense){
        this.name = name;
        this.blood = blood;
        this.defense = defense;
    }
    //get与set方法
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

    public int getDefense() {
        return defense;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    ///////////////////////////////////////////////////////////////////////////////
    //定义攻击方法
    public void attack(Ship ship){
        //定义造成伤害
        Random r = new Random();
        int hurt = r.nextInt(3) + 2;
        int damage = hurt - ship.getDefense();
        //定义剩余血量
        int remainBlood = ship.getBlood() - damage;
        remainBlood = remainBlood < 0 ? 0 : remainBlood;
        ship.setBlood(remainBlood);
        System.out.println(this.getName() + " attacks " + ship.getName() + ", causing "
                + damage + " damage. " + ship.getName() + " remains " + remainBlood + " hp.");
    }
    //定义修复方法
    public void repair(){
        Random r = new Random();
            int repairblood = r.nextInt(5); // 可根据需要调整修复量
            blood = blood + repairblood;
            System.out.println(this.getName() + " repairs, restoring " + repairblood + " hp. "
                    + this.getName() + " now has " + blood + " hp.");
         //定义修复
    }
}