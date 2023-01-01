import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * 
 *  Taken from Derek Aguirre 
 * 
 * The UserConsole class will be used hold all the user choice methods.
 *
 * @author Derek Aguirre
 * @version 2.0
 * @since 10/30/2020
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
     * @param checkingAcc An object of Checking, holds info stored about a customer's checking account
     * @param savingsAcc An object of Savings, holds info stored about a customer's savings account
     * @param creditAcc An object of Credit, holds info stored about a customer's credit account
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @return x, a loop condition for the method that calls it
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws OutOfRangeException If the user inputs a number outside the range of selections
     * @throws FileNotFoundException If file is not found
     * @throws IOException InputOutputException
     * @throws AccountExistenceException If the user does not have acccess to a specific account
     * 
     */
    public int inquireBalanceChoice(Checking checkingAcc, Savings savingsAcc, Credit creditAcc, String firstName, String lastName) throws InputMismatchException, OutOfRangeException, FileNotFoundException, IOException, AccountExistenceException{
        int x = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Which account balance would you like to see?");
                Menu.printAccountSelectionAll();
                int userChoice = sc.nextInt();
                if (userChoice > 4 || userChoice < 0)
                    throw new OutOfRangeException("Out of range");
                while (userChoice <= 4 || userChoice >= 0) {
                    switch (userChoice) {
                        case 1:
                         if(checkingAcc.getAccountNumber()== -1 && creditAcc.getAccountNumber()== -1) //only savings exists
                            System.out.println(firstName + " " + lastName + "'s Savings Account ("+ savingsAcc.getAccountNumber() + ") Balance: " + savingsAcc.getStartingBalance());

                         else if(checkingAcc.getAccountNumber()== -1 && creditAcc.getAccountNumber()!=-1){ //checking acc does not exist
                                System.out.println(firstName + " " + lastName + "'s Credit Account ("+ creditAcc.getAccountNumber() + ") Balance: " + creditAcc.getStartingBalance());
                                System.out.println(firstName + " " + lastName + "'s Savings Account ("+ savingsAcc.getAccountNumber() + ") Balance: " + savingsAcc.getStartingBalance());
                            }
                         else if(creditAcc.getAccountNumber()== -1 && checkingAcc.getAccountNumber()!=-1){ //credit acc does not exist
                                System.out.println(firstName + " " + lastName + "'s Checking Account ("+ checkingAcc.getAccountNumber() + ") Balance: "+ checkingAcc.getStartingBalance());
                                System.out.println(firstName + " " + lastName + "'s Savings Account ("+ savingsAcc.getAccountNumber() + ") Balance: " + savingsAcc.getStartingBalance());
                            }

                            else if (checkingAcc.getAccountNumber() != -1 && creditAcc.getAccountNumber() != -1) {
                                System.out.println(firstName + " " + lastName + "'s Checking Account ("+ checkingAcc.getAccountNumber() + ") Balance: "+ checkingAcc.getStartingBalance());
                                System.out.println(firstName + " " + lastName + "'s Savings Account ("+ savingsAcc.getAccountNumber() + ") Balance: "+ savingsAcc.getStartingBalance());
                                System.out.println(firstName + " " + lastName + "'s Credit Account ("+ creditAcc.getAccountNumber() + ") Balance: "+ creditAcc.getStartingBalance());
                                singletonLogger.inquireBalanceLog(checkingAcc, savingsAcc, creditAcc, firstName, lastName);
                            }
                            break;
                        case 2:
                        if(checkingAcc.getAccountNumber()== -1) //checking acc does not exist
                            throw new AccountExistenceException("You do not have this type of account.");
                            singletonLogger.inquireBalanceLog(checkingAcc, firstName, lastName);
                            System.out.println(firstName + " " + lastName + "'s Checking Account ("+ checkingAcc.getAccountNumber() + ") Balance: "+ checkingAcc.getStartingBalance());
                            break;
                        case 3:
                            singletonLogger.inquireBalanceLog(savingsAcc, firstName, lastName);
                            System.out.println(firstName + " " + lastName + "'s Savings Account ("+ savingsAcc.getAccountNumber() + ") Balance: " + savingsAcc.getStartingBalance());
                            break;
                        case 4:
                        if(creditAcc.getAccountNumber()== -1) //checking acc does not exist
                            throw new AccountExistenceException("You do not have this type of account.");
                            singletonLogger.inquireBalanceLog(creditAcc, firstName, lastName);
                            System.out.println(firstName + " " + lastName + "'s Credit Account ("+ creditAcc.getAccountNumber() + ") Balance: " + creditAcc.getStartingBalance());
                            break;
                        case 0:
                            System.out.println("Going back...\n");
                            x = 1;
                            break;
                    }
                    if (x == 0) {
                        System.out.println("Which account balance would you like to see?");
                        Menu.printAccountSelectionAll();
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
            } catch (AccountExistenceException e5){
                System.out.println("You do not have this type of account.");
            }
        } while (x == 0);
        if (x == 1)
            return 1;
        return 0;
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
     * @param checkingAcc An object of Checking, holds info stored about a customer's checking account
     * @param savingsAcc An object of Savings, holds info stored about a customer's savings account
     * @param creditAcc An object of Credit, holds info stored about a customer's credit account
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @return x, a loop condition for the method that calls it
     * @throws NegativeNumberException If the customer inputs a negative number
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws SubtractionOverflowException If the resulting operation yields the opposite signed value
     * @throws FileNotFoundException If file is not found
     * @throws IOException InputOutputException
     * @throws AccountExistenceException If the user does not have acccess to a specific account
     * 
     */
    public int depositChoice(Checking checkingAcc, Savings savingsAcc, Credit creditAcc, String firstName, String lastName) throws NegativeNumberException, InputMismatchException, SubtractionOverflowException, FileNotFoundException, IOException, AccountExistenceException{
        int x = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Choose account to deposit to.");
                Menu.printAccountSelectionNoAll();
                int userChoice = sc.nextInt();
                if (userChoice > 3 || userChoice < 0)
                    throw new OutOfRangeException("Out of range");
                while (userChoice <= 3 || userChoice >= 0) {
                    switch (userChoice) {
                        
                        case 1:// deposit to checking
                            if(checkingAcc.getAccountNumber() == -1) //if acc does not exist
                                throw new AccountExistenceException("Account does not exist");
                                
                                sc = new Scanner(System.in);
                                System.out.println("You have chosen your checking account. How much would you like to deposit?");
                                double depositAmount = sc.nextDouble(); // asks user to input amount
                                if (depositAmount < 0)
                                    throw new NegativeNumberException("Cannot deposit a negative amount.");
                                System.out.println("\nDepositing $" + depositAmount + " into Checking Account (" + checkingAcc.getAccountNumber() + ")"); // confirmation on amount
                                double initialBalanceCh = checkingAcc.getStartingBalance(); // Saves user's initial balance before the transaction
                                checkingAcc.depositFunction(depositAmount);
                                System.out.println("\nUpdated Balance: $" + checkingAcc.getStartingBalance() + "\nInitial Balance: $" + initialBalanceCh + "\n");
                                singletonLogger.depositLog(checkingAcc, firstName, lastName, depositAmount, initialBalanceCh, checkingAcc.getStartingBalance());

                            break;
                        case 2:// deposit to savings
                            sc = new Scanner(System.in);
                            System.out.println("You have chosen your savings account. How much would you like to deposit?");
                            depositAmount = sc.nextDouble(); // asks user to input amount
                            if (depositAmount < 0)
                                throw new NegativeNumberException("Cannot deposit a negative amount.");
                            System.out.println("\nDepositing $" + depositAmount); // confirmation on amount
                            double initialBalanceSa = savingsAcc.getStartingBalance(); // Saves user's initial balance before the transaction
                            savingsAcc.depositFunction(depositAmount);
                            System.out.println("\nUpdated Balance: $" + savingsAcc.getStartingBalance() + "\nInitial Balance: $" + initialBalanceSa + "\n");
                            singletonLogger.depositLog(savingsAcc, firstName, lastName, depositAmount, initialBalanceSa, savingsAcc.getStartingBalance());
                            break;
                        case 3:// deposit to credit
                            if (creditAcc.getAccountNumber() == -1) // if acc does not exist
                                throw new AccountExistenceException("Account does not exist");

                            sc = new Scanner(System.in);
                            System.out.println("You have chosen your credit account. How much would you like to deposit?");
                            depositAmount = sc.nextDouble(); // asks user to input amount
                            if (depositAmount + creditAcc.getStartingBalance() > 0)
                                throw new SubtractionOverflowException("Cannot deposit more than current balance.");
                            if (depositAmount < 0)
                                throw new NegativeNumberException("Cannot deposit a negative amount.");
                            System.out.println("\nDepositing $" + depositAmount); // confirmation on amount
                            double initialBalanceCr = creditAcc.getStartingBalance(); // Saves user's initial balance before the transaction
                            creditAcc.depositFunction(depositAmount);
                            System.out.println("\nUpdated Balance: $" + creditAcc.getStartingBalance() + "\nInitial Balance: $" + initialBalanceCr + "\n");
                            singletonLogger.depositLog(creditAcc, firstName, lastName, depositAmount, initialBalanceCr, creditAcc.getStartingBalance());
                            break;
                        case 0:// go back
                            System.out.println("Going back...\n");
                            x = 1;
                            break;
                    }
                    if (x == 0) {// if x is 0 then it can loop again
                        System.out.println("Choose an account to deposit to.");
                        Menu.printAccountSelectionNoAll();
                        userChoice = sc.nextInt(); // saves user input
                        if (userChoice > 3 || userChoice < 0)
                            throw new OutOfRangeException("Please select a value inside the range.");
                    } else
                        break;
                }
            } catch (NegativeNumberException e) {
                System.out.println("Cannot deposit a negative amount.");
            } catch (InputMismatchException e2) {
                System.out.println("Please enter a number.");
            } catch (SubtractionOverflowException e3) {
                System.out.println("Cannot enter more money than currently held in your balance.");
            } catch (FileNotFoundException e4) {
                System.out.println("File not found.");
            } catch (IOException e5) {
                System.out.println("IOException");
            } catch (OutOfRangeException e6) {
                System.out.println("Please choose between the range of selections.");
            } catch (AccountExistenceException e7){
                System.out.println("You do not have that type of account.");
            }
        } while (x == 0);
        if (x == 1)
            return 1;
        return 0;
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
     * @param checkingAcc An object of Checking, holds info stored about a customer's checking account
     * @param savingsAcc An object of Savings, holds info stored about a customer's savings account
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @return x, a loop condition for the method that calls it
     * @throws NegativeNumberException If the customer inputs a negative number
     * @throws SubtractionOverflowException If the resulting operation yields the opposite signed value
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws OutOfRangeException If the user inputs a number outside the range of selections
     * @throws FileNotFoundException If file is not found
     * @throws IOException InputOutputException
     * @throws AccountExistenceException If the user does not have acccess to a specific account
     */
    public int withdrawChoice(Checking checkingAcc, Savings savingsAcc, String firstName, String lastName) throws NegativeNumberException, SubtractionOverflowException, InputMismatchException, OutOfRangeException, FileNotFoundException, IOException, AccountExistenceException{
        int x = 0;
        do{
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println("Choose account to withdraw from.");
                Menu.printAccountSelectionNoAllNoCredit();
                int userChoice = sc.nextInt();
                if (userChoice > 2 || userChoice < 0)
                    throw new OutOfRangeException("Out of range");
                while (userChoice <= 2 || userChoice >= 0) {
                    switch (userChoice) { 
                        case 1:
                            if (checkingAcc.getAccountNumber() == -1) // if checking acc does not exist
                                throw new AccountExistenceException("Account does not exist");
                            sc = new Scanner(System.in);
                            System.out.println("You have chosen to withdraw from your checking account. Please enter amount.");
                            double withdrawAmount = sc.nextDouble(); // asks user to input amount
                            if (withdrawAmount > checkingAcc.getStartingBalance())
                                throw new SubtractionOverflowException("Cannot withdraw more than current balance.");
                            if (withdrawAmount < 0)
                                throw new NegativeNumberException("Cannot withdraw a negative amount.");
                            System.out.println("\nWithdrawing $" + withdrawAmount + " from Checking Account ("+ checkingAcc.getAccountNumber() + ")"); // confirmation on amount
                            double initialBalanceCh = checkingAcc.getStartingBalance(); // Saves user's initial balance before the transaction
                            checkingAcc.withdrawFunction(withdrawAmount);
                            System.out.println("\nUpdated Balance: $" + checkingAcc.getStartingBalance()+ "\nInitial Balance: $" + initialBalanceCh + "\n");
                            singletonLogger.withdrawLog(checkingAcc, initialBalanceCh, firstName, lastName, withdrawAmount, checkingAcc.getStartingBalance());
                            break;
                        case 2:
                            sc = new Scanner(System.in);
                            System.out.println("You have chosen to withdraw from your savings account. Please enter amount.");
                            withdrawAmount = sc.nextDouble(); //asks user to input amount
                            if (withdrawAmount > savingsAcc.getStartingBalance())
                                throw new SubtractionOverflowException("Cannot withdraw more than current balance.");
                            if (withdrawAmount < 0)
                                throw new NegativeNumberException("Cannot withdraw a negative amount.");
                            System.out.println("\nWithdrawing $" + withdrawAmount +" from Checking Account (" + savingsAcc.getAccountNumber() + ")"); //confirmation on amount
                            double initialBalanceSa = savingsAcc.getStartingBalance(); //Saves user's initial balance before the transaction
                            savingsAcc.withdrawFunction(withdrawAmount);
                            System.out.println("\nUpdated Balance: $" + savingsAcc.getStartingBalance() +"\nInitial Balance: $"+ initialBalanceSa + "\n");
                            singletonLogger.withdrawLog(savingsAcc, initialBalanceSa, firstName, lastName, withdrawAmount, savingsAcc.getStartingBalance());
                            break;
                        case 0://go back
                            System.out.println("Going back...\n");
                            x = 1;
                            break;
                    }
                    if (x == 0) {// if 0 then loop
                        System.out.println("Choose an account to withdraw from.");
                        Menu.printAccountSelectionNoAllNoCredit();
                        userChoice = sc.nextInt(); // saves user input
                        if (userChoice > 2 || userChoice < 0)
                            throw new OutOfRangeException("Please enter a number inside the selection range");
                    } 
                    else
                        break;
                }
            } catch (NegativeNumberException e) {
                System.out.println("You cannot withdraw a negative amount.");
            } catch (SubtractionOverflowException e2) {
                System.out.println("You cannot withdraw more than current balance.");
            } catch (InputMismatchException e3) {
                System.out.println("Please enter a number.");
            } catch (OutOfRangeException e4) {
                System.out.println("Please choose between the range of selections.");
            } catch (FileNotFoundException e5) {
                System.out.println("File not found.");
            } catch (IOException e6) {
                System.out.println("IOException");
            } catch (AccountExistenceException e7){
                System.out.println("You do not have that type of account.");
            }
        } while (x == 0);
        if (x == 1)
            return 1;
        return 0;
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
     * @param checkingAcc An object of Checking, holds info stored about a customer's checking account
     * @param savingsAcc An object of Savings, holds info stored about a customer's savings account
     * @param creditAcc An object of Credit, holds info stored about a customer's credit account
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @return x, a loop condition for the method that calls it
     * @throws NegativeNumberException If the customer inputs a negative number
     * @throws SubtractionOverflowException If the resulting operation yields the opposite signed value
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws FileNotFoundException If file is not found
     * @throws IOException Input Output Exception
     * @throws OutOfRangeException If the choice that is made is out of the range of selections
     * @throws AccountExistenceException If the user does not have acccess to a specific account
     */
    public int transferChoice(Checking checkingAcc, Savings savingsAcc, Credit creditAcc, String firstName, String lastName) throws NegativeNumberException, SubtractionOverflowException, InputMismatchException, FileNotFoundException, IOException, OutOfRangeException, AccountExistenceException{
        int x = 0;
        do{
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println("Choose account to transfer funds from.");
                Menu.printAccountSelectionNoAllNoCredit();
                int userChoice = sc.nextInt();
                if (userChoice > 2 || userChoice < 0)
                    throw new OutOfRangeException("Out of range");
                while (userChoice <= 2 || userChoice >= 0) {
                    switch (userChoice) { // choose to transfer from checking or savings
                        case 1:// transfer from checking
                            if (checkingAcc.getAccountNumber() == -1) // if acc does not exist
                                throw new AccountExistenceException("Account does not exist");
                            System.out.println("You have chosen to transfer from your checking account.");
                            transferChoiceNest1(checkingAcc, savingsAcc, creditAcc, firstName, lastName);
                            break;
                        case 2:// transfer from savings
                            System.out.println("You have chosen to transfer from your savings account.");
                            transferChoiceNest2(checkingAcc, savingsAcc, creditAcc, firstName, lastName);
                            break;
                        case 0://go back
                            System.out.println("Going back...\n");
                            x=1;
                            break;
                    }
                    if (x == 0) {//if x is 0 then it can loop again
                        System.out.println("Choose account to transfer funds from.");
                        Menu.printAccountSelectionNoAllNoCredit();
                        userChoice = sc.nextInt(); // saves user input
                        if (userChoice > 2 || userChoice < 0)
                            throw new OutOfRangeException("Please enter a number inside selection range");
                    }
                    else
                    break;
                }
            } catch (NegativeNumberException e1){
                System.out.println("You cannot withdraw a negative amount. Please try again.");
            } catch (SubtractionOverflowException e2){
                System.out.println("You cannot withdraw more than current balance. Please try again.");
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
        } while (x == 0);
        if (x == 1)
            return 1;
        return 0;

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
     * @param checkingAcc An object of Checking, holds info stored about a customer's checking account
     * @param savingsAcc An object of Savings, holds info stored about a customer's savings account
     * @param creditAcc An object of Credit, holds info stored about a customer's credit account
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @return x, a loop condition for the method that calls it
     * @throws NegativeNumberException If the customer inputs a negative number
     * @throws SubtractionOverflowException If the resulting operation yields the opposite signed value
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws OutOfRangeException If the user inputs a number outside the range of selections
     * @throws FileNotFoundException If file is not found
     * @throws IOException InputOutputException
     * @throws AccountExistenceException If the user does not have acccess to a specific account
     * 
     */
    public int transferChoiceNest1(Checking checkingAcc, Savings savingsAcc, Credit creditAcc, String firstName, String lastName) throws NegativeNumberException, SubtractionOverflowException, InputMismatchException, OutOfRangeException, FileNotFoundException, IOException, AccountExistenceException{
        int x = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Which account will receive the funds?");
                Menu.printAccountSelectionNoAllNoChecking();
                int nestedChoice = sc.nextInt();
                if (nestedChoice > 2 || nestedChoice < 0)
                    throw new OutOfRangeException("Out of range");
                while (nestedChoice <= 2 || nestedChoice >= 0) { // in range
                    switch (nestedChoice) {
                        case 1:
                            if(checkingAcc.getAccountNumber() == -1)
                                throw new AccountExistenceException("This account does not exist");
                            
                            System.out.println("You have chosen to transfer from your Checking account to your Savings account.\nPlease enter transfer amount.");
                            double transferAmount = sc.nextDouble();
                            if (transferAmount > checkingAcc.getStartingBalance())
                                throw new SubtractionOverflowException("Cannot withdraw more than current balance.");
                            else if (transferAmount < 0)
                                throw new NegativeNumberException("Cannot enter negative amount.");
                            double initialBalanceCh = checkingAcc.getStartingBalance(); // save initial checking bal
                            double initialBalanceSa = savingsAcc.getStartingBalance();  // save initial savings bal
                            checkingAcc.withdrawFunction(transferAmount); //withdraw checking
                            savingsAcc.depositFunction(transferAmount); //deposit savings

                            System.out.println("Successfully transferred $" + transferAmount+ 
                                               " from Checking Account (" + checkingAcc.getAccountNumber() + 
                                               ") to Savings Account (" + savingsAcc.getAccountNumber() + ")\n"); // confirmation

                            System.out.println("Checking Account Updated Balance: $" + checkingAcc.getStartingBalance() + "\t Checking Account Initial Balance: $" + initialBalanceCh);
                            System.out.println("Savings Account Updated Balance $" + savingsAcc.getStartingBalance() + "\t Savings Account Initial Balance $" + initialBalanceSa);

                            singletonLogger.transferLogFromChecking(checkingAcc, savingsAcc, initialBalanceCh, initialBalanceSa,
                                                                    firstName, lastName, transferAmount, checkingAcc.getStartingBalance(),
                                                                    savingsAcc.getStartingBalance());
                            break;
                        case 2:// into credit
                        if (creditAcc.getAccountNumber() == -1) // if acc does not exist
                                throw new AccountExistenceException("Account does not exist");
                            System.out.println("You have chosen to transfer from your Checking account to your Credit account.\nPlease enter transfer amount.");
                            transferAmount = sc.nextDouble();
                            if (transferAmount > checkingAcc.getStartingBalance())
                                throw new SubtractionOverflowException("Cannot withdraw more than current balance.");
                            else if (transferAmount < 0)
                                throw new NegativeNumberException("Cannot enter negative amount.");
                            initialBalanceCh = checkingAcc.getStartingBalance(); //saves initial checking bal
                            double initialBalanceCr = creditAcc.getStartingBalance(); //saves initial credit bal
                            checkingAcc.withdrawFunction( transferAmount); //withdraw checking
                            creditAcc.depositFunction( transferAmount); //deposit credit
                            System.out.println("Successfully transferred $" + transferAmount + 
                                               " from Checking Account (" + checkingAcc.getAccountNumber() + 
                                               ") to Credit Account (" + creditAcc.getAccountNumber() + ")\n"); // confirmation

                            System.out.println("Checking account funds updated to $" + checkingAcc.getStartingBalance() + " from the initial balance of $" + initialBalanceCh);
                            System.out.println("Credit account funds updated to $" + creditAcc.getStartingBalance() + " from the initial balance of $" + initialBalanceCr);
                            singletonLogger.transferLogFromChecking(checkingAcc, creditAcc, initialBalanceCh, initialBalanceCr,
                                                                    firstName, lastName, transferAmount, checkingAcc.getStartingBalance(),
                                                                    creditAcc.getStartingBalance());
                            break;
                        case 0:// go back
                            System.out.println("Going back...\n");
                            x = 1;
                            break;
                    }
                    if (x == 0) {// if x is 0 then it can loop again
                        System.out.println("Which account will receive the funds?");
                        Menu.printAccountSelectionNoAllNoChecking();
                        nestedChoice = sc.nextInt(); // saves user input
                        if (nestedChoice > 2 || nestedChoice < 0)
                            throw new OutOfRangeException("Please select a value inside the range.");
                    } else
                        break;
                }
            } catch (NegativeNumberException e) {
                System.out.println("You cannot withdraw a negative amount. Please try again.");
            } catch (SubtractionOverflowException e2) {
                System.out.println("You cannot withdraw more than current balance. Please try again.");
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
        } while (x == 0);
        if (x == 1)
            return 1;
        return 0;
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
     * @param checkingAcc An object of Checking, holds info stored about a customer's checking account
     * @param savingsAcc An object of Savings, holds info stored about a customer's savings account
     * @param creditAcc An object of Credit, holds info stored about a customer's credit account
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @return x, a loop condition for the method that calls it
     * @throws NegativeNumberException If the customer inputs a negative number
     * @throws SubtractionOverflowException If the resulting operation yields the opposite signed value
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws OutOfRangeException If the user inputs a number outside the range of selections
     * @throws FileNotFoundException If file is not found
     * @throws IOException InputOutputException
     * @throws AccountExistenceException If the user does not have acccess to a specific account
     */
    public int transferChoiceNest2(Checking checkingAcc, Savings savingsAcc, Credit creditAcc, String firstName, String lastName) throws NegativeNumberException, SubtractionOverflowException, InputMismatchException, OutOfRangeException, FileNotFoundException, IOException, AccountExistenceException{
        int x = 0;
        do{
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println("Which account will receive the funds?");
                Menu.printAccountSelectionNoAllNoSavings();
                int nestedChoice = sc.nextInt();
                if (nestedChoice > 2 || nestedChoice < 0)
                    throw new OutOfRangeException("Out of range");
                while (nestedChoice <= 2 || nestedChoice >= 0) { // in range
                    switch (nestedChoice) {
                        case 1:
                            if (checkingAcc.getAccountNumber() == -1) // if acc does not exist
                                throw new AccountExistenceException("Account does not exist");
                            System.out.println("You have chosen to transfer from your Savings Account to your Checking account.\nPlease enter transfer amount.");
                            double transferAmount = sc.nextDouble();
                            if (transferAmount > savingsAcc.getStartingBalance())
                                throw new SubtractionOverflowException("Cannot withdraw more than current balance.");
                            else if (transferAmount < 0)
                                throw new NegativeNumberException("Cannot enter negative amount.");
                            double initialBalanceSa = savingsAcc.getStartingBalance(); //saves savings initial bal
                            double initialBalanceCh = checkingAcc.getStartingBalance();//saves checking initial bal
                        savingsAcc.withdrawFunction (transferAmount); //withdraw savings
                        checkingAcc.depositFunction (transferAmount); //deposit checking
                            System.out.println("Successfully transferred $" + transferAmount + 
                                               " from Savings Account (" + savingsAcc.getAccountNumber() + 
                                               ") to Checking Account ("+ checkingAcc.getAccountNumber() + ")\n"); // confirmation

                            System.out.println("Savings account funds updated to $" + savingsAcc.getStartingBalance() + 
                                               " from the initial balance of $" + initialBalanceSa);

                            System.out.println("Checking account funds updated to $" + checkingAcc.getStartingBalance() +
                                               " from the initial balance of $" + initialBalanceCh);

                            singletonLogger.transferLogFromSavings(savingsAcc, checkingAcc, initialBalanceSa, initialBalanceCh,
                                                                   firstName, lastName, transferAmount, savingsAcc.getStartingBalance(),
                                                                   checkingAcc.getStartingBalance());                   
                            break;
                        case 2:
                            if (creditAcc.getAccountNumber() == -1) // if acc does not exist
                                throw new AccountExistenceException("Account does not exist");
                            System.out.println("You have chosen to transfer from your Savings Account to your Credit account.\nPlease enter transfer amount.");
                            transferAmount = sc.nextDouble();
                            if (transferAmount > savingsAcc.getStartingBalance())
                                throw new SubtractionOverflowException("Cannot withdraw more than current balance.");
                            else if (transferAmount < 0)
                                throw new NegativeNumberException("Cannot enter negative amount.");
                            initialBalanceSa = savingsAcc.getStartingBalance(); // saves initial savings bal
                            double initialBalanceCr = creditAcc.getStartingBalance(); //saves initial credit bal
                            savingsAcc.withdrawFunction(transferAmount);// withdraw savings
                            creditAcc.depositFunction(transferAmount); //deposit credit
                            System.out.println("Successfully transferred $" + transferAmount + 
                                               " from Savings Account ("+ savingsAcc.getAccountNumber() + 
                                               ") to Credit Account (" + creditAcc.getAccountNumber() + ")\n"); // confirmation

                            System.out.println("Savings account funds updated to $" + savingsAcc.getStartingBalance()+ " from the initial balance of $" + initialBalanceSa);
                            System.out.println("Credit account funds updated to $" + creditAcc.getStartingBalance()+ " from the initial balance of $" + initialBalanceCr);
                            singletonLogger.transferLogFromSavings(savingsAcc, creditAcc, initialBalanceSa, initialBalanceCr,
                                                          firstName, lastName, transferAmount, savingsAcc.getStartingBalance(),
                                                          creditAcc.getStartingBalance());
                            break;
                        case 0:
                            System.out.println("Going back...\n");
                            x = 1;
                            break;
                    }
                    if (x == 0) {// if x is 0 then it can loop again
                        System.out.println("Which account will receive the funds?");
                        Menu.printAccountSelectionNoAllNoSavings();
                        nestedChoice = sc.nextInt(); // saves user input
                        if (nestedChoice > 2 || nestedChoice < 0)
                            throw new OutOfRangeException("Please select a value inside the range.");
                    } else
                        break;
                }
            } catch (NegativeNumberException e) {
                System.out.println("You cannot withdraw a negative amount. Please try again.");
            } catch (SubtractionOverflowException e2) {
                System.out.println("You cannot withdraw more than current balance. Please try again.");
            } catch (InputMismatchException e3) {
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
        } while (x == 0);
        if (x == 1)
            return 1;
        return 0;
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
     * @param checkingAcc1 An object of Checking, holds info stored about the first customer's checking account
     * @param savingsAcc1 An object of Savings, holds info stored about the first customer's savings account
     * @param creditAcc1 An object of Credit, holds info stored about the first customer's credit account
     * @param checkingAcc2 An object of Checking, holds info stored about the second customer's checking account
     * @param savingsAcc2 An object of Savings, holds info stored about the second customer's savings account
     * @param creditAcc2 An object of Credit, holds info stored about the second customer's credit account
     * @param firstName1 The first name of the first customer
     * @param firstName2 The first name of the second customer
     * @param lastName1 The last name of the first customer
     * @param lastName2 The last name of the second customer
     * @return x, a loop condition for the method that calls it
     * @throws NegativeNumberException If the customer inputs a negative number
     * @throws SubtractionOverflowException If the resulting operation yields the opposite signed value
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws OutOfRangeException If the user inputs a number outside the range of selections
     * @throws FileNotFoundException If file is not found
     * @throws IOException InputOutputException
     * @throws AccountExistenceException If the user does not have access to a specific type of account
     */
    public int payNest(Checking checkingAcc1, Savings savingsAcc1, Credit creditAcc1, Checking checkingAcc2, Savings savingsAcc2, Credit creditAcc2, String firstName1, String firstName2, String lastName1, String lastName2) throws NegativeNumberException, SubtractionOverflowException, InputMismatchException, OutOfRangeException, FileNotFoundException, IOException, AccountExistenceException {
        int x = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Which account will receive the funds?");
                Menu.printAccountSelectionNoAllNoCredit();
                int nestedChoice = sc.nextInt();
                if (nestedChoice > 2 || nestedChoice < 0)
                    throw new OutOfRangeException("Out of range");
                while (nestedChoice <= 2 || nestedChoice >= 0) {
                    switch (nestedChoice) { 
                        case 1:
                            if (checkingAcc1.getAccountNumber() == -1) // if acc does not exist
                                throw new AccountExistenceException("Account does not exist");
                            System.out.println("You have chosen: Pay from checking to checking.\nPlease enter pay amount.");
                            double payAmount = sc.nextDouble();
                            if(payAmount > checkingAcc1.getStartingBalance())
                                throw new SubtractionOverflowException("Cannot withdraw more than current balance.");
                            else if (payAmount < 0)
                                throw new NegativeNumberException("Cannot enter negative amount.");
                            double initialBalanceCh1 = checkingAcc1.getStartingBalance(); //saves initial balance of both users
                            double initialBalanceCh2 = checkingAcc2.getStartingBalance(); 
                            checkingAcc1.transferFunction(payAmount, checkingAcc2);
                            System.out.println("Successfully transferred: $"+ payAmount+" from your Checking Account (" + checkingAcc1.getAccountNumber() + ") to their Checking Account (" +checkingAcc2.getAccountNumber() +")\n"); //confirmation
                            System.out.println("Your Updated Checking Account Balance: $"+checkingAcc1.getStartingBalance() + "\nYour Initial Checking Account Balance: $" +initialBalanceCh1);
                            System.out.println("\nTheir Updated Checking Account Balance: $"+checkingAcc2.getStartingBalance() + "\nTheir Initial Checking Account Balance: $" +initialBalanceCh2+"\n");
                            singletonLogger.payLog(checkingAcc1, checkingAcc2, initialBalanceCh1, initialBalanceCh2, firstName1, lastName1, firstName2, lastName2, payAmount, checkingAcc1.getStartingBalance(), checkingAcc2.getStartingBalance());
                            break;
                        case 2:
                            System.out.println("You have chosen: Pay from savings to savings\nPlease enter pay amount.");
                            payAmount = sc.nextDouble();
                            if(payAmount > savingsAcc1.getStartingBalance())
                                throw new SubtractionOverflowException("Cannot withdraw more than current balance.");
                            else if (payAmount < 0)
                                throw new NegativeNumberException("Cannot enter negative amount.");
                            double initialBalanceSa1 = savingsAcc1.getStartingBalance(); //saves initial balance of both users
                            double initialBalanceSa2 = savingsAcc2.getStartingBalance();  
                            savingsAcc1.transferFunction(payAmount, savingsAcc2);
                            System.out.println("Successfully transferred: $"+ payAmount+" from your Savings Account (" + savingsAcc1.getAccountNumber() + ") to their Savings Account (" +savingsAcc2.getAccountNumber() +")\n"); //confirmation
                            System.out.println("\nYour Updated Savings Account Balance: $"+savingsAcc1.getStartingBalance() + "\nYour Initial Savings Account Balance: $" +initialBalanceSa1);
                            System.out.println("\nTheir Updated Savings Account Balance: $"+savingsAcc2.getStartingBalance() + "\nTheir Initial Savings Account Balance: $" +initialBalanceSa2+"\n");
                            singletonLogger.payLog(savingsAcc1, savingsAcc2, initialBalanceSa1, initialBalanceSa2, firstName1, lastName1, firstName2, lastName2, payAmount, savingsAcc1.getStartingBalance(), savingsAcc2.getStartingBalance());
                            break;
                        case 0:
                            System.out.println("Going back...\n");
                            x=1;
                            break;
                    }
                    
                    if (x == 0) {
                        System.out.println("Which account will receive the funds?");
                        Menu.printAccountSelectionNoAllNoCredit();
                        nestedChoice = sc.nextInt(); // saves user type selection
                        if (nestedChoice > 2 || nestedChoice < 0)
                            throw new OutOfRangeException("Please enter a number inside the range.");
                    } else
                        break;
                }
            } catch (NegativeNumberException e) {
                System.out.println("You cannot withdraw a negative amount. Please try again.");
            } catch (SubtractionOverflowException e2) {
                System.out.println("You cannot withdraw more than current balance. Please try again.");
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
        } while (x == 0);
        if (x == 1)
            return 1;
        return 0;
    }

    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>This method handles the choice-making when a manager logs in.
     * <br><br>It will prompt the manager to select which service is available to them.
     * <br><br>Entering 1 will allow the manager to see the options for inquiring customer account information.
     * <br><br>Entering 2 will allow the manager to create a new user through the console.
     * <br><br>Entering 2 will allow the manager to generate a bank statement for a customer given their full name.
     * <br><br>Entering 0 will log the manager out.
     * </p>
     * @param customerObject An instance of an ArrayList of type Customer
     * @return x, a loop condition for the method that calls it
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws OutOfRangeException If the user inputs a number outside the range of selections
     * @throws FileNotFoundException If the file is not found or is currently open while being written to
     * @throws IOException Input Output Exception
     */
    public int managerChoice(ArrayList<Customer> customerObject) throws InputMismatchException, OutOfRangeException, FileNotFoundException, IOException{
      int x = 0;
        do{
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println("Logged in as a manager. What would you like to do?");
                Menu.printManagerMainDirectory();
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
                    case 0:
                        System.out.println("Successfully Logged out.");
                        x = 1;
                        break;
                    }
                    if (x == 0) {
                        System.out.println("Logged in as a manager. What would you like to do?");
                        Menu.printManagerMainDirectory();
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
        } while (x == 0);
        if (x == 1)
            return 1;
        return 0;
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
     * @return x, a loop condition for the method that calls it
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws OutOfRangeException If the user inputs a number outside the range of selections
     * @throws FileNotFoundException When the file is not found
     * @throws IOException Input Output Exception
     */
    public int managerChoiceNest1(ArrayList<Customer> customerObject) throws InputMismatchException, OutOfRangeException, FileNotFoundException, IOException{
        int x = 0;
        do{
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println("How will you be inquiring accounts?");
                Menu.printManagerInquirySelection();
                int userChoice = sc.nextInt();
                if(userChoice >3 || userChoice < 0)
                    throw new OutOfRangeException("Out of range");
                while (userChoice <= 3 || userChoice >= 0) {
                    boolean isFound = false;
                switch (userChoice) {
                    case 1: //full name
                    System.out.println("Please enter customer's First Name. (Case insensitive");
                    String fName = sc.next();
                    System.out.println("Please enter customer's Last Name. (Case-insensitive)");
                    String lName = sc.next();
                    String nameInput = (fName + " " + lName);
                    for (Customer cTmp : customerObject) {
                        String toComp = (cTmp.getFirstName() + " " + cTmp.getLastName());

                        if(nameInput.equalsIgnoreCase(toComp)){// if true, saves values on current iteration
                            isFound = true;
                           if(cTmp.getCheckingAccount().getAccountNumber() == -1 && cTmp.getCreditAccount().getAccountNumber()==-1){ //only savings exists
                            System.out.println("User found successfully.");
                            String name = (cTmp.getFirstName()+ " " + cTmp.getLastName());
                            String info =("\nDate of Birth: \t"+ cTmp.getDateOfBirth() + 
                                          "\nIdentification Number: \t" + cTmp.getIdNumber()+ 
                                          "\nAddress: \t" + cTmp.getAddress() + 
                                          "\nPhone Number: \t" + cTmp.getPhoneNumber());
                            int accInfoSa =(cTmp.getSavingsAccount().getAccountNumber());
                            double accBalSa = (cTmp.getSavingsAccount().getStartingBalance());

                            System.out.println("Name: \t" + name + info +
                                               "\nSavings account Number & Balance: \t" + accInfoSa+ ", "+ accBalSa);
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
                    int methodCondition = 0;
                    do{
                        methodCondition = managerChoiceInquiryByAccNest(customerObject);
                    } while(methodCondition ==0);   
                    break;
                    case 3: //list all
                        menuObj.print(customerObject);
                        break;
                    case 0://go back
                        System.out.println("Going back...\n");
                        x = 1;
                        break;
                    }
                    if (x == 0) {
                        System.out.println("How will you be inquiring accounts?");
                        Menu.printManagerInquirySelection();
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
        } while (x == 0);
        if (x == 1)
            return 1;
        return 0;
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
        @return x, a loop condition for the method that calls it
      * @throws InputMismatchException The input is not the same data type expected by compiler
      * @throws OutOfRangeException    If the user inputs a number outside the range of selections
      * @throws IOException Input Output Exception
      * @throws FileNotFoundException If the file is not found
      */

      public int managerChoiceNest2(ArrayList<Customer> customerObject) throws InputMismatchException, OutOfRangeException, FileNotFoundException, IOException, NegativeNumberException{
        int x = 0;
        do{
            try{
                FileWriter fw = new FileWriter("Updated Balance Sheet.csv", true);
                Checking newChecking;
                Savings newSavings;
                Credit newCredit;
                double initialBalCh = 0;
                double initialBalCr = 0;
                int maxChecking = findMaxChecking(customerObject) + 1;;
                int maxSavings = findMaxSavings(customerObject) + 1;
                int maxCredit = findMaxCredit(customerObject) + 1;
                int maxId = findMaxID(customerObject)+1;


                System.out.println("Starting User Account Creation...");
                Scanner sc = new Scanner(System.in);

                System.out.println("Step 1 of 6");
                System.out.println("Savings account created by default. How much money will the user be opening the account with? Enter Positive Amount.");
                double initialBalSa = sc.nextDouble();
                if(initialBalSa < 0)
                    throw new NegativeNumberException("Please enter in a value of the opposite sign");
                newSavings = new Savings(maxSavings, initialBalSa);
                 System.out.println("Savings Account Information Saved Successfully\n");
                
                System.out.println("Step 2 of 6");
                System.out.println("Enter First Name (Capitalization Encouraged, No Spaces)");
                String fName = sc.next();
                System.out.println("First Name Saved Successfully\n");

                System.out.println("Step 3 of 6");
                System.out.println("Enter Last Name (Capitalization Encouraged, No Spaces)");
                String lName = sc.next();
                System.out.println("Last Name Saved Successfully\n");

                System.out.println("Step 4 of 6");
                System.out.println("Enter Date of Birth (dd/MM/yyyy)");
                String dob = sc.next();
                System.out.println("Date of Birth Saved Successfully\n");

                System.out.println("Step 5 of 6");
                System.out.println("Enter Address (Underscore Separated: 123_Address_St,_City,_State_Zip)");
                String address = sc.next();
                System.out.println("Address Saved Successfully\n");

                System.out.println("Step 6 of 6");
                System.out.println("Enter Phone Number (Format No Spaces: (  (123)456-7890 ) ");
                String phone = sc.next();
                System.out.println("Phone Number Successfully\n");
                
                String pass = lName+"*"+fName+"!987";
                
                System.out.println("Which account would you like to open in addition to the Savings Account (Must select confirm when done otherwise info will be lost)");
                Menu.printManagerUserCreationSelection();
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
                                        initialBalCh = sc.nextDouble();
                                        if (initialBalCh < 0)
                                            throw new NegativeNumberException("Please enter in a value of the opposite sign");
                                        
                                        System.out.println("Checking Account Information Saved Successfully");
                                        checkingCreationLoop = false;
                                    } while(checkingCreationLoop == true);
                                
                                    break;
                                case 2:// credit creation 
                                    System.out.println("Starting Credit Account Creation..");
                                    System.out.println("How much money will the user be opening their Credit account with? Positive Amount.");
                                    initialBalCr = sc.nextDouble();
                                    if (initialBalCr < 0)
                                        throw new NegativeNumberException("Please enter in a value of the opposite sign");
                                    System.out.println("Credit Account Information Saved Successfully");
                                    break;
                                case 3:// confirm will exit
                                    System.out.println("Creating the User...");
                                    
                                    //all accounts exist
                                
                                   
                                    if (initialBalCh != -1 && initialBalCr != -1 && initialBalSa != -1) {
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
                                    if (initialBalCh != -1 && initialBalCr == -1 && initialBalSa != -1){
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
                                    if (initialBalCh == -1 && initialBalCr !=-1 && initialBalSa !=-1) {
                                        newCredit = new Credit(maxCredit, initialBalCr);
                                        newChecking = new Checking(-1, -1);
                                        customerObject.add(new Customer(fName, lName, dob, address, phone, maxId, newSavings, newCredit));
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
                                    if (initialBalCh == -1 && initialBalCr ==-1 && initialBalSa !=-1) {
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
                                    RunBank.generateUpdatedBalanceSheet(customerObject);
                                    System.out.println("Customer is able to do business now. They are updated in the Updated Balance Sheet.");
                                    break;
                                case 0:// go back
                                    System.out.println("Cancelling...\n");
                                    x = 1;
                                    switchLoop = false;
                                    break;
                            }
                            if(switchLoop==true){//loop choice-making
                                System.out.println("Which account would you like to open in addition to the Savings Account (Must select confirm when done otherwise info will be lost)");
                                Menu.printManagerUserCreationSelection();
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

                if (x == 0) {
                    System.out.println("user creation");
                    userChoice = sc.nextInt(); // saves user type selection
                    if (userChoice > 3 || userChoice < 0)
                        throw new OutOfRangeException("Please enter value inside the range of selections.");
                } else
                    break;
                    
            } catch (InputMismatchException e1) {
                System.out.println("Please follow the instructions for expected information in the fields.");
            } catch (OutOfRangeException e2) {
                System.out.println("Please choose between the range of selections.");
            } catch (IOException e3) {
                System.out.println("Input Output Exception");
                break;
            } catch (NegativeNumberException e5) {
                System.out.println("Please enter a number of the opposite sign.");
            }
        } while (x == 0);
        if (x == 1)
            return 1;
        return 0;
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
     * @return x, a loop condition for the method that calls it
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws OutOfRangeException If the user inputs a number outside the range of selections
     * @throws AccountExistenceException If the manager tries inquiring by -1
     */
    public int managerChoiceInquiryByAccNest(ArrayList<Customer> customerObject) throws InputMismatchException, OutOfRangeException, AccountExistenceException{
        int x = 0;
        do {
            try{
                Scanner sc = new Scanner(System.in); 
                System.out.println("Please choose account type.");
                Menu.printAccountSelectionNoAll();
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
                        x = 1;
                        break;
                    }
                    if (x == 0) {
                        System.out.println("\nPlease choose account type.");
                        Menu.printAccountSelectionNoAll();
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
        } while (x == 0);
        if (x == 1)
            return 1;
        return 0;
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
     * Taken from Anthony Jasso
     * 
     * This method will read from an input file called "Transaction Actions.csv" and
     *  perform actions listed on the file in a quick succession.
     * 
     * @param fileName the file name
     * @param bank ArrayList of type Customer
     * @throws FileNotFoundException If the file is not found
     * @throws IOException Input Output Exception
     * @throws SubtractionOverflowException If a subtraction goes past zero from both sides
     * @throws NegativeNumberException If an inputted number is negative
     */

    public void transactionActions(String fileName, ArrayList<Customer> bank) throws NegativeNumberException,SubtractionOverflowException, IOException { //Method that reads the customer's data from the file and stores it into an arraylist
  try {
   BufferedReader br = new BufferedReader(new FileReader(fileName));
            String st;
            String firstLine = br.readLine();
            String[] titles = firstLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
  
   while((st = br.readLine()) != null) { //While loop that checks if the line is empty
                String[] customerData = st.split(","); //Array of strings that contains the data of one customer
                
    if(customerData[findIndex(titles, "Action")].contains("pays")) {
                    String customerOne = customerData[findIndex(titles, "From First Name")] + " " + customerData[findIndex(titles, "From Last Name")];
                    String customerTwo = customerData[findIndex(titles, "To First Name")] + " " + customerData[findIndex(titles, "To Last Name")];
     Customer sender = RunBank.findAccountByName(bank, customerOne);
                    Customer receiver = RunBank.findAccountByName(bank, customerTwo);
     if(customerData[findIndex(titles, "From Where")].contains("Checking") && customerData[findIndex(titles, "To Where")].contains("Checking")) { //checking to checking
      System.out.println(customerOne + " paid "+ customerTwo + " $"+ customerData[findIndex(titles, "Action Amount")]+" from Checking ("+sender.getCheckingAccount().getAccountNumber()+")");
      sender.getCheckingAccount().transferFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]) , receiver.getCheckingAccount()); //Method pay someone is called and the user input is used for the parameter
     }
     else if(customerData[findIndex(titles, "From Where")].equals("Savings") && customerData[findIndex(titles, "To Where")].equals("Checking")) {
      System.out.println(customerOne + " paid "+ customerTwo + " $"+ customerData[findIndex(titles, "Action Amount")]+" from Checking ("+sender.getSavingsAccount().getAccountNumber()+")");
      sender.getSavingsAccount().transferFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]),receiver.getCheckingAccount());
                    }
                    
    }
    else if(customerData[findIndex(titles, "Action")].equals("transfers")) {
                    String customerOne = customerData[findIndex(titles, "From First Name")] + " " + customerData[findIndex(titles, "From Last Name")];
     Customer disChar = RunBank.findAccountByName(bank,customerOne);
     if(customerData[findIndex(titles, "From Where")].equals("Checking") && customerData[findIndex(titles, "To Where")].equals("Savings")) {
                        System.out.println(customerOne + " transferred $"+ customerData[findIndex(titles, "Action Amount")]+" from Checking ("+ disChar.getCheckingAccount().getAccountNumber()+") to Savings "+ disChar.getSavingsAccount().getAccountNumber());
      disChar.getCheckingAccount().transferFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]),disChar.getSavingsAccount()); //Method pay someone is called and the user input is used for the parameter
     }
     else if(customerData[findIndex(titles, "From Where")].equals("Checking") && customerData[findIndex(titles, "To Where")].equals("Credit")) {
      System.out.println(customerOne + " transferred $"+ customerData[findIndex(titles, "Action Amount")]+" from Checking ("+ disChar.getCheckingAccount().getAccountNumber()+") to Credit "+ disChar.getSavingsAccount().getAccountNumber());
      disChar.getCheckingAccount().transferFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]),disChar.getCreditAccount()); //Method pay someone is called and the user input is used for the parameter
     }
     else if(customerData[findIndex(titles, "From Where")].equals("Savings") && customerData[findIndex(titles, "To Where")].equals("Checking")) {
      System.out.println(customerOne + " transferred $"+ customerData[findIndex(titles, "Action Amount")]+" from Savings ("+ disChar.getSavingsAccount().getAccountNumber()+") to Savings "+ disChar.getSavingsAccount().getAccountNumber());
      disChar.getSavingsAccount().transferFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]),disChar.getCheckingAccount()); //Method pay someone is called and the user input is used for the parameter
     }
     else if(customerData[findIndex(titles, "From Where")].equals("Savings") && customerData[findIndex(titles, "To Where")].equals("Credit")) {
      System.out.println(customerOne + " transferred $"+ customerData[findIndex(titles, "Action Amount")]+" from Savings ("+ disChar.getSavingsAccount().getAccountNumber()+") to Credit "+ disChar.getCreditAccount().getAccountNumber());
      disChar.getSavingsAccount().transferFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]),disChar.getCreditAccount()); //Method pay someone is called and the user input is used for the parameter
     }
     else if(customerData[findIndex(titles, "From Where")].equals("Credit") && customerData[findIndex(titles, "To Where")].equals("Checking")) {
      System.out.println(customerOne + " transferred $"+ customerData[findIndex(titles, "Action Amount")]+" from Credit ("+ disChar.getCreditAccount().getAccountNumber()+") to Checking "+ disChar.getCheckingAccount().getAccountNumber());
      disChar.getCreditAccount().transferFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]),disChar.getCheckingAccount()); //Method pay someone is called and the user input is used for the parameter
     }
     else if(customerData[findIndex(titles, "From Where")].equals("Credit") && customerData[findIndex(titles, "To Where")].equals("Savings")) {
      System.out.println(customerOne + " transferred $"+ customerData[findIndex(titles, "Action Amount")]+" from Credit ("+ disChar.getCreditAccount().getAccountNumber()+") to Savings "+ disChar.getSavingsAccount().getAccountNumber());
      disChar.getCreditAccount().transferFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]),disChar.getSavingsAccount()); //Method pay someone is called and the user input is used for the parameter
     }
    }
    else if(customerData[findIndex(titles, "Action")].equals("inquires")) {
                    String customerOne = customerData[findIndex(titles, "From First Name")] + " " + customerData[findIndex(titles, "From Last Name")];
     Customer disChar = RunBank.findAccountByName(bank,customerOne);
     if(customerData[findIndex(titles, "From Where")].equals("Checking")) 
      System.out.println(customerOne+" made a balance inquiry on Checking ("+disChar.getCheckingAccount().getAccountNumber()+")");
     else if(customerData[findIndex(titles, "From Where")].equals("Savings"))
      System.out.println(customerOne+" made a balance inquiry on Saving ("+disChar.getSavingsAccount().getAccountNumber()+")");
     else if(customerData[findIndex(titles, "From Where")].equals("Credit")) 
      System.out.println(customerOne+" made a balance inquiry on Credit ("+disChar.getCreditAccount().getAccountNumber()+")");
     
    }
    else if(customerData[findIndex(titles, "Action")].equals("withdraws")) {
     String customerOne = customerData[findIndex(titles, "From First Name")] + " " + customerData[findIndex(titles, "From Last Name")];
     Customer disChar = RunBank.findAccountByName(bank,customerOne);
     if(customerData[findIndex(titles, "From Where")].equals("Checking")) {
      System.out.println(customerOne+" withdrew $"+customerData[findIndex(titles, "Action Amount")]+" from Checking ("+disChar.getCheckingAccount().getAccountNumber()+")");
      disChar.getCheckingAccount().withdrawFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]));
     }
     else if(customerData[findIndex(titles, "From Where")].equals("Savings")) {
                        System.out.println(customerOne +" withdrew $" +customerData[findIndex(titles, "Action Amount")]+" from Savings ("+disChar.getSavingsAccount().getAccountNumber()+")");
      disChar.getSavingsAccount().withdrawFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]));
     }
     else if(customerData[findIndex(titles, "From Where")].equals("Credit")) {
                        System.out.println(customerOne +" withdrew $"+customerData[findIndex(titles, "Action Amount")]+" from Credit ("+disChar.getCreditAccount().getAccountNumber()+")");
      disChar.getCreditAccount().withdrawFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]));
     }
    }
    else if(customerData[findIndex(titles, "Action")].equals("deposits")) {
                    String customerTwo = customerData[findIndex(titles, "To First Name")] + " " + customerData[findIndex(titles, "To Last Name")];
     Customer recipient = RunBank.findAccountByName(bank,customerTwo);
     if(customerData[findIndex(titles, "To Where")].equals("Checking")) {
                        System.out.println(customerTwo+" deposited "+customerData[findIndex(titles, "Action Amount")]+" to Checking ("+recipient.getCheckingAccount().getAccountNumber()+")");
                        recipient.getCheckingAccount().depositFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]));
     }
     else if(customerData[findIndex(titles, "To Where")].equals("Savings")) {
      System.out.println(customerTwo+" deposited "+customerData[findIndex(titles, "Action Amount")]+" to Savings ("+recipient.getSavingsAccount().getAccountNumber()+")");
      recipient.getSavingsAccount().depositFunction(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]));
     }
     else if(customerData[findIndex(titles, "To Where")].equals("Credit")) {
      System.out.println(customerTwo+" deposited "+customerData[findIndex(titles, "Action Amount")]+" to Credit ("+recipient.getCreditAccount().getAccountNumber()+")");
      recipient.getCreditAccount().deposit(Double.parseDouble(customerData[findIndex(titles, "Action Amount")]));
     }
    }
            }
   RunBank.generateUpdatedBalanceSheet(bank);
   br.close();
  } catch (IOException e) {
   System.out.println("IOException\n");
  } catch (NegativeNumberException e){
            System.out.println("Cannot perform operation with a negative value.\n");
        } catch(SubtractionOverflowException e){
            System.out.println("Cannot withdraw more than current balance.\n");
        }
 }
}