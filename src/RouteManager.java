import java.security.PublicKey;
import java.util.ArrayList;

public class RouteManager {
    private InputManager inputManager;
    private AccountManager accountManager;
    
    // CONSTRUCTOR
    public RouteManager(InputManager inputManager, AccountManager accountManager){
        this.inputManager = inputManager;
        this.accountManager = accountManager;
    }

    // METHODS
    public void route(User currentUser){
        String route = inputManager.promptRoute();

        // deposit
        if(route.equals("deposit")){
            String input = inputManager.promptSelectAccount(currentUser);
            BankAccount selectedAccount = selectAccount(input, currentUser);
            double amount = inputManager.promptDepositAmount();

            selectedAccount.deposit(amount);
        }

        // withdraw
        if(route.equals("withdraw")){
            String input = inputManager.promptSelectAccount(currentUser);
            BankAccount selectedAccount = selectAccount(input,currentUser);
            double amount = inputManager.promptWithdrawAmount();

            selectedAccount.withdraw(amount);
        }

        // transactions
        if(route.equals("transactions") || route.equals("view transactions")){
            String input = inputManager.promptSelectAccount(currentUser);
            BankAccount selectedAccount = selectAccount(input, currentUser);

            if(selectedAccount.getTransactions().isEmpty()){
                System.out.println("No transactions available\n");
            }else{
                System.out.printf("🔄Loading %s Account transactions...\n\n", capitalize(selectedAccount.getAccountType()));
                System.out.println("🧾Transactions");
                selectedAccount.viewTransactions();
            }
        }

        // transfer

        if(route.equals("transfer")){
            while (true) {
                System.out.println("🔀Transfer");

                if (currentUser.getAccounts().size() == 1) {
                    System.out.println("⚠️No transfer-to account\n");
                    break;
                }

                // from
                String transferFrom = inputManager.promptAccountFrom();
                BankAccount transferFromAccount = transferFrom(transferFrom, currentUser);

                if (transferFromAccount.getBalance() <= 0) {
                    System.out.printf("⚠️Insufficient funds for transfer, available balance $%.2f\n\n", transferFromAccount.getBalance());
                }

                // to
                String transferTo = inputManager.promptTransferTo(transferFrom);
                BankAccount transferToAccount = transferTo(transferTo, currentUser);

                // transfer amount
                double transferAmount = transferAmount(transferFromAccount);

                transferFromAccount.transfer(transferToAccount, transferAmount);
                System.out.println("🔄Processing transfer...");
                System.out.printf("✅Successfully transferred $%.2f from %s Account to %s Account\n\n", transferAmount, capitalize(transferFrom),
                        capitalize(transferTo));
            }
        }

        // view details
        if(route.equals("view details")){
            String input = inputManager.promptSelectAccount(currentUser);
            BankAccount selectedAccount = selectAccount(input, currentUser);

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
        if(route.equals("close account")){
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
        if(route.equals("logout") || route.equals("log out")){
            System.out.println("🔄Logging out...\n\n");
            accountManager.logout(currentUser);
        }
    }

    // helper methods
    private BankAccount selectAccount(String input, User currentUser){
        BankAccount selectedAccount = currentUser.getAccounts().getFirst();
        if(input.isEmpty()) {
            return selectedAccount;
        }

        if(currentUser.getAccounts().size() > 1) {
            for (BankAccount account : currentUser.getAccounts()) {
                if (account.getAccountType().equalsIgnoreCase(input)) {
                    selectedAccount = account;
                    break;
                }
            }
        }

        return selectedAccount;
    }

    // capitalize first letter
    private String capitalize(String word){
        word = word.substring(0, 1).toUpperCase() + word.substring(1);
        return word;
    }

    private BankAccount transferFrom(String transferFrom, User currentUser){
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

    private BankAccount transferTo(String transferTo, User currentUser){
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

    private double transferAmount(BankAccount transferFromAccount){
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
