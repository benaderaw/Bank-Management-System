import java.util.ArrayList;
import java.util.UUID;

public class DB {
    private ArrayList<User> db;
    public ArrayList<BankAccount> sam;
    public ArrayList<BankAccount> william;
    public ArrayList<BankAccount> ben;

    // CONSTRUCTOR
    public DB(){
        this.db = new ArrayList<>();

        this.sam = new ArrayList<>();
        this.ben = new ArrayList<>();
        this.william = new ArrayList<>();

        this.sam.add(new CheckingAccount());
        this.sam.add(new SavingAccount());
        this.ben.add(new CheckingAccount());
        this.william.add(new SavingAccount());

        this.db.add(new User(UUID.randomUUID().toString(), "sam", "dean", 24, "sam@gmail.com", "sam1234", "momo002.", false, sam));
        this.db.add(new User(UUID.randomUUID().toString(), "ben", "aderaw", 33, "ben001@gmail.com", "dogwood", "falcon005.", false, william));
        this.db.add(new User(UUID.randomUUID().toString(), "william", "McCormic", 65, "william@gmail.com", "willisgod", "nowayjose.", false, ben));

        System.out.print("\n");
        System.out.println("TEST USERS");
        for(User user: db){
            System.out.println(user);
        }
    }



    // getter
    public ArrayList<User> getDb(){
        return db;
    }
}
