public class SavingAccount extends BankAccount{
    private double interestRate;

    public SavingAccount() {
        super();
        this.setAccountType("savings");
        this.setBalance(2500);
        this.interestRate = 3.6;
    }

    //METHODS
    @Override
    public double deposit(double amount) {
        System.out.printf("Depositing %.2f into savings account...\n", amount);
        return super.deposit(amount);
    }

    @Override
    public double withdraw(double amount) {
        System.out.printf("Withdrawing %.2f from savings account...\n", amount);

        if(amount <= 0){
            System.out.println("🔶Withdraw amount must be greater then 0");
        }else if(amount > getBalance()){
            System.out.println("🔶insufficient funds");
        }else{
            setBalance(getBalance() - amount);
            getTransactions().add("-" + amount);
        }

        return getBalance();
    }

    @Override
    public void checkTransactions() {
        System.out.println("Loading savings transactions...\n");
        super.checkTransactions();
    }

    public double monthlyInterrestPay(){
        return (double) Math.round((getBalance() * (interestRate / 100)/ 12) * 100)/100;
    }

    @Override
    public String toString() {
        return "accountType: " + getAccountType() + ", balance: " + getBalance();
    }
}
