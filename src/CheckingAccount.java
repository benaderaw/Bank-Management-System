import java.util.ArrayList;
import java.util.Random;

public class CheckingAccount extends BankAccount{
    private final double dailyWithdrawLimit;
    private double dailyWithdrawLimitTracker;

    public CheckingAccount(){
        super();
        this.setAccountType("checking");
        this.setBalance(10);
        this.dailyWithdrawLimit = 500.00;
        this.dailyWithdrawLimitTracker = 500;

    }

    //METHODS
    @Override
    public double deposit(double amount){
        if(amount <= 0){
            System.out.println("⚠️Deposit amount must be greater then 0\n");
        }else{
            System.out.printf("🔄Processing deposit of $%.2f to Checking Account...\n", amount);
            System.out.println("✅Deposit successfully \n");
        }

        return super.deposit(amount);
    }

    @Override
    public double withdraw(double amount){
        if(amount <= 0){
            System.out.println("⚠️Withdraw amount must be greater then 0\n");
        }else if(dailyWithdrawLimitTracker == 0){
            System.out.println("⚠️Daily withdrawal limit of $500.00 reached\n");
        }else if(amount > dailyWithdrawLimitTracker){
            System.out.println("⚠️Cannot withdrawal more then daily limit of $500.00");
            System.out.println("💡View Details to check remaining withdraw limit\n");
        }else if(amount > getBalance()){
            System.out.println("⚠️insufficient funds\n");
        }else{
            setBalance(getBalance() - amount);
            getTransactions().add("W-$" + amount);
            dailyWithdrawLimitTracker -= amount;

            System.out.printf("🔄Processing withdrawal of $%.2f from Checking Account...\n", amount);
            System.out.println("✅Withdrawal successfully \n");
        }


        return getBalance();
    }

    @Override
    public void viewDetails(User currentUser){
        String typeOfAccount = getAccountType().substring(0, 1).toUpperCase() + getAccountType().substring(1);
        String firstName = currentUser.getFirstName().substring(0, 1).toUpperCase() + currentUser.getFirstName().substring(1);
        String lastName = currentUser.getLastName().substring(0, 1).toUpperCase() + currentUser.getLastName().substring(1);


        super.viewDetails(currentUser);
        System.out.printf("%-20s %20s\n", "Daily Withdraw Limit", String.format("$%.2f", dailyWithdrawLimit));
        System.out.printf("%-20s %20s\n", "Withdraw Limit left", String.format("$%.2f", dailyWithdrawLimitTracker) );
        System.out.printf("%-20s %20s\n", "Account Type", typeOfAccount);
        System.out.printf("%-20s %20s\n", "Account Owner", firstName + " " + lastName );
    }


    @Override
    public String toString() {
        return "accountType: " + getAccountType() + ", balance: " + getBalance();
    }

}
