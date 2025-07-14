public class SavingAccount extends BankAccount{
    public SavingAccount() {
        super();
    }

    //METHODS
    @Override
    public void accountType(){
        setAccountType("Saving");
    }

    @Override
    public double deposit(double amount) {
        return getBalance() + amount;
    }

    @Override
    public double withdraw(double amount) {
        double newBalance = 0;
        if (getBalance() >= amount) {
            newBalance = getBalance() - amount;
        }
        return newBalance;
    }

    @Override
    public void checkTransactions() {
        for (double transaction : getTransactions()) {
            System.out.println(transaction);
        }
    }

    @Override
    public double checkBalance() {
        return getBalance();
    }


    //    @Override
    public String toString() {
        return "accountType: " + getAccountType() + ", balance: " + getBalance();
    }
}
