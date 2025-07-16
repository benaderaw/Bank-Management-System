import java.util.ArrayList;

public abstract class BankAccount implements BankOperations {
    // type of bank accounts - checking and saving
    private String accountType;
    private double balance;
    private ArrayList<Double> transactions;

    public BankAccount(){
        this.accountType = "";
        this.balance = 0;
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
        System.out.println("Transactions");
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


}
