import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class RunApp {
    private final User currentUser;

    InputManager inputManager = new InputManager();
    AccountManager accountManager = new AccountManager();

    // CONSTRUCTOR
    public RunApp(){
        this.currentUser = new User(UUID.randomUUID().toString(), "sam", "dean", 24, "sam@gmail.com", "sam1234", "momo002.", false,
                accountManager.xx);

        currentUser.setActive(true);
    }


    // METHOD
    public void start(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\n===== Welcome to Blue Everest Bank =====\n");

        OuterLoop:
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
            InnerLoop:
            while (currentUser.isActive()){
//                String input = scanner.nextLine().toLowerCase().trim();
                loggedInMenuDisplay();
                welcomeDisplay("l");
                for(BankAccount account: currentUser.getAccounts()){
                    System.out.print(capitalize(account.getAccountType()) + " Account");
                    if(account.getBalance() >= 0){
                        System.out.printf("%-15s $%.2f \n", "", account.getBalance());
                    }else{
                        System.out.printf("-$%15.2f\n", account.getBalance());
                    }
                    System.out.print(hideAccountAndRouting(account.getAccountNumber()));
                    System.out.printf("%-24s %s\n\n", "", "available");
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

                    System.out.println("🧾Transactions");
                    if(selectedAccount.getTransactions().isEmpty()){
                        System.out.println("No transactions available\n");
                    }else{
                        selectedAccount.viewTransactions();
                    }
                }

                // transfer
                if(action.equals("transfer")){
                    System.out.println("🔀Transfer");

                    if(currentUser.getAccounts().size() == 1){
                        System.out.println("⚠️No transfer-to account\n");
                        continue;
                    }

                    // from
                    String transferFrom = inputManager.promptAccountFrom();
                    BankAccount transferFromAccount = transferFrom(transferFrom);

                    if (transferFromAccount.getBalance() <= 0) {
                        System.out.printf("⚠️Insufficient funds for transfer, available balance $%.2f\n\n", transferFromAccount.getBalance());
                        continue InnerLoop;
                    }

                    // to
                    String transferTo = inputManager.promptTransferTo(transferFrom);
                    BankAccount transferToAccount = transferTo(transferTo);

                    // transfer amount
                    double transferAmount = transferAmount(transferFromAccount);

                    transferFromAccount.transfer(transferToAccount, transferAmount);
                    System.out.println("🔄Processing transfer...");
                    System.out.printf("✅Successfully transferred $%.2f from %s Account to %s Account\n\n", transferAmount, capitalize(transferFrom),
                            capitalize(transferTo));

                }

                // view details
                if(action.equals("view details")){
                    BankAccount selectedAccount = selectAccount(scanner);

                    System.out.println("🔄Loading details...\n");
                    System.out.println("📑Details");
                    selectedAccount.viewDetails(currentUser);
                    for(ArrayList<String> details: selectedAccount.getDetails()){
                        System.out.printf("%-20s ", details.get(0));
                        System.out.printf("%20s \n", details.get(1));
                    }
                    System.out.print("\n");
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
        System.out.println(welcome + capitalize(currentUser.getFirstName()) + "!\n");
        System.out.println("Bank Accounts");
        System.out.println("=================");
    }

    private void loggedInMenuDisplay(){
        System.out.println("\n🔗MENU: [ DEPOSIT | WITHDRAW | VIEW TRANSACTIONS | TRANSFER | VIEW DETAILS | LOGOUT | CLOSE ACCOUNT ]");
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
                case "transfer":
                case "view details":
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
                    System.out.println(capitalize(account.getAccountType()));
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

    public  String hideAccountAndRouting(long num){
        String convertToString = "" + num;
        String shortened =  convertToString.substring(convertToString.length() - 6);

        String hiddenNum = "";
        for(int i = 0; i < shortened.length(); i++){
            if(i < 2){
                hiddenNum = hiddenNum + "*";
            }else{
                hiddenNum = hiddenNum + shortened.charAt(i);
            }
        }

        return hiddenNum;
    }

    public BankAccount transferFrom(String transferFrom){
        BankAccount transferFromAccount = null;

        // get transfer-from account
        for (BankAccount account : currentUser.getAccounts()) {
            if (account.getAccountType().equals(transferFrom)) {
                transferFromAccount = account;
                break;
            }
        }

        return transferFromAccount;
    }

    public BankAccount transferTo(String transferTo){
        BankAccount transferToAccount = null;

        // get transfer-to account
        for (BankAccount account : currentUser.getAccounts()) {
            if (account.getAccountType().equals(transferTo)) {
                transferToAccount = account;
               break;
            }
        }

        return transferToAccount;
    }

    public double transferAmount(BankAccount transferFromAccount){
        double transferAmount;
        while (true) {
            transferAmount = inputManager.promptTransferAmount(transferFromAccount);

            if (transferAmount <= 0) {
                System.out.println("⚠️Please enter an amount greater then 0\n");
                continue;
            }

            if (transferAmount > transferFromAccount.getBalance()) {
                System.out.println("⚠️Amount exceeds your available balance\n");
                continue;
            }

            break;
        }

        return transferAmount;
    }

}
