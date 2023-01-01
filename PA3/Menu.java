
/**
 * The Menu class is what will be used to hold the various types of menu prints used for UserConsole.
 *
 * @author Derek Aguirre
 * @version 1.0
 * @since 10/10/2020
 * @see UserConsole
 */

public class Menu{
    /**
     * <p>This method will print the welcome when a user arrives to the main program page.
     * <br><br>It will ask the user what their business is.
     * </p>
     * 
     */
    public static void printBankIntro(){
        System.out.println("Welcome to the Bank of the Miners!\nWhich type of user are you?");
    }
    /**
     * <p>This method will print the choice selection when a user arrives to the main program page.
     * <br><br>It will ask the user to select from 1-3 to have the program handle the features of their choice.
     * </p>
     * 
     */
    public static void printUserChoice(){
        System.out.println("(1) Customer \n(2) Bank Manager \n(3) Transaction Reader \n(0) Exit");
    }

    /** 
     * This method will print a welcome when a customer logs in.
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * 
     */
    public static void printWelcome(String firstName, String lastName){
        System.out.println("\nWelcome back " + firstName + " " + lastName+"!");
    }
    
    /**
     * <p>This method will print the main directory of the bank for a customer.
     * <br><br>It will ask the customer to select 1-5 to have the program perform a specified function.
     * </p>
     * 
     */
    public static void printMainDirectory(){
        System.out.println("Select the desired action:");
        System.out.println("\n (1) \t Inquire Balance \n (2) \t Deposit Cash \n (3) \t Withdraw Cash \n (4) \t Transfer Money \n (5) \t Pay An Individual \n (0) \t Logout \n");
    }

    /**
     * This method will print the main directory of the bank for a manager. 
     */
    public static void printManagerMainDirectory(){
        System.out.println("Select the desired action:");
        System.out.println("\n (1) \t Inquire Accounts \n (2) \t Create New User \n (3) \t Generate Statement \n (0) \t Logout\n");
    }

    /**
     * <p>This method will print the menu of all accounts.
     * <br><br>Includes the choice to choose all accounts.
     * </p>
     */
    public static void printAccountSelectionAll(){
        System.out.println(" (1) \t All Accounts \n (2) \t Checking Account \n (3) \t Savings Account \n (4) \t Credit Account \n (0) \t Go back \n");
    }

    /**
     * <p>This method will print the menu of accounts.
     * <br><br>Excluding the choice to choose all accounts. 
     * </p>
     */
    public static void printAccountSelectionNoAll(){
        System.out.println(" (1) \t Checking Account \n (2) \t Savings Account \n (3) \t Credit Account \n (0) \t Go back");
    }

    /**
     * <p>This method will print the menu of accounts.
     * <br><br>Excludes the choice to choose all accounts and the credit account. 
     * </p>
     */
    public static void printAccountSelectionNoAllNoCredit(){
        System.out.println(" (1) \t Checking Account \n (2) \t Savings Account \n (0) \t Go back");
    }

    /**
     * <p>This method will print the menu of accounts.
     * <br><br>Excludes the choice to choose all accounts and the checking account.
     * </p>
     */
    public static void printAccountSelectionNoAllNoChecking(){
        System.out.println(" (1) \t Savings Account \n (2) \t Credit Account \n (0) \t Go back");
    }

    /**
     * <p>This method will print the menu of accounts.
     * <br><br>Excludes the choice to choose all accounts and the savings account. 
     * </p>
     */
    public static void printAccountSelectionNoAllNoSavings(){
        System.out.println(" (1) \t Checking Account \n (2) \t Credit Account \n (0) \t Go back");
    }

    /**
     * <p>This method will print the menu of ways to search for a customer.
     * <br><br>Choices printed include:
     * <br><br>Full Name
     * <br><br>Account Type &amp; Account Number
     * </p>
     */
    public static void printManagerInquirySelection(){
        System.out.println(" (1) \t Full Name \n (2) \t Account Type & Account Number \n (3) \t List All Accounts \n (0) \t Go Back \n");
    }
    public static void printManagerUserCreationSelection(){
        System.out.println(" (1) \t Checking Account \n (2) \t Credit Account \n (3) \t Confirm/Create \n (0) \t Cancel \n");
    }

}