import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class RunApp {
    private User currentUser;

    InputManager inputManager = new InputManager();
    AccountManager accountManager = new AccountManager();

    public RunApp(){
        this.currentUser = new User(UUID.randomUUID().toString(), "sam", "dean", 24, "sam@gmail.com", "sam1234", "momo002.", false,
                accountManager.xx);

        currentUser.setActive(true);

    }


    public void start(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n===== Welcome to Blue Everest Bank =====\n");


        while (true) {
//            System.out.println("🔗MENU: [ CREATE ACCOUNT | LOGIN ]");
//            System.out.print("To create an account type 'C', or type 'L' to login: ");
//            String input = scanner.nextLine().toLowerCase().trim();
//
//            switch (input) {
//                case "c":
//                    currentUser = accountManager.createAccount();
//                    break;
//                case "l":
//                    currentUser = accountManager.login();
//                    break;
//                default:
//                    System.out.println("⚠️Create an account or login to continue\n");
//                    continue;
//            }

            // currentUser
            while (currentUser.isActive()){
//                String input = scanner.nextLine().toLowerCase().trim();
                loggedInMenuDisplay();
                welcomeDisplay("l");
                for(BankAccount account: currentUser.getAccounts()){
                    System.out.println(capitalize(account.getAccountType()) + " Account") ;
                    System.out.printf("available: $%.2f\n", account.getBalance());
                    System.out.print("\n");
                }

                String action = handleAction(scanner);

                // deposit
                if(action.equals("deposit")){
                    BankAccount selectedAccount = selectAccount(scanner);
                    double amount = inputManager.promptDepositAmount();

                    selectedAccount.deposit(amount);
                }

                // withdraw
                if(action.equals("withdraw")){
                    BankAccount selectedAccount = selectAccount(scanner);
                    double amount = inputManager.promptWithdrawAmount();

                    selectedAccount.withdraw(amount);
                }

                // transactions
                if(action.equals("transactions") || action.equals("view transactions")){
                    BankAccount selectedAccount = selectAccount(scanner);
                    selectedAccount.viewTransactions();
                }

                // close accounts
                if(action.equals("close account")){
                    double balance = 0;
                    for (BankAccount account : currentUser.getAccounts()) {
                        balance = balance + account.getBalance();
                    }

                    String input = inputManager.promptCloseAccount(balance);

                    if(input.equals("CLOSE ACCOUNT")) {
                        accountManager.closeAccount(currentUser);
                    }

                }

                // logout
                if(action.equals("logout") || action.equals("log out")){
                    accountManager.logout(currentUser);
                }

//                for(User user: accountManager.db.getDb()){
//                    System.out.println(user);
//                }

            }
        }
    }


    // HELPER METHODS
    private void welcomeDisplay(String input){
        String welcome = input.equals("c") ? "Welcome, " : "Welcome Back, ";
        System.out.println(welcome + capitalize(currentUser.getFirstName()));
        System.out.println("Bank Accounts");
        System.out.println("=================");
    }

    private void loggedInMenuDisplay(){
        System.out.println("🔗MENU: [ DEPOSIT | WITHDRAW | VIEW TRANSACTIONS | LOGOUT | CLOSE ACCOUNT ]");
    }

    private String handleAction(Scanner scanner){
        while (true) {
            System.out.print("🔷What would you like to do: ");
            String input = scanner.nextLine().toLowerCase().trim();

            switch (input){
                case "deposit":
                case "withdraw":
                case "transaction":
                case "transactions":
                case "view transactions":
                case "logout":
                case "log out":
                case "close account":
                    break;
                default:
                    System.out.println("⚠️Please choose an action fro the menu");
                    System.out.println("💡input does not need to be capitalized\n");
                    continue;
            }

            return input;
        }
    }

    private BankAccount selectAccount(Scanner scanner){
        BankAccount selectedAccount = currentUser.getAccounts().getFirst();

        System.out.print("\n");
        if(currentUser.getAccounts().size() > 1) {
            while (true) {
                for (BankAccount account : currentUser.getAccounts()) {
                    System.out.println("- " + account.getAccountType());
                }
                System.out.print("🔷Select account: ");
                String input = scanner.nextLine().toLowerCase().trim();

                if(input.equals("checking") || input.equals("savings")){
                    for (BankAccount account : currentUser.getAccounts()) {
                        if (account.getAccountType().equalsIgnoreCase(input)) {
                            selectedAccount = account;
                            break;
                        }
                    }
                    break;
                }else {
                    System.out.println("⚠️Please select an account");
                    System.out.println("💡make sure to include the last 's' in 'savings'\n");
                }
            }
        }

        return selectedAccount;
    }


    // capitalize
    private String capitalize(String word){
        word = word.substring(0, 1).toUpperCase() + word.substring(1);
        return word;
    }



}
