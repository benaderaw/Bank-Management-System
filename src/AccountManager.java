import java.util.ArrayList;

public abstract class AccountManager implements BankOperations{

    public boolean accountCreated;
    public boolean loggedIn;
    public boolean loggedOff;
    public ArrayList<Object> db;

    public AccountManager(){
        this.accountCreated = false;
        this.db = new ArrayList<>();
    }

    @Override
    public boolean createAccount(String email, String username, String password){


        // check and validate if email exists
        if(!db.contains(email) && !db.contains(username) && password.length() > 8){
            accountCreated = true;
        }

        db.add(new User(id, firstName, lastName, age, email, username, password));

        return accountCreated;
    }

    @Override
    public boolean login(String username, String password){
        if(db.contains(username) && db.contains(password)){
            loggedOff = true;
        }

        return loggedOff;
    }

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
