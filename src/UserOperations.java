public interface UserOperations {
    boolean createAccount();
    User login();
    boolean logout();
    void closeAccount(String type);
}
