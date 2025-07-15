public class CheckingAccount extends BankAccount{
    private double dailyWithdrawLimit;
    private double dailyWithdrawLimitTracker;

    public CheckingAccount(){
        super();
        this.setAccountType("checking");
        this.dailyWithdrawLimit = 500.00;
        this.dailyWithdrawLimitTracker = 0;
    }

    //METHODS
    @Override
    public double deposit(double amount){
        System.out.printf("Depositing %.2f into checking account...\n", amount);
        return super.deposit(amount);
    }

    @Override
    public double withdraw(double amount){
        System.out.printf("Withdrawing %.2f from checking account...\n", amount);

        if(amount <= 0){
            System.out.println("🔶Withdraw amount must be greater then 0");
        }else if(dailyWithdrawLimitTracker >= 500){
            System.out.println("🔶Daily withdrawal limit of $500.00 reached");
        }else if((amount + dailyWithdrawLimitTracker) > dailyWithdrawLimit){
            System.out.println("🔶Cannot withdrawal more then daily limit of $500.00");
        }else if(amount > getBalance()){
            System.out.println("🔶insufficient funds");
        }else{
            setBalance(getBalance() - amount);
            getTransactions().add("-" + amount);
            dailyWithdrawLimitTracker = dailyWithdrawLimitTracker + amount;
        }

        return getBalance();
    }

    @Override
    public void checkTransactions(){
        System.out.println("Loading checking transactions...\n");
        super.checkTransactions();
    }


    @Override
    public String toString() {
        return "accountType: " + getAccountType() + ", balance: " + getBalance();
    }

}
