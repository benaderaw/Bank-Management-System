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










}
