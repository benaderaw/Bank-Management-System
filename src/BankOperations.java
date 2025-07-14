public interface BankOperations {
    // user can
    boolean createAccount(String email, String username, String password);
    boolean login();
    void checkBalance();
    double deposit(double amount);
    double withdraw(double amount);
    void checkTransactions();
    boolean logout();
    void closeAccount(String type);
}
