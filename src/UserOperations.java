public interface UserOperations {
    User createAccount();
    User login();
    void logout(User currentUser);
    void closeAccount(User currentUser);
}
