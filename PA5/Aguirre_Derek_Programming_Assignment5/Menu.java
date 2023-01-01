import java.util.ArrayList;


/**
 * 
 * Taken from Derek Aguirre
 * 
 * The Menu class is what will be used to hold the various types of menu prints
 * used for UserConsole.
 *
 * @author Derek Aguirre
 * @version 2.0
 * @since 11/11/2020
 * @see UserConsole
 */

public class Menu implements Printable {

    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>
     * This method will print the welcome when a user arrives to the main program
     * page. <br>
     * <br>
     * It will ask the user what their business is.
     * </p>
     * 
     */
    public void printBankIntro() {
        System.out.println("Welcome to the Bank of the Miners!\nWhich type of user are you?");
    }

    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>
     * This method will print the choice selection when a user arrives to the main
     * program page. <br>
     * <br>
     * It will ask the user to select from 1-3 to have the program handle the
     * features of their choice.
     * </p>
     * 
     */
    public void printUserChoice() {
        System.out.println("(1) Customer \n(2) Bank Manager \n(3) Transaction Reader \n(0) Exit");
    }

    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>
     * This method will print the main directory of the bank for a customer. <br>
     * <br>
     * It will ask the customer to select 1-5 to have the program perform a
     * specified function.
     * </p>
     * 
     */
    public void printMainDirectory() {
        System.out.println("Select the desired action:");
        System.out.println("\n (1) \t Inquire Balance \n (2) \t Deposit Cash \n (3) \t Withdraw Cash \n (4) \t Transfer Money \n (5) \t Pay An Individual \n (0) \t Logout \n");
    }

    /**
     * 
     * Taken from Derek Aguirre
     * 
     * This method will print the main directory of the bank for a manager.
     */
    public void printManagerMainDirectory() {
        System.out.println("Select the desired action:");
        System.out.println(
                "\n (1) \t Inquire Accounts \n (2) \t Create New User \n (3) \t Generate Statement \n (0) \t Logout\n");
    }

    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>
     * This method will print the menu of all accounts. <br>
     * <br>
     * Includes the choice to choose all accounts.
     * </p>
     */
    public void printAccountSelectionAll() {
        System.out.println(
                " (1) \t All Accounts \n (2) \t Checking Account \n (3) \t Savings Account \n (4) \t Credit Account \n (0) \t Go back \n");
    }

    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>
     * This method will print the menu of accounts. <br>
     * <br>
     * Excluding the choice to choose all accounts.
     * </p>
     */
    public void printAccountSelectionNoAll() {
        System.out.println(
                " (1) \t Checking Account \n (2) \t Savings Account \n (3) \t Credit Account \n (0) \t Go back");
    }

    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>
     * This method will print the menu of accounts. <br>
     * <br>
     * Excludes the choice to choose all accounts and the credit account.
     * </p>
     */
    public void printAccountSelectionNoAllNoCredit() {
        System.out.println(" (1) \t Checking Account \n (2) \t Savings Account \n (0) \t Go back");
    }

    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>
     * This method will print the menu of accounts. <br>
     * <br>
     * Excludes the choice to choose all accounts and the checking account.
     * </p>
     */
    public void printAccountSelectionNoAllNoChecking() {
        System.out.println(" (1) \t Savings Account \n (2) \t Credit Account \n (0) \t Go back");
    }

    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>
     * This method will print the menu of accounts. <br>
     * <br>
     * Excludes the choice to choose all accounts and the savings account.
     * </p>
     */
    public void printAccountSelectionNoAllNoSavings() {
        System.out.println(" (1) \t Checking Account \n (2) \t Credit Account \n (0) \t Go back");
    }

    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>
     * This method will print the menu of ways to search for a customer. <br>
     * <br>
     * Choices printed include: <br>
     * <br>
     * Full Name <br>
     * <br>
     * Account Type &amp; Account Number
     * </p>
     */
    public void printManagerInquirySelection() {
        System.out.println(" (1) \t Full Name \n (2) \t Account Type & Account Number \n (3) \t List All Accounts \n (0) \t Go Back \n");
    }

    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>
     * This method will print the menu of actions after a savings account is created. <br>
     * <br>
     * Choices printed include: <br>
     * <br>
     * Checking Account <br>
     * <br>
     * Credit Account <br>
     * <br>
     * Confirm/Create <br>
     * <br>
     * Cancel <br>
     * </p>
     */
    public void printManagerUserCreationSelection() {
        System.out.println(" (1) \t Checking Account \n (2) \t Credit Account \n (3) \t Confirm/Create \n (0) \t Cancel \n");
    }

    /**
     * 
     * Taken from Derek Aguirre
     * @param firstName The first name of the user
     * @param lastName The last name of the user
     * <p>
     * This method will print a welcome message and address the customer by name. <br>
     * </p>
     */
    public void reportLogin(String firstName, String lastName) {
        System.out.println("Welcome " + firstName + " " + lastName+ "!");
    }

    /**
     * 
     * Taken from Derek Aguirre
     * @param firstName The first name of the user
     * @param lastName The last name of the user
     * <p>
     * This method will print a logout message and address the customer by name. <br>
     * </p>
     */
    public void reportLogout(String firstName, String lastName) {
        System.out.println("Successfully logged out. Have a good day " + firstName + " " + lastName+ "!");
    }

     /**
     * 
     * Taken from Derek Aguirre
     * @param custList The first name of the user
     * <p>
     * This method will perform the printing of all information for all accounts. <br>
     * </p>
     */
    public void print(ArrayList<Customer> custList) {
            for (Customer iter : custList) {
                
                if(iter.getCheckingAccount().getAccountNumber() == -1 && iter.getCreditAccount().getAccountNumber()==-1){ //only savings exists
                    System.out.println("User found successfully.");
                    String name = (iter.getFirstName()+ " " + iter.getLastName());
                    String info =("\nDate of Birth: \t"+ iter.getDateOfBirth() + 
                                  "\nIdentification Number: \t" + iter.getIdNumber()+ 
                                  "\nAddress: \t" + iter.getAddress() + 
                                  "\nPhone Number: \t" + iter.getPhoneNumber());
                    int accInfoSa =(iter.getSavingsAccount().getAccountNumber());
                    double accBalSa = (iter.getSavingsAccount().getStartingBalance());

                    System.out.println("Name: \t" + name + info +
                                       "\nSavings account Number & Balance: \t" + accInfoSa+ ", "+ accBalSa);
                   }
                   if(iter.getCheckingAccount().getAccountNumber() == -1 && iter.getCreditAccount().getAccountNumber()!=-1){//checking does not exist
                    System.out.println("User found successfully.");
                    String name = (iter.getFirstName()+ " " + iter.getLastName());
                    String info =("\nDate of Birth: \t"+ iter.getDateOfBirth() + 
                                  "\nIdentification Number: \t" + iter.getIdNumber()+ 
                                  "\nAddress: \t" + iter.getAddress() + 
                                  "\nPhone Number: \t" + iter.getPhoneNumber());

                    int accInfoSa =(iter.getSavingsAccount().getAccountNumber());
                    int accInfoCr =(iter.getCreditAccount().getAccountNumber());
                    double accBalSa = (iter.getSavingsAccount().getStartingBalance());
                    double accBalCr = (iter.getCreditAccount().getStartingBalance());
                    
                    System.out.println("Name: \t" + name + info +
                                       "\nSavings account Number & Balance: \t" + accInfoSa+ ", "+ accBalSa+
                                       "\nCredit account Number & Balance: \t" + accInfoCr+ ", "+ accBalCr);
                   }
                   if(iter.getCreditAccount().getAccountNumber() == -1 && iter.getCheckingAccount().getAccountNumber()!=-1){ //credit does not exist
                    System.out.println("User found successfully.");
                    String name = (iter.getFirstName()+ " " + iter.getLastName());
                    String info =("\nDate of Birth: \t"+ iter.getDateOfBirth() + 
                                  "\nIdentification Number: \t" + iter.getIdNumber()+ 
                                  "\nAddress: \t" + iter.getAddress() + 
                                  "\nPhone Number: \t" + iter.getPhoneNumber());
                    int accInfoSa =(iter.getSavingsAccount().getAccountNumber());
                    int accInfoCr =(iter.getCreditAccount().getAccountNumber());
                    double accBalSa = (iter.getSavingsAccount().getStartingBalance());
                    double accBalCr = (iter.getCreditAccount().getStartingBalance());
                    
                    System.out.println("Name: \t" + name + info +
                                       "\nSavings account Number & Balance: \t" + accInfoSa+ ", "+ accBalSa+
                                       "\nCredit account Number & Balance: \t" + accInfoCr+ ", "+ accBalCr);
                   }
                    if(iter.getCreditAccount().getAccountNumber() != -1 && iter.getCheckingAccount().getAccountNumber()!=-1){//all accounts exist
                    System.out.println("User found successfully.");
                    String name = (iter.getFirstName()+ " " + iter.getLastName());
                    String info =("\nDate of Birth: \t"+ iter.getDateOfBirth() + 
                                  "\nIdentification Number: \t" + iter.getIdNumber()+ 
                                  "\nAddress: \t" + iter.getAddress() + 
                                  "\nPhone Number: \t" + iter.getPhoneNumber());

                    int accInfoCh =(iter.getCheckingAccount().getAccountNumber());
                    int accInfoSa =(iter.getSavingsAccount().getAccountNumber());
                    int accInfoCr =(iter.getCreditAccount().getAccountNumber());
                    double accBalCh = (iter.getCheckingAccount().getStartingBalance());
                    double accBalSa = (iter.getSavingsAccount().getStartingBalance());
                    double accBalCr = (iter.getCreditAccount().getStartingBalance());
                    
                    System.out.println("Name: \t" + name + info +
                                       "\nChecking account Number & Balance: \t" + accInfoCh+ ", "+ accBalCh +
                                       "\nSavings account Number & Balance: \t" + accInfoSa+ ", "+ accBalSa+
                                       "\nCredit account Number & Balance: \t" + accInfoCr+ ", "+ accBalCr+"\n");
                   }
                    
                    
                }
            }
    
    }

    