public class SavingAccount extends BankAccount{
    private double interestRate;

    public SavingAccount() {
        super();
        this.setAccountType("savings");
        this.setBalance(35);
        this.interestRate = 3.6;
    }

    //METHODS
    @Override
    public double deposit(double amount) {
        if(amount <= 0){
            System.out.println("⚠️Deposit amount must be greater then 0\n");
        }else{
            System.out.printf("🔄Processing deposit of $%.2f to Savings Account...\n", amount);
            System.out.println("✅Deposit successfully \n");
        }

        return super.deposit(amount);
    }

    @Override
    public double withdraw(double amount) {

        if(amount <= 0){
            System.out.println("⚠️Withdraw amount must be greater then 0\n");
        }else if(amount > getBalance()){
            System.out.println("⚠️insufficient funds\n");
        }else{
            setBalance(getBalance() - amount);
            getTransactions().add("W-$" + amount);

            System.out.printf("🔄Processing withdrawal of $%.2f from Savings Account...\n", amount);
            System.out.println("✅Withdrawal successful\n");
        }

        return getBalance();
    }

    @Override
    public void viewTransactions() {
        System.out.println("🔄Loading savings transactions...\n");
        super.viewTransactions();
    }

    public double monthlyInterrestPay(){
        return (double) Math.round((getBalance() * (interestRate / 100)/ 12) * 100)/100;
    }

    @Override
    public String toString() {
        return "accountType: " + getAccountType() + ", balance: " + getBalance();
    }
}
