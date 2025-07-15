public class CheckingAccount extends BankAccount{
    private double dailyWithdrawLimit;
    private double openingMinimum;

    public CheckingAccount(double openingMinimum){
        super();
        this.setAccountType("Checking");
        this.dailyWithdrawLimit = 500.00;
        this.openingMinimum = deposit(openingMinimum);
    }

    //METHODS
    @Override
    public void accountType(){
        setAccountType("Checking");
    }

    @Override
    public double deposit(double amount){
        System.out.printf("Depositing %.2f into checking account...\n", amount);
        return super.deposit(amount);
    }

    @Override
    public double withdraw(double amount){
        System.out.printf("Withdrawing %.2f from checking account...\n", amount);
        double newBalance = 0;
        if(getBalance() >= amount && amount <= dailyWithdrawLimit && amount > 0){
            newBalance =  getBalance() - amount;
            getTransactions().add("-" + amount);
        }
        return newBalance;
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
