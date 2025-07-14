interface BankOperations {
    // user can
    void createAccount(String email, String username, String password);
    void login(String username, String password);
    void checkBalance();
    void deposit(double amount);
    void withdraw(double amount);
    void checkTransactions();
    void logout();
    void closeAccount(String type);
}
