import java.util.ArrayList;
import java.util.Scanner;

public class RunApp {
    private User currentUser;

    InputManager inputManager = new InputManager();


    public RunApp(){
        this.currentUser = new User("", "", "", 0, "", "", "", false, new ArrayList<>());
    }

    AccountManager accountManager = new AccountManager();

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

            //
            while (currentUser.isActive()){
                welcomeDisplay(input);
                for(BankAccount account: currentUser.getAccounts()){
                    System.out.println("Account Type: " + account.getAccountType());
                    System.out.println("Balance: " + account.getBalance());
                    System.out.print("\n");
                }

                loggedInMenuDisplay();
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

                    input = inputManager.promptCloseAccount(balance);

                    if(input.equals("CLOSE ACCOUNT")) {
                        accountManager.closeAccount(currentUser);
                    }

                }

                // logout
                if(action.equals("logout") || action.equals("log out")){
                    accountManager.logout(currentUser);
                }

                for(User user: accountManager.db){
                    System.out.println(user);
                }
            }
        }
    }


    // HELPER METHODS
    private void welcomeDisplay(String input){
        String welcome = input.equals("c") ? "Welcome " : "Welcome Back ";
        System.out.println("\n" + welcome + currentUser.getFirstName().toUpperCase());
    }

    private void loggedInMenuDisplay(){
        System.out.println("\nMENU: [ DEPOSIT | WITHDRAW | VIEW TRANSACTIONS | LOGOUT | CLOSE ACCOUNT ]");
    }

    private String handleAction(Scanner scanner){
        while (true) {
            System.out.print("What would you like to do: ");
            String input = scanner.nextLine().toLowerCase().trim();

            switch (input){
                case "deposit":
                case "withdraw":
                case "transactions":
                case "view transactions":
                case "logout":
                case "log out":
                case "close account":
                    break;
                default:
                    System.out.println("🔶Please choose an action");
                    continue;
            }

            return input;
        }
    }

    private BankAccount selectAccount(Scanner scanner){
        BankAccount selectedAccount = currentUser.getAccounts().getFirst();

        if(currentUser.getAccounts().size() > 1) {
            for (BankAccount account : currentUser.getAccounts()) {
                System.out.println("- " + account.getAccountType());
            }

            while (true) {
                System.out.print("Select account: ");
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
                    System.out.println("🔶Please select an account");
                    System.out.println("💡make sure to include the last 's' in 'savings'");
                }
            }
        }

        return selectedAccount;
    }







}
