import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String username;
    private String password;
    private boolean active;
    private ArrayList<BankAccount> accountType;


    public User(String id, String firstName, String lastName, int age, String email, String username, String password, boolean active,
                ArrayList<BankAccount> accountType){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = password;
        this.active = false;
        this.accountType = accountType;
    }

    // getter
    public String getId(){
        return id;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public boolean isActive(){
        return active;
    }
    public ArrayList<BankAccount> getAccountType(){
        return accountType;
    }

    // setter
    public void setActive(boolean active){
        this.active = active;
    }


    @Override
    public String toString() {
        return "id: " + id + ", firstName: " + firstName + ", lastName: " + lastName + ", age: " + age + ", email: "
                + email + ", username: " + username + ", password: " + password + ", active: " + active + ", accounts: " + accountType;
    }
}
