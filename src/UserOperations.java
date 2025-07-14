public interface UserOperations {
    boolean createAccount();
    boolean login();
    boolean logout();
    void closeAccount(String type);
}
