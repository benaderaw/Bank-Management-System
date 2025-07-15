public class SavingAccount extends BankAccount{
    private double interestRate;

    public SavingAccount() {
        super();
        this.setAccountType("savings");
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
        if (getBalance() >= amount && amount > 0) {
            setBalance(getBalance() - amount);
            getTransactions().add("-" + amount);
        }else{
            System.out.println("🔶insufficient funds");
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
