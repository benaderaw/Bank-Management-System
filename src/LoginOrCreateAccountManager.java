public class LoginOrCreateAccountManager {
    private final AccountManager accountManager;

    // CONSTRUCTOR
    public LoginOrCreateAccountManager(AccountManager accountManager){
        this.accountManager = accountManager;
    }

    public User LoginOrCreateAccount(String createOrLogin, User currentUser){
        switch (createOrLogin) {
            case "c":
                currentUser = accountManager.createAccount();
                currentUser.setActive(true);
                System.out.println("🔄Processing account creation...");
                System.out.println("✅Account Created\n\n");
                break;
            case "l":
                currentUser = accountManager.login();
                break;
        }

        return currentUser;
    }
}
