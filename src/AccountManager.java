import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class AccountManager implements UserOperations {

    public boolean accountCreated;
    public boolean loggedIn;
    public boolean loggedOff;
    public ArrayList<User> db;
    public ArrayList<BankAccount> xx;
    public ArrayList<BankAccount> zz;
    public ArrayList<BankAccount> yy;

    InputManager input = new InputManager();
    Random random = new Random();


    public AccountManager() {
        this.accountCreated = false;
        this.db = new ArrayList<>();
        this.xx = new ArrayList<>();
        this.zz = new ArrayList<>();
        this.yy = new ArrayList<>();

        System.out.print("\n\n");
        xx.add(new CheckingAccount());
        xx.add(new SavingAccount());
        yy.add(new SavingAccount());
        db.add(new User(UUID.randomUUID().toString(), "sam", "dean", 24, "sam@gmail.com", "sam1234", "momo002.", false, xx));
        db.add(new User(UUID.randomUUID().toString(), "ben", "aderaw", 33, "ben001@gmail.com", "dogwood", "falcon005.", false, zz));
        db.add(new User(UUID.randomUUID().toString(), "william", "McCormic", 65, "william@gmail.com", "willisgod", "nowayjose.", false, yy));

        for(User user: db){
            System.out.println(user);
        }

    }

    @Override
    public User createAccount(){
        // create ID
        String id = UUID.randomUUID().toString();

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

        // active
        boolean active = true;

        String newAccountType = input.promptNewAccountType();

        ArrayList<BankAccount> accountType = new ArrayList<>();
        if(newAccountType.equals("checking")){
            accountType.add(new CheckingAccount());
        }

        if(newAccountType.equals("savings") || newAccountType.equals("saving")){
            accountType.add(new SavingAccount());
        }

        if(newAccountType.equals("checking and savings") || newAccountType.equals("checking and saving")){
            accountType.add(new CheckingAccount());
            accountType.add(new SavingAccount());
        }

        // add new user and account to database
        db.add(new User(id, firstName, lastName, age, email, username, password, active, accountType));

        System.out.println("Account created");
        for(User user: db){
            System.out.println(user);
        }

        return db.getLast();
    }

    // login
    @Override
    public User login(){
        return input.loginValidation(db);
    }

    // logout
    @Override
    public void logout(User currentUser){
        System.out.println("Logging out...");
        currentUser.setActive(false);
    }

    @Override
    public void closeAccount(User currentUser){
        System.out.println("Processing account closer...");
        System.out.println("Account closed");
        currentUser.setActive(false);
        db.remove(currentUser);
    }
}
