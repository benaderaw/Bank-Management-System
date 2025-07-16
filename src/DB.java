import java.util.ArrayList;

public class DB {
    private ArrayList<User> db;

    public DB(){
        this.db = new ArrayList<>();
    }


    // getter
    public ArrayList<User> getDb(){
        return db;
    }
}
