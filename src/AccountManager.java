import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class AccountManager implements UserOperations {

    DB db = new DB();
    private Scanner scanner;
    InputManager inputManager;
    public ArrayList<BankAccount> xx;
    public ArrayList<BankAccount> zz;
    public ArrayList<BankAccount> yy;



    // CONSTRUCTOR
    public AccountManager(Scanner scanner) {
        System.out.print("\n\n");
        this.xx = new ArrayList<>();
        this.yy = new ArrayList<>();
        this.zz = new ArrayList<>();
        this.xx.add(new CheckingAccount());
        this.xx.add(new SavingAccount());
        this.yy.add(new SavingAccount());
        this.zz.add(new SavingAccount());
        this.db.getDb().add(new User(UUID.randomUUID().toString(), "sam", "dean", 24, "sam@gmail.com", "sam1234", "momo002.", false, xx));
        this.db.getDb().add(new User(UUID.randomUUID().toString(), "ben", "aderaw", 33, "ben001@gmail.com", "dogwood", "falcon005.", false, zz));
        this.db.getDb().add(new User(UUID.randomUUID().toString(), "william", "McCormic", 65, "william@gmail.com", "willisgod", "nowayjose.", false, yy));
        for(User user: db.getDb()){
            System.out.println(user);
        }

        this.scanner = scanner;
        this.inputManager= new InputManager(scanner);

    }

    @Override
    public User createAccount(){
        // create ID
        String id = UUID.randomUUID().toString();

        // first name
        String firstName = inputManager.promptFirstname();

        // last name
        String lastName = inputManager.promptLastname();

        // age
        int age = inputManager.promptAge();

        // email
        String email = inputManager.checkUniqueEmail(db.getDb());

        // username
        String username = inputManager.checkUniqueUsername(db.getDb());

        // password
        String password = inputManager.passwordValidation();

        // active
        boolean active = true;

        String newAccountType = inputManager.promptNewAccountType();

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
        db.getDb().add(new User(id, firstName, lastName, age, email, username, password, active, accountType));

        return db.getDb().getLast();
    }

    // login
    @Override
    public User login(){
        return inputManager.loginValidation(db.getDb());
    }

    // logout
    @Override
    public void logout(User currentUser){
        currentUser.setActive(false);
    }

    @Override
    public void closeAccount(User currentUser){
        currentUser.setActive(false);
        db.getDb().remove(currentUser);
    }
}
