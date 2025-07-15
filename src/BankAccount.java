import java.util.ArrayList;

public abstract class BankAccount implements BankOperations {
    // type of bank accounts - checking and saving
    private String accountType;
    private double balance;
    private ArrayList<String> transactions;

    public BankAccount(){
        this.accountType = "";
        this.balance = 1000;
        this.transactions = new ArrayList<>();
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
            transactions.add("+" + amount);
        }

        return balance;
    }

    @Override
    public double withdraw(double amount){
        if(balance >= amount && amount > 0){
            balance = balance - amount;
            transactions.add("-" + amount);
        }
        return balance;
    }

    @Override
    public void checkTransactions(){
        for(String transaction: transactions){
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

    public ArrayList<String> getTransactions(){
        return transactions;
    }

    public String getAccountType(){
        return accountType;
    }

    public void setAccountType(String accountType){
        this.accountType = accountType;
    }


}
