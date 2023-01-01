import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.*;

/**
 * 
 *  Taken from Derek Aguirre 
 * 
 * The UserConsole class will be used hold all the user choice methods.
 *
 * @author Derek Aguirre
 * @version 3.0
 * @since 11/11/2020
 * @see RunBank
 */
public class UserConsole{
    Menu menuObj = new Menu();
    Logger singletonLogger = Logger.getInstance();

    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>This method handles the choice-making when a customer selects "Inquire Balance".
     * <br><br>It will prompt the customer to enter in their choice to view a specified account.
     * <br><br>Entering 1 will inquire balance for all accounts.
     * <br><br>Entering 2 will inquire balance for their checking account.
     * <br><br>Entering 3 will inquire balance for their savings account.
     * <br><br>Entering 4 will inquire balance for their credit account.
     * <br><br>Entering 0 will bring the customer back to the main directory.
     * </p>
     * 
     * 
     * @param customerObject Object of a customer populated with values parsed from the input file
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws OutOfRangeException If the user inputs a number outside the range of selections
     * @throws FileNotFoundException If file is not found
     * @throws IOException InputOutputException
     * @throws AccountExistenceException If the user does not have acccess to a specific account
     * 
     */
    public void inquireBalanceChoice(Customer customerObject) throws InputMismatchException, OutOfRangeException, FileNotFoundException, IOException, AccountExistenceException {
        boolean loopCondition = true;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Which account balance would you like to see?");
                menuObj.printAccountSelectionAll();
                int userChoice = sc.nextInt();
                if (userChoice > 4 || userChoice < 0)
                    throw new OutOfRangeException("Out of range");
                while (userChoice <= 4 || userChoice >= 0) {
                    switch (userChoice) {
                        case 1: //inquire all
                            if (customerObject.getCheckingAccount().getAccountNumber() == -1 && customerObject.getCreditAccount().getAccountNumber() == -1){ // only savings exists
                                System.out.println(customerObject.getFirstName() + " " + customerObject.getLastName() + "'s Savings Account (" + customerObject.getSavingsAccount().getAccountNumber() + ") Balance: " + customerObject.getSavingsAccount().getStartingBalance());
                            }
                            else if (customerObject.getCheckingAccount().getAccountNumber() == -1 && customerObject.getCreditAccount().getAccountNumber() != -1) { // checking acc does not exist
                                System.out.println(customerObject.getFirstName() + " " + customerObject.getLastName()+ "'s Credit Account (" + customerObject.getCreditAccount().getAccountNumber() + ") Balance: " + customerObject.getCreditAccount().getStartingBalance());
                                System.out.println(customerObject.getFirstName() + " " + customerObject.getLastName() + "'s Savings Account (" + customerObject.getSavingsAccount().getAccountNumber() + ") Balance: " + customerObject.getSavingsAccount().getStartingBalance());

                            } else if (customerObject.getCheckingAccount().getAccountNumber() != -1 && customerObject.getCreditAccount().getAccountNumber() == -1) { // credit acc does not exist
                                System.out.println(customerObject.getFirstName() + " " + customerObject.getLastName() + "'s Checking Account (" + customerObject.getCheckingAccount().getAccountNumber() + ") Balance: " + customerObject.getCheckingAccount().getStartingBalance());
                                System.out.println(customerObject.getFirstName() + " " + customerObject.getLastName() + "'s Savings Account (" + customerObject.getSavingsAccount().getAccountNumber() + ") Balance: " + customerObject.getSavingsAccount().getStartingBalance());

                            }
                            else if (customerObject.getCheckingAccount().getAccountNumber() != -1 && customerObject.getCreditAccount().getAccountNumber() != -1) {
                                System.out.println(customerObject.getFirstName() + " " + customerObject.getLastName() + "'s Checking Account (" + customerObject.getCheckingAccount().getAccountNumber() + ") Balance: " + customerObject.getCheckingAccount().getStartingBalance());
                                System.out.println(customerObject.getFirstName() + " " + customerObject.getLastName() + "'s Savings Account (" + customerObject.getSavingsAccount().getAccountNumber() + ") Balance: " + customerObject.getSavingsAccount().getStartingBalance());
                                System.out.println(customerObject.getFirstName() + " " + customerObject.getLastName() + "'s Credit Account (" + customerObject.getCreditAccount().getAccountNumber() + ") Balance: " + customerObject.getCreditAccount().getStartingBalance());

                                singletonLogger.inquireBalanceLog(customerObject, "All");
                            }
                            break;
                        case 2://inquire checking
                            if (customerObject.getCheckingAccount().getAccountNumber() == -1) // checking acc does not exist
                                throw new AccountExistenceException("You do not have this type of account.");
                            singletonLogger.inquireBalanceLog(customerObject, "Checking");
                            System.out.println(customerObject.getFirstName() + " " + customerObject.getLastName()+ "'s Checking Account (" + customerObject.getCheckingAccount().getAccountNumber()+ ") Balance: " + customerObject.getCheckingAccount().getStartingBalance());
                            break;
                        case 3://inquire savings
                            singletonLogger.inquireBalanceLog(customerObject, "Savings");
                            System.out.println(customerObject.getFirstName() + " " + customerObject.getLastName()+ "'s Savings Account (" + customerObject.getSavingsAccount().getAccountNumber()+ ") Balance: " + customerObject.getSavingsAccount().getStartingBalance());
                            break;
                        case 4://inquire credit
                            if (customerObject.getCreditAccount().getAccountNumber() == -1) // credit acc does not exist
                                throw new AccountExistenceException("You do not have this type of account.");
                            singletonLogger.inquireBalanceLog(customerObject, "Credit");
                            System.out.println(customerObject.getFirstName() + " " + customerObject.getLastName()+ "'s Credit Account (" + customerObject.getCreditAccount().getAccountNumber()+ ") Balance: " + customerObject.getCreditAccount().getStartingBalance());
                            break;
                        case 0:
                            System.out.println("Going back...\n");
                            loopCondition = false;
                            break;
                    }
                    if (loopCondition == true) {
                        System.out.println("Which account balance would you like to see?");
                        menuObj.printAccountSelectionAll();
                        userChoice = sc.nextInt(); // saves user input
                        if (userChoice > 4 || userChoice < 0)
                            throw new OutOfRangeException("Please select a value inside the range");
                    } else
                        break;
                }
            } catch (InputMismatchException e1) {
                System.out.println("Please enter either an integer or a decimal. Please try again.");
            } catch (OutOfRangeException e2) {
                System.out.println("Please choose between the range of selections. Please try again.");
            } catch (FileNotFoundException e3) {
                System.out.println("File not found.");
            } catch (IOException e4) {
                System.out.println("IOException");
            } catch (AccountExistenceException e5) {
                System.out.println("You do not have this type of account.");
            }
        } while (loopCondition == true);
    }

    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>This method handles the choice-making when a customer selects "Deposit Cash".
     * <br><br>It will prompt the customer to select which account to deposit a specified account into.
     * <br><br>Entering 1 will allow the customer to deposit into their checking account.
     * <br><br>Entering 2 will allow the customer to deposit into their savings account.
     * <br><br>Entering 3 will allow the customer to deposit into their credit account. (Will not allow the user to deposit enough funds to make the credit balance a positive value)
     * <br><br>Entering 0 will bring the customer back to the main directory.
     * </p>
     * 
     * @param customerObject Object of a customer populated with values parsed from the input file
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws FileNotFoundException If file is not found
     * @throws IOException InputOutputException
     * @throws AccountExistenceException If the user does not have acccess to a specific account
     * 
     */
    public void depositChoice(Customer customerObject) throws InputMismatchException, FileNotFoundException, IOException, AccountExistenceException{
        boolean loopCondition = true;
        DecimalFormat df = new DecimalFormat("#.##");
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Choose account to deposit to.");
                menuObj.printAccountSelectionNoAll();
                int userChoice = sc.nextInt();
                if (userChoice > 3 || userChoice < 0)
                    throw new OutOfRangeException("Out of range");
                while (userChoice <= 3 || userChoice >= 0) {
                    switch (userChoice) {

                        case 1:// deposit to checking
                            if (customerObject.getCheckingAccount().getAccountNumber() == -1) // if acc does not exist
                                throw new AccountExistenceException("Account does not exist");
                            sc = new Scanner(System.in);
                            System.out.println("You have chosen your checking account. How much would you like to deposit?");
                            double format = sc.nextDouble(); // asks user to input amount
                            double depositAmount = Double.parseDouble(df.format(format));
                            System.out.println("\nDepositing $" + depositAmount + " into Checking Account (" + customerObject.getCheckingAccount().getAccountNumber() + ")"); // confirmation on amount
                            double initialBalanceCh = customerObject.getCheckingAccount().getStartingBalance(); // Saves user's initial balance before the transaction
                            customerObject.getCheckingAccount().depositFunction(depositAmount);
                            if(depositAmount > 0&&customerObject.getCheckingAccount().getStartingBalance() + depositAmount >= 0){
                            System.out.println("\nUpdated Balance: $" + customerObject.getCheckingAccount().getStartingBalance() + "\nInitial Balance: $" + initialBalanceCh + "\n");
                            singletonLogger.depositLog(customerObject, "Checking", depositAmount, initialBalanceCh);
                            }
                            break;
                        case 2:// deposit to savings
                            sc = new Scanner(System.in);
                            System.out.println("You have chosen your savings account. How much would you like to deposit?");
                            format = sc.nextDouble(); // asks user to input amount
                            depositAmount = Double.parseDouble(df.format(format));                            
                            System.out.println("\nDepositing $" + depositAmount); // confirmation on amount
                            double initialBalanceSa = customerObject.getSavingsAccount().getStartingBalance(); // Saves user's initial balance before the transaction
                            customerObject.getSavingsAccount().depositFunction(depositAmount);
                            if(depositAmount > 0&&customerObject.getSavingsAccount().getStartingBalance() + depositAmount >= 0){
                                System.out.println("\nUpdated Balance: $" + customerObject.getSavingsAccount().getStartingBalance()+ "\nInitial Balance: $" + initialBalanceSa + "\n");
                                singletonLogger.depositLog(customerObject, "Savings", depositAmount, initialBalanceSa);
                            }
                            break;
                        case 3:// deposit to credit
                            if (customerObject.getCreditAccount().getAccountNumber() == -1) // if acc does not exist
                                throw new AccountExistenceException("Account does not exist");

                            sc = new Scanner(System.in);
                            System.out.println("You have chosen your credit account. How much would you like to deposit?");
                            format = sc.nextDouble(); // asks user to input amount
                            depositAmount = Double.parseDouble(df.format(format));
                            System.out.println("\nDepositing $" + depositAmount); // confirmation on amount
                            double initialBalanceCr = customerObject.getCreditAccount().getStartingBalance(); // Saves user's initial balance before the transaction
                            customerObject.getCreditAccount().depositFunction(depositAmount);
                            

                            if(depositAmount > 0 && customerObject.getCreditAccount().getStartingBalance() + depositAmount <= 0){
                                System.out.println("\nUpdated Balance: $" + customerObject.getCreditAccount().getStartingBalance()+ "\nInitial Balance: $" + initialBalanceCr + "\n");
                                singletonLogger.depositLog(customerObject, "Credit", depositAmount, initialBalanceCr);
                            }
                            break;
                        case 0:// go back
                            System.out.println("Going back...\n");
                            loopCondition = false;
                            break;
                    }
                    if (loopCondition == true) {// if x is 0 then it can loop again
                        System.out.println("Choose an account to deposit to.");
                        menuObj.printAccountSelectionNoAll();
                        userChoice = sc.nextInt(); // saves user input
                        if (userChoice > 3 || userChoice < 0)
                            throw new OutOfRangeException("Please select a value inside the range.");
                    } else
                        break;
                }
            } catch (InputMismatchException e2) {
                System.out.println("Please enter a number.");
            } catch (FileNotFoundException e4) {
                System.out.println("File not found.");
            } catch (IOException e5) {
                System.out.println("IOException");
            } catch (OutOfRangeException e6) {
                System.out.println("Please choose between the range of selections.");
            } catch (AccountExistenceException e7) {
                System.out.println("You do not have that type of account.");
            }
        } while (loopCondition == true);
    }
    
    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>This method handles the choice-making when a customer selects "Withdraw Cash".
     * <br><br>It will prompt the customer to select which account to withdraw a specified account from.
     * <br><br>Entering 1 will allow the customer to withdraw from their checking account.
     * <br><br>Entering 2 will allow the customer to withdraw from their savings account.
     * <br><br>Entering 0 will bring the customer back to the main directory.
     * </p>
     * @param customerObject Object of a customer populated with values parsed from the input file
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws OutOfRangeException If the user inputs a number outside the range of selections
     * @throws FileNotFoundException If file is not found
     * @throws IOException InputOutputException
     * @throws AccountExistenceException If the user does not have acccess to a specific account
     */
    public void withdrawChoice(Customer customerObject) throws InputMismatchException, OutOfRangeException, FileNotFoundException, IOException, AccountExistenceException{
        boolean loopCondition = true;
        DecimalFormat df = new DecimalFormat("#.##");
        do{
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println("Choose account to withdraw from.");
                menuObj.printAccountSelectionNoAllNoCredit();
                int userChoice = sc.nextInt();
                if (userChoice > 2 || userChoice < 0)
                    throw new OutOfRangeException("Out of range");
                while (userChoice <= 2 || userChoice >= 0) {
                    switch (userChoice) { 
                        case 1:
                            if (customerObject.getCheckingAccount().getAccountNumber() == -1) // if checking acc does not exist
                                throw new AccountExistenceException("Account does not exist");
                            sc = new Scanner(System.in);
                            System.out.println("You have chosen to withdraw from your checking account. Please enter amount.");
                            double format = sc.nextDouble(); // asks user to input amount
                            double withdrawAmount = Double.parseDouble(df.format(format));                            
                            System.out.println("\nWithdrawing $" + withdrawAmount + " from Checking Account ("+ customerObject.getCheckingAccount().getAccountNumber() + ")"); // confirmation on amount
                            double initialBalanceCh = customerObject.getCheckingAccount().getStartingBalance(); // Saves user's initial balance before the transaction
                            customerObject.getCheckingAccount().withdrawFunction(withdrawAmount);
                            if(withdrawAmount > 0&&customerObject.getCheckingAccount().getStartingBalance() - withdrawAmount >= 0){
                                System.out.println("\nUpdated Balance: $" + customerObject.getCheckingAccount().getStartingBalance()+ "\nInitial Balance: $" + initialBalanceCh + "\n");
                                singletonLogger.withdrawLog(customerObject, "Checking", withdrawAmount, initialBalanceCh);
                            }
                            break;
                        case 2:
                            sc = new Scanner(System.in);
                            System.out.println("You have chosen to withdraw from your Savings account. Please enter amount.");
                            format = sc.nextDouble(); // asks user to input amount
                            withdrawAmount = Double.parseDouble(df.format(format));                            
                            System.out.println("\nWithdrawing $" + withdrawAmount +" from Savings Account (" + customerObject.getSavingsAccount().getAccountNumber() + ")"); //confirmation on amount
                            double initialBalanceSa = customerObject.getSavingsAccount().getStartingBalance(); //Saves user's initial balance before the transaction
                            customerObject.getSavingsAccount().withdrawFunction(withdrawAmount);
                            if(withdrawAmount > 0&&customerObject.getSavingsAccount().getStartingBalance() - withdrawAmount >= 0){
                                System.out.println("\nUpdated Balance: $" + customerObject.getSavingsAccount().getStartingBalance() +"\nInitial Balance: $"+ initialBalanceSa + "\n");
                                singletonLogger.withdrawLog(customerObject, "Savings", withdrawAmount, initialBalanceSa);
                            }
                            break;
                        case 0://go back
                            System.out.println("Going back...\n");
                            loopCondition = false;
                            break;
                    }
                    if (loopCondition == true) {// if 0 then loop
                        System.out.println("Choose an account to withdraw from.");
                        menuObj.printAccountSelectionNoAllNoCredit();
                        userChoice = sc.nextInt(); // saves user input
                        if (userChoice > 2 || userChoice < 0)
                            throw new OutOfRangeException("Please enter a number inside the selection range");
                    } 
                    else
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number.");
            } catch (OutOfRangeException e) {
                System.out.println("Please choose between the range of selections.");
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            } catch (IOException e) {
                System.out.println("IOException");
            } catch (AccountExistenceException e){
                System.out.println("You do not have that type of account.");
            }
        } while (loopCondition == true);
    }

    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>This method handles the choice-making when a customer selects "Transfer Money".
     * <br><br>It will prompt the customer to select which account to transfer a specified account from.
     * <br><br>Entering 1 will allow the customer to transfer from their checking account.
     * <br><br>Entering 2 will allow the customer to transfer from their savings account.
     * <br><br>Entering 0 will bring the customer back to the main directory.
     * </p>
     * 
     * @param customerObject Object of a customer populated with values parsed from the input file
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws FileNotFoundException If file is not found
     * @throws IOException Input Output Exception
     * @throws OutOfRangeException If the choice that is made is out of the range of selections
     * @throws AccountExistenceException If the user does not have acccess to a specific account
     */
    public void transferChoice(Customer customerObject)throws InputMismatchException, FileNotFoundException, IOException, OutOfRangeException, AccountExistenceException{
    boolean loopCondition = true;
    do{
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Choose account to transfer funds from.");
            menuObj.printAccountSelectionNoAllNoCredit();
            int userChoice = sc.nextInt();
            if (userChoice > 2 || userChoice < 0)
                throw new OutOfRangeException("Out of range");
            while (userChoice <= 2 || userChoice >= 0) {
                switch (userChoice) { // choose to transfer from checking or savings
                    case 1:// transfer from checking
                        if (customerObject.getCheckingAccount().getAccountNumber() == -1) // if acc does not exist
                            throw new AccountExistenceException("Account does not exist");
                        System.out.println("You have chosen to transfer from your checking account.");
                        transferChoiceNest1(customerObject);
                        break;
                    case 2:// transfer from savings
                        System.out.println("You have chosen to transfer from your savings account.");
                        transferChoiceNest2(customerObject);
                        break;
                    case 0://go back
                        System.out.println("Going back...\n");
                        loopCondition = false;
                        break;
                }
                if (loopCondition == true) {//if x is 0 then it can loop again
                    System.out.println("Choose account to transfer funds from.");
                    menuObj.printAccountSelectionNoAllNoCredit();
                    userChoice = sc.nextInt(); // saves user input
                    if (userChoice > 2 || userChoice < 0)
                        throw new OutOfRangeException("Please enter a number inside selection range");
                }
                else
                break;
            }
        } catch (InputMismatchException e3){
            System.out.println("Please enter either an integer or a decimal. Please try again.");
        } catch (FileNotFoundException e4){
            System.out.println("File not found.");
        } catch (IOException e5){
            System.out.println("IOException");
        } catch (OutOfRangeException e6){
            System.out.println("Please choose between the range of selections. Please try again.");
        } catch (AccountExistenceException e7){
            System.out.println("You do not have that type of account.");
        }
    } while (loopCondition == true);
}

    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>This method handles the choice-making when a customer selects "Checking Account".
     * <br><br>It will prompt the customer to select which account to transfer a specified account into.
     * <br><br>Entering 1 will allow the customer to transfer from their checking account into their savings account.
     * <br><br>Entering 2 will allow the customer to transfer from their checking account into their credit account.
     * <br><br>Entering 0 will bring the customer back to the account selection prompt without credit listed.
     * </p>
     * @param customerObject Object of a customer populated with values parsed from the input file
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws OutOfRangeException If the user inputs a number outside the range of selections
     * @throws FileNotFoundException If file is not found
     * @throws IOException InputOutputException
     * @throws AccountExistenceException If the user does not have acccess to a specific account
     * 
     */
    public void transferChoiceNest1(Customer customerObject) throws InputMismatchException, OutOfRangeException, FileNotFoundException, IOException, AccountExistenceException{
        boolean loopCondition = true;
        DecimalFormat df = new DecimalFormat("#.##");
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Which account will receive the funds?");
                menuObj.printAccountSelectionNoAllNoChecking();
                int nestedChoice = sc.nextInt();
                if (nestedChoice > 2 || nestedChoice < 0)
                    throw new OutOfRangeException("Out of range");
                while (nestedChoice <= 2 || nestedChoice >= 0) { // in range
                    switch (nestedChoice) {
                        case 1:
                            if(customerObject.getCheckingAccount().getAccountNumber() == -1)
                                throw new AccountExistenceException("This account does not exist");
                            
                            System.out.println("You have chosen to transfer from your Checking account to your Savings account.\nPlease enter transfer amount.");
                            double format = sc.nextDouble(); // asks user to input amount
                            double transferAmount = Double.parseDouble(df.format(format)); 
                            double initialBalanceCh = customerObject.getCheckingAccount().getStartingBalance(); // save initial checking bal
                            double initialBalanceSa = customerObject.getSavingsAccount().getStartingBalance();  // save initial savings bal
                            customerObject.getCheckingAccount().withdrawFunction(transferAmount); //withdraw checking
                            if(transferAmount > 0 && customerObject.getCheckingAccount().getStartingBalance() - transferAmount >= 0){
                                customerObject.getSavingsAccount().depositFunction(transferAmount); //deposit savings
                                System.out.println("Successfully transferred $" + transferAmount+ " from Checking Account (" + customerObject.getCheckingAccount().getAccountNumber() + ") to Savings Account (" + customerObject.getSavingsAccount().getAccountNumber() + ")\n"); // confirmation
                                System.out.println("Checking Account Updated Balance: $" + customerObject.getCheckingAccount().getStartingBalance() + "\t Checking Account Initial Balance: $" + initialBalanceCh);
                                System.out.println("Savings Account Updated Balance $" + customerObject.getSavingsAccount().getStartingBalance() + "\t Savings Account Initial Balance $" + initialBalanceSa +"\n");
                                singletonLogger.transferLogFromChecking(customerObject, "Savings", initialBalanceCh, initialBalanceSa, transferAmount);
                            }
                            break;
                        case 2:// into credit
                        if (customerObject.getCreditAccount().getAccountNumber() == -1) // if acc does not exist
                                throw new AccountExistenceException("Account does not exist");
                            System.out.println("You have chosen to transfer from your Checking account to your Credit account.\nPlease enter transfer amount.");
                            format = sc.nextDouble(); // asks user to input amount
                            transferAmount = Double.parseDouble(df.format(format));                            
                            initialBalanceCh = customerObject.getCheckingAccount().getStartingBalance(); //saves initial checking bal
                            double initialBalanceCr = customerObject.getCreditAccount().getStartingBalance(); //saves initial credit bal
                            customerObject.getCheckingAccount().withdrawFunction( transferAmount); //withdraw checking
                            if(transferAmount > 0 && customerObject.getCheckingAccount().getStartingBalance() - transferAmount >= 0){
                                customerObject.getCreditAccount().depositFunction( transferAmount); //deposit credit
                                System.out.println("Successfully transferred $" + transferAmount + " from Checking Account (" + customerObject.getCheckingAccount().getAccountNumber() + ") to Credit Account (" + customerObject.getCreditAccount().getAccountNumber() + ")\n"); // confirmation
                                System.out.println("Checking Account Updated Balance: $" + customerObject.getCheckingAccount().getStartingBalance() + "\t Checking Account Initial Balance: $" + initialBalanceCh);
                                System.out.println("Credit Account Updated Balance $" + customerObject.getCreditAccount().getStartingBalance() + "\t Credit Account Initial Balance $" + initialBalanceCr+"\n");
                                singletonLogger.transferLogFromChecking(customerObject, "Credit", initialBalanceCh, initialBalanceCr, transferAmount);
                        } 
                        break;
                        case 0:// go back
                            System.out.println("Going back...\n");
                            loopCondition = false;
                            break;
                    }
                    if (loopCondition == true) {// if x is 0 then it can loop again
                        System.out.println("Which account will receive the funds?");
                        menuObj.printAccountSelectionNoAllNoChecking();
                        nestedChoice = sc.nextInt(); // saves user input
                        if (nestedChoice > 2 || nestedChoice < 0)
                            throw new OutOfRangeException("Please select a value inside the range.");
                    } else
                        break;
                }
            } catch (InputMismatchException e3) {
                System.out.println("Please enter either an integer or a decimal. Please try again.");
            } catch (OutOfRangeException e4) {
                System.out.println("Please choose between the range of selections. Please try again.");
            } catch (FileNotFoundException e5) {
                System.out.println("File not found.");
            } catch (IOException e6) {
                System.out.println("IOException");
            } catch (AccountExistenceException e7){
                System.out.println("You do not have that type of account.");
            }
        } while (loopCondition == true);
    }
      
    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>This method handles the choice-making when a customer selects "Savings Account".
     * <br><br>It will prompt the customer to select which account to transfer a specified account into.
     * <br><br>Entering 1 will allow the customer to transfer from their savings account into their checking account.
     * <br><br>Entering 2 will allow the customer to transfer from their savings account into their credit account.
     * <br><br>Entering 0 will bring the customer back to the account selection prompt without credit listed.
     * </p>
     * @param customerObject Object of a customer populated with values parsed from the input file
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws OutOfRangeException If the user inputs a number outside the range of selections
     * @throws FileNotFoundException If file is not found
     * @throws IOException InputOutputException
     * @throws AccountExistenceException If the user does not have acccess to a specific account
     */
    public void transferChoiceNest2(Customer customerObject) throws InputMismatchException, OutOfRangeException, FileNotFoundException, IOException, AccountExistenceException{
        boolean loopCondition = true;
        DecimalFormat df = new DecimalFormat("#.##");
        do{
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println("Which account will receive the funds?");
                menuObj.printAccountSelectionNoAllNoSavings();
                int nestedChoice = sc.nextInt();
                if (nestedChoice > 2 || nestedChoice < 0)
                    throw new OutOfRangeException("Out of range");
                while (nestedChoice <= 2 || nestedChoice >= 0) { // in range
                    switch (nestedChoice) {
                        case 1:
                            if (customerObject.getCheckingAccount().getAccountNumber() == -1) // if acc does not exist
                                throw new AccountExistenceException("Account does not exist");
                            System.out.println("You have chosen to transfer from your Savings Account to your Checking account.\nPlease enter transfer amount.");
                            double format = sc.nextDouble(); // asks user to input amount
                            double transferAmount = Double.parseDouble(df.format(format));                             
                            double initialBalanceSa = customerObject.getSavingsAccount().getStartingBalance(); //saves savings initial bal
                            double initialBalanceCh = customerObject.getCheckingAccount().getStartingBalance();//saves checking initial bal
                            customerObject.getSavingsAccount().withdrawFunction (transferAmount); //withdraw savings

                        if(transferAmount > 0 && customerObject.getSavingsAccount().getStartingBalance() - transferAmount >= 0){
                            customerObject.getCheckingAccount().depositFunction (transferAmount); //deposit checking
                            System.out.println("Successfully transferred $" + transferAmount + " from Savings Account (" + customerObject.getSavingsAccount().getAccountNumber() + ") to Checking Account ("+ customerObject.getCheckingAccount().getAccountNumber() + ")\n"); // confirmation
                            System.out.println("Savings account funds updated to $" + customerObject.getSavingsAccount().getStartingBalance() + " from the initial balance of $" + initialBalanceSa);
                            System.out.println("Checking account funds updated to $" + customerObject.getCheckingAccount().getStartingBalance() +" from the initial balance of $" + initialBalanceCh);
                            singletonLogger.transferLogFromSavings(customerObject, "Checking", initialBalanceSa, initialBalanceCh, transferAmount);
                        }
                            break;
                        case 2:
                            if (customerObject.getCreditAccount().getAccountNumber() == -1) // if acc does not exist
                                throw new AccountExistenceException("Account does not exist");
                            System.out.println("You have chosen to transfer from your Savings Account to your Credit account.\nPlease enter transfer amount.");
                            format = sc.nextDouble(); // asks user to input amount
                            transferAmount = Double.parseDouble(df.format(format));                              
                            initialBalanceSa = customerObject.getSavingsAccount().getStartingBalance(); // saves initial savings bal
                            double initialBalanceCr = customerObject.getCreditAccount().getStartingBalance(); //saves initial credit bal
                            customerObject.getSavingsAccount().withdrawFunction(transferAmount);// withdraw savings

                            if(transferAmount > 0 && customerObject.getSavingsAccount().getStartingBalance() - transferAmount >= 0){
                                customerObject.getCreditAccount().depositFunction(transferAmount); //deposit credit
                                System.out.println("Successfully transferred $" + transferAmount + " from Savings Account ("+ customerObject.getSavingsAccount().getAccountNumber() + ") to Credit Account (" + customerObject.getCreditAccount().getAccountNumber() + ")\n"); // confirmation
                                System.out.println("Savings account funds updated to $" + customerObject.getSavingsAccount().getStartingBalance()+ " from the initial balance of $" + initialBalanceSa);
                                System.out.println("Credit account funds updated to $" + customerObject.getCreditAccount().getStartingBalance()+ " from the initial balance of $" + initialBalanceCr);
                                singletonLogger.transferLogFromSavings(customerObject, "Credit", initialBalanceSa, initialBalanceCr, transferAmount);
                            }
                            break;
                        case 0:
                            System.out.println("Going back...\n");
                            loopCondition = false;
                            break;
                    }
                    if (loopCondition == true) {// if x is 0 then it can loop again
                        System.out.println("Which account will receive the funds?");
                        menuObj.printAccountSelectionNoAllNoSavings();
                        nestedChoice = sc.nextInt(); // saves user input
                        if (nestedChoice > 2 || nestedChoice < 0)
                            throw new OutOfRangeException("Please select a value inside the range.");
                    } else
                        break;
                }
            }catch (InputMismatchException e3) {
                System.out.println("Please enter either an integer or a decimal. Please try again.");
            } catch (OutOfRangeException e4) {
                System.out.println("Please choose between the range of selections. Please try again.");
            } catch (FileNotFoundException e5) {
                System.out.println("File not found.");
            } catch (IOException e6) {
                System.out.println("IOException");
            } catch (AccountExistenceException e7){
                System.out.println("You do not have this type of account.");
            }
        } while (loopCondition == true);
    }
    
    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>This method handles the choice-making when a customer selects "Pay An Individual".
     * <br><br>It will prompt the customer to select which account to transfer a specified account into.
     * <br><br>Entering 1 will allow the customer to pay another customer from checking account to checking account.
     * <br><br>Entering 2 will allow the customer to pay another customer from savings account to savings account.
     * <br><br>Entering 0 will bring the customer back to the main directory.
     * </p>
     * @param customerObject1 Object of the first customer populated with values parsed from the input file
     * @param customerObject2 Object of the second customer populated with values parsed from the input file
     * @return false Returns when the loop is broken from the method 
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws OutOfRangeException If the user inputs a number outside the range of selections
     * @throws FileNotFoundException If file is not found
     * @throws IOException InputOutputException
     * @throws AccountExistenceException If the user does not have access to a specific type of account
     */
    public boolean payNest(Customer customerObject1, Customer customerObject2) throws InputMismatchException, OutOfRangeException, FileNotFoundException, IOException, AccountExistenceException {
        boolean loopCondition = true;
        DecimalFormat df = new DecimalFormat("#.##");
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Which account will receive the funds?");
                menuObj.printAccountSelectionNoAllNoCredit();
                int nestedChoice = sc.nextInt();
                if (nestedChoice > 2 || nestedChoice < 0)
                    throw new OutOfRangeException("Out of range");
                while (nestedChoice <= 2 || nestedChoice >= 0) {
                    switch (nestedChoice) { 
                        case 1:
                            if (customerObject1.getCheckingAccount().getAccountNumber() == -1) // if acc does not exist
                                throw new AccountExistenceException("Account does not exist");
                            System.out.println("You have chosen: Pay from checking to checking.\nPlease enter pay amount.");
                            double format = sc.nextDouble(); // asks user to input amount
                            double payAmount = Double.parseDouble(df.format(format)); 
                            double initialBalanceCh1 = customerObject1.getCheckingAccount().getStartingBalance(); //saves initial balance of both users
                            double initialBalanceCh2 = customerObject2.getCheckingAccount().getStartingBalance(); 
                            customerObject1.getCheckingAccount().transferFunction(payAmount, customerObject2.getCheckingAccount());
                            if(payAmount > 0 && customerObject1.getSavingsAccount().getStartingBalance() - payAmount >= 0){
                                System.out.println("Successfully transferred: $"+ payAmount+" from your Checking Account (" + customerObject1.getCheckingAccount().getAccountNumber() + ") to their Checking Account (" +customerObject2.getCheckingAccount().getAccountNumber() +")\n"); //confirmation
                                System.out.println("Your Updated Checking Account Balance: $"+customerObject1.getCheckingAccount().getStartingBalance() + "\nYour Initial Checking Account Balance: $" +initialBalanceCh1);
                                System.out.println("\nTheir Updated Checking Account Balance: $"+customerObject2.getCheckingAccount().getStartingBalance() + "\nTheir Initial Checking Account Balance: $" +initialBalanceCh2+"\n");
                                singletonLogger.payLog(customerObject1, customerObject2, "Checking", initialBalanceCh1, initialBalanceCh2, payAmount);
                            }
                            break;
                        case 2:
                            System.out.println("You have chosen: Pay from savings to savings\nPlease enter pay amount.");
                            format = sc.nextDouble(); // asks user to input amount
                            payAmount = Double.parseDouble(df.format(format));                            
                            double initialBalanceSa1 = customerObject1.getSavingsAccount().getStartingBalance(); //saves initial balance of both users
                            double initialBalanceSa2 = customerObject2.getSavingsAccount().getStartingBalance();  
                            customerObject1.getSavingsAccount().transferFunction(payAmount, customerObject2.getSavingsAccount());
                            if(payAmount > 0 && customerObject1.getSavingsAccount().getStartingBalance() - payAmount >= 0){
                                System.out.println("Successfully transferred: $"+ payAmount+" from your Savings Account (" + customerObject1.getSavingsAccount().getAccountNumber() + ") to their Savings Account (" +customerObject2.getSavingsAccount().getAccountNumber() +")\n"); //confirmation
                                System.out.println("\nYour Updated Savings Account Balance: $"+customerObject1.getSavingsAccount().getStartingBalance() + "\nYour Initial Savings Account Balance: $" +initialBalanceSa1);
                                System.out.println("\nTheir Updated Savings Account Balance: $"+customerObject2.getSavingsAccount().getStartingBalance() + "\nTheir Initial Savings Account Balance: $" +initialBalanceSa2+"\n");
                                singletonLogger.payLog(customerObject1, customerObject2, "Savings", initialBalanceSa1, initialBalanceSa2, payAmount);
                            }
                            break;
                        case 0:
                            System.out.println("Going back...\n");
                            loopCondition = false;
                            break;
                    }
                    
                    if (loopCondition== true) {
                        System.out.println("Which account will receive the funds?");
                        menuObj.printAccountSelectionNoAllNoCredit();
                        nestedChoice = sc.nextInt(); // saves user type selection
                        if (nestedChoice > 2 || nestedChoice < 0)
                            throw new OutOfRangeException("Please enter a number inside the range.");
                    } else
                        break;
                }
            } catch (InputMismatchException e3) {
                System.out.println("Please enter either an integer or a decimal. Please try again.");
            } catch (OutOfRangeException e4) {
                System.out.println("Please choose between the range of selections. Please try again.");
            } catch (FileNotFoundException e5){
                System.out.println("File not found.");
            } catch (IOException e6){
                System.out.println("IOException");
            } catch (AccountExistenceException e7){
                System.out.println("The account does not exist.");
            }
        } while (loopCondition == true);
        if (loopCondition == false)
            return false;
        return true;
    }
    
    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>This method handles the choice-making when a manager logs in.
     * <br><br>It will prompt the manager to select which service is available to them.
     * <br><br>Entering 1 will allow the customer to inquire about their balances.
     * <br><br>Entering 2 will allow the customer deposit cash into a selected account.
     * <br><br>Entering 3 will allow the customer withdraw cash into a selected account .
     * <br><br>Entering 4 will allow the customer transfer cash between accounts of their choosing.
     * <br><br>Entering 5 will allow the customer to pay another customer's accounts.
     * <br><br>Entering 0 will log the customer out.
     * </p>
     * 
     * @param customerObject An instance of an ArrayList of type Customer
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws OutOfRangeException If the user inputs a number outside the range of selections
     * @throws FileNotFoundException If file is not found
     * @throws IOException InputOutputException
     * 
     */
    public void customerChoice(ArrayList<Customer> customerObject) throws InputMismatchException, OutOfRangeException, FileNotFoundException, IOException{
        boolean mainLoop = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter Identification number (Up to 3 Digits)");
        int idNum = sc.nextInt();
        System.out.println("\nEnter password");
        String pass = sc.next();
        Customer firstUser = findAccountById(customerObject, idNum);
        int choice;

        do {
            try {
                //if login is incorrect
                if (!(idNum == firstUser.getIdNumber() && firstUser.getPassword().equals(pass))) {
                    System.out.println("Input does not match records. Please try again");
                    break;
                } 
                //if the user exists
                else {
                        generateUpdatedBalanceSheet(customerObject); //update balance sheet upon login
                        singletonLogger.reportLogin(firstUser.getFirstName(), firstUser.getLastName()); //log login
                        menuObj.reportLogin(firstUser.getFirstName(), firstUser.getLastName());
                        menuObj.printMainDirectory();
                        choice = sc.nextInt();
                        if (choice > 5 || choice < 0) // range check for menu
                            throw new OutOfRangeException("Out of range please try again.");
                        boolean loopCondition = true;
                        do { 
                            while (choice <= 5 && choice >= 0) { //in condition
                                switch (choice) {
                                    case 0:// Will log the user out
                                        singletonLogger.reportLogout(firstUser.getFirstName(), firstUser.getLastName());
                                        menuObj.reportLogout(firstUser.getFirstName(), firstUser.getLastName());
                                        generateUpdatedBalanceSheet(customerObject);
                                        loopCondition = false;
                                        mainLoop = false;
                                        break;
                                    case 1:// Will inquire a balance
                                            inquireBalanceChoice(firstUser);
                                        break;
                                    case 2:// Will deposit money into user's account
                                            depositChoice(firstUser);
                                        break;
                                    case 3:// Will withdraw money from a user's account
                                            withdrawChoice(firstUser);
                                        break;
                                    case 4:// Will move money from account to account
                                            transferChoice(firstUser);
                                        break;
                                    case 5:// Will send money to another person's specified account
                                        mainLoop = true;
                                        do {
                                            System.out.println("Please enter recipient's Full Name. (Case-insensitive)");
                                            String nameInput = sc.next();
                                            nameInput += sc.nextLine();
                                            boolean isFound = false;
                                            boolean isSameName = false;
                                            for (Customer secondUser : customerObject) {
                                                String toComp = secondUser.getFirstName() + " " + secondUser.getLastName();
                                                if (nameInput.equalsIgnoreCase(firstUser.getFirstName() + " " + firstUser.getLastName())) { // same name
                                                    isFound = true;
                                                    isSameName = true;
                                                    System.out.println("\nPlease enter another person's name.");
                                                    break;
                                                }
                                                if (nameInput.equalsIgnoreCase(toComp)) { // found
                                                    isFound = true;
                                                    isSameName = false;
                                                    loopCondition = payNest(firstUser, secondUser);
                                                    if (loopCondition == false)
                                                        break;
                                                }
                                                if (isFound) // if found stop looking
                                                    break;
                                            }
                                            if (!isFound && isSameName)
                                                System.out.println("Cannot pay yourself you silly goose.");
                                            if (!isFound&& !isSameName)
                                                System.out.println("Input does not match records. Please try again.");
                                        } while (loopCondition == true);
                                        break;
                                }
                                if (loopCondition == true) {
                                    menuObj.reportLogin(firstUser.getFirstName(),firstUser.getLastName());
                                    menuObj.printMainDirectory();
                                    choice = sc.nextInt();
                                    if (choice > 5 || choice < 0)
                                        throw new OutOfRangeException("Please select a value inside the range.");
                                } else
                                    break;
                            }
                        } while (loopCondition == true);
                    }
            }catch (InputMismatchException e1) {
                System.out.println("Please enter an integer selection.");
                sc.nextLine();
            } catch (OutOfRangeException e2) {
                System.out.println("Please choose between the range of selections.");
                sc.nextLine();
            }catch (FileNotFoundException e3){
                System.out.println("File not found, please check name or close the file.");
            } catch (IOException e4){
                System.out.println("IOException");
            } 
            
        }while(mainLoop== true);
    }

    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>This method handles the choice-making when a manager logs in.
     * <br><br>It will prompt the manager to select which service is available to them.
     * <br><br>Entering 1 will allow the manager to see the options for inquiring customer account information.
     * <br><br>Entering 2 will allow the manager to create a new user through the console.
     * <br><br>Entering 3 will allow the manager to generate a bank statement for a customer given their full name.
     * <br><br>Entering 0 will log the manager out.
     * </p>
     * @param customerObject An instance of an ArrayList of type Customer
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws OutOfRangeException If the user inputs a number outside the range of selections
     * @throws FileNotFoundException If the file is not found or is currently open while being written to
     * @throws IOException Input Output Exception
     */
    public void managerChoice(ArrayList<Customer> customerObject) throws InputMismatchException, OutOfRangeException, FileNotFoundException, IOException{
      boolean loopCondition = true;
        do{
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println("Logged in as a manager. What would you like to do?");
                menuObj.printManagerMainDirectory();
                int userChoice = sc.nextInt();
                if(userChoice >3 || userChoice < 0)
                    throw new OutOfRangeException("Out of range");
                while (userChoice <= 3 || userChoice >= 0) {
                switch (userChoice) {
                    case 1: //inquire accounts
                        managerChoiceNest1(customerObject);
                        break;
                    case 2: //create a new customer
                        managerChoiceNest2(customerObject);
                        break;
                    case 3://generate a bank statement
                        BankStatement.writeStatement();
                        break;
                    case 0:
                        System.out.println("Successfully Logged out.");
                        loopCondition = false;
                        break;
                    }
                    if (loopCondition == true) {
                        System.out.println("Logged in as a manager. What would you like to do?");
                        menuObj.printManagerMainDirectory();
                        userChoice = sc.nextInt(); // saves user input
                        if (userChoice > 3 || userChoice < 0)
                            throw new OutOfRangeException("Please select a value inside the range of selections");
                    } else
                        break;
                }
            } catch (InputMismatchException e1) {
                System.out.println("Please enter an integer selection.");
            } catch (OutOfRangeException e2) {
                System.out.println("Please choose between the range of selections.");
            } catch (FileNotFoundException e3){
                System.out.println("File not found, please check name or close the file.");
            } catch (IOException e4){
                System.out.println("IOException");
            } 
        } while (loopCondition == true);
    }

    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>This method handles the choice-making when a manager selects "Inquire Accounts".
     * <br><br>It will prompt the manager to select which service is available to them.
     * <br><br>Entering 1 will allow the manager inquire a customer's information by inputting their full name.
     * <br><br>Entering 2 will execute a method that handles inquiring a customer's information by account type and account number.
     * <br><br>Entering 3 will allow the manager to view a list of all customer account information.
     * <br><br>Entering 0 will bring the manager back to the manager main directory.
     * </p>
     * @param customerObject An instance of an ArrayList of type Customer
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws OutOfRangeException If the user inputs a number outside the range of selections
     * @throws FileNotFoundException When the file is not found
     * @throws IOException Input Output Exception
     */
    public void managerChoiceNest1(ArrayList<Customer> customerObject) throws InputMismatchException, OutOfRangeException, FileNotFoundException, IOException{
        boolean loopCondition = true;
        do{
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println("How will you be inquiring accounts?");
                menuObj.printManagerInquirySelection();
                int userChoice = sc.nextInt();
                if(userChoice >3 || userChoice < 0)
                    throw new OutOfRangeException("Out of range");
                while (userChoice <= 3 || userChoice >= 0) {
                    boolean isFound = false;
                switch (userChoice) {
                    case 1: //full name
                    System.out.println("Please enter customer's Full Name. (Case insensitive");
                    String nameInput = sc.next();
                    nameInput += sc.nextLine();
                    for (Customer cTmp : customerObject) {
                        String toComp = (cTmp.getFirstName() + " " + cTmp.getLastName());
                        if(nameInput.equalsIgnoreCase(toComp)){// if true, saves values on current iteration
                            isFound = true;
                           if(cTmp.getCheckingAccount().getAccountNumber() == -1 && cTmp.getCreditAccount().getAccountNumber()==-1){ //only savings exists
                            System.out.println("User found successfully.");
                           
                           }
                           else if(cTmp.getCheckingAccount().getAccountNumber() == -1 && cTmp.getCreditAccount().getAccountNumber()!=-1){//checking does not exist
                            System.out.println("User found successfully.");
                            String name = (cTmp.getFirstName()+ " " + cTmp.getLastName());
                            String info =("\nDate of Birth: \t"+ cTmp.getDateOfBirth() + 
                                          "\nIdentification Number: \t" + cTmp.getIdNumber()+ 
                                          "\nAddress: \t" + cTmp.getAddress() + 
                                          "\nPhone Number: \t" + cTmp.getPhoneNumber());

                            int accInfoSa =(cTmp.getSavingsAccount().getAccountNumber());
                            int accInfoCr =(cTmp.getCreditAccount().getAccountNumber());
                            double accBalSa = (cTmp.getSavingsAccount().getStartingBalance());
                            double accBalCr = (cTmp.getCreditAccount().getStartingBalance());
                            
                            System.out.println("Name: \t" + name + info +
                                               "\nSavings account Number & Balance: \t" + accInfoSa+ ", "+ accBalSa+
                                               "\nCredit account Number & Balance: \t" + accInfoCr+ ", "+ accBalCr);
                           }
                           else if(cTmp.getCreditAccount().getAccountNumber() == -1 && cTmp.getCheckingAccount().getAccountNumber()!=-1){ //credit does not exist
                            System.out.println("User found successfully.");
                            String name = (cTmp.getFirstName()+ " " + cTmp.getLastName());
                            String info =("\nDate of Birth: \t"+ cTmp.getDateOfBirth() + 
                                          "\nIdentification Number: \t" + cTmp.getIdNumber()+ 
                                          "\nAddress: \t" + cTmp.getAddress() + 
                                          "\nPhone Number: \t" + cTmp.getPhoneNumber());
                            int accInfoSa =(cTmp.getSavingsAccount().getAccountNumber());
                            int accInfoCh =(cTmp.getCheckingAccount().getAccountNumber());
                            double accBalSa = (cTmp.getSavingsAccount().getStartingBalance());
                            double accBalCh = (cTmp.getCheckingAccount().getStartingBalance());
                            
                            System.out.println("Name: \t" + name + info +
                                               "\nSavings account Number & Balance: \t" + accInfoSa+ ", "+ accBalSa+
                                               "\nChecking account Number & Balance: \t" + accInfoCh+ ", "+ accBalCh);
                           }
                           else if(cTmp.getCreditAccount().getAccountNumber() != -1 && cTmp.getCheckingAccount().getAccountNumber()!=-1){//all accounts exist
                            System.out.println("User found successfully.");
                            String name = (cTmp.getFirstName()+ " " + cTmp.getLastName());
                            String info =("\nDate of Birth: \t"+ cTmp.getDateOfBirth() + 
                                          "\nIdentification Number: \t" + cTmp.getIdNumber()+ 
                                          "\nAddress: \t" + cTmp.getAddress() + 
                                          "\nPhone Number: \t" + cTmp.getPhoneNumber());

                            int accInfoCh =(cTmp.getCheckingAccount().getAccountNumber());
                            int accInfoSa =(cTmp.getSavingsAccount().getAccountNumber());
                            int accInfoCr =(cTmp.getCreditAccount().getAccountNumber());
                            double accBalCh = (cTmp.getCheckingAccount().getStartingBalance());
                            double accBalSa = (cTmp.getSavingsAccount().getStartingBalance());
                            double accBalCr = (cTmp.getCreditAccount().getStartingBalance());
                            
                            System.out.println("Name: \t" + name + info +
                                               "\nChecking account Number & Balance: \t" + accInfoCh+ ", "+ accBalCh +
                                               "\nSavings account Number & Balance: \t" + accInfoSa+ ", "+ accBalSa+
                                               "\nCredit account Number & Balance: \t" + accInfoCr+ ", "+ accBalCr);
                           }
                        }
                        if (isFound)
                            break;
                    }
                    if (!isFound)
                        System.out.println("No record found. Please try again.");
                    break;
                    case 2: //acc type and number
                        managerChoiceInquiryByAccNest(customerObject);
                        break;
                    case 3: //list all
                        menuObj.print(customerObject);
                        break;
                    case 0://go back
                        System.out.println("Going back...\n");
                        loopCondition = false;
                        break;
                    }
                    if (loopCondition == true) {
                        System.out.println("How will you be inquiring accounts?");
                        menuObj.printManagerInquirySelection();
                        userChoice = sc.nextInt(); // saves user type selection
                        if (userChoice > 3 || userChoice < 0)
                            throw new OutOfRangeException("Please enter value inside the range of selections.");
                    } else
                        break;
                }
                } catch (InputMismatchException e1) {
                    System.out.println("Please follow the instructions for expected information in the fields.");
                } catch (OutOfRangeException e2){
                    System.out.println("Please choose between the range of selections.");
                }
        } while (loopCondition == true);
    }

     /**
      * 
      * Taken from Anthony Jasso
      *
      * <p>
      * This method handles the choice-making when a manager selects "Create User".
      * <br>
      * <br>
      * It will first prompt the manager to provide information to create a new user.
      * <br>
      * <br>
      * It will then prompt the manager to select which additional account to create
      * and will provide the information needed for the accounts. <br>
      * <br>
      * Entering 1 will start the process for creating a checking account. <br>
      * <br>
      * Entering 2 will start the process for creating a credit account. <br>
      * <br>
      * Entering 3 will confirm the information and will create the user. <br>
      * <br>
      * Entering 0 will bring the manager back to the manager main directory.
      * </p>
      * 
      * @param customerObject An instance of an ArrayList of type Customer
      * @throws InputMismatchException The input is not the same data type expected by compiler
      * @throws OutOfRangeException    If the user inputs a number outside the range of selections
      * @throws FileNotFoundException If the file is not found 
      * @throws IOException Input Output Exception
      * 
      */
    public void managerChoiceNest2(ArrayList<Customer> customerObject) throws InputMismatchException, OutOfRangeException, FileNotFoundException, IOException{
        boolean loopCondition = true;
        DecimalFormat df = new DecimalFormat("#.##");
        Scanner sc = new Scanner(System.in);
        FileWriter fw = new FileWriter("Updated Balance Sheet.csv", true);
        Checking newChecking;
        Savings newSavings;
        Credit newCredit;
        double initialBalCh=-1;
        double initialBalCr=1;
        int maxChecking = findMaxChecking(customerObject) + 1;;
        int maxSavings = findMaxSavings(customerObject) + 1;
        int maxCredit = findMaxCredit(customerObject) + 1;
        int maxId = findMaxID(customerObject)+1;

        System.out.println("Starting User Account Creation...");
        System.out.println("Step 1 of 7");
        System.out.println("Savings account created by default. How much money will the user be opening the account with? Enter Positive Amount.");
        double format = sc.nextDouble(); // asks user to input amount
        double initialBalSa = Double.parseDouble(df.format(format));
        if (initialBalSa < 0)
            throw new NegativeNumberException("Please enter in a value of the opposite sign");
        newSavings = new Savings(maxSavings, initialBalSa);
        System.out.println("Savings Account Information Saved Successfully\n");

        System.out.println("Step 2 of 7");
        System.out.println("Enter First Name (Capitalization Encouraged, No Spaces)");
        String fName = sc.next();
        System.out.println("First Name Saved Successfully\n");

        System.out.println("Step 3 of 7");
        System.out.println("Enter Last Name (Capitalization Encouraged, No Spaces)");
        String lName = sc.next();
        System.out.println("Last Name Saved Successfully\n");

        System.out.println("Step 4 of 7");
        System.out.println("Enter Date of Birth (dd/MM/yyyy)");
        String dob = sc.next();
        System.out.println("Date of Birth Saved Successfully\n");

        System.out.println("Step 5 of 7");
        System.out.println("Enter Address (Spaces allowed: 123 Address St, City, State Zip)");
        String address = sc.next();
        address += sc.nextLine();
        System.out.println("Address Saved Successfully\n");
        System.out.println("Step 6 of 7");
        System.out.println("Enter Phone Number (Format: (123) 456-7890  )");
        String phone = sc.next();
        phone += sc.nextLine();
        System.out.println("Phone Number Successfully\n");
        System.out.println("Step 7 of 7");
        System.out.println("Enter Account Password (No Spaces, Case-sensitive)");
        String pass = sc.next();
        do{
            try{
                System.out.println("Which account would you like to open in addition to the Savings Account (Must select confirm when done otherwise info will be lost)");
                menuObj.printManagerUserCreationSelection();
                int userChoice = sc.nextInt();
                if (userChoice > 3 || userChoice < 0)
                    throw new OutOfRangeException("Out of range");
                    boolean switchLoop = true;
                    do{
                        while (userChoice <= 3 || userChoice >= 0) { // if in range
                            switch (userChoice) {
                                case 1:// checking creation
                                    boolean checkingCreationLoop = true;
                                    do{
                                        System.out.println("Starting Checking Account Creation..");
                                        System.out.println("How much money will the user be opening their Checking account with? Enter Positive Amount");
                                        format = sc.nextDouble(); // asks user to input amount
                                        initialBalCh = Double.parseDouble(df.format(format));
                                        if (initialBalCh < 0)
                                            throw new NegativeNumberException("Please enter in a value of the opposite sign");
                                        
                                        System.out.println("Checking Account Information Saved Successfully");
                                        checkingCreationLoop = false;
                                    } while(checkingCreationLoop == true);
                                
                                    break;
                                case 2:// credit creation
                                    System.out.println("Starting Credit Account Creation..");
                                    System.out.println("How much money will the user be opening their Credit account with? Positive Amount.");
                                    format = sc.nextDouble(); // asks user to input amount
                                    initialBalCr = Double.parseDouble(df.format(format));
                                    if (initialBalCr < 0)
                                        throw new NegativeNumberException("Please enter in a value of the opposite sign");
                                    System.out.println("Credit Account Information Saved Successfully");
                                    break;
                                case 3:// confirm will exit
                                    System.out.println("Creating the User...");
                                    
                                    //all accounts exist
                                    if (initialBalCh != -1 && initialBalCr != 1 && initialBalSa != -1) {
                                        newChecking = new Checking(maxChecking, initialBalCh);
                                        newCredit = new Credit(maxCredit, initialBalCr);
                                        customerObject.add(new Customer(fName, lName, dob, address, phone, maxId, pass, newChecking, newSavings, newCredit));
                                        fw.append(String.valueOf("\n" + fName + ","));
                                        fw.append(String.valueOf(lName + ","));
                                        fw.append(String.valueOf(dob + ","));
                                        fw.append(String.valueOf(maxId + ","));
                                        fw.append(String.valueOf(address + ","));
                                        fw.append(String.valueOf(phone + ","));
                                        fw.append(String.valueOf(maxChecking + ","));
                                        fw.append(String.valueOf(maxSavings + ","));
                                        fw.append(String.valueOf(maxCredit + ","));
                                        fw.append(String.valueOf(initialBalCh + ","));
                                        fw.append(String.valueOf(initialBalSa + ","));
                                        fw.append(String.valueOf(initialBalCr*-1 + ","));
                                        System.out.println("User created with checking, savings, and credit account");
                                        
                                    }
                                    //checking and savings exist
                                    if (initialBalCh != -1 && initialBalCr == 1 && initialBalSa != -1){
                                        newChecking = new Checking(maxChecking, initialBalCh);
                                        newCredit = new Credit(-1, 1);
                                        customerObject.add(new Customer(fName, lName, dob, address, phone, maxId, pass, newChecking, newSavings, newCredit));
                                        fw.append(String.valueOf("\n" + fName + ","));
                                        fw.append(String.valueOf(lName + ","));
                                        fw.append(String.valueOf(dob + ","));
                                        fw.append(String.valueOf(maxId + ","));
                                        fw.append(String.valueOf(address + ","));
                                        fw.append(String.valueOf(phone + ","));
                                        fw.append(String.valueOf(maxChecking + ","));
                                        fw.append(String.valueOf(maxSavings + ","));
                                        fw.append(String.valueOf(maxCredit + ","));
                                        fw.append(String.valueOf(initialBalCh + ","));
                                        fw.append(String.valueOf(initialBalSa + ","));
                                        fw.append(String.valueOf(initialBalCr*-1 + ","));
                                        System.out.println("User created with checking and savings account");
                                        switchLoop = false;
                                    }
                                    //savings and credit exist
                                    if (initialBalCh == -1 && initialBalCr != 1 && initialBalSa !=-1) {
                                        newCredit = new Credit(maxCredit, initialBalCr);
                                        newChecking = new Checking(-1, -1);
                                        customerObject.add(new Customer(fName, lName, dob, address, phone, maxId, pass, newChecking, newSavings, newCredit));
                                        fw.append(String.valueOf("\n" + fName + ","));
                                        fw.append(String.valueOf(lName + ","));
                                        fw.append(String.valueOf(dob + ","));
                                        fw.append(String.valueOf(maxId + ","));
                                        fw.append(String.valueOf(address + ","));
                                        fw.append(String.valueOf(phone + ","));
                                        fw.append(String.valueOf(maxChecking + ","));
                                        fw.append(String.valueOf(maxSavings + ","));
                                        fw.append(String.valueOf(maxCredit + ","));
                                        fw.append(String.valueOf(initialBalCh + ","));
                                        fw.append(String.valueOf(initialBalSa + ","));
                                        fw.append(String.valueOf(initialBalCr*-1 + ","));
                                        System.out.println("User created with savings and credit account");
                                        switchLoop = false;
                                    }
                                    
                                    //only savings exists
                                    if (initialBalCh == -1 && initialBalCr == 1 && initialBalSa != -1) {
                                        newChecking = new Checking(-1, -1);
                                        newCredit = new Credit(-1, 1);
                                        customerObject.add(new Customer(fName, lName, dob, address, phone, maxId, pass, newChecking, newSavings, newCredit));
                                        fw.append(String.valueOf("\n" + fName + ","));
                                        fw.append(String.valueOf(lName + ","));
                                        fw.append(String.valueOf(dob + ","));
                                        fw.append(String.valueOf(maxId + ","));
                                        fw.append(String.valueOf(address + ","));
                                        fw.append(String.valueOf(phone + ","));
                                        fw.append(String.valueOf(maxChecking + ","));
                                        fw.append(String.valueOf(maxSavings + ","));
                                        fw.append(String.valueOf(maxCredit + ","));
                                        fw.append(String.valueOf(initialBalCh + ","));
                                        fw.append(String.valueOf(initialBalSa + ","));
                                        fw.append(String.valueOf(initialBalCr*-1 + ","));
                                        System.out.println("User created with savings account");
                                        switchLoop = false;
                                    }
                                    switchLoop = false;
                                    generateUpdatedBalanceSheet(customerObject);
                                    System.out.println("Customer is able to do business now. They are updated in the Updated Balance Sheet.");
                                    break;
                                case 0:// go back
                                    System.out.println("Cancelling...\n");
                                    loopCondition = false;
                                    switchLoop = false;
                                    break;
                            }
                            if(switchLoop==true){//loop choice-making
                                System.out.println("Which account would you like to open in addition to the Savings Account (Must select confirm when done otherwise info will be lost)");
                                menuObj.printManagerUserCreationSelection();
                                userChoice = sc.nextInt();
                                if (userChoice > 3 || userChoice < 0)
                                    throw new OutOfRangeException("Out of range");
                            }
                            else
                                break;    
                        }
                    } while (switchLoop == true);
                    if (switchLoop == false)
                        break;

                if (loopCondition == true) {
                    System.out.println("user creation");
                    userChoice = sc.nextInt(); // saves user type selection
                    if (userChoice > 3 || userChoice < 0)
                        throw new OutOfRangeException("Please enter value inside the range of selections.");
                } else
                    break;
                    
            } catch (InputMismatchException e1) {
                System.out.println("Please follow the instructions for expected information in the fields.");
                sc.next();
            } catch (OutOfRangeException e2) {
                System.out.println("Please choose between the range of selections.");
            } catch (IOException e3) {
                System.out.println("Input Output Exception");
                break;
            } catch (NegativeNumberException e5) {
                System.out.println("Please enter a number of the opposite sign.");
            }
        } while (loopCondition == true);
    }
    
    /**
     * 
     * Taken from Derek Aguirre
     *  
     * <p>This method handles the choice-making when a manager selects "Account Type &amp; Account Number".
     * <br><br>It will prompt the manager to select which account type they want to inquire by.
     * <br><br>Entering 1 will allow the manager inquire by a customer's checking account type. They must input a checking account number that matches an account on record.
     * <br><br>Entering 2 will allow the manager inquire by a customer's savings account type. They must input a savings account number that matches an account on record.
     * <br><br>Entering 3 will allow the manager inquire by a customer's credit account type. They must input a credit account number that matches an account on record.
     * <br><br>Entering 0 will bring the manager back to the account inquiry selection.
     * </p>
     * @param customerObject An instance of an ArrayList of type Customer
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws OutOfRangeException If the user inputs a number outside the range of selections
     * @throws AccountExistenceException If the manager tries inquiring by -1
     */
    public void managerChoiceInquiryByAccNest(ArrayList<Customer> customerObject) throws InputMismatchException, OutOfRangeException, AccountExistenceException{
        boolean loopCondition = true;
        do {
            try{
                Scanner sc = new Scanner(System.in); 
                System.out.println("Please choose account type.");
                menuObj.printAccountSelectionNoAll();
                int userChoice = sc.nextInt();
                if(userChoice >3 || userChoice < 0)
                    throw new OutOfRangeException("Out of range");
                while (userChoice <= 3 || userChoice >= 0) {
                    boolean isFound = false;
                switch (userChoice) {
                    case 1://checking was chosen
                        System.out.println("Please enter the customer's checking account number. (4-Digit integer 1XXX)");
                        int accNumInput = sc.nextInt();
                        if(accNumInput==-1)
                            throw new AccountExistenceException("Cannot inquire an account that does not exist.");
                        for (Customer chAcc : customerObject) {
                            
                            if (accNumInput == chAcc.getCheckingAccount().getAccountNumber()) { // if true, saves values on current iteration
                                isFound = true;
                                System.out.println("User found successfully.");
                                String name = (chAcc.getFirstName() + " " + chAcc.getLastName());

                                String info = ("\nDate of Birth: \t" + chAcc.getDateOfBirth() + 
                                               "\nIdentification Number: \t" + chAcc.getIdNumber() + 
                                               "\nAddress: \t" + chAcc.getAddress() + 
                                               "\nPhone Number: \t" + chAcc.getPhoneNumber());

                                int accInfoCh = (chAcc.getCheckingAccount().getAccountNumber());
                                int accInfoSa = (chAcc.getSavingsAccount().getAccountNumber());
                                int accInfoCr = (chAcc.getCreditAccount().getAccountNumber());
                                double accBalCh = (chAcc.getCheckingAccount().getStartingBalance());
                                double accBalSa = (chAcc.getSavingsAccount().getStartingBalance());
                                double accBalCr = (chAcc.getCreditAccount().getStartingBalance());

                                System.out.println("Name: \t" + name + info + 
                                                   "\nChecking account Number & Balance: \t" + accInfoCh + ", " + accBalCh + 
                                                   "\nSavings account Number & Balance: \t" + accInfoSa + ", " + accBalSa + 
                                                   "\nCredit account Number & Balance: \t" + accInfoCr + ", " + accBalCr);
                            }
                            
                            if (isFound)
                                break;
                        }
                        if (!isFound){
                            System.out.println("Account not found. Please try again ");
                        }
                        break;
                    case 2://savings was chosen
                        System.out.println("Please enter the customer's savings account number. (4-Digit integer 2XXX)");
                       accNumInput = sc.nextInt();
                        for (Customer saAcc : customerObject) {
                            if(accNumInput == saAcc.getSavingsAccount().getAccountNumber()){ // if true, saves values on current iteration
                                isFound = true;
                                if(saAcc.getCheckingAccount().getAccountNumber() == -1 && saAcc.getCreditAccount().getAccountNumber()==-1){ //only savings exists
                                    System.out.println("User found successfully.");
                                    String name = (saAcc.getFirstName()+ " " + saAcc.getLastName());
                                    String info =("\nDate of Birth: \t"+ saAcc.getDateOfBirth() + 
                                                  "\nIdentification Number: \t" + saAcc.getIdNumber()+ 
                                                  "\nAddress: \t" + saAcc.getAddress() + 
                                                  "\nPhone Number: \t" + saAcc.getPhoneNumber());
                                    int accInfoSa =(saAcc.getSavingsAccount().getAccountNumber());
                                    double accBalSa = (saAcc.getSavingsAccount().getStartingBalance());
        
                                    System.out.println("Name: \t" + name + info +
                                                       "\nSavings account Number & Balance: \t" + accInfoSa+ ", "+ accBalSa);
                                   }
                                   if(saAcc.getCheckingAccount().getAccountNumber() == -1 && saAcc.getCreditAccount().getAccountNumber()!=-1){//checking does not exist
                                    System.out.println("User found successfully.");
                                    String name = (saAcc.getFirstName()+ " " + saAcc.getLastName());
                                    String info =("\nDate of Birth: \t"+ saAcc.getDateOfBirth() + 
                                                  "\nIdentification Number: \t" + saAcc.getIdNumber()+ 
                                                  "\nAddress: \t" + saAcc.getAddress() + 
                                                  "\nPhone Number: \t" + saAcc.getPhoneNumber());
        
                                    int accInfoSa =(saAcc.getSavingsAccount().getAccountNumber());
                                    int accInfoCr =(saAcc.getCreditAccount().getAccountNumber());
                                    double accBalSa = (saAcc.getSavingsAccount().getStartingBalance());
                                    double accBalCr = (saAcc.getCreditAccount().getStartingBalance());
                                    
                                    System.out.println("Name: \t" + name + info +
                                                       "\nSavings account Number & Balance: \t" + accInfoSa+ ", "+ accBalSa+
                                                       "\nCredit account Number & Balance: \t" + accInfoCr+ ", "+ accBalCr);
                                   }
                                   if(saAcc.getCreditAccount().getAccountNumber() == -1 && saAcc.getCheckingAccount().getAccountNumber()!=-1){ //credit does not exist
                                    System.out.println("User found successfully.");
                                    String name = (saAcc.getFirstName()+ " " + saAcc.getLastName());
                                    String info =("\nDate of Birth: \t"+ saAcc.getDateOfBirth() + 
                                                  "\nIdentification Number: \t" + saAcc.getIdNumber()+ 
                                                  "\nAddress: \t" + saAcc.getAddress() + 
                                                  "\nPhone Number: \t" + saAcc.getPhoneNumber());
                                    int accInfoSa =(saAcc.getSavingsAccount().getAccountNumber());
                                    int accInfoCr =(saAcc.getCreditAccount().getAccountNumber());
                                    double accBalSa = (saAcc.getSavingsAccount().getStartingBalance());
                                    double accBalCr = (saAcc.getCreditAccount().getStartingBalance());
                                    
                                    System.out.println("Name: \t" + name + info +
                                                       "\nSavings account Number & Balance: \t" + accInfoSa+ ", "+ accBalSa+
                                                       "\nCredit account Number & Balance: \t" + accInfoCr+ ", "+ accBalCr);
                                   }
                                    if(saAcc.getCreditAccount().getAccountNumber() != -1 && saAcc.getCheckingAccount().getAccountNumber()!=-1){//all accounts exist
                                    System.out.println("User found successfully.");
                                    String name = (saAcc.getFirstName()+ " " + saAcc.getLastName());
                                    String info =("\nDate of Birth: \t"+ saAcc.getDateOfBirth() + 
                                                  "\nIdentification Number: \t" + saAcc.getIdNumber()+ 
                                                  "\nAddress: \t" + saAcc.getAddress() + 
                                                  "\nPhone Number: \t" + saAcc.getPhoneNumber());
        
                                    int accInfoCh =(saAcc.getCheckingAccount().getAccountNumber());
                                    int accInfoSa =(saAcc.getSavingsAccount().getAccountNumber());
                                    int accInfoCr =(saAcc.getCreditAccount().getAccountNumber());
                                    double accBalCh = (saAcc.getCheckingAccount().getStartingBalance());
                                    double accBalSa = (saAcc.getSavingsAccount().getStartingBalance());
                                    double accBalCr = (saAcc.getCreditAccount().getStartingBalance());
                                    
                                    System.out.println("Name: \t" + name + info +
                                                       "\nChecking account Number & Balance: \t" + accInfoCh+ ", "+ accBalCh +
                                                       "\nSavings account Number & Balance: \t" + accInfoSa+ ", "+ accBalSa+
                                                       "\nCredit account Number & Balance: \t" + accInfoCr+ ", "+ accBalCr+"\n");
                                   }
                            }
                            if (isFound)
                                break;
                        }
                        if (!isFound){
                            System.out.println("Account not found. Please try again ");
                        }
                        break;
                    case 3: //credit was chosen
                    System.out.println("Please enter customer's credit account number. (4-Digit integer 3XXX)");
                        accNumInput = sc.nextInt();
                        if(accNumInput==-1)
                           throw new AccountExistenceException("Cannot inquire an account that does not exist.");
                        for (Customer crAcc : customerObject) {
                            if(accNumInput == crAcc.getCreditAccount().getAccountNumber()){ // if true, saves values on current iteration
                                isFound = true;
                                System.out.println("User found successfully.");
                                String name = (crAcc.getFirstName()+ " " + crAcc.getLastName());
                                String info =("\nDate of Birth: \t"+ crAcc.getDateOfBirth() + "\nIdentification Number: \t" + crAcc.getIdNumber()+ "\nAddress: \t" + crAcc.getAddress() + "\nPhone Number: \t" + crAcc.getPhoneNumber());
                                int accInfoCh =(crAcc.getCheckingAccount().getAccountNumber());
                                int accInfoSa =(crAcc.getSavingsAccount().getAccountNumber());
                                int accInfoCr =(crAcc.getCreditAccount().getAccountNumber());
                                double accBalCh = (crAcc.getCheckingAccount().getStartingBalance());
                                double accBalSa = (crAcc.getSavingsAccount().getStartingBalance());
                                double accBalCr = (crAcc.getCreditAccount().getStartingBalance());
                                System.out.println("Name: \t" + name + info +"\nChecking account Number & Balance: \t" + accInfoCh+ ", "+ accBalCh +"\nSavings account Number & Balance: \t" + accInfoSa+ ", "+ accBalSa+"\nCredit account Number & Balance: \t" + accInfoCr+ ", "+ accBalCr);
                            }
                            if(isFound)
                            break;
                        }
                        if(!isFound){
                            System.out.println("Account not found. Please try again.");
                        }   
                        break;
                    case 0://go back
                        System.out.println("Going back...\n");
                        loopCondition = false;
                        break;
                    }
                    if (loopCondition == true) {
                        System.out.println("\nPlease choose account type.");
                        menuObj.printAccountSelectionNoAll();
                        userChoice = sc.nextInt(); // saves user type selection
                        if (userChoice > 3 || userChoice < 0)
                            throw new OutOfRangeException("Please choose inside the range of selections.");
                    } else
                        break;
                }
            } catch (InputMismatchException e1) {
                System.out.println("Please enter an integer.");
            } catch (OutOfRangeException e2) {
                System.out.println("Please choose between the range of selections.");
            } catch (AccountExistenceException e3){
                System.out.println("Cannot inquire an account that does not exist.");
            }
        } while (loopCondition == true);
    }

    /**
     * Taken from Derek Aguirre
     * 
     * This method iterates through an ArrayList of type Customer to find the max checking account number on the input file.
     * 
     * @param bank ArrayList of type Customer
     * @return max
     */
    public int findMaxChecking(ArrayList<Customer> bank) {
        int max = -1;
        for (Customer iter : bank) {
            if (iter.getCheckingAccount().getAccountNumber() > max)
                max = iter.getCheckingAccount().getAccountNumber();
        }
        return max;
    }
    
    /**
     * Taken from Derek Aguirre
     * 
     * This method iterates through an ArrayList of type Customer to find the max savings account number on the input file.
     * 
     * @param bank ArrayList of type Customer
     * @return max
     */
    public int findMaxSavings(ArrayList<Customer> bank) {
        int max = -1;
        for (Customer iter : bank) {
            if (iter.getSavingsAccount().getAccountNumber() > max)
                max = iter.getSavingsAccount().getAccountNumber();
        }
        return max;
    }
    
    /**
     * 
     * Taken from Derek Aguirre
     * 
     * This method iterates through an ArrayList of type Customer to find the max credit account number on the input file.
     * 
     * @param bank ArrayList of type Customer
     * @return max
     */
    public int findMaxCredit(ArrayList<Customer> bank) {
        int max = -1;
        for (Customer iter : bank) {
            if (iter.getCreditAccount().getAccountNumber() > max)
                max = iter.getCreditAccount().getAccountNumber();
        }
        return max;
    }
    
    /**
     * Taken from Derek Aguirre
     * 
     * This method iterates through an ArrayList of type Customer to find the max id number on the input file.
     * 
     * @param bank ArrayList of type customer
     * @return max
     */
    public int findMaxID(ArrayList<Customer> bank) {
        int max = -1;
        for (Customer iter : bank) {
            if (iter.getIdNumber() > max)
                max = iter.getIdNumber();
        }
        return max;
    }
  
    /** 
     * Taken from Anthony Jasso
     * 
     * This method will read from an input file called "Transaction Actions.csv" and
     *  perform actions listed on the file in a quick succession.
     * 
     * @param fileName the file name
     * @param bank ArrayList of type Customer
     * @throws FileNotFoundException If the file is not found
     * @throws IOException Input Output Exception
     */
    public void transactionActions(String fileName, ArrayList<Customer> bank)
            throws FileNotFoundException, IOException { // Method that reads the customer's data from the file and stores it into an arraylist
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String st;
            String firstLine = br.readLine();
            String[] titles = firstLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            while ((st = br.readLine()) != null) { // While loop that checks if the line is empty
                String[] customerData = st.split(","); // Array of strings that contains the data of one customer

                if (customerData[findIndex(titles, "Action")].contains("pays")) {
                    String customerOne = customerData[findIndex(titles, "From First Name")] + " " + customerData[findIndex(titles, "From Last Name")];
                    String customerTwo = customerData[findIndex(titles, "To First Name")] + " " + customerData[findIndex(titles, "To Last Name")];
                    Customer sender = findAccountByName(bank, customerOne);
                    Customer receiver = findAccountByName(bank, customerTwo);

                    if (customerData[findIndex(titles, "From Where")].contains("Checking") && customerData[findIndex(titles, "To Where")].contains("Checking")) { // checking to checking
                        System.out.println(customerOne + " paid " + customerTwo + " $" + customerData[findIndex(titles, "Action Amount")] + " from Checking (" + sender.getCheckingAccount().getAccountNumber() + ")");
                        sender.getCheckingAccount().transferFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]),receiver.getCheckingAccount()); // Method pay someone is called and the user input is used for the parameter
                    }
                    else if (customerData[findIndex(titles, "From Where")].equals("Savings") && customerData[findIndex(titles, "To Where")].equals("Checking")) {
                        System.out.println(customerOne + " paid " + customerTwo + " $" + customerData[findIndex(titles, "Action Amount")] + " from Checking (" + sender.getSavingsAccount().getAccountNumber() + ")");
                        sender.getSavingsAccount().transferFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]), receiver.getCheckingAccount());
                    }

                }

                else if (customerData[findIndex(titles, "Action")].equals("transfers")) {
                    String customerOne = customerData[findIndex(titles, "From First Name")] + " " + customerData[findIndex(titles, "From Last Name")];
                    Customer disChar = findAccountByName(bank, customerOne);

                    if (customerData[findIndex(titles, "From Where")].equals("Checking") && customerData[findIndex(titles, "To Where")].equals("Savings")) {
                        System.out.println(customerOne + " transferred $" + customerData[findIndex(titles, "Action Amount")] + " from Checking (" + disChar.getCheckingAccount().getAccountNumber() + ") to Savings " + disChar.getSavingsAccount().getAccountNumber());
                        disChar.getCheckingAccount().transferFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]), disChar.getSavingsAccount()); // Method pay someone is called and the user input is used for the parameter
                    }
                     else if (customerData[findIndex(titles, "From Where")].equals("Checking") && customerData[findIndex(titles, "To Where")].equals("Credit")) {
                        System.out.println(customerOne + " transferred $" + customerData[findIndex(titles, "Action Amount")] + " from Checking (" + disChar.getCheckingAccount().getAccountNumber() + ") to Credit " + disChar.getSavingsAccount().getAccountNumber());
                        disChar.getCheckingAccount().transferFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]), disChar.getCreditAccount()); // Method pay someone is called and the user input is used for the parameter
                    }
                     else if (customerData[findIndex(titles, "From Where")].equals("Savings") && customerData[findIndex(titles, "To Where")].equals("Checking")) {
                        System.out.println(customerOne + " transferred $" + customerData[findIndex(titles, "Action Amount")] + " from Savings (" + disChar.getSavingsAccount().getAccountNumber() + ") to Savings " + disChar.getSavingsAccount().getAccountNumber());
                        disChar.getSavingsAccount().transferFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]),disChar.getCheckingAccount()); // Method pay someone is called and the user input is used for the parameter
                    }
                     else if (customerData[findIndex(titles, "From Where")].equals("Savings") && customerData[findIndex(titles, "To Where")].equals("Credit")) {
                        System.out.println(customerOne + " transferred $" + customerData[findIndex(titles, "Action Amount")] + " from Savings (" + disChar.getSavingsAccount().getAccountNumber() + ") to Credit " + disChar.getCreditAccount().getAccountNumber());
                        disChar.getSavingsAccount().transferFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]),disChar.getCreditAccount()); // Method pay someone is called and the user input is used for the parameter
                    }
                     else if (customerData[findIndex(titles, "From Where")].equals("Credit") && customerData[findIndex(titles, "To Where")].equals("Checking")) {
                        System.out.println(customerOne + " transferred $" + customerData[findIndex(titles, "Action Amount")] + " from Credit (" + disChar.getCreditAccount().getAccountNumber() + ") to Checking " + disChar.getCheckingAccount().getAccountNumber());
                        disChar.getCreditAccount().transferFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]),disChar.getCheckingAccount()); // Method pay someone is called and the user input is used for the parameter
                    }
                     else if (customerData[findIndex(titles, "From Where")].equals("Credit") && customerData[findIndex(titles, "To Where")].equals("Savings")) {
                        System.out.println(customerOne + " transferred $" + customerData[findIndex(titles, "Action Amount")] + " from Credit (" + disChar.getCreditAccount().getAccountNumber() + ") to Savings " + disChar.getSavingsAccount().getAccountNumber());
                        disChar.getCreditAccount().transferFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]),disChar.getSavingsAccount()); // Method pay someone is called and the user input is used for the parameter
                    }
                } 
                
                else if (customerData[findIndex(titles, "Action")].equals("inquires")) {
                    String customerOne = customerData[findIndex(titles, "From First Name")] + " " + customerData[findIndex(titles, "From Last Name")];
                    Customer disChar = findAccountByName(bank, customerOne);

                    if (customerData[findIndex(titles, "From Where")].equals("Checking"))
                        System.out.println(customerOne + " made a balance inquiry on Checking (" + disChar.getCheckingAccount().getAccountNumber() + ")");
                    else if (customerData[findIndex(titles, "From Where")].equals("Savings"))
                        System.out.println(customerOne + " made a balance inquiry on Saving (" + disChar.getSavingsAccount().getAccountNumber() + ")");
                    else if (customerData[findIndex(titles, "From Where")].equals("Credit"))
                        System.out.println(customerOne + " made a balance inquiry on Credit (" + disChar.getCreditAccount().getAccountNumber() + ")");
                } 
                
                else if (customerData[findIndex(titles, "Action")].equals("withdraws")) {
                    String customerOne = customerData[findIndex(titles, "From First Name")] + " " + customerData[findIndex(titles, "From Last Name")];
                    Customer disChar = findAccountByName(bank, customerOne);

                    if (customerData[findIndex(titles, "From Where")].equals("Checking")) {
                        System.out.println(customerOne + " withdrew $" + customerData[findIndex(titles, "Action Amount")] + " from Checking (" + disChar.getCheckingAccount().getAccountNumber() + ")");
                        disChar.getCheckingAccount().withdrawFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]));
                    } 
                    else if (customerData[findIndex(titles, "From Where")].equals("Savings")) {
                        System.out.println(customerOne + " withdrew $" + customerData[findIndex(titles, "Action Amount")] + " from Savings (" + disChar.getSavingsAccount().getAccountNumber() + ")");
                        disChar.getSavingsAccount().withdrawFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]));
                    } 
                    else if (customerData[findIndex(titles, "From Where")].equals("Credit")) {
                        System.out.println(customerOne + " withdrew $" + customerData[findIndex(titles, "Action Amount")] + " from Credit (" + disChar.getCreditAccount().getAccountNumber() + ")");
                        disChar.getCreditAccount().withdrawFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]));
                    }
                } 
                
                else if (customerData[findIndex(titles, "Action")].equals("deposits")) {
                    String customerTwo = customerData[findIndex(titles, "To First Name")] + " " + customerData[findIndex(titles, "To Last Name")];
                    Customer recipient = findAccountByName(bank, customerTwo);

                    if (customerData[findIndex(titles, "To Where")].equals("Checking")) {
                        System.out.println(customerTwo + " deposited " + customerData[findIndex(titles, "Action Amount")] + " to Checking (" + recipient.getCheckingAccount().getAccountNumber() + ")");
                        recipient.getCheckingAccount().depositFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]));
                    } 
                    else if (customerData[findIndex(titles, "To Where")].equals("Savings")) {
                        System.out.println(customerTwo + " deposited " + customerData[findIndex(titles, "Action Amount")] + " to Savings (" + recipient.getSavingsAccount().getAccountNumber() + ")");
                        recipient.getSavingsAccount().depositFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]));
                    } 
                    else if (customerData[findIndex(titles, "To Where")].equals("Credit")) {
                        System.out.println(customerTwo + " deposited " + customerData[findIndex(titles, "Action Amount")] + " to Credit (" + recipient.getCreditAccount().getAccountNumber() + ")");
                        recipient.getCreditAccount().depositFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]));
                    }
                }
            }
            generateUpdatedBalanceSheet(bank);
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please try again.\n");
        } catch (IOException e) {
            System.out.println("IOException\n");
        }
    }

    /**
     * Taken from Anthony Jasso
     * 
     * Method that finds customer's account in the arraylist given their name and returns said account
     * 
     * @param custList Data structure ArrayList of type customer that holds all customer information
     * @param custName The name of the user 
     * @return custList.get(i)
     */
    public static Customer findAccountByName(ArrayList<Customer> custList, String custName) throws NullPointerException{
        try{
         for (int i = 0; i < custList.size(); i++) { // For loop that traverses the arraylist
             String fullName = custList.get(i).getFirstName() + " " + custList.get(i).getLastName();
             
             if (fullName.equalsIgnoreCase(custName))
                 return custList.get(i); // Once found, returns the customer object
 
             
         }
         
        } catch (NullPointerException e){
            System.out.println("Element is null.");
        }
        return null; // If the account is not found, returns null
     }
 
     /**
      * Taken from Anthony Jasso
      * 
      * Method that finds customer's account in the arraylist given their account number and returns said account
      * 
      * @param custList Data structure ArrayList of type customer that holds all customer information
      * @param idNum The identification number of the customer
      * @return custList.get(i)
      */
     public static Customer findAccountById(ArrayList<Customer> custList, int idNum) throws NullPointerException{
         try{
          for (int i = 0; i < custList.size(); i++) { // For loop that traverses the arraylist
              if (idNum == custList.get(i).getIdNumber())
                  return custList.get(i); // Once found, returns the customer object
          }
          
         } catch (NullPointerException e){
             System.out.println("Element is null.");
         }
         return null; // If the account is not found, returns null
      }
 
     /**
      * 
      * Taken from Anthony Jasso
      * 
      * This method iterates through an array of strings to find which index the
      * input lies.
      * 
      * @param titles An array of strings that holds the titles of every category of user information.
      * @param input  Title for the category of user information.
      * @return indexNumber
      */
     public static int findIndex(String[] titles, String input) {
         int indexNumber = -1;
         for (int i = 0; i < titles.length; i++) {
             if (titles[i].equalsIgnoreCase(input))
                 indexNumber = i;
         }
         return indexNumber;
     }
 
     /** 
      * Taken from Derek Aguirre
      * <p>
      * This method stores the contents of the input file "CS 3331 - Bank Users 4.csv" to an ArrayList of type Customer. 
      * <br><br>It will utilize the ArrayList method add to push the information to the data structure. <br>
      * <br> The data is pushed according to the fields of the Customer constructor.
      * </p>
      * 
      * @param file the name of the file to read
      * @return bank
      * @throws IOException Input Output Exception
      * @throws FileNotFoundException File not found
      * @see Customer 
      */
     public static ArrayList<Customer> fileRead(String file) throws FileNotFoundException, IOException {
         ArrayList<Customer> bank = new ArrayList<Customer>(); // Create main list that will preserve the information
         BufferedReader buffReader = new BufferedReader(new FileReader(file));
 
         String firstLine = buffReader.readLine();
         String[] titles = firstLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
         String line;
         while ((line = buffReader.readLine()) != null) {
             String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // splits string every comma & ignores commas inside quotations
             if(file.equals("Updated Balance Sheet.csv")) {
                 //no checking
                 if(Integer.parseInt(tokens[findIndex(titles, "Checking Account Number")]) == -1 && Integer.parseInt(tokens[findIndex(titles, "Credit Account Number")]) != -1) {
                      Checking checkingAcc = new Checking(Integer.parseInt(tokens[findIndex(titles, "Checking Account Number")]),Double.parseDouble(tokens[findIndex(titles, "Checking Updated Balance")]));
                      Savings savingsAcc = new Savings(Integer.parseInt(tokens[findIndex(titles, "Savings Account Number")]),Double.parseDouble(tokens[findIndex(titles, "Savings Updated Balance")]));
                      Credit creditAcc = new Credit(Integer.parseInt(tokens[findIndex(titles, "Credit Account Number")]),Double.parseDouble(tokens[findIndex(titles, "Credit Updated Balance")]));
                      bank.add(new Customer(tokens[findIndex(titles, "First Name")], tokens[findIndex(titles, "Last Name")],
                           tokens[findIndex(titles, "Date Of Birth")],
                           tokens[findIndex(titles, "Address")],
                           tokens[findIndex(titles, "Phone Number")],
                           Integer.parseInt(tokens[findIndex(titles, "Identification Number")]),
                           tokens[findIndex(titles, "Password")],
                           checkingAcc, savingsAcc, creditAcc));
                 }
                 //no checking or credit
                 else if(Integer.parseInt(tokens[findIndex(titles, "Checking Account Number")]) == -1 && Integer.parseInt(tokens[findIndex(titles, "Credit Account Number")]) == -1) {
                      Checking checkingAcc = new Checking(-1,-1);
                      Savings savingsAcc = new Savings(Integer.parseInt(tokens[findIndex(titles, "Savings Account Number")]),Double.parseDouble(tokens[findIndex(titles, "Savings Updated Balance")]));
                      Credit creditAcc = new Credit(-1,1);
                      bank.add(new Customer(tokens[findIndex(titles, "First Name")], tokens[findIndex(titles, "Last Name")],
                           tokens[findIndex(titles, "Date Of Birth")],
                           tokens[findIndex(titles, "Address")],
                           tokens[findIndex(titles, "Phone Number")],
                           Integer.parseInt(tokens[findIndex(titles, "Identification Number")]),
                           tokens[findIndex(titles, "Password")],
                           checkingAcc, savingsAcc, creditAcc));
                 }
                 //no credit
                 else if(Integer.parseInt(tokens[findIndex(titles, "Checking Account Number")]) != -1 && Integer.parseInt(tokens[findIndex(titles, "Credit Account Number")]) == -1) {
                      Checking checkingAcc = new Checking(Integer.parseInt(tokens[findIndex(titles, "Checking Account Number")]),Double.parseDouble(tokens[findIndex(titles, "Checking Updated Balance")]));             
                      Savings savingsAcc = new Savings(Integer.parseInt(tokens[findIndex(titles, "Savings Account Number")]),Double.parseDouble(tokens[findIndex(titles, "Savings Updated Balance")]));
                      Credit creditAcc = new Credit(-1,1);
                      bank.add(new Customer(tokens[findIndex(titles, "First Name")], tokens[findIndex(titles, "Last Name")],
                           tokens[findIndex(titles, "Date Of Birth")],
                           tokens[findIndex(titles, "Address")],
                           tokens[findIndex(titles, "Phone Number")],
                           Integer.parseInt(tokens[findIndex(titles, "Identification Number")]),
                           tokens[findIndex(titles, "Password")],
                           checkingAcc, savingsAcc, creditAcc));
                 }
                 //all accounts exist
                 else {
                     Checking checkingAcc = new Checking(Integer.parseInt(tokens[findIndex(titles, "Checking Account Number")]),
                     Double.parseDouble(tokens[findIndex(titles, "Checking Updated Balance")]));
                                     
                     Savings savingsAcc = new Savings(Integer.parseInt(tokens[findIndex(titles, "Savings Account Number")]),
                     Double.parseDouble(tokens[findIndex(titles, "Savings Updated Balance")]));
                             
                     Credit creditAcc = new Credit(Integer.parseInt(tokens[findIndex(titles, "Credit Account Number")]),
                     Double.parseDouble(tokens[findIndex(titles, "Credit Updated Balance")]));
                                     
                     // The program will now save the information in accordance to their data types
                     bank.add(new Customer(tokens[findIndex(titles, "First Name")], 
                                           tokens[findIndex(titles, "Last Name")],
                                           tokens[findIndex(titles, "Date Of Birth")],
                                           tokens[findIndex(titles, "Address")],
                                           tokens[findIndex(titles, "Phone Number")],
                                           Integer.parseInt(tokens[findIndex(titles, "Identification Number")]),
                                           tokens[findIndex(titles, "Password")],
                                           checkingAcc, savingsAcc, creditAcc));
                 }
             }
             //CS 3331 - Bank Users 4.csv
             else {
                 Checking checkingAcc = new Checking(Integer.parseInt(tokens[findIndex(titles, "Checking Account Number")]),
                 Double.parseDouble(tokens[findIndex(titles, "Checking Starting Balance")]));
                         
                 Savings savingsAcc = new Savings(Integer.parseInt(tokens[findIndex(titles, "Savings Account Number")]),
                 Double.parseDouble(tokens[findIndex(titles, "Savings Starting Balance")]));
                 
                 Credit creditAcc = new Credit(Integer.parseInt(tokens[findIndex(titles, "Credit Account Number")]),
                 Double.parseDouble(tokens[findIndex(titles, "Credit Starting Balance")]));
                         
                 // The program will now save the information in accordance to their data types
                 bank.add(new Customer(tokens[findIndex(titles, "First Name")], tokens[findIndex(titles, "Last Name")],
                 tokens[findIndex(titles, "Date Of Birth")],
                 tokens[findIndex(titles, "Address")],
                 tokens[findIndex(titles, "Phone Number")],
                 Integer.parseInt(tokens[findIndex(titles, "Identification Number")]),
                 tokens[findIndex(titles, "Password")],
                 checkingAcc, savingsAcc, creditAcc));
             }
         }
         buffReader.close();
         return bank;
     }
  
     /** 
      * 
      * Taken from Derek Aguirre
      * 
      * Will generate a file "Updated Balance Sheet.csv" that will reflect the
      * updated account balances of every customer. This method is executed if a
      * customer chooses the log out selection on the main directory. The balance
      * sheet will show all information about every user.
      * 
      * @param customers An array list of type customer
      * @throws IOException           InputOutputException
      * @throws FileNotFoundException If file is not found it can also be thrown if
      *                               the file is open when trying to be written to.
      */
     public static void generateUpdatedBalanceSheet(ArrayList<Customer> customers) throws IOException, FileNotFoundException {
         try {
             FileWriter fw = new FileWriter("Updated Balance Sheet.csv", false);
             fw.write("First Name,Last Name,Date Of Birth,Identification Number,Address,Phone Number,Password,Checking Account Number,Savings Account Number," + 
                      "Credit Account Number,Checking Updated Balance,Savings Updated Balance,Credit Updated Balance");
 
             for (Customer iter : customers) {
                 fw.append(String.valueOf("\n" + iter.getFirstName()) + ",");
                 fw.append(String.valueOf(iter.getLastName()) + ",");
                 fw.append(String.valueOf(iter.getDateOfBirth()) + ",");
                 fw.append(String.valueOf(iter.getIdNumber()) + ",");
                 fw.append(String.valueOf(iter.getAddress()) + ",");
                 fw.append(String.valueOf(iter.getPhoneNumber()) + ",");
                 fw.append(String.valueOf(iter.getPassword() + ","));
                 fw.append(String.valueOf(iter.getCheckingAccount().getAccountNumber()) + ",");
                 fw.append(String.valueOf(iter.getSavingsAccount().getAccountNumber()) + ",");
                 fw.append(String.valueOf(iter.getCreditAccount().getAccountNumber()) + ",");
                 fw.append(String.valueOf(iter.getCheckingAccount().getStartingBalance()) + ",");
                 fw.append(String.valueOf(iter.getSavingsAccount().getStartingBalance()) + ",");
                 fw.append(String.valueOf(iter.getCreditAccount().getStartingBalance()) + ",");
                 
             }
             fw.close();
         } catch (FileNotFoundException e) {
             System.out.print("Error retrieving file, please make sure it is closed before writing to it again.");
         } catch (IOException e2) {
             System.out.print(e2);
         }
     }
}