import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//GameDemo Final
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
                    int fourDigitRandomNum;
                    do {
                        fourDigitRandomNum = rand.nextInt(10000);
                    }
                    while (fourDigitRandomNum < 1000 || String.valueOf(fourDigitRandomNum).charAt(0) == '0');
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
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Main programme
    public static void main(String[] args) {
        ArrayList<User> Users = new ArrayList<>();
        boolean shouldContinue = true;
        //Menu
        while(shouldContinue){
            System.out.println("Welcome to Spaceship Wars!");
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

        //generate ships
        //generate original player ships' info

        Ship yourShip1 = new Ship("Olympus",0,0,0);
        Ship yourShip2 = new Ship("Artemis",0,0,0);
        Ship yourShip3 = new Ship("Aurora",0,0,0);
        Ship[] yourShips = {yourShip1, yourShip2, yourShip3};

        //generate fixed enemy ship's info

        Ship enemyShip1 = new Ship("Achilles",0,0,0);
        Ship enemyShip2 = new Ship("Troy",0,0,0);
        Ship enemyShip3 = new Ship("Ares",0,0,0);

        //store ship's info
        Ship[] enemyShips = {enemyShip1, enemyShip2, enemyShip3};

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //generate reactors' info
        Reactor reactor1 = new Reactor("Nuclear reactor",40);
        Reactor reactor2 = new Reactor("Annihilation reactor",50);
        Reactor reactor3 = new Reactor("Zero point energy reactor",55);

        //store reactor's info
        Reactor[] reactors = {reactor1, reactor2, reactor3};
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //generate weapons' info
        Weapon weapon1 = new Weapon("Laser gun",35);
        Weapon weapon2 = new Weapon("Nuclear missile",40);
        Weapon weapon3 = new Weapon("Annihilation bomb",50);

        //store weapons' info
        Weapon[] weapons = {weapon1, weapon2, weapon3};

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //generate shields' info
        Shield shield1 = new Shield("Vibranium shield",7);
        Shield shield2 = new Shield("Electric energy shield",10);
        Shield shield3 = new Shield("Bioregenerative shield",15);

        //store shields' info
        Shield[] shields = {shield1, shield2, shield3};
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        loop0: while(true){
            //game manu
            System.out.println("Spaceship Wars.");
            System.out.println("Press 1 to start journey.");
            System.out.println("Press 2 to quit game.");

            //start game
            Scanner sc2 = new Scanner(System.in);
            int choose2 = sc2.nextInt();
            loop1: while(true){
                switch (choose2) {
                    case 1 -> {
                        //choose ship's name
                        System.out.println("Choose a name for your ship.");
                        //print ships' name
                        System.out.println("1. " + yourShip1.getName());
                        System.out.println("2. " + yourShip2.getName());
                        System.out.println("3. " + yourShip3.getName());
                        Scanner sc = new Scanner(System.in);
                        int chooseShip = sc.nextInt();
                        int f = 0;
                        switch (chooseShip) {
                            case 1 -> {
                            }
                            case 2 -> f = 1;
                            case 3 -> f = 2;
                        }

                        //equip ship

                        //print reactors' info
                        System.out.println("Choose a reactor for your ship (set blood value).");
                        System.out.println(Arrays.toString(reactors));
                        int chooseReactor = sc.nextInt();
                        int r = 0;
                        loop3: while (true){
                            switch (chooseReactor) {
                                //set player ship's blood to reactor's provided value
                                case 1 -> {
                                    yourShips[f].setBlood(reactors[r].getProvideBlood());
                                    break loop3;
                                }
                                case 2 -> {
                                    r = 1;
                                    yourShips[f].setBlood(reactors[r].getProvideBlood());
                                    break loop3;
                                }
                                case 3 -> {
                                    r = 2;
                                    yourShips[f].setBlood(reactors[r].getProvideBlood());
                                    break loop3;
                                }
                                default -> System.out.println("Reactor does not exist.");
                            }
                        }

                        //print weapons' info
                        System.out.println("Choose a weapon for your ship (set attack power).");
                        System.out.println(Arrays.toString(weapons));

                        int chooseWeapon = sc.nextInt();
                        int w = 0;
                        loop4: while(true){
                            switch (chooseWeapon) {
                                //set player ship's attack power to the weapon's provided value
                                case 1 -> {
                                    yourShips[f].setAttackPower(weapons[w].getWeaponAttackPower());
                                    break loop4;
                                }
                                case 2 -> {
                                    w = 1;
                                    yourShips[f].setAttackPower(weapons[w].getWeaponAttackPower());
                                    break loop4;
                                }
                                case 3 -> {
                                    w = 2;
                                    yourShips[f].setAttackPower(weapons[w].getWeaponAttackPower());
                                    break loop4;
                                }
                                default -> System.out.println("Weapon does not exist");
                            }
                        }

                        //print shields' info
                        System.out.println("Choose a shield for your ship (set defense power).");
                        System.out.println(Arrays.toString(shields));

                        int chooseShield = sc.nextInt();
                        int s = 0;
                        loop5: while(true){
                            switch (chooseShield) {
                                //set player ship's defense power to the shield's provided value
                                case 1 -> {
                                    yourShips[f].setDefensePower(shields[s].getProvideDefense());
                                    break loop5;
                                }
                                case 2 -> {
                                    s = 1;
                                    yourShips[f].setDefensePower(shields[s].getProvideDefense());
                                    break loop5;
                                }
                                case 3 -> {
                                    s = 2;
                                    yourShips[f].setDefensePower(shields[s].getProvideDefense());
                                    break loop5;
                                }
                                default -> System.out.println("Shield does not exist.");
                            }
                        }

                        System.out.println("Now you have created your ship. Let's start the game.");
                        ////////////////////////////////////////////////////////////////////////////////////////////////////////

                        //generate random enemy ship's name
                        Random n = new Random();
                        int en = n.nextInt(3);

                        //generate random enemy ship's reactor
                        Random a = new Random();
                        int er = a.nextInt(3);
                        //set enemy ship's blood to the reactor's provided value
                        enemyShips[en].setBlood(reactors[er].getProvideBlood());

                        //generate random enemy ship's weapon
                        Random b = new Random();
                        int ew = b.nextInt(3);
                        //set enemy ship's attack power to the weapon's provided value
                        enemyShips[en].setAttackPower(weapons[ew].getWeaponAttackPower());

                        //generate random enemy ship's shield
                        Random c = new Random();
                        int es = c.nextInt(3);
                        //set enemy ship's defense power to the weapon's provided value
                        enemyShips[en].setDefensePower(shields[es].getProvideDefense());

                        //print enemy ship's info
                        System.out.println("Enemy detected.");
                        System.out.println("Enemy ship info: " + enemyShips[en]
                                + "/" + reactors[er] + "/" + weapons[ew] + "/" + shields[es]);

                        //game process
                        loop2: while (yourShips[f].getBlood() > 0 && enemyShips[en].getBlood() > 0) {
                            System.out.println("Press 1 to attack enemy ship.");
                            System.out.println("Press 2 to check ship's info.");
                            System.out.println("Press 3 to check enemy's info.");
                            Scanner sc4 = new Scanner(System.in);
                            int choose4 = sc4.nextInt();
                            switch (choose4) {
                                case 1 -> {
                                    yourShips[f].attack(enemyShips[en]);
                                    //check enemy ship's blood
                                    //if the enemy ship's blood is not zero, then it could attack the player's ship
                                    if (enemyShips[en].getBlood() > 0){
                                        enemyShips[en].attack(yourShips[f]);

                                        //check player ship's blood
                                        //if the player ship's blood zero, announce the player loses
                                        //Then ask whether to try again or to quit game
                                        if (yourShips[f].getBlood() == 0){
                                            System.out.println("You lose!");
                                            System.out.println("Press 1 to start again.");
                                            System.out.println("Press 2 to quit game.");

                                            Scanner sc5 = new Scanner(System.in);
                                            int choose5 = sc5.nextInt();
                                            switch (choose5) {
                                                //if the player choose to try again, break loop2 and restart from loop1
                                                case 1 -> {
                                                    break loop2;
                                                }
                                                //if the player choose to quit, then exit the programme
                                                case 2 -> System.exit(0);
                                            }
                                        }
                                    }
                                    //if the enemy ship's blood is zero, announce the player wins
                                    //Then ask whether to return to manu or to quit game
                                    else {
                                        System.out.println("You win");
                                        System.out.println("Press 1 to return to menu.");
                                        System.out.println("Press 2 to quit game.");

                                        Scanner sc6 = new Scanner(System.in);
                                        int choose6 = sc6.nextInt();
                                        switch (choose6){
                                            //if the player choose to return to menu, break loop1 and restart from menu
                                            case 1 -> {
                                                break loop1;
                                            }
                                            case 2 -> System.exit(0);
                                        }
                                    }
                                }
                                case 2 -> {
                                    System.out.println(yourShips[f]
                                            + "/" + reactors[r]
                                            + "/" + weapons[w]
                                            + "/" + shields[s]);
                                }
                                case 3 -> {
                                    System.out.println(enemyShips[en]
                                            + "/" + reactors[er]
                                            + "/" + weapons[ew]
                                            + "/" + shields[es]);
                                }
                                default -> System.out.println("No this option");
                            }
                        }
                    }
                    //quit game
                    case 2 -> {
                        System.out.println("See you.");
                        break loop0;
                    }
                    default -> System.out.println("No this option.");
                }
            }
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