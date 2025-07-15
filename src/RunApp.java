import java.util.ArrayList;
import java.util.Scanner;

public class RunApp {
    private User currentUser;


    public RunApp(){
        this.currentUser = new User(0, "", "", 0, "", "", "", new ArrayList<>());
    }

    Scanner scanner = new Scanner(System.in);



    public void start(){
        while (true) {
            AccountManager accountManager = new AccountManager();
            System.out.println("\n\n===== Welcome to Blue Everest Bank =====");
            System.out.println("MENU: [ CREATE ACCOUNT | LOGIN ]");
            System.out.print("What would you like to do, type 'c' for create account or 'l' for login: ");
            String input = scanner.nextLine().toLowerCase().trim();

            if(input.isEmpty()){
                System.out.println("🔶Create an account of log in to continue");
                continue;
            }

            if(input.equals("c")){
                currentUser = accountManager.createAccount();
            } else if (input.equals("l")) {
                currentUser = accountManager.login();
            }

            while (currentUser.id != 0){
                welcomeDisplay(input);
                CheckingAccount ca = (CheckingAccount) currentUser.accountType.get(0);

                System.out.println(">>" + ca.getAccountType());
                System.out.println("Balance: " +currentUser.accountType.get(0).getBalance());

                loggedInMenuDisplay();
                String action =handleAction();

                if(action.equals("deposit")){
                    System.out.println("lol");
                }

                break;
            }


        }
    }


    private void welcomeDisplay(String input){
        String welcome = input.equals("c") ? "Welcome " : "Welcome Back ";
        System.out.println("\n" + welcome + currentUser.getFirstName().toUpperCase());
    }

    private void loggedInMenuDisplay(){
        System.out.println("\nMENU: [ DEPOSIT | WITHDRAW | TRANSACTIONS | LOGOUT | CLOSE ACCOUNT ]");
    }

    private String handleAction(){
        while (true) {
            System.out.print("What would you like to do: ");
            String input = scanner.nextLine().toLowerCase().trim();

            switch (input){
                case "deposit":
                case "withdraw":
                case "transaction":
                case "logout":
                case "close account":
                    break;
                default:
                    System.out.println("🔶Please choose an action");
                    continue;
            }

            return input;
        }
    }








}
