import java.util.ArrayList;
import java.util.Scanner;

public class InputManager {
    private String stringInput = "";
    private final Scanner scanner;

    // CONSTRUCTOR
    public InputManager(Scanner scanner){
        this.scanner = scanner;
    }

    // login or create account
    public String promptCreateOrLogin(){
        String input;

        while (true){
            System.out.print("🔷To create an account type 'c', or type 'l' to login: ");
            input = scanner.nextLine().toLowerCase().trim();

            switch (input){
                case "l":
                case "c":
                    return input;
                default:
                    System.out.println("⚠️Login or Create an account to continue\n");
            }
        }
    }

    // firstname prompt
    public String promptFirstname() {
        while (true) {
            System.out.print("🔷First name: ");
            stringInput = scanner.nextLine();

            if (stringInput.isEmpty()) {
                System.out.print("⚠️Please provide a first name\n");
                continue;
            }
            break;
        }
        return stringInput;
    }

    // lastname prompt
    public String promptLastname() {
        while (true) {
            System.out.print("🔷Last name: ");
            stringInput = scanner.nextLine();

            if (stringInput.isEmpty()) {
                System.out.print("⚠️Please provide a last name\n");
                continue;
            }
            break;
        }
        return stringInput;
    }

    // age prompt
    public int promptAge() {
        int intInput;
        while (true) {
            System.out.print("🔷Age: ");

            if (scanner.hasNextInt()) {
                intInput = scanner.nextInt();
                if (intInput < 18) {
                    System.out.println("⚠️You must be 18 years or older to create an account\n");
                    continue;
                }
                scanner.nextLine();
                break;
            } else {
                scanner.nextLine();
                System.out.println("⚠️Age must be a a whole number\n");
            }
        }

        return intInput;
    }

    // email validation
    public boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public String emailValidation() {
        while (true) {
            System.out.print("🔷Email: ");
            stringInput = scanner.nextLine().toLowerCase().trim();

            if (isValidEmail(stringInput)) {
                break;
            } else {
                System.out.println("⚠️Invalid email, try again\n");
            }
        }

        return stringInput;
    }

    public String checkUniqueEmail(ArrayList<User> db) {
        String email;

        OuterLoop:
        while (true) {
            email = emailValidation();

            for (User user : db) {
                if (email.equals(user.getEmail())) {
                    System.out.println("⚠️Email already exists, try again\n");
                    continue OuterLoop;
                }
            }
            break;
        }

        return email;
    }

    // username
    public String usernameValidation() {
        while (true) {
            System.out.print("🔷Username: ");
            stringInput = scanner.nextLine().trim();

            if (stringInput.isEmpty()) {
                System.out.print("⚠️Please provide a username\n");
                continue;
            } else if (stringInput.length() < 4) {
                System.out.print("⚠️Username needs to be at lease 4 characters long\n");
                continue;
            }
            break;
        }
        return stringInput;
    }

    public String checkUniqueUsername(ArrayList<User> db) {
        String username;

        OuterLoop:
        while (true) {
            username = usernameValidation();// bob88

            for (User user : db) {
                if (username.equals(user.getUsername())) {
                    System.out.println("⚠️Username already exists, try a different username\n");
                    continue OuterLoop;
                }
            }
            break;
        }

        return username;
    }

    // password
    public String passwordValidation() {
        while (true) {
            System.out.print("🔷Password: ");
            stringInput = scanner.nextLine().trim();

            if (stringInput.isEmpty()) {
                System.out.println("⚠️Please provide a password\n");
            } else if (stringInput.length() < 8) {
                System.out.println("⚠️Password needs to be 8 characters or above\n");
            } else {
                break;
            }
        }

        return stringInput;
    }

    // account type
    public String promptNewAccountType() {
        while (true) {
            System.out.println("Which account type would ypu like to open");
            System.out.print("🔷Checking | Saving | Checking and Saving: ");
            stringInput = scanner.nextLine().toLowerCase().trim();

            if (stringInput.isEmpty()) {
                System.out.println("⚠️Please choose account type\n");
            } else if (stringInput.equals("checking") || stringInput.equals("savings") || stringInput.equals("saving") || stringInput.equals("checking and savings") || stringInput.equals("checking and saving")) {
                break;
            } else {
                System.out.println("⚠️Please choose account type\n");
            }
        }

        return stringInput;
    }

    // login
    public User loginValidation(ArrayList<User> db) {
        while (true) {
            System.out.println("\n🔐Log In");
            System.out.print("👤Username: ");
            String usernameInput = scanner.nextLine();

            System.out.print("🔑Password: ");
            String passwordInput = scanner.nextLine();

            // check
            Integer index = null;
            for (User user : db) {
                if (user.getUsername().equals(usernameInput)) {
                    index = db.indexOf(user);
                    break;
                }
            }

            // if username isn't found start login process again
            if(index == null){
                System.out.println("⚠️invalid credentials, try again");
                continue;
            }

            String password = db.get(index).getPassword();

            if (password.equals(passwordInput)) {
                System.out.println("🔄Logging in...");
                System.out.println("✅Logged in\n");
                db.get(index).setActive(true);
                return db.get(index);
            }

            System.out.println("⚠️invalid credentials, try again");
        }
    }

    // deposit
    public double promptDepositAmount(){
        double amount;

        while (true){
            System.out.print("🔷Deposit amount: ");

            if(scanner.hasNextDouble()){
                amount = scanner.nextDouble();
                scanner.nextLine();
                break;
            }else{
                scanner.nextLine();
                System.out.println("⚠️Please type a valid number\n");
            }
        }

        return amount;
    }

    // withdraw
    public double promptWithdrawAmount(){
        double amount;
        while (true){
            System.out.print("🔷Withdrawal amount: ");

            if(scanner.hasNextDouble()){
                amount = scanner.nextDouble();
                scanner.nextLine();
                break;
            }else{
                scanner.nextLine();
                System.out.println("⚠️Please type a valid number\n");
            }
        }

        return amount;
    }

    // transfer from
    public String promptAccountFrom(){
        String transferFrom;
        while (true){
            System.out.print("From Account: ");
            transferFrom = scanner.nextLine().toLowerCase().trim();

            switch (transferFrom) {
                case "checking":
                case "savings":
                    break;
                default:
                    System.out.println("⚠️Please choose which account to transfer from");
                    System.out.println("💡️be sure to type the last 's' in 'savings'");
                    continue;
            }
            break;
        }

        return transferFrom;
    }

    // transfer to
    public String promptTransferTo(String transferFrom){
        String transferTo;
        while (true){
            System.out.print("To Account: ");
            transferTo = scanner.nextLine().toLowerCase().trim();

            if (transferTo.equals(transferFrom)) {
                System.out.println("⚠️Please choose another account to transfer-to\n");
                continue;
            }

            switch (transferTo) {
                case "checking":
                case "savings":
                    break;
                default:
                    System.out.println("⚠️Please choose which account to transfer-to");
                    System.out.println("💡️be sure to type the last 's' in 'savings'\n");
                    continue;
            }
            break;
        }

        return transferTo;
    }

    // transfer amount
    public double promptTransferAmount(BankAccount transferFromAccount){
        double transferAmount;

        while (true) {
            System.out.printf("Amount (available balance $%.2f) : ", transferFromAccount.getBalance());

            if (scanner.hasNextDouble()) {
                transferAmount = scanner.nextDouble();
                scanner.nextLine();
                break;
            } else {
                scanner.nextLine();
                System.out.println("⚠️Please enter an amount to transfer\n");
            }
        }

        return transferAmount;
    }

    // close account
    public String promptCloseAccount(double balance){
        String input;
        if(balance > 0){
            System.out.println("⚠️Please make sure your have no balance before closing account\n");
            return "ABORT";
        }else {
            System.out.println("🔷We are sad to see you go, but it was out pleasure to serve you!");
            while (true){
                System.out.print("🔷Please type 'CLOSE ACCOUNT' to confirm account closure: ");
                input = scanner.nextLine().trim();

                if(input.equals("CLOSE ACCOUNT")){
                    break;
                }else{
                    System.out.println("⚠️Type 'CLOSE ACCOUNT' to confirm");
                    System.out.println("💡'CLOSE ACCOUNT' must be types exactly as it appears\n");
                }
            }
        }

        return input;
    }

    // select account
    public String promptSelectAccount(User currentUser){
        if (currentUser.getAccounts().size() <= 1) {
            // No need to prompt, only one account available
            return "";
        }

        String input;

        while (true) {
            for (BankAccount account : currentUser.getAccounts()) {
                String accountType = account.getAccountType().substring(0, 1).toUpperCase() + account.getAccountType().substring(1);
                System.out.println(accountType);
            }
            System.out.print("🔷Select account: ");
            input = scanner.nextLine().toLowerCase().trim();

            if(input.equals("checking") || input.equals("savings")){
                break;
            }else {
                System.out.println("⚠️Please select an account");
                System.out.println("💡make sure to include the last 's' in 'savings'\n");
            }
        }

        return input;
    }

    public String promptRoute(){
        while (true) {
            System.out.print("🔷What would you like to do: ");
            String input = scanner.nextLine().toLowerCase().trim();

            switch (input){
                case "deposit":
                case "withdraw":
                case "transaction":
                case "transactions":
                case "view transactions":
                case "transfer":
                case "view details":
                case "logout":
                case "log out":
                case "close account":
                    break;
                default:
                    System.out.println("⚠️Please choose an action fro the menu");
                    System.out.println("💡input does not need to be capitalized\n");
                    continue;
            }

            return input;
        }
    }

}
