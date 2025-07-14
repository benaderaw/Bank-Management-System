import java.util.Scanner;

public class BankApp {
    public static void main(String[] args){
        System.out.println("Bank Management System");


        Scanner scanner = new Scanner(System.in);













        System.out.print("Create Account | Login: ");
        String action = scanner.nextLine();

        AccountManager ac = new AccountManager();

        if(action.equals("c")){
            ac.createAccount();
        }else if (action.equals("l")){
            ac.login();
        }


    }
}
