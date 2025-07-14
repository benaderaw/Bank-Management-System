public class CheckingAccount extends BankAccount{

    private String accountType;
    private double dailyWithdrawLimit;
//    private double transactions;

    public CheckingAccount(){
        this.accountType = "Checking";
        this.dailyWithdrawLimit = 500.00;
    }

    @Override
    public double deposit(double amount) {
        return getBalance() + amount;
    }

    @Override
    public double withdraw(double amount){
        double newBalance = 0;
        if(getBalance() >= amount && amount <= dailyWithdrawLimit){
            newBalance =  getBalance() - amount;
        }
        return newBalance;
    }

    @Override
    public void checkTransactions(){
        for(double transaction: getTransactions()){
            System.out.println(transaction);
        }
    }

    @Override
    public double checkBalance(){
        return getBalance();
    }


    //    @Override
    public String toString() {
        return "accountType: " + accountType + ", balance: " + getBalance();
    }






}
