import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//GameDemoV2.0
public class GameDemo {
    //Management System
    //Sign up method
    public static void signUp(ArrayList<User> Users){
        //Set username
        System.out.println("Enter your username.");
        Scanner sc = new Scanner(System.in);
        String userName = sc.next();

        //Keep operating until the passwords before and after are the same.
        while (true) {
            //Set password
            System.out.println("Enter your password.");
            String password = sc.next();
            //Ensure password
            System.out.println("Ensure your password.");
            String ensurePassword = sc.next();

            //Check whether the passwords are the same
            boolean result = password.equals(ensurePassword);
            if (result) {
                //Generate a four-digit id
                while (true){
                    //Generate random four-digit id
                    Random rand = new Random();
                    int fourDigitRandomNum = rand.nextInt(10000);
                    int id = Math.abs(fourDigitRandomNum);
                    //Check whether the id is the same as an existing one
                    boolean only1 = ensureIdOnly(Users,id);
                    //if so, keep generating until different
                    if (only1){
                        continue;
                    }
                    //if not, add it into array
                    else {
                        User user = new User(id, userName, password);
                        Users.add(user);
                        System.out.println("Account created successfully!");
                        System.out.println("Your id is " + id + ".");
                        break;
                    }
                }
            break;
            }
            System.out.println("The password before and after is different!");
            System.out.println("Please check and try again.");
        }
    }

    //Sign out method
    public static void signOut(ArrayList<User> Users){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the id of your account which you want to sign out.");
        int id = sc.nextInt();
        //查询id在集合中的索引
        int index = getIdIndex(Users,id);
        if (index >= 0){
            //询问是否确认注销
            System.out.println("Are you sure to sign up?");
            System.out.println("Press 1 to ensure.");
            System.out.println("Press 2 to cancel");
            int choose = sc.nextInt();
            switch (choose){
                case 1 -> {
                    Users.remove(index);
                    System.out.println("Sign out successfully.");
                }
                case 2 -> {}
            }
        }
        else {
            System.out.println("User does not exist.");
        }
        return;
    }

    //Sign in method
    public static void signIn(ArrayList<User> Users){
        boolean shouldContinue = true;
        while (shouldContinue){
            System.out.println("Enter your uid.");
            Scanner sc = new Scanner(System.in);
            int uid = sc.nextInt();
            int only0 = getIdIndex(Users,uid);
            if (only0 >= 0){
                System.out.println("Enter your password.");
                String password = sc.next();
                int only1 = getPasswordIndex(Users,password);
                if (only1 >= 0){
                    System.out.println("Sign in successfully. Welcome!");
                    shouldContinue = false;
                }
                else {
                    System.out.println("Password is wrong. Please check and try again.");
                }
            }
            else {
                System.out.println("Uid is wrong. Please check try again.");
            }
        }
    }

    //Reset user method
    public static void resetUser(ArrayList<User> Users){
        String securityCode = "Shaw050722";
        System.out.println("Please enter security code.");
        Scanner sc = new Scanner(System.in);
        String enterCode = sc.next();
        while (true){
            if(enterCode.equals(securityCode)){
                System.out.println("Please enter the id of the user whose password you want to reset.");
                int resetId = sc.nextInt();
                int idIndex = getIdIndex(Users,resetId);
                if (idIndex == -1){
                    System.out.println("User does not exist. Please try again.");
                }
                while(true){
                    User user = Users.get(idIndex);
                    System.out.println("Enter your new password.");
                    String newPassword = sc.next();
                    System.out.println("Ensure your new password.");
                    String ensureNewPassword = sc.next();
                    if (newPassword.equals(ensureNewPassword)){
                        user.setPassword(newPassword);
                        System.out.println("Password reset successfully.");
                        break;
                    }
                    System.out.println("The password before and after is different!");
                    System.out.println("Please check and try again.");
                }
            }
            break;
        }

    }

    //Query user method
    public static void queryUser(ArrayList<User> Users){
        String securityCode = "Shaw050722";
        System.out.println("Please enter security code.");
        Scanner sc = new Scanner(System.in);
        String enterCode = sc.next();
        if(enterCode.equals(securityCode)){
            //判断集合是否为空
            if (Users.isEmpty()){
                System.out.println("No user info yet.");
            }
            else{
                //遍历集合
                for (int i = 0; i < Users.size(); i++) {
                    //打印表头
                    System.out.println("id\t\tusername\tpassword");
                    //获取每个user对象并打印
                    User user = Users.get(i);
                    System.out.println(user.getId() + "\t" + user.getUserName() + "\t" + user.getPassword());
                }
            }
        }
        else {
            System.out.println("Security code error.");
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Main programme
    public static void main(String[] args) {
        ArrayList<User> Users = new ArrayList<>();
        boolean shouldContinue = true;
        //开始菜单
        while(shouldContinue){
            System.out.println("GameDemoV1.0");
            System.out.println("1: Sign up to join us.");
            System.out.println("2: Already have an account? Sign in.");
            System.out.println("3: Sign out.");
            System.out.println("4. Forget your password? Here to reset.");
            System.out.println("5. Enter manage system.");
            Scanner sc1 = new Scanner(System.in);
            int choose1 = sc1.nextInt();
            switch (choose1) {
                case 1 -> signUp(Users);
                case 2 -> {
                    signIn(Users);
                    shouldContinue = false;
                }
                case 3 -> signOut(Users);
                case 4 -> resetUser(Users);
                case 5 -> queryUser(Users);
            }
        }

        //生成飞船信息
        //生成初始友方飞船信息

        Ship yourShip1 = new Ship("Olympus",0,0,0);
        Ship yourShip2 = new Ship("Artemis",0,0,0);
        Ship yourShip3 = new Ship("Aurora",0,0,0);
        Ship[] yourShips = {yourShip1, yourShip2, yourShip3};

        //生成固定敌方飞船信息

        Ship enemyShip1 = new Ship("Achilles",0,0,0);
        Ship enemyShip2 = new Ship("Troy",0,0,0);
        Ship enemyShip3 = new Ship("Ares",0,0,0);

        Ship[] enemyShips = {enemyShip1, enemyShip2, enemyShip3};

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //生成反应炉信息
        Reactor reactor1 = new Reactor("Nuclear reactor",40);
        Reactor reactor2 = new Reactor("Annihilation reactor",50);
        Reactor reactor3 = new Reactor("Zero point energy reactor",55);


        Reactor[] reactors = {reactor1, reactor2, reactor3};
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //生成武器信息
        Weapon weapon1 = new Weapon("Rail gun",20);
        Weapon weapon2 = new Weapon("Laser gun",35);
        Weapon weapon3 = new Weapon("Nuclear missile",40);
        Weapon weapon4 = new Weapon("Annihilation bomb",50);

        Weapon[] weapons = {weapon1, weapon2, weapon3, weapon4};

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //生成护盾信息
        Shield shield1 = new Shield("Vibranium shield",7);
        Shield shield2 = new Shield("Electric energy shield",10);
        Shield shield3 = new Shield("Bioregenerative shield",15);


        Shield[] shields = {shield1, shield2, shield3};
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //游戏开始菜单
        System.out.println("Welcome!");
        System.out.println("Press 1 to start journey.");
        System.out.println("Press 2 to quit game.");

        //开始游戏
        Scanner sc2 = new Scanner(System.in);
        int choose2 = sc2.nextInt();
        loop1: switch (choose2) {
            case 1 -> {
                //创造飞船
                System.out.println("Create your ship:");
                //选择飞船
                System.out.println("Choose your ship.");
                //打印飞船的名字
                System.out.println("1. " + yourShip1.getName());
                System.out.println("2. " + yourShip2.getName());
                System.out.println("3. " + yourShip3.getName());
                Scanner sc = new Scanner(System.in);
                int chooseShip = sc.nextInt();


                //选择飞船
                int f = 0;
                switch (chooseShip) {
                    case 1 -> {
                    }
                    case 2 -> f = 1;
                    case 3 -> f = 2;
                }

                //给飞船添加装备
                //打印反应炉信息
                System.out.println("Choose a reactor for your ship (set blood value).");
                System.out.println(Arrays.toString(reactors));
                int chooseReactor = sc.nextInt();
                int r = 0;
                switch (chooseReactor) {
                    //将飞船初始血量修改为反应炉提供的血量
                    case 1 -> yourShips[f].setBlood(reactors[r].getProvideBlood());
                    case 2 -> {
                        r = 1;
                        yourShips[f].setBlood(reactors[r].getProvideBlood());
                    }
                    case 3 -> {
                        r = 2;
                        yourShips[f].setBlood(reactors[r].getProvideBlood());
                    }
                    default -> System.out.println("Reactor does not exist.");
                }
                //打印武器信息
                System.out.println("Choose a weapon for your ship (set attack power).");
                System.out.println(Arrays.toString(weapons));
                int chooseWeapon = sc.nextInt();
                int w = 0;
                switch (chooseWeapon) {
                    //将飞船攻击力修改为武器提供的攻击力
                    case 1 -> yourShips[f].setAttackPower(weapons[w].getWeaponAttackPower());
                    case 2 -> {
                        r = 1;
                        yourShips[f].setAttackPower(weapons[w].getWeaponAttackPower());
                    }
                    case 3 -> {
                        r = 2;
                        yourShips[f].setAttackPower(weapons[w].getWeaponAttackPower());
                    }
                    case 4 -> {
                        r = 3;
                        yourShips[f].setAttackPower(weapons[w].getWeaponAttackPower());
                    }
                    default -> System.out.println("Weapon does not exist");
                }
                //打印护盾信息
                System.out.println("Choose a shield for your ship (set defense power).");
                System.out.println(Arrays.toString(shields));
                int chooseShield = sc.nextInt();
                int s = 0;
                switch (chooseShield) {
                    case 1 -> yourShips[f].setDefensePower(shields[s].getProvideDefense());
                    case 2 -> {
                        s = 1;
                        yourShips[f].setDefensePower(shields[s].getProvideDefense());
                    }
                    case 3 -> {
                        s = 2;
                        yourShips[f].setDefensePower(shields[s].getProvideDefense());
                    }
                    default -> System.out.println("Shield does not exist.");
                }
                System.out.println("Now you have created your ship. Let's start the game.");
                ////////////////////////////////////////////////////////////////////////////////////////////////////////
                //随机生成敌方飞船
                Random n = new Random();
                int en = n.nextInt(2);
                //随机生成敌方反应炉
                Random a = new Random();
                int er = a.nextInt(3);
                enemyShips[en].setBlood(reactors[er].getProvideBlood());
                //随机生成敌方武器
                Random b = new Random();
                int ew = b.nextInt(2);
                enemyShips[ew].setAttackPower(weapons[ew].getWeaponAttackPower());
                //随机生成敌方护盾
                Random c = new Random();
                int es = c.nextInt(2);
                enemyShips[es].setDefensePower(shields[es].getProvideDefense());

                System.out.println("Enemy detected.");
                System.out.println("Enemy ship info: " + enemyShips[en]
                        + reactors[er] + weapons[ew] + shields[es]);

                //游戏进行中
                loop2:
                while (yourShips[f].getBlood() > 0 && enemyShips[en].getBlood() > 0) {
                    System.out.println("Press 1 to attack enemy ship.");
                    System.out.println("Press 2 to check ship's info.");
                    System.out.println("Press 3 to check enemy's info.");
                    Scanner sc4 = new Scanner(System.in);
                    int choose4 = sc4.nextInt();
                    switch (choose4) {
                        case 1 -> {
                            yourShips[f].attack(enemyShips[en]);
                            enemyShips[en].attack(yourShips[f]);
                        }
                        case 2 -> {
                            System.out.println(yourShips[f].getName()
                                    + " " + reactors[r].getReactorName()
                                    + " " + weapons[w].getWeaponName()
                                    + " " + shields[s].getShieldName());
                        }
                        case 3 -> {
                            System.out.println(enemyShips[en].getName()
                                    + " " + reactors[er].getReactorName()
                                    + " " + weapons[ew].getWeaponName()
                                    + " " + shields[es].getShieldName());
                        }
                        default -> System.out.println("No this option");
                    }


                    if (enemyShips[en].getBlood() == 0) {
                        System.out.println("You win!");
                        System.out.println("Press 1 to return to menu.");
                        System.out.println("Press 2 to quit game.");
                        Scanner sc5 = new Scanner(System.in);
                        int choose5 = sc5.nextInt();
                        switch (choose5) {
                            case 1 -> {
                                break loop1;
                            }
                            case 2 -> {
                                System.out.println("See you!");
                                System.exit(0);
                            }
                        }
                    }
                    if (yourShips[f].getBlood() == 0) {
                        System.out.println("You lose!");
                        System.out.println("Press 1 to start again.");
                        System.out.println("Press 2 to quit game.");
                        Scanner sc6 = new Scanner(System.in);
                        int choose6 = sc6.nextInt();
                        switch (choose6) {
                            case 1 -> {
                                break loop2;
                            }
                            case 2 -> System.exit(0);
                        }
                    }
                }
            }
            //结束游戏
            case 2 -> System.out.println("See you.");
            //其他情况
            default -> System.out.println("No this option.");
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Assisting methods
    //ensureIdOnly method
    public static boolean ensureIdOnly(ArrayList<User> Users,int id){
        return getIdIndex(Users,id) >= 0;
    }

    //getIdIndex method
    public static int getIdIndex(ArrayList<User> Users,int id){
        //Traverse an array
        for (int i = 0; i < Users.size(); i++) {
            //Get every user object
            User user = Users.get(i);
            int uid = user.getId();
            //Check whether the id is the same
            if (uid == id){
                //if so, return index
                return i;
            }
        }
        //if not, return -1
        return -1;
    }

    //getPasswordIndex
    public static int getPasswordIndex(ArrayList<User> Users,String password){
        //遍历集合
        for (int i = 0; i < Users.size(); i++) {
            //获取每个user对象
            User user = Users.get(i);
            //判断password是否相等
            String pswd = user.getPassword();
            if (pswd.equals(password)){
                return i;
            }
        }
        return -1;
    }
}