public class DisplayManager {
    public void welcomeDisplay(String input, User currentUser){
        String welcome = input.equals("c") ? "Welcome, " : "Welcome Back, ";
        System.out.println("👋" + welcome + capitalize(currentUser.getFirstName()) + "!\n");
        System.out.println("Bank Accounts");
        System.out.println("==============");
    }

    // capitalize first letter
    private String capitalize(String word){
        word = word.substring(0, 1).toUpperCase() + word.substring(1);
        return word;
    }
}
