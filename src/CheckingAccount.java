public class CheckingAccount extends BankAccount{

    private String accountType;
    private double balance;
    private double deposit;
    private double withdraw;
    private double dailyWithdrawLimit;
//    private double transactions;

    public CheckingAccount(double balance){
        this.accountType = "Checking";
        this.balance = balance;
        this.deposit = 0;
        this.withdraw = 0;
        this.dailyWithdrawLimit = 500.00;
    }


//    @Override
    public String toString() {
        return "accountType: " + accountType + ", balance: " + balance;
    }






}
