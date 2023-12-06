import java.util.Scanner;

public class GameDemo {
    public static void main(String[] args) {

        //游戏开始菜单
        System.out.println("GameDemoV1.0");
        System.out.println("Press 1 to start journey.");
        System.out.println("Press 2 to quit game.");
        Scanner sc1 = new Scanner(System.in);
        //获取用户选择
        int choose1 = sc1.nextInt();
        switch (choose1){
            case 1 -> {
                System.out.println("Game starts.");
                System.out.println("Enemy detected.");

                //生成飞船信息
                Ship yourShip1 = new Ship("Olympus",10,1);
                Ship enemyShip1 = new Ship("Achilles",10,1);

                System.out.println("Enemy ship info: "
                        + enemyShip1.getName() + " "
                        + enemyShip1.getBlood() + "hp "
                        + enemyShip1.getDefense() + "defense.");

                //游戏开始
                while(yourShip1.getBlood() > 0 && enemyShip1.getBlood() >0){
                    System.out.println("Press 1 to attack enemy ship.");
                    System.out.println("Press 2 to repair your ship.");
                    Scanner sc2 = new Scanner(System.in);
                    //获取用户选择
                    int choose2 = sc2.nextInt();
                    switch (choose2){
                        //玩家攻击敌船并敌船反击
                        case 1 -> {
                            yourShip1.attack(enemyShip1);
                            enemyShip1.attack(yourShip1);}
                        case 2 -> yourShip1.repair();//修复玩家船
                    }
                    if(yourShip1.getBlood() == 0 || enemyShip1.getBlood() == 0){
                        System.out.println(yourShip1.getBlood() == 0 ? "You lose!" : "You win!");
                        break;
                    }
                }
            }

            //游戏结束
            case 2 -> {
                System.out.println("See you.");
            }
            default -> {
                System.out.println("No this option");
            }
        }


    }
}
