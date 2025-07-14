import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Random;

public class AccountManager implements UserOperations {

    public boolean accountCreated;
    public boolean loggedIn;
    public boolean loggedOff;
    public ArrayList<User> db;
    public ArrayList<BankAccount> xx;
    public ArrayList<BankAccount> zz;

    InputManager input = new InputManager();
    Random random = new Random();


    public AccountManager() {
        this.accountCreated = false;
        this.db = new ArrayList<>();
        this.xx = new ArrayList<>();
        this.zz = new ArrayList<>();

        System.out.print("\n\n");
        xx.add(new CheckingAccount());
        zz.add(new CheckingAccount());
        db.add(new User(1234, "sam", "dean", 24, "sam@gmail.com", "sam1234", "momo002.", xx));
        db.add(new User(356367, "ben", "aderaw", 33, "ben001@gmail.com", "dogwood", "falcon005.", zz));

        for(User user: db){
            System.out.println(user);
        }

    }

    @Override
    public User createAccount(){
        // create ID
        int id = random.nextInt(1, 7265) * 35;

        /*
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

        String account = input.promptAccountType();

        ArrayList<BankAccount> accountType = new ArrayList<>();
        if(account.equals("checking")){
            accountType.add(new CheckingAccount(25.00));
        }
        */


//        db.add(new User(id, firstName, lastName, age, email, username, password, accountType));

        System.out.println("Account created");
        return db.get(id);
    }

    // login
    @Override
    public User login(){
        return input.loginValidation(db);
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
