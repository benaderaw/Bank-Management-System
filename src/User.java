public class User {
    public int id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String username;
    private String password;
    private double balance;

    public User(int id, String firstName, String lastName, int age, String email, String username, String password){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = password;
        this.balance = 0;
    }

    public String getUsername(){
        return username;
    }

    @Override
    public String toString() {
        return "id: " + id + ", firstName: " + firstName + ", lastName: " + lastName + ", age: " + age + ", email: "
                + email + ", username: " + username + ", password: " + password + ", balance: " + balance;
    }
}
