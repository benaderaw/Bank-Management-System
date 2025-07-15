import java.util.ArrayList;
import java.util.Scanner;

public class RunApp {
    private User currentUser;

    public RunApp(){
        this.currentUser = new User("", "", "", 0, "", "", "", false, new ArrayList<>());
    }

    AccountManager accountManager = new AccountManager();

    BankAccount account = new SavingAccount();

    public void start(){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n\n===== Welcome to Blue Everest Bank =====");
            System.out.println("MENU: [ CREATE ACCOUNT | LOGIN ]");
            System.out.print("To create an account type 'C', or type 'L' to login: ");
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

            while (currentUser.isActive()){
                welcomeDisplay(input);
                System.out.println("Account Type: " + currentUser.getAccountType().getFirst().getAccountType());
                System.out.println("Balance: " + currentUser.getAccountType().getFirst().getBalance());

//                BankAccount account = new CheckingAccount(25);
//                if(currentUser.accountType.getFirst().getAccountType().equalsIgnoreCase("checking")){
//                    account = new CheckingAccount(70);
//                } else if (currentUser.accountType.getFirst().getAccountType().equalsIgnoreCase("savings")) {
//                    account = new SavingAccount();
//                }


                loggedInMenuDisplay();
                String action = handleAction(scanner);

                // deposit
                if(action.equals("deposit")){
                    double amount;
                    while (true){
                        System.out.print("How much would you like to deposit: ");

                        if(scanner.hasNextDouble()){
                            amount = scanner.nextDouble();
                            scanner.nextLine();
                            break;
                        }else{
                            scanner.nextLine();
                            System.out.println("🔶Please type a valid number");
                        }
                    }

                    double newBalance = account.deposit(amount);
                    currentUser.getAccountType().getFirst().setBalance(newBalance);
                }

                // logout
                if(action.equals("logout") || action.equals("log out")){
                    accountManager.logout(currentUser);
                    break;
                }
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

    private String handleAction(Scanner scanner){
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
