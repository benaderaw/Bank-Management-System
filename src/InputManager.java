import java.util.ArrayList;
import java.util.Scanner;

public class InputManager {
    public String stringInput = "";
    public int intInput = 0;

    Scanner scanner = new Scanner(System.in);

    // firstname
    public String promptFirstname(){
        while (true){
            System.out.print("First name: ");
            stringInput = scanner.nextLine();

            if(stringInput.isEmpty()){
                System.out.print("🔶Please provide a first name\n");
                continue;
            }
            break;
        }
        return stringInput;
    }

    // lastname
    public String  promptLastname(){
        while (true){
            System.out.print("Last name: ");
            stringInput = scanner.nextLine();

            if(stringInput.isEmpty()){
                System.out.print("🔶Please provide a last name\n");
                continue;
            }
            break;
        }
        return stringInput;
    }

    // firstname
    public int promptAge(){
        while (true){
            System.out.print("Age: ");

            if(scanner.hasNextInt()){
                intInput = scanner.nextInt();
                if(intInput < 18){
                    System.out.println("🔶You must be 18 years or older to create an account\n");
                    continue;
                }
                scanner.nextLine();
                break;
            }else{
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


    public String emailValidation(){
        while (true){
            System.out.print("Email: ");
            stringInput = scanner.nextLine().toLowerCase().trim();

            if(isValidEmail(stringInput)){
                break;
            }else{
                System.out.println("🔶Invalid email, try again");
            }
        }

        return stringInput;
    }

    public String checkUniqueEmail(ArrayList<User> db){
        String email;

        OuterLoop:
        while (true){
            email = emailValidation();

            for(User user: db){
                if(email.equals(user.getEmail())){
                    System.out.println("🔶Email already exists, try again");
                    continue OuterLoop;
                }
            }
            break;
        }

        return email;
    }

    // username
    public String usernameValidation(){
        while (true){
            System.out.print("Username: ");
            stringInput = scanner.nextLine().trim();

            if(stringInput.isEmpty()){
                System.out.print("🔶Please provide a username\n");
                continue;
            }else if(stringInput.length() < 4){
                System.out.print("🔶Username needs to be at lease 4 characters long\n");
                continue;
            }
            break;
        }
        return stringInput;
    }

    public String checkUniqueUsername(ArrayList<User> db){
        String username;

        OuterLoop:
        while (true) {
            username = usernameValidation();// bob88

            for(User user: db){
                if(username.equals(user.getUsername())){
                    System.out.println("🔶Username already exists, try a different username");
                    continue OuterLoop;
                }
            }
            break;
        }

        return username;
    }












}
