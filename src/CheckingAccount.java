public class CheckingAccount extends BankAccount{
    private double dailyWithdrawLimit;

    public CheckingAccount(){
        super();
        this.setAccountType("checking");
        this.dailyWithdrawLimit = 500.00;
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
        if(getBalance() >= amount && amount <= dailyWithdrawLimit && amount > 0){
            setBalance(getBalance() - amount);
            getTransactions().add("-" + amount);
        }else{
            System.out.println("🔶insufficient funds");
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
