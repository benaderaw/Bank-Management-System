public interface UserOperations {
    User createAccount();
    User login();
    boolean logout();
    void closeAccount(String type);
}
