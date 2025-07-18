public class DisplayAccountDashboard {

    public void displayAccountDashboard(User currentUser){
        for(BankAccount account: currentUser.getAccounts()){
            System.out.print(capitalize(account.getAccountType()) + " Account");
            if(account.getBalance() >= 0){
                System.out.printf("%-15s $%.2f \n", "", account.getBalance());
            }else{
                System.out.printf("-$%15.2f\n", account.getBalance());
            }
            System.out.print(hideAccountAndRouting(account.getAccountNumber()));
            System.out.printf("%-24s %s\n\n", "", "available");
        }
    }

    // HELPER METHODS
    private  String hideAccountAndRouting(long num){
        String convertToString = "" + num;
        String shortened =  convertToString.substring(convertToString.length() - 6);

        String hiddenNum = "";
        for(int i = 0; i < shortened.length(); i++){
            if(i < 2){
                hiddenNum += "*";
            }else{
                hiddenNum += shortened.charAt(i);
            }
        }

        return hiddenNum;
    }

    // capitalize first letter
    private String capitalize(String word){
        word = word.substring(0, 1).toUpperCase() + word.substring(1);
        return word;
    }
}
