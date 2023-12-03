import java.util.Scanner;

public class GameDemo {
    public static void main(String[] args) {

        //游戏开始菜单
        System.out.println("GameDemoV1.0");
        System.out.println("Press 1 to start journey.");
        System.out.println("Press 2 to quit game.");
        Scanner sc1 = new Scanner(System.in);
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
                    int choose2 = sc2.nextInt();
                    switch (choose2){
                        case 1 -> {yourShip1.attack(enemyShip1);enemyShip1.attack(yourShip1);}
                        case 2 -> {
                            //判断是否需要修复
                            if (yourShip1.getBlood() == 10){
                                System.out.println("Do not need repairing.");
                            }
                            else{
                                yourShip1.repair(yourShip1);
                            }
                        }
                    }
                    if(yourShip1.getBlood() == 0 && enemyShip1.getBlood() == 0){
                        System.out.println("You win!");
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
