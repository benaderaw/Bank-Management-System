import java.util.Random;

public class CheckingAccount extends BankAccount{
    private double dailyWithdrawLimit;
    private double dailyWithdrawLimitTracker;

    public CheckingAccount(){
        super();
        this.setAccountType("checking");
        this.setBalance(10);
        this.dailyWithdrawLimit = 500.00;
        this.dailyWithdrawLimitTracker = 0;

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
        }else if(dailyWithdrawLimitTracker >= 500){
            System.out.println("⚠️Daily withdrawal limit of $500.00 reached\n");
        }else if((amount + dailyWithdrawLimitTracker) > dailyWithdrawLimit){
            System.out.println("⚠️Cannot withdrawal more then daily limit of $500.00\n");
        }else if(amount > getBalance()){
            System.out.println("⚠️insufficient funds\n");
        }else{
            setBalance(getBalance() - amount);
            getTransactions().add("W-$" + amount);
            dailyWithdrawLimitTracker = dailyWithdrawLimitTracker + amount;

            System.out.printf("🔄Processing withdrawal of $%.2f from Checking Account...\n", amount);
            System.out.println("✅Withdrawal successfully \n");
        }


        return getBalance();
    }

    @Override
    public void viewTransactions(){
        System.out.println("🔄Loading checking transactions...\n");
        super.viewTransactions();
    }


    @Override
    public String toString() {
        return "accountType: " + getAccountType() + ", balance: " + getBalance();
    }

}
