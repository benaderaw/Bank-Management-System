public class User {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String username;
    private String password;
    private double balance;

    public User(String id, String firstName, String lastName, int age, String email, String username, String password){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = password;
        this.balance = 0;
    }
}
