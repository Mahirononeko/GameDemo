import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SpaceshipWars {
    private static final String SECURITY_CODE = "Shaw050722";

    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        boolean shouldContinue = true;

        while (shouldContinue) {
            displayMainMenu();

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> signUp(users);
                case 2 -> signIn(users);
                case 3 -> signOut(users);
                case 4 -> resetUser(users);
                case 5 -> queryUser(users);
                case 6 -> shouldContinue = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("Welcome to Spaceship Wars!");
        System.out.println("1: Sign up to join us.");
        System.out.println("2: Already have an account? Sign in.");
        System.out.println("3: Sign out.");
        System.out.println("4. Forget your password? Here to reset.");
        System.out.println("5. Enter manage system.");
        System.out.println("6. Quit");
        System.out.print("Choose an option: ");
    }

    private static void signUp(ArrayList<User> users) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String userName = scanner.next();

        while (true) {
            System.out.print("Enter your password: ");
            String password = scanner.next();

            System.out.print("Ensure your password: ");
            String ensurePassword = scanner.next();

            if (password.equals(ensurePassword)) {
                int id = generateUniqueUserId(users);
                User user = new User(id, userName, password);
                users.add(user);

                System.out.println("Account created successfully!");
                System.out.println("Your id is " + id + ".");
                break;
            } else {
                System.out.println("The password before and after is different! Please check and try again.");
            }
        }
    }

    private static void signIn(ArrayList<User> users) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your user id: ");
        int userId = scanner.nextInt();

        int userIndex = getIdIndex(users, userId);
        if (userIndex >= 0) {
            System.out.print("Enter your password: ");
            String password = scanner.next();

            if (users.get(userIndex).getPassword().equals(password)) {
                System.out.println("Sign in successful. Welcome!");
            } else {
                System.out.println("Password is wrong. Please check and try again.");
            }
        } else {
            System.out.println("User does not exist.");
        }
    }

    private static void signOut(ArrayList<User> users) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the id of your account which you want to sign out: ");
        int userId = scanner.nextInt();

        int index = getIdIndex(users, userId);
        if (index >= 0) {
            System.out.print("Are you sure to sign out? Press 1 to ensure, 2 to cancel: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                users.remove(index);
                System.out.println("Sign out successful.");
            }
        } else {
            System.out.println("User does not exist.");
        }
    }

    private static void resetUser(ArrayList<User> users) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter security code: ");
        String enterCode = scanner.next();

        if (enterCode.equals(SECURITY_CODE)) {
            System.out.print("Please enter the id of the user whose password you want to reset: ");
            int resetId = scanner.nextInt();

            int idIndex = getIdIndex(users, resetId);
            if (idIndex != -1) {
                User user = users.get(idIndex);

                while (true) {
                    System.out.print("Enter your new password: ");
                    String newPassword = scanner.next();

                    System.out.print("Ensure your new password: ");
                    String ensureNewPassword = scanner.next();

                    if (newPassword.equals(ensureNewPassword)) {
                        user.setPassword(newPassword);
                        System.out.println("Password reset successfully.");
                        break;
                    } else {
                        System.out.println("The password before and after is different! Please check and try again.");
                    }
                }
            } else {
                System.out.println("User does not exist. Please try again.");
            }
        } else {
            System.out.println("Security code error.");
        }
    }

    private static void queryUser(ArrayList<User> users) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter security code: ");
        String enterCode = scanner.next();

        if (enterCode.equals(SECURITY_CODE)) {
            if (users.isEmpty()) {
                System.out.println("No user info yet.");
            } else {
                System.out.println("id\t\tusername\tpassword");
                for (User user : users) {
                    System.out.println(user.getId() + "\t" + user.getUserName() + "\t" + user.getPassword());
                }
            }
        } else {
            System.out.println("Security code error.");
        }
    }

    private static int generateUniqueUserId(ArrayList<User> users) {
        Random rand = new Random();
        int id;

        do {
            id = rand.nextInt(9000) + 1000;
        } while (getIdIndex(users, id) >= 0);

        return id;
    }

    private static int getIdIndex(ArrayList<User> users, int id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
