public interface BankOperations {
    double checkBalance();
    double deposit(double amount);
    double withdraw(double amount);
    void viewTransactions();
    void transfer(BankAccount to, double amount);
    void viewDetails(User currentUser);
}
