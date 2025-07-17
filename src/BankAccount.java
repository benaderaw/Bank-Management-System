import java.util.ArrayList;
import java.util.Random;

public abstract class BankAccount implements BankOperations {
    // type of bank accounts - checking and saving
    private String accountType;
    private double balance;
    private ArrayList<Double> transactions;
    private long accountNumber;
    private long routingNumber;

    Random random = new Random();

    public BankAccount(){
        this.accountType = "";
        this.balance = 0;
        this.transactions = new ArrayList<>();
        this.accountNumber = random.nextLong(100000000000L, 999999999999L);
        this.routingNumber = random.nextLong(100000000, 999999999);
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
            transactions.add(amount);
        }

        return balance;
    }

    @Override
    public double withdraw(double amount){
        if(balance >= amount && amount > 0){
            balance = balance - amount;
            transactions.add(-amount);
        }
        return balance;
    }

    @Override
    public void viewTransactions(){
        for(double transaction: transactions){
            String action = transaction > 0 ? "Deposit" : "Withdraw";
            if(transaction > 0){
                System.out.printf("%-10s\t\t+$%.2f\n", action, transaction);
            }else{
                System.out.printf("%-10s\t\t-$%.2f\n", action, Math.abs(transaction));
            }
        }
        System.out.print("\n");
    }

    public void transfer(BankAccount to, double amount){

        double transferFromNewBalance = this.getBalance() - amount;
        this.setBalance(transferFromNewBalance);
        transactions.add(-transferFromNewBalance);

        double transferToNewBalance = to.getBalance() + amount;
        to.setBalance(transferToNewBalance);
        transactions.add(transferToNewBalance);

    }



    // getters and setters
    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public ArrayList<Double> getTransactions(){
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
}
