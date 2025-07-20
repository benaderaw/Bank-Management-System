import java.util.ArrayList;
import java.util.Random;

public abstract class BankAccount implements BankOperations {
    // type of bank accounts - checking and saving
    private String accountType;
    private double balance;
    private ArrayList<String> transactions;
    private long accountNumber;
    private long routingNumber;
    private ArrayList<ArrayList<String>> details;

    Random random = new Random();

    public BankAccount(){
        this.accountType = "";
        this.balance = 0;
        this.transactions = new ArrayList<>();
        this.accountNumber = random.nextLong(100000000000L, 999999999999L);
        this.routingNumber = random.nextLong(100000000, 999999999);
        this.details = new ArrayList<>();
    }

    // METHODS
    @Override
    public double checkBalance(){
        return balance;
    }

    @Override
    public double deposit(double amount){
        if(amount > 0){
            balance = amount + balance;
            transactions.add("D+$" + amount);
        }

        return balance;
    }

    @Override
    public double withdraw(double amount){
        if(balance >= amount && amount > 0){
            balance = balance - amount;
            transactions.add("W-$" + amount);
        }
        return balance;
    }

    @Override
    public void viewTransactions(){
        for(String transaction: transactions.reversed()){
            String action = "";
            if(transaction.startsWith("D")) action = "Deposit";
            if(transaction.startsWith("W")) action = "Withdraw";
            if(transaction.startsWith("T")) action = "Transfer";

            System.out.printf("%-15s %15s\n", action, transaction.substring(1));
        }
        System.out.print("\n");
    }

    @Override
    public void transfer(BankAccount to, double amount){
        double transferFromNewBalance = this.getBalance() - amount;
        this.setBalance(transferFromNewBalance);
        this.transactions.add("T-$" + amount);

        double transferToNewBalance = to.getBalance() + amount;
        to.setBalance(transferToNewBalance);
        to.transactions.add("T+$" + amount);
    }

    @Override
    public void viewDetails(User currentUser){
        System.out.printf("%-20s %20d\n", "Account Number",  accountNumber);
        System.out.printf("%-20s %20d\n", "Routing Number", routingNumber);
        System.out.printf("%s\n", "----");
        System.out.printf("%-20s %20s\n", "Current Balance", String.format("$%.2f", balance) );
    }


    // getters and setters
    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public ArrayList<String> getTransactions(){
        return transactions;
    }

    public String getAccountType(){
        return accountType;
    }

    public void setAccountType(String accountType){
        this.accountType = accountType;
    }

    public long getAccountNumber(){
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber){
        this.accountNumber = accountNumber;
    }

    public long getRoutingNumber(){
        return routingNumber;
    }

    public void setRoutingNumber(long routingNumber){
        this.routingNumber = routingNumber;
    }

    public  ArrayList<ArrayList<String>> getDetails(){
        return details;
    }
}
