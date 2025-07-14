import java.util.ArrayList;
import java.util.Scanner;

public class InputManager {
    public String stringInput = "";
    public int intInput = 0;

    Scanner scanner = new Scanner(System.in);

    // firstname
    public String promptFirstname() {
        while (true) {
            System.out.print("First name: ");
            stringInput = scanner.nextLine();

            if (stringInput.isEmpty()) {
                System.out.print("🔶Please provide a first name\n");
                continue;
            }
            break;
        }
        return stringInput;
    }

    // lastname
    public String promptLastname() {
        while (true) {
            System.out.print("Last name: ");
            stringInput = scanner.nextLine();

            if (stringInput.isEmpty()) {
                System.out.print("🔶Please provide a last name\n");
                continue;
            }
            break;
        }
        return stringInput;
    }

    // firstname
    public int promptAge() {
        while (true) {
            System.out.print("Age: ");

            if (scanner.hasNextInt()) {
                intInput = scanner.nextInt();
                if (intInput < 18) {
                    System.out.println("🔶You must be 18 years or older to create an account\n");
                    continue;
                }
                scanner.nextLine();
                break;
            } else {
                scanner.nextLine();
                System.out.println("🔶Age must be a a whole number");
            }
        }

        return intInput;
    }

    // email input and validation
    public boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }


    public String emailValidation() {
        while (true) {
            System.out.print("Email: ");
            stringInput = scanner.nextLine().toLowerCase().trim();

            if (isValidEmail(stringInput)) {
                break;
            } else {
                System.out.println("🔶Invalid email, try again");
            }
        }

        return stringInput;
    }

    public String checkUniqueEmail(ArrayList<User> db) {
        String email;

        OuterLoop:
        while (true) {
            email = emailValidation();

            for (User user : db) {
                if (email.equals(user.getEmail())) {
                    System.out.println("🔶Email already exists, try again");
                    continue OuterLoop;
                }
            }
            break;
        }

        return email;
    }

    // username
    public String usernameValidation() {
        while (true) {
            System.out.print("Username: ");
            stringInput = scanner.nextLine().trim();

            if (stringInput.isEmpty()) {
                System.out.print("🔶Please provide a username\n");
                continue;
            } else if (stringInput.length() < 4) {
                System.out.print("🔶Username needs to be at lease 4 characters long\n");
                continue;
            }
            break;
        }
        return stringInput;
    }

    public String checkUniqueUsername(ArrayList<User> db) {
        String username;

        OuterLoop:
        while (true) {
            username = usernameValidation();// bob88

            for (User user : db) {
                if (username.equals(user.getUsername())) {
                    System.out.println("🔶Username already exists, try a different username");
                    continue OuterLoop;
                }
            }
            break;
        }

        return username;
    }

    // password
    public String passwordValidation() {
        while (true) {
            System.out.print("Password: ");
            stringInput = scanner.nextLine().trim();

            if (stringInput.isEmpty()) {
                System.out.println("🔶Please provide a password");
            } else if (stringInput.length() < 8) {
                System.out.println("🔶Password needs to be 8 characters or above");
            } else {
                break;
            }
        }

        return stringInput;
    }

    // account type
    public String promptAccountType() {

        while (true) {
            System.out.println("Which account type would ypu like to open");
            System.out.print("Checking | Saving | Checking and Saving: ");
            stringInput = scanner.nextLine().toLowerCase().trim();

            if (stringInput.isEmpty()) {
                System.out.println("🔶Please choose account type");
            } else if (stringInput.equals("checking") || stringInput.equals("saving") || stringInput.equals("checking and saving")) {
                break;
            } else {
                System.out.println("🔶Please choose account type");
            }
        }

        return stringInput;
    }


    // login
    public int loginUsernameValidation(ArrayList<User> db) {
        int index = 0;

        System.out.println("Log In");
        System.out.print("Username: ");
        stringInput = scanner.nextLine();

        for (User user : db) {
            if (user.getUsername().equals(stringInput)) {
                index =  db.indexOf(user);
            }
        }

        return index;
    }

    public User loginValidation(ArrayList<User> db){
        while (true){
            int index = loginUsernameValidation(db);
            System.out.print("Password: ");
            stringInput = scanner.nextLine();

            if(db.get(index).getPassword().equals(stringInput)){
                System.out.printf("Welcome back %s, you are now logged in.", db.get(index).getFirstName());
                return db.get(index);
            }

            System.out.println("🔶invalid credentials, try again");
        }
    }














}
