import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The UserConsole class will be used hold all the user choice methods.
 *
 * @author Derek Aguirre
 * @version 1.0
 * @since 10/10/2020
 * @see RunBank
 */

public class UserConsole{
    Logger singletonLogger = Logger.getInstance();

    /**
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
     * 
     */
    public int inquireBalanceChoice(Checking checkingAcc, Savings savingsAcc, Credit creditAcc, String firstName, String lastName) throws InputMismatchException, OutOfRangeException, FileNotFoundException, IOException {
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
                            singletonLogger.inquireBalanceLog(checkingAcc, savingsAcc, creditAcc, firstName, lastName);
                            System.out.println(firstName + " " + lastName + "'s Checking Account ("+ checkingAcc.getAccountNumber() + ") Balance: "+ checkingAcc.getStartingBalance());
                            System.out.println(firstName + " " + lastName + "'s Savings Account ("+ savingsAcc.getAccountNumber() + ") Balance: " + savingsAcc.getStartingBalance());
                            System.out.println(firstName + " " + lastName + "'s Credit Account ("+ creditAcc.getAccountNumber() + ") Balance: " + creditAcc.getStartingBalance());
                            break;
                        case 2:
                            singletonLogger.inquireBalanceLog(checkingAcc, firstName, lastName);
                            System.out.println(firstName + " " + lastName + "'s Checking Account ("+ checkingAcc.getAccountNumber() + ") Balance: "+ checkingAcc.getStartingBalance());
                            break;
                        case 3:
                            singletonLogger.inquireBalanceLog(savingsAcc, firstName, lastName);
                            System.out.println(firstName + " " + lastName + "'s Savings Account ("+ savingsAcc.getAccountNumber() + ") Balance: " + savingsAcc.getStartingBalance());
                            break;
                        case 4:
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
            }
        } while (x == 0);
        if (x == 1)
            return 1;
        return 0;
    }

    /**
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
     * 
     */
    public int depositChoice(Checking checkingAcc, Savings savingsAcc, Credit creditAcc, String firstName, String lastName) throws NegativeNumberException, InputMismatchException, SubtractionOverflowException, FileNotFoundException, IOException {
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
                            sc = new Scanner(System.in);
                            System.out.println("You have chosen your checking account. How much would you like to deposit?");
                            double depositAmount = sc.nextDouble(); // asks user to input amount
                            if (depositAmount < 0)
                                throw new NegativeNumberException("Cannot deposit a negative amount.");
                            System.out.println("\nDepositing $" + depositAmount + " into Checking Account (" + checkingAcc.getAccountNumber() + ")"); // confirmation on amount
                            double initialBalanceCh = checkingAcc.getStartingBalance(); // Saves user's initial balance before the transaction
                            checkingAcc.setStartingBalance(checkingAcc.depositFunction(initialBalanceCh, depositAmount));
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
                            savingsAcc.setStartingBalance(savingsAcc.depositFunction(initialBalanceSa, depositAmount));
                            System.out.println("\nUpdated Balance: $" + savingsAcc.getStartingBalance() + "\nInitial Balance: $" + initialBalanceSa + "\n");
                            singletonLogger.depositLog(savingsAcc, firstName, lastName, depositAmount, initialBalanceSa, savingsAcc.getStartingBalance());
                            break;
                        case 3:// deposit to credit
                            sc = new Scanner(System.in);
                            System.out.println("You have chosen your credit account. How much would you like to deposit?");
                            depositAmount = sc.nextDouble(); // asks user to input amount
                            if (depositAmount + creditAcc.getStartingBalance() > 0)
                                throw new SubtractionOverflowException("Cannot deposit more than current balance.");
                            if (depositAmount < 0)
                                throw new NegativeNumberException("Cannot deposit a negative amount.");
                            System.out.println("\nDepositing $" + depositAmount); // confirmation on amount
                            double initialBalanceCr = creditAcc.getStartingBalance(); // Saves user's initial balance before the transaction
                            creditAcc.setStartingBalance(creditAcc.depositFunction(initialBalanceCr, depositAmount));
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
            }
        } while (x == 0);
        if (x == 1)
            return 1;
        return 0;
    }
    
    /**
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
     */
    public int withdrawChoice(Checking checkingAcc, Savings savingsAcc, String firstName, String lastName) throws NegativeNumberException, SubtractionOverflowException, InputMismatchException, OutOfRangeException, FileNotFoundException, IOException {
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
                            sc = new Scanner(System.in);
                            System.out.println("You have chosen to withdraw from your checking account. Please enter amount.");
                            double withdrawAmount = sc.nextDouble(); // asks user to input amount
                            if (withdrawAmount > checkingAcc.getStartingBalance())
                                throw new SubtractionOverflowException("Cannot withdraw more than current balance.");
                            if (withdrawAmount < 0)
                                throw new NegativeNumberException("Cannot withdraw a negative amount.");
                            System.out.println("\nWithdrawing $" + withdrawAmount + " from Checking Account ("+ checkingAcc.getAccountNumber() + ")"); // confirmation on amount
                            double initialBalanceCh = checkingAcc.getStartingBalance(); // Saves user's initial balance before the transaction
                            checkingAcc.setStartingBalance(checkingAcc.withdrawFunction(initialBalanceCh, withdrawAmount));
                            System.out.println("\nUpdated Balance: $" + checkingAcc.getStartingBalance()+ "\nInitial Balance: $" + initialBalanceCh + "\n");
                            singletonLogger.withdrawLog(checkingAcc, initialBalanceCh, firstName, lastName, withdrawAmount, checkingAcc.getStartingBalance());
                            break;
                        case 2:
                            sc = new Scanner(System.in);
                            System.out.println("You have chosen to withdraw from your savings account. Please enter amount.");
                            withdrawAmount = sc.nextDouble(); //asks user to input amount
                            if (withdrawAmount > checkingAcc.getStartingBalance())
                                throw new SubtractionOverflowException("Cannot withdraw more than current balance.");
                            if (withdrawAmount < 0)
                                throw new NegativeNumberException("Cannot withdraw a negative amount.");
                            System.out.println("\nWithdrawing $" + withdrawAmount +" from Checking Account (" + savingsAcc.getAccountNumber() + ")"); //confirmation on amount
                            double initialBalanceSa = savingsAcc.getStartingBalance(); //Saves user's initial balance before the transaction
                            savingsAcc.setStartingBalance(savingsAcc.withdrawFunction(initialBalanceSa, withdrawAmount));
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
            }
        } while (x == 0);
        if (x == 1)
            return 1;
        return 0;
    }

    /**
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
     */
    public int transferChoice(Checking checkingAcc, Savings savingsAcc, Credit creditAcc, String firstName, String lastName) throws NegativeNumberException, SubtractionOverflowException, InputMismatchException, FileNotFoundException, IOException, OutOfRangeException{
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
            }
        } while (x == 0);
        if (x == 1)
            return 1;
        return 0;

    }

    /**
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
     * 
     */
    public int transferChoiceNest1(Checking checkingAcc, Savings savingsAcc, Credit creditAcc, String firstName, String lastName) throws NegativeNumberException, SubtractionOverflowException, InputMismatchException, OutOfRangeException, FileNotFoundException, IOException {
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
                            System.out.println("You have chosen to transfer from your Checking account to your Savings account.\nPlease enter transfer amount.");
                            double transferAmount = sc.nextDouble();
                            if (transferAmount > checkingAcc.getStartingBalance())
                                throw new SubtractionOverflowException("Cannot withdraw more than current balance.");
                            else if (transferAmount < 0)
                                throw new NegativeNumberException("Cannot enter negative amount.");
                            double initialBalanceCh = checkingAcc.getStartingBalance(); // save initial checking bal
                            double initialBalanceSa = savingsAcc.getStartingBalance();  // save initial savings bal
                            checkingAcc.setStartingBalance(checkingAcc.withdrawFunction(initialBalanceCh, transferAmount)); //withdraw checking
                            savingsAcc.setStartingBalance(savingsAcc.depositFunction(initialBalanceSa, transferAmount)); //deposit savings

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
                            System.out.println("You have chosen to transfer from your Checking account to your Credit account.\nPlease enter transfer amount.");
                            transferAmount = sc.nextDouble();
                            if (transferAmount > checkingAcc.getStartingBalance())
                                throw new SubtractionOverflowException("Cannot withdraw more than current balance.");
                            else if (transferAmount < 0)
                                throw new NegativeNumberException("Cannot enter negative amount.");
                            initialBalanceCh = checkingAcc.getStartingBalance(); //saves initial checking bal
                            double initialBalanceCr = creditAcc.getStartingBalance(); //saves initial credit bal
                            checkingAcc.setStartingBalance(checkingAcc.withdrawFunction(initialBalanceCh, transferAmount)); //withdraw checking
                            creditAcc.setStartingBalance(creditAcc.depositFunction(initialBalanceCr, transferAmount)); //deposit credit
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
            }
        } while (x == 0);
        if (x == 1)
            return 1;
        return 0;
    }
      
    /**
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
     */
    public int transferChoiceNest2(Checking checkingAcc, Savings savingsAcc, Credit creditAcc, String firstName, String lastName) throws NegativeNumberException, SubtractionOverflowException, InputMismatchException, OutOfRangeException, FileNotFoundException, IOException {
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
                            System.out.println(
                                    "You have chosen to transfer from your Savings Account to your Checking account.\nPlease enter transfer amount.");
                            double transferAmount = sc.nextDouble();
                            if (transferAmount > savingsAcc.getStartingBalance())
                                throw new SubtractionOverflowException("Cannot withdraw more than current balance.");
                            else if (transferAmount < 0)
                                throw new NegativeNumberException("Cannot enter negative amount.");
                            double initialBalanceSa = savingsAcc.getStartingBalance(); //saves savings initial bal
                            double initialBalanceCh = checkingAcc.getStartingBalance();//saves checking initial bal
                            savingsAcc.setStartingBalance(savingsAcc.withdrawFunction(initialBalanceSa, transferAmount)); //withdraw savings
                            checkingAcc.setStartingBalance(checkingAcc.depositFunction(initialBalanceCh, transferAmount)); //deposit checking
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
                            System.out.println(
                                    "You have chosen to transfer from your Savings Account to your Credit account.\nPlease enter transfer amount.");
                            transferAmount = sc.nextDouble();
                            if (transferAmount > savingsAcc.getStartingBalance())
                                throw new SubtractionOverflowException("Cannot withdraw more than current balance.");
                            else if (transferAmount < 0)
                                throw new NegativeNumberException("Cannot enter negative amount.");
                            initialBalanceSa = savingsAcc.getStartingBalance(); // saves initial savings bal
                            double initialBalanceCr = creditAcc.getStartingBalance(); //saves initial credit bal
                            savingsAcc.setStartingBalance(savingsAcc.withdrawFunction(initialBalanceSa, transferAmount));// withdraw savings
                            creditAcc.setStartingBalance(creditAcc.depositFunction(initialBalanceCr, transferAmount)); //deposit credit
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
            }
        } while (x == 0);
        if (x == 1)
            return 1;
        return 0;
    }
    
    /**
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
     */
    public int payNest(Checking checkingAcc1, Savings savingsAcc1, Credit creditAcc1, Checking checkingAcc2, Savings savingsAcc2, Credit creditAcc2, String firstName1, String firstName2, String lastName1, String lastName2) throws NegativeNumberException, SubtractionOverflowException, InputMismatchException, OutOfRangeException, FileNotFoundException, IOException {
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
                            System.out.println("You have chosen: Pay from checking to checking.\nPlease enter pay amount.");
                            double payAmount = sc.nextDouble();
                            if(payAmount > checkingAcc1.getStartingBalance())
                                throw new SubtractionOverflowException("Cannot withdraw more than current balance.");
                            else if (payAmount < 0)
                                throw new NegativeNumberException("Cannot enter negative amount.");
                            double initialBalanceCh1 = checkingAcc1.getStartingBalance(); //saves initial balance of both users
                            double initialBalanceCh2 = checkingAcc2.getStartingBalance(); 
                            checkingAcc1.setStartingBalance(checkingAcc1.withdrawFunction(checkingAcc1.getStartingBalance(), payAmount)); //performs withdraw from checking account of the first user
                            checkingAcc2.setStartingBalance(checkingAcc1.depositFunction(checkingAcc2.getStartingBalance(), payAmount));  //performs deposit into checking account of the second user
                            System.out.println("Successfully transferred: $"+ payAmount+" from your Checking Account (" + checkingAcc1.getAccountNumber() + ") to their Checking Account (" +checkingAcc2.getAccountNumber() +")\n"); //confirmation
                            System.out.println("Your Updated Checking Account Balance: $"+checkingAcc1.getStartingBalance() + "\nYour Initial Checking Account Balance: $" +initialBalanceCh1);
                            System.out.println("\nTheir Updated Checking Account Balance: $"+checkingAcc2.getStartingBalance() + "\nTheir Initial Checking Account Balance: $" +initialBalanceCh2+"\n");
                            singletonLogger.payLog(checkingAcc1, checkingAcc2, initialBalanceCh1, initialBalanceCh2, firstName1, lastName1, firstName2, lastName2, payAmount, checkingAcc1.getStartingBalance(), checkingAcc2.getStartingBalance());
                            break;
                        case 2:
                            System.out.println("You have chosen: Pay from savings to savings\nPlease enter pay amount.");
                            payAmount = sc.nextDouble();
                            if(payAmount > checkingAcc1.getStartingBalance())
                                throw new SubtractionOverflowException("Cannot withdraw more than current balance.");
                            else if (payAmount < 0)
                                throw new NegativeNumberException("Cannot enter negative amount.");
                            double initialBalanceSa1 = savingsAcc1.getStartingBalance(); //saves initial balance of both users
                            double initialBalanceSa2 = savingsAcc2.getStartingBalance();  
                            savingsAcc1.setStartingBalance(savingsAcc1.withdrawFunction(savingsAcc1.getStartingBalance(), payAmount)); //performs withdraw from savings account of the first user
                            savingsAcc2.setStartingBalance(savingsAcc2.depositFunction(savingsAcc2.getStartingBalance(), payAmount));  //performs deposit into savings account of the second user
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
            }
        } while (x == 0);
        if (x == 1)
            return 1;
        return 0;
    }

    /**
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
                        BankStatement.writeStatement(customerObject);
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
     */
    public int managerChoiceNest1(ArrayList<Customer> customerObject) throws InputMismatchException, OutOfRangeException{
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
                    System.out.println("Please enter recipient's First Name. (Case insensitive");
                    String fName = sc.next();
                    System.out.println("Please enter recipient's Last Name. (Case-insensitive)");
                    String lName = sc.next();
                    String nameInput = (fName + " " + lName);
                    for (Customer cTmp : customerObject) {
                        String toComp = (cTmp.getFirstName() + " " + cTmp.getLastName());

                        if(nameInput.equalsIgnoreCase(toComp)){// if true, saves values on current iteration
                            isFound = true;
                            System.out.println("User found successfully.");
                            String name = (cTmp.getFirstName()+ " " + cTmp.getLastName());
                            String info =("\nDate of Birth: \t"+ cTmp.getDateOfBirth() + "\nIdentification Number: \t" + cTmp.getIdNumber()+ "\nAddress: \t" + cTmp.getAddress() + "\nPhone Number: \t" + cTmp.getPhoneNumber());
                            int accInfoCh =(cTmp.getCheckingAccount().getAccountNumber());
                            int accInfoSa =(cTmp.getSavingsAccount().getAccountNumber());
                            int accInfoCr =(cTmp.getCreditAccount().getAccountNumber());
                            double accBalCh = (cTmp.getCheckingAccount().getStartingBalance());
                            double accBalSa = (cTmp.getSavingsAccount().getStartingBalance());
                            double accBalCr = (cTmp.getCreditAccount().getStartingBalance());
                            System.out.println("Name: \t" + name + info +"\nChecking account Number & Balance: \t" + accInfoCh+ ", "+ accBalCh +"\nSavings account Number & Balance: \t" + accInfoSa+ ", "+ accBalSa+"\nCredit account Number & Balance: \t" + accInfoCr+ ", "+ accBalCr);
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
                    for (Customer cTmp : customerObject) {
                            System.out.println("-----------------------------------------------------------------------------------------");
                            String info =("\nDate of Birth: \t"+ cTmp.getDateOfBirth() + "\nIdentification Number: \t" + cTmp.getIdNumber()+ "\nAddress: \t" + cTmp.getAddress() + "\nPhone Number: \t" + cTmp.getPhoneNumber());
                            String name = (cTmp.getFirstName()+ " " + cTmp.getLastName());
                            int accInfoCh =(cTmp.getCheckingAccount().getAccountNumber());
                            int accInfoSa =(cTmp.getSavingsAccount().getAccountNumber());
                            int accInfoCr =(cTmp.getCreditAccount().getAccountNumber());
                            double accBalCh = (cTmp.getCheckingAccount().getStartingBalance());
                            double accBalSa = (cTmp.getSavingsAccount().getStartingBalance());
                            double accBalCr = (cTmp.getCreditAccount().getStartingBalance());
                            System.out.println("Name: \t" + name + info +"\nChecking account Number & Balance: \t" + accInfoCh+ ", "+ accBalCh +"\nSavings account Number & Balance: \t" + accInfoSa+ ", "+ accBalSa+"\nCredit account Number & Balance: \t" + accInfoCr+ ", "+ accBalCr);
                        }
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
                                    if (initialBalCh < 0)
                                        throw new NegativeNumberException("Please enter in a value of the opposite sign");
                                    System.out.println("Credit Account Information Saved Successfully");
                                    break;
                                case 3:// confirm will exit
                                    System.out.println("Creating the User...");
                                    if (initialBalCh != 0 && initialBalCr != 0 && initialBalSa !=0) {
                                        newChecking = new Checking(maxChecking, initialBalCr);
                                        newCredit = new Credit(maxCredit, initialBalCr);
                                        customerObject.add(new Customer(fName, lName, dob, maxId, address, phone, newChecking, newSavings, newCredit));
                                        fw.append(String.valueOf("\n" + fName + ","));
                                        fw.append(String.valueOf(lName + ","));
                                        fw.append(String.valueOf(dob + ","));
                                        fw.append(String.valueOf(maxId + ","));
                                        fw.append(String.valueOf(address + ","));
                                        fw.append(String.valueOf(phone + ","));
                                        fw.append(String.valueOf(maxChecking + ","));
                                        fw.append(String.valueOf(maxSavings + ","));
                                        fw.append(String.valueOf(maxCredit + ","));
                                        fw.append(String.valueOf(initialBalCh*-1 + ","));
                                        fw.append(String.valueOf(initialBalSa + ","));
                                        fw.append(String.valueOf(initialBalCr + ","));
                                        System.out.println("User created with checking, savings, and credit account");
                                        System.out.println("Customer is able to do business now. They will be updated in the Updated Balance Sheet when they login and logout.");
                                    }
                                    if (initialBalCh != 0 && initialBalCr ==0 && initialBalSa !=0){
                                        newChecking = new Checking(maxChecking, initialBalCr);
                                        customerObject.add(new Customer(fName, lName, dob, maxId, address, phone, newChecking, newSavings));
                                        fw.append(String.valueOf("\n" + fName + ","));
                                        fw.append(String.valueOf(lName + ","));
                                        fw.append(String.valueOf(dob + ","));
                                        fw.append(String.valueOf(maxId + ","));
                                        fw.append(String.valueOf(address + ","));
                                        fw.append(String.valueOf(phone + ","));
                                        fw.append(String.valueOf(maxChecking+ ","));
                                        fw.append(String.valueOf(maxSavings+ ","));
                                        fw.append(String.valueOf(initialBalCh*-1 + ","));
                                        fw.append(String.valueOf(initialBalSa + ","));
                                        System.out.println("User created with checking and savings account");
                                        switchLoop = false;
                                    }
                                    if (initialBalCh == 0 && initialBalCr !=0 && initialBalSa !=0) {
                                        newCredit = new Credit(maxCredit, initialBalCr);
                                        customerObject.add(new Customer(fName, lName, dob, maxId, address, phone, newSavings, newCredit));
                                        fw.append(String.valueOf("\n" + fName + ","));
                                        fw.append(String.valueOf(lName + ","));
                                        fw.append(String.valueOf(dob + ","));
                                        fw.append(String.valueOf(maxId + ","));
                                        fw.append(String.valueOf(address + ","));
                                        fw.append(String.valueOf(phone + ","));
                                        fw.append(String.valueOf(maxSavings + ","));
                                        fw.append(String.valueOf(maxCredit + ","));
                                        fw.append(String.valueOf(initialBalSa + ","));
                                        fw.append(String.valueOf(initialBalCr*-1 + ","));
                                        System.out.println("User created with savings and credit account");
                                        switchLoop = false;
                                    }
                                    if (initialBalCh == 0 && initialBalCr ==0 && initialBalSa !=0) {
                                        customerObject.add(new Customer(fName, lName, dob, maxId, address, phone, newSavings));
                                        fw.append(String.valueOf("\n" + fName + ","));
                                        fw.append(String.valueOf(lName + ","));
                                        fw.append(String.valueOf(dob + ","));
                                        fw.append(String.valueOf(maxId+ ","));
                                        fw.append(String.valueOf(address + ","));
                                        fw.append(String.valueOf(phone + ","));
                                        fw.append(String.valueOf(maxSavings + ","));
                                        fw.append(String.valueOf(initialBalSa + ","));
                                        System.out.println("User created with savings account");
                                        switchLoop = false;
                                    }
                                    switchLoop = false;
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
     */
    public int managerChoiceInquiryByAccNest(ArrayList<Customer> customerObject) throws InputMismatchException, OutOfRangeException{
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
                        System.out.println("Please enter recipient's checking account number. (4-Digit integer 1XXX)");
                        int accNumInput = sc.nextInt();
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
                        System.out.println("Please enter recipient's savings account number. (4-Digit integer 2XXX)");
                       accNumInput = sc.nextInt();
                        for (Customer saAcc : customerObject) {
                            if(accNumInput == saAcc.getSavingsAccount().getAccountNumber()){ // if true, saves values on current iteration
                                isFound = true;
                                System.out.println("User found successfully.");
                                String name = (saAcc.getFirstName()+ " " + saAcc.getLastName());
                                String info =("\nDate of Birth: \t"+ saAcc.getDateOfBirth() + "\nIdentification Number: \t" + saAcc.getIdNumber()+ "\nAddress: \t" + saAcc.getAddress() + "\nPhone Number: \t" + saAcc.getPhoneNumber());
                                int accInfoCh =(saAcc.getCheckingAccount().getAccountNumber());
                                int accInfoSa =(saAcc.getSavingsAccount().getAccountNumber());
                                int accInfoCr =(saAcc.getCreditAccount().getAccountNumber());
                                double accBalCh = (saAcc.getCheckingAccount().getStartingBalance());
                                double accBalSa = (saAcc.getSavingsAccount().getStartingBalance());
                                double accBalCr = (saAcc.getCreditAccount().getStartingBalance());
                                System.out.println("Name: \t" + name + info +"\nChecking account Number & Balance: \t" + accInfoCh+ ", "+ accBalCh +"\nSavings account Number & Balance: \t" + accInfoSa+ ", "+ accBalSa+"\nCredit account Number & Balance: \t" + accInfoCr+ ", "+ accBalCr);
                            }
                            if (isFound)
                                break;
                        }
                        if (!isFound){
                            System.out.println("Account not found. Please try again ");
                        }
                        break;
                    case 3: //credit was chosen
                    System.out.println("Please enter recipient's credit account number. (4-Digit integer 3XXX)");
                        accNumInput = sc.nextInt();
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
            }
        } while (x == 0);
        if (x == 1)
            return 1;
        return 0;
    }

    /**
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

    
    /** This method will read from an input file called "Transaction Actions.csv" and
     *  perform actions listed on the file in a quick succession.
     * 
     * @param file the file name
     * @param bank ArrayList of type Customer
     * @throws FileNotFoundException If the file is not found
     * @throws IOException Input Output Exception
     * @throws SubtractionOverflowException If a subtraction goes past zero from both sides
     * @throws NegativeNumberException If an inputted number is negative
     */
    public void transactionReader(String file, ArrayList<Customer> bank) throws FileNotFoundException, IOException , SubtractionOverflowException, NegativeNumberException{
        try {
            BufferedReader buffReader = new BufferedReader(new FileReader(file)); // read file
            boolean isFound1 = false;
            boolean isFound2 = false;
            String firstLine = buffReader.readLine();
            String[] titles = firstLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            String line;
            while ((line = buffReader.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // splits string every comma & ignores commas inside quotations

                int firstName1Index = findIndex(titles, "From First Name");
                int lastName1Index = findIndex(titles, "From Last Name");
                int acc1Index = findIndex(titles, "From Where");
                int actionIndex = findIndex(titles, "Action");
                int firstName2Index = findIndex(titles, "To First Name");
                int lastName2Index = findIndex(titles, "To Last Name");
                int acc2Index = findIndex(titles, "To Where");
                int amountIndex = findIndex(titles, "Action Amount");
    


            


                while (tokens[actionIndex].contains("pays")) { // while the action contains pays
                    for (Customer firstIter : bank) { // iterate through bank users
                        
                        String toComp1 = tokens[firstName1Index] + " " + tokens[lastName1Index]; // create string to compare based off names
                        String originalName = firstIter.getFirstName() + " " + firstIter.getLastName();
                        if (toComp1.equalsIgnoreCase(originalName)) { // if first name is found
                            isFound1 = true;
                            for (Customer secondIter : bank) { // second iteration through bank users

                                String toComp2 = tokens[firstName2Index] + " " + tokens[lastName2Index]; // create string to compare based off names
                                String originalName2 = secondIter.getFirstName() + " " + secondIter.getLastName();
                                if (toComp2.equals(originalName2)) {// if found
                                    isFound2 = true;

                                    if (tokens[acc1Index].contains("Checking")) {// if sending from checking

                                        if (tokens[acc2Index].contains("Checking")) {// if receiving to checking
                                            firstIter.getCheckingAccount().transferFunction(firstIter.getCheckingAccount(), secondIter.getCheckingAccount(),Double.parseDouble(tokens[amountIndex]));
                                        }
                                        if (tokens[acc2Index].contains("Savings")) {// if receiving to checking
                                            firstIter.getCheckingAccount().transferFunction(firstIter.getCheckingAccount(), secondIter.getSavingsAccount(),Double.parseDouble(tokens[amountIndex]));
                                        }
                                    }

                                    if (tokens[acc1Index].contains("Savings")) { // if sending from savings
                                        if (tokens[acc2Index].contains("Checking")) {// if receiving to checking
                                            firstIter.getSavingsAccount().transferFunction(firstIter.getSavingsAccount(), secondIter.getCheckingAccount(),Double.parseDouble(tokens[amountIndex]));
                                        }
                                        if (tokens[acc2Index].contains("Savings")) {// if receiving to savings
                                            firstIter.getSavingsAccount().transferFunction(firstIter.getSavingsAccount(), secondIter.getSavingsAccount(),Double.parseDouble(tokens[amountIndex]));
                                        }
                                    }
                                    if (isFound2)
                                    break;
                                }
                                else 
                                    isFound2 = false;
                                
                            }
                            if (isFound1)
                            break;
                        }
                        else
                            isFound1 = false;
                    }
                    if(isFound1&&isFound2)
                        break;
                }

                while (tokens[actionIndex].contains("transfers")) {// while the action contains transfers
                    for (Customer firstIter : bank) { // iterate through bank users
                        String toComp1 = tokens[firstName1Index] + " " + tokens[lastName1Index]; // create string to compare based off names
                        if (toComp1.equals(firstIter.getFirstName() + " " + firstIter.getLastName())) { // if first name is found
                            isFound1 = true;
                            boolean isFoundInner = false;

                            if (tokens[acc1Index].contains("Checking")) {// if sending from checking
                                isFoundInner = true;

                                if (tokens[acc2Index].contains("Credit")) // if receiving to checking
                                    firstIter.getCheckingAccount().transferFunction(firstIter.getCheckingAccount(), firstIter.getCreditAccount(), Double.parseDouble(tokens[amountIndex]));

                                if (tokens[acc2Index].contains("Savings")) // if receiving to checking
                                    firstIter.getCheckingAccount().transferFunction(firstIter.getCheckingAccount(), firstIter.getSavingsAccount(), Double.parseDouble(tokens[amountIndex]));

                                if(isFoundInner)
                                    break;
                            }
                            if (tokens[acc1Index].contains("Savings")) { // if sending from savings
                                isFoundInner = true;
                                if (tokens[acc2Index].contains("Checking")){ // if receiving to checking
                                    firstIter.getSavingsAccount().transferFunction(firstIter.getSavingsAccount(), firstIter.getCheckingAccount(), Double.parseDouble(tokens[amountIndex]));
                                }
                            
                                if (tokens[acc2Index].contains("Credit")){ // if receiving to savings
                                    firstIter.getSavingsAccount().transferFunction(firstIter.getSavingsAccount(), firstIter.getCreditAccount(), Double.parseDouble(tokens[amountIndex]));
                                } 
                                
                            }
                            if(isFound1==true && isFoundInner==true)
                                break;
                            
                        }
                        else
                            isFound1 = false;
                    }
                    if(isFound1==true)
                                break;
                }

                while (tokens[actionIndex].contains("inquires")) {// while the action contains inquires
                    for (Customer firstIter : bank) { // iterate through bank users
                        String toComp1 = tokens[firstName1Index] + " " + tokens[lastName1Index];
                        String original = firstIter.getFirstName() + " " + firstIter.getLastName();
                        if (toComp1.equals(original)) { // if first name is found
                            isFound1 = true;
                            if (tokens[acc1Index].contains("Checking"))
                                System.out.println("\n"+toComp1 + " Inquired their Checking Account");
                            if (tokens[acc1Index].contains("Savings"))
                                System.out.println("\n"+toComp1 + " Inquired their Savings Account");
                            if (tokens[acc1Index].contains("Credit"))
                                System.out.println("\n"+toComp1 + " Inquired their Credit Account");
                            
                            if(isFound1)
                                break;
                        }
                        else
                            isFound1 = false;
                    }
                    if(isFound1)
                        break;
                }

                while (tokens[actionIndex].contains("deposits")) { // while the action contains withdraws
                    for (Customer firstIter : bank) { // iterate through bank users
                        String toComp1 = tokens[firstName2Index] + " " + tokens[lastName2Index];
                        String oriString = firstIter.getFirstName() + " " + firstIter.getLastName();
                        if (toComp1.equals(oriString)) { // if first name is found
                            isFound1 = true;

                            if (tokens[acc1Index].contains("Checking")) {
                                firstIter.getCheckingAccount().depositFunction( firstIter.getCheckingAccount().getStartingBalance(), Double.parseDouble(tokens[amountIndex]));
                                System.out.println("\n" + toComp1 + "Deposited: " + tokens[amountIndex] + " from their Checking account");
                            }

                            if (tokens[acc1Index].contains("Savings")) {
                                firstIter.getSavingsAccount().depositFunction( firstIter.getSavingsAccount().getStartingBalance(), Double.parseDouble(tokens[amountIndex]));
                                System.out.println("\n" + toComp1 + "Deposited: " + tokens[amountIndex] + " from their Savings account");
                            }

                            if (tokens[acc1Index].contains("Credit")) {
                                firstIter.getCreditAccount().depositFunction( firstIter.getCreditAccount().getStartingBalance(), Double.parseDouble(tokens[amountIndex]));
                                System.out.println("\n" + toComp1 + " Deposited: " + tokens[amountIndex] + " from their Credit account");
                            }
                            if(isFound1)
                            break;
                        }
                        else    
                            isFound1 = false;
                    }
                    if(isFound1)
                        break;
                }


                while (tokens[actionIndex].contains("withdraws")) { // while the action contains withdraws
                    for (Customer firstIter : bank) { // iterate through bank users
                        String toComp1 = tokens[firstName1Index] + " " + tokens[lastName1Index];
                        String oriString = firstIter.getFirstName() + " " + firstIter.getLastName();
                        if (toComp1.equals(oriString)) { // if first name is found
                            isFound1 = true;

                            if (tokens[acc1Index].contains("Checking")) {
                                firstIter.getCheckingAccount().withdrawFunction( firstIter.getCheckingAccount().getStartingBalance(), Double.parseDouble(tokens[amountIndex]));
                                System.out.println("\n" + toComp1 + "Withdrew: " + tokens[amountIndex] + " from their Checking account");
                            }

                            if (tokens[acc1Index].contains("Savings")) {
                                firstIter.getSavingsAccount().withdrawFunction( firstIter.getSavingsAccount().getStartingBalance(), Double.parseDouble(tokens[amountIndex]));
                                System.out.println("\n" + toComp1 + "Withdrew: " + tokens[amountIndex] + " from their Savings account");
                            }

                            if (tokens[acc1Index].contains("Credit")) {
                                firstIter.getCreditAccount().withdrawFunction( firstIter.getCreditAccount().getStartingBalance(), Double.parseDouble(tokens[amountIndex]));
                                System.out.println("\n" + toComp1 + " Withdrew: " + tokens[amountIndex] + " from their Credit account");
                            }
                            if(isFound1)
                            break;
                        }
                        else    
                            isFound1 = false;
                    }
                    if(isFound1)
                        break;
                }

            }

            buffReader.close();

            System.out.println("\n Transaction Reader Complete.");

        } catch (SubtractionOverflowException e3) {
            System.out.println("The balance cannot flip to negatives or vice versa");

        } catch (NegativeNumberException e4) {
            System.out.println("Cannot input a negative number");
        }
    }
}