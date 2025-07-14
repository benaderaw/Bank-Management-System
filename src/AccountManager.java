import java.util.ArrayList;
import java.util.Random;

public class AccountManager implements UserOperations {

    public boolean accountCreated;
    public boolean loggedIn;
    public boolean loggedOff;
    public ArrayList<User> db;
    InputManager input = new InputManager();
    Random random = new Random();


    public AccountManager(){
        this.accountCreated = false;
        this.db = new ArrayList<>();
    }

    @Override
    public boolean createAccount(){
        db.add(new User(1234, "sam", "dean", 24, "sam@gmail.com", "sam1234", "momo002."));

        // create ID
        int id = random.nextInt(1, 7265) * 35;

        // first name
        String firstName = input.promptFirstname();

        // last name
        String lastName = input.promptLastname();

        // age
        int age = input.promptAge();

        // email
        String email = input.checkUniqueEmail(db);

        // username
        String username = input.checkUniqueUsername(db);

        // password
        String password = input.passwordValidation();

        input.promptAccountType();


        db.add(new User(id, firstName, lastName, age, email, username, password));

        for(User user: db){
            System.out.println(user);
        }

        return accountCreated;
    }

    // login
    @Override
    public boolean login(){
        return loggedIn;
    }

    // logout
    @Override
    public boolean logout(){
        loggedIn = false;
        return loggedIn;
    }

    @Override
    public void closeAccount(String type){
        switch (type){
            case "checking":
                db.remove("checking");
                break;
            case "saving":
                db.remove("saving");
                break;
            case "all":
                db.remove("checking");
                db.remove("saving");
                break;
            default:
                System.out.println("Please select the type of account you want to close.");
        }
    }
}
