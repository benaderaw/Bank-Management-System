import java.util.Scanner;

public class RunApp {
    public Scanner scanner = new Scanner(System.in);
    private User currentUser;
    private final InputManager inputManager = new InputManager(scanner);
    private final AccountManager accountManager = new AccountManager(scanner);
    private final MenuDisplayManager displayMenu = new MenuDisplayManager();
    private final LoginOrCreateAccountManager loginOrCreate = new LoginOrCreateAccountManager(accountManager);
    private final DisplayManager displayManager = new DisplayManager();
    private final RouteManager routeManager = new RouteManager(inputManager, accountManager);
    private final DisplayAccountDashboard displayAccountDashboard = new DisplayAccountDashboard();

    // CONSTRUCTOR
    public RunApp(){
        this.currentUser = null;
    }
    
    // METHOD
    public void start(){
        System.out.println("\n\n🏔️Welcome to Everest Bank");

        while (true) {
            displayMenu.preLoginMenu();
            String createOrLogin = inputManager.promptCreateOrLogin();
            this.currentUser = loginOrCreate.LoginOrCreateAccount(createOrLogin, currentUser);
            runLoggedInFlow(createOrLogin);
        }
    }

    private void runLoggedInFlow(String createOrLogin){
        while (currentUser.isActive()){
            displayMenu.postLoginMenu();
            displayManager.welcomeDisplay(createOrLogin, currentUser);
            displayAccountDashboard.displayAccountDashboard(currentUser);
            routeManager.route(currentUser);
        }
    }

}
