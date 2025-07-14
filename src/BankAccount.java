import java.util.ArrayList;

public abstract class BankAccount implements BankOperations {
    // type of bank accounts - checking and saving
    private double balance;
    private ArrayList<Double> transactions;

    public BankAccount(){
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    // METHODS
    @Override
    public double deposit(double amount){
        balance = amount + balance;
        return balance;
    }

    @Override
    public double withdraw(double amount){
        if(balance >= amount){
            balance = balance - amount;
        }
        return balance;
    }

    @Override
    public void checkTransactions(){
        for(double transaction: transactions){
            System.out.println(transaction);
        }
    }

    // getters and setters
    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

}
