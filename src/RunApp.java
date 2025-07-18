import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class RunApp {
    Scanner scanner = new Scanner(System.in);
    private User currentUser;
    private InputManager inputManager = new InputManager(scanner);
    private AccountManager accountManager = new AccountManager(scanner);
    private MenuDisplayManager displayMenu = new MenuDisplayManager();
    private LoginOrCreateAccountManager loginOrCreate = new LoginOrCreateAccountManager();
    private DisplayManager displayManager = new DisplayManager();
    private RouteManager routeManager = new RouteManager(inputManager, accountManager);
    private DisplayAccountDashboard displayAccountDashboard = new DisplayAccountDashboard();

    // CONSTRUCTOR
    public RunApp(){
//        this.currentUser = new User(UUID.randomUUID().toString(), "sam", "dean", 24, "sam@gmail.com", "sam1234", "momo002.", false,
//                accountManager.xx);
        this.currentUser = null;

    }
    
    // METHOD
    public void start(){
        System.out.println("\n\n===== Welcome to Blue Everest Bank =====");

        while (true) {
            displayMenu.preLoginMenu();
            String createOrLogin = inputManager.promptCreateOrLogin();
            this.currentUser = loginOrCreate.LoginOrCreateAccount(createOrLogin, currentUser, accountManager);
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
