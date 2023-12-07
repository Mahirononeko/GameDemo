import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//GameDemoV1.2
public class GameDemo {
    public static void main(String[] args) {
        //生成飞船信息
        //友方飞船信息
        Ship yourShip1 = new Ship("Olympus",10,1);
        Ship yourShip2 = new Ship("Artemis",10,1);
        Ship yourShip3 = new Ship("Aurora",10,1);
        Ship [] yourShips = {yourShip1,yourShip2,yourShip3};
        //敌方飞船信息
        Ship enemyShip1 = new Ship("Achilles",10,1);
        Ship enemyShip2 = new Ship("Troy",10,1);
        Ship enemyShip3 = new Ship("Ares",10,1);
        Ship [] enemyShips = {enemyShip1,enemyShip2,enemyShip3};

        //储存飞船信息
        ArrayList<Ship[]> Ships = new ArrayList<>();
        Ships.add(yourShips);
        Ships.add(enemyShips);

        //游戏开始菜单
        System.out.println("GameDemoV1.0");
        System.out.println("Press 1 to start journey.");
        System.out.println("Press 2 to quit game.");

        //开始游戏
        Scanner sc1 = new Scanner(System.in);
        int choose1 = sc1.nextInt();
        switch (choose1){
            case 1 -> {
                System.out.println("Game starts.");
                System.out.println("Choose your ship:");

                //选择飞船
                for (int i = 0; i < 3; i++) {
                    System.out.println((i+1) + "." +yourShips[i]);
                }
                int f = 0;
                Scanner sc2 = new Scanner(System.in);
                int choose2 = sc2.nextInt();
                switch (choose2){
                    case 1 -> {}
                    case 2 -> f = 1;
                    case 3 -> f = 2;
                }

                //随机生成敌方飞船
                Random n = new Random();
                int e = n.nextInt(3);
                System.out.println("Enemy detected.");
                System.out.println("Enemy ship info: " + enemyShips[e]);

                //游戏进行中
                while(yourShips[f].getBlood() > 0 && enemyShips[e].getBlood() >0){
                    System.out.println("Press 1 to attack enemy ship.");
                    System.out.println("Press 2 to repair your ship.");
                    Scanner sc3 = new Scanner(System.in);
                    int choose3 = sc3.nextInt();
                    switch (choose3){
                        case 1 -> {
                            yourShips[f].attack(enemyShips[e]);
                            enemyShips[e].attack(yourShips[f]);
                        }
                        case 2 -> {
                            //判断是否需要修复
                            if (yourShip1.getBlood() == 10){
                                System.out.println("Do not need repairing.");
                            }
                            else{
                                yourShip1.repair();
                            }
                        }
                        default -> System.out.println("No this option");
                    }
                    if(enemyShips[e].getBlood() == 0){
                        System.out.println("You win!");
                        break;
                    }
                    if (yourShips[f].getBlood() == 0){
                        System.out.println("You lose!");
                        break;
                    }
                }
            }

            //结束游戏
            case 2 -> {
                System.out.println("See you.");
                break;
            }
            //其他情况
            default -> {
                System.out.println("No this option.");
                break;
            }
        }
    }
}