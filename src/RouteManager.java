import java.security.PublicKey;
import java.util.ArrayList;

public class RouteManager {
    private final InputManager inputManager;
    private final ServiceManager serviceManager;


    // CONSTRUCTOR
    public RouteManager(InputManager inputManager, AccountManager accountManager){
        this.inputManager = inputManager;
        this.serviceManager = new ServiceManager(inputManager, accountManager);
    }

    // METHODS
    public void route(User currentUser) {
        String route = inputManager.promptRoute();

        switch (route) {
            case "deposit" -> serviceManager.depositService(currentUser);
            case "withdraw" -> serviceManager.withdrawService(currentUser);
            case "transfer" -> serviceManager.transferService(currentUser);
            case "view transactions" -> serviceManager.transactionsService(currentUser);
            case "view details" -> serviceManager.viewDetailsService(currentUser);
            case "logout" -> serviceManager.logoutService(currentUser);
            case "close account" -> serviceManager.closeAccountService(currentUser);
        }

    }
}
