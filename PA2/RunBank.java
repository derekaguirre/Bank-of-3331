import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The RunBank class is what will be used to run most of the code for this assignment.
 * A user can log in as a Customer or as a Bank manager. Both choices have different uses.
 *
 * @author Derek Aguirre
 * @version 1.0
 * @since 9/27/2020
 */
public class RunBank {
    
    /** 
     * This method is the primary function to perform depositing. 
     * @param startingBalanceInput The balance before the operation
     * @param depositAmount An amount to deposit
     * @return double
     * @throws NegativeNumberException If the customer inputs a negative number
     */

    public static double depositFunction(double startingBalanceInput, double depositAmount) throws NegativeNumberException {
        if (depositAmount < 0)
            throw new NegativeNumberException("Cannot deposit a negative amount.");
        double depositResult = startingBalanceInput + depositAmount;
        return depositResult;
    }
    

    
    /** 
     * This method serves as the primary function for withdrawing funds from a user's account.
     * @param startingBalanceInput The balance before the operation
     * @param withdrawAmount An amount to withdraw
     * @return double
     * @throws NegativeNumberException If the customer inputs a negative number
     * @throws SubtractionOverflowException If the resulting operation yields the opposite signed value
     */
    public static double withdrawFunction(double startingBalanceInput, double withdrawAmount) throws NegativeNumberException, SubtractionOverflowException {
        if (withdrawAmount < 0)
            throw new NegativeNumberException("Cannot withdraw a negative amount.");
        else if (withdrawAmount > startingBalanceInput)
            throw new SubtractionOverflowException("Cannot withdraw more than your current balance.");
        double withdrawResult = startingBalanceInput - withdrawAmount;
        return withdrawResult;
    }

    /** 
     * This method performs account updating when paying another customer.
     * It will update checking accounts.
     * 
     * @param checkingAcc1 The checking account of the first customer
     * @param checkingAcc2 The checking account of the first customer
     * @param payAmount The amount of funds that will be paid from one customer to the other
     * @throws NegativeNumberException Will be thrown if the pay amount is less than zero
     */
    public static void payFunction(Checking checkingAcc1, Checking checkingAcc2, double payAmount) throws NegativeNumberException{
        if (payAmount < 0)
            throw new NegativeNumberException("Cannot deposit a negative amount.");
        checkingAcc1.setStartingBalance(checkingAcc1.getStartingBalance() - payAmount);
        checkingAcc2.setStartingBalance(checkingAcc2.getStartingBalance() + payAmount);
    }

    
    /** 
     * This method performs account updating when paying another customer.
     * It will update savings accounts.
     * 
     * @param savingsAcc1 The savings account of the first customer
     * @param savingsAcc2 The savings account of the second customer
     * @param payAmount The amount of funds that will be paid from one customer to the other
     * @throws NegativeNumberException Will be thrown if the pay amount is less than zero
     */

    public static void payFunction(Savings savingsAcc1, Savings savingsAcc2, double payAmount)throws NegativeNumberException{
        if (payAmount < 0)
            throw new NegativeNumberException("Cannot deposit a negative amount.");
        savingsAcc1.setStartingBalance(savingsAcc1.getStartingBalance() - payAmount);
        savingsAcc2.setStartingBalance(savingsAcc2.getStartingBalance() + payAmount);
    }
    
    /**
     * Will report on the file "Log.txt" that a user has logged in as a customer. 
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    
    public static void loginLog(String firstName, String lastName)throws IOException, FileNotFoundException{
        try{
            FileWriter fw = new FileWriter("Log.txt", false);
            fw.write(firstName + " " + lastName + " logged in. \n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /** 
     * Will report on the file "Log.txt" that a user has logged out as a customer. 
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public static void logoutLog(String firstName, String lastName)throws IOException, FileNotFoundException{
        try{
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName + " " + lastName + " logged out. \n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /** 
     * Will report on the file "Log.txt" that the currently logged in customer has inquired to check the balance of all of their accounts at once.
     * @param checkingAcc An object of Checking, holds info stored about a customer's checking account
     * @param savingsAcc An object of Savings, holds info stored about a customer's savings account
     * @param creditAcc An object of Credit, holds info stored about a customer's credit account
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public static void inquireBalanceLog(Checking checkingAcc, Savings savingsAcc, Credit creditAcc, String firstName, String lastName)throws IOException, FileNotFoundException{
        try{
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+ " " + lastName + " has requested to see all account balances: \n"+ 
                    "Checking Account (" + checkingAcc.getAccountNumber()+ ") Balance: "+ checkingAcc.getStartingBalance()+"\n"+
                    "Savings Account (" + savingsAcc.getAccountNumber() + ") Balance:"+ savingsAcc.getStartingBalance()+
                    "\nCredit Account (" + creditAcc.getAccountNumber() + ") Balance:"+ creditAcc.getStartingBalance()+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /** 
     * Will report on the file "Log.txt" that the currently logged in customer has inquired to check the balance of their checking account.
     * @param checkingAcc An object of Checking, holds info stored about a customer's checking account
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public static void inquireBalanceLog(Checking checkingAcc, String firstName, String lastName)throws IOException, FileNotFoundException{
        try{
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+ " " + lastName + " has requested to see their Checking Account balance: \n"+ 
                    "Checking Account (" + checkingAcc.getAccountNumber()+ ") Balance: "+ checkingAcc.getStartingBalance()+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /** 
     * Will report on the file "Log.txt" that the currently logged in customer has inquired to check the balance of their savings account.
     * @param savingsAcc An object of Savings, holds info stored about a customer's savings account
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public static void inquireBalanceLog(Savings savingsAcc, String firstName, String lastName)throws IOException, FileNotFoundException{
        try{
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+ " " + lastName + " has requested to see their Savings Account balance: \n"+ 
                    "Savings Account (" + savingsAcc.getAccountNumber() + ") Balance:"+ savingsAcc.getStartingBalance()+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /** 
     * Will report on the file "Log.txt" that the currently logged in customer has inquired to check the balance of their credit account.
     * @param creditAcc An object of Credit, holds info stored about a customer's credit account
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public static void inquireBalanceLog(Credit creditAcc, String firstName, String lastName)throws IOException, FileNotFoundException{
        try{
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+ " " + lastName + " has requested to see their Credit Account balance: \n"+ 
                    "Credit Account (" + creditAcc.getAccountNumber() + ") Balance:"+ creditAcc.getStartingBalance()+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /** 
     * Will report on the file "Log.txt" that the currently logged in customer has deposited a specified amount of funds into their checking account.
     * @param checkingAcc An object of Checking, holds info stored about a customer's checking account
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @param moneyInput The amount of money that the user is depositing
     * @param initial The initial amount of funds before the transaction
     * @param endAmount The resulting amount of funds after the transaction
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public static void depositLog(Checking checkingAcc, String firstName, String lastName, double moneyInput, double initial, double endAmount) throws IOException, FileNotFoundException{
        try{
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+" "+lastName+" with an initial balance of $"+initial+" deposited $"+ moneyInput +" totaling $"+endAmount+ "\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        } 
    }
    
    /** 
     * Will report on the file "Log.txt" that the currently logged in customer has deposited a specified amount of funds into their savings account.
     * @param savingsAcc An object of Savings, holds info stored about a customer's savings account
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @param moneyInput The amount of money that the user is depositing
     * @param initial The initial amount of funds before the transaction
     * @param endAmount The resulting amount of funds after the transaction
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public static void depositLog(Savings savingsAcc, String firstName, String lastName, double moneyInput, double initial, double endAmount) throws IOException, FileNotFoundException{
        try{
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+" "+lastName+" with an initial balance of $"+initial+" deposited $"+ moneyInput +" totaling $"+endAmount+ "\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        } 
    }
    
    /** 
     * Will report on the file "Log.txt" that the currently logged in customer has deposited a specified amount of funds into their credit account.
     * @param creditAcc An object of Credit, holds info stored about a customer's credit account
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @param moneyInput The amount of money that the user is depositing
     * @param initial The initial amount of funds before the transaction
     * @param endAmount The resulting amount of funds after the transaction
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public static void depositLog(Credit creditAcc, String firstName, String lastName, double moneyInput, double initial, double endAmount) throws IOException, FileNotFoundException{
        try{
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+" "+lastName+" with an initial balance of $"+initial+" deposited $"+ moneyInput +" totaling $"+endAmount+ "\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        } 
    }
    
    /** 
     * Will report on the file "Log.txt" that the currently logged in customer has withdrawn a specified amount of funds from their checking account.
     * @param checkingAcc An object of Checking, holds info stored about a customer's checking account
     * @param initial The initial amount of funds before the transaction
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @param withdrawFunds Amount customer specified to withdraw
     * @param endAmount The resulting amount of funds after the transaction
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public static void withdrawLog(Checking checkingAcc, double initial, String firstName, String lastName, double withdrawFunds, double endAmount) throws IOException, FileNotFoundException{
        try{
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+" "+lastName+" with an initial balance of $"+initial+" withdrew $"+ withdrawFunds +" totaling $" + endAmount+ "\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /** 
     * Will report on the file "Log.txt" that the currently logged in customer has withdrawn a specified amount of funds from their savings account.
     * @param savingsAcc An object of Savings, holds info stored about a customer's savings account
     * @param initial The initial amount of funds before the transaction
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @param withdrawFunds Amount customer specified to withdraw
     * @param endAmount The resulting amount of funds after the transaction
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public static void withdrawLog(Savings savingsAcc, double initial, String firstName, String lastName, double withdrawFunds, double endAmount) throws IOException, FileNotFoundException{
        try{
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+" "+lastName+" with an initial balance of $"+initial+" withdrew $"+ withdrawFunds +" totaling $" + endAmount+ "\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    
    /** 
     * Will report on the file "Log.txt" that the currently logged in customer has transferred a specified amount of funds from their checking account into their savings account.
     * @param checkingAcc An object of Checking, holds info stored about a customer's checking account
     * @param savingsAcc An object of Savings, holds info stored about a customer's savings account
     * @param initialAmountFirst The initial amount of funds on checking account before the transaction
     * @param initialAmountSecond The initial amount of funds on savings account before the transaction
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @param amountTransferred The specified amount of funds that has been transferred
     * @param endAmountFirst The resulting amount of funds on checking account before the transaction
     * @param endAmountSecond The resulting amount of funds on savings account before the transaction
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public static void transferLogFromChecking(Checking checkingAcc, Savings savingsAcc, double initialAmountFirst, double initialAmountSecond, String firstName, String lastName, double amountTransferred, double endAmountFirst, double endAmountSecond) throws IOException, FileNotFoundException{
        try{
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+" "+lastName+" has transferred $"+ amountTransferred +" from their Checking Account ("+ checkingAcc.getAccountNumber() + ") to their Savings Account ("+savingsAcc.getAccountNumber()+")\n");
            fw.write("Their Checking now has "+ endAmountFirst +" from a balance of "+ initialAmountFirst +"\n");
            fw.write("Their Savings now has "+ endAmountSecond +" from a balance of "+ initialAmountSecond +"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /** 
     * Will report on the file "Log.txt" that the currently logged in customer has transferred a specified amount of funds from their checking account into their credit account.
     * @param checkingAcc An object of Checking, holds info stored about a customer's checking account
     * @param creditAcc An object of Credit, holds info stored about a customer's credit account
     * @param initialAmountFirst The initial amount of funds on checking account before the transaction
     * @param initialAmountSecond The initial amount of funds on credit account before the transaction
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @param amountTransferred The specified amount of funds that has been transferred
     * @param endAmountFirst The resulting amount of funds on the checking account after the transaction
     * @param endAmountSecond The resulting amount of funds on the credit account after the transaction
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public static void transferLogFromChecking(Checking checkingAcc, Credit creditAcc, double initialAmountFirst, double initialAmountSecond, String firstName, String lastName, double amountTransferred, double endAmountFirst, double endAmountSecond) throws IOException, FileNotFoundException{
        try{
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+" "+lastName+" has transferred $"+ amountTransferred +" from their Checking Account ("+ checkingAcc.getAccountNumber() + ") to their Credit Account ("+ creditAcc.getAccountNumber()+")\n");
            fw.write("Their Checking now has "+ endAmountFirst +" from a balance of "+ initialAmountFirst +"\n");
            fw.write("Their Credit now has "+ endAmountSecond +" from a balance of "+ initialAmountSecond +"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /** 
     * Will report on the file "Log.txt" that the currently logged in customer has transferred specified amount of funds from their savings account into their checking account.
     * @param savingsAcc An object of Savings, holds info stored about a customer's savings account
     * @param checkingAcc An object of Checking, holds info stored about a customer's checking account
     * @param initialAmountFirst The initial amount of funds on savings account before the transaction
     * @param initialAmountSecond The initial amount of funds on checking account before the transaction
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @param amountTransferred The specified amount of funds that has been transferred
     * @param endAmountFirst The resulting amount of funds on the savings account after the transaction
     * @param endAmountSecond The resulting amount of funds on the checking account after the transaction
     * @throws IOException InputOutputException InputOutput Exception
     * @throws FileNotFoundException If file is not found If the file is not found
     */
    public static void transferLogFromSavings(Savings savingsAcc, Checking checkingAcc, double initialAmountFirst, double initialAmountSecond, String firstName, String lastName, double amountTransferred, double endAmountFirst, double endAmountSecond) throws IOException, FileNotFoundException{
        try{
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+" "+lastName+" has transferred $"+ amountTransferred +" from their Savings Account ("+ savingsAcc.getAccountNumber() + ") to their Checking Account ("+ checkingAcc.getAccountNumber()+")\n");
            fw.write("Their Savings now has "+ endAmountFirst +" from a balance of "+ initialAmountFirst +"\n");
            fw.write("Their Checking now has "+ endAmountSecond +" from a balance of "+ initialAmountSecond +"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /** 
     * Will report on the file "Log.txt" that the currently logged in customer has transferred a specified amount of funds from their savings account into their credit account.
     * @param savingsAcc An object of Savings, holds info stored about a customer's savings account
     * @param creditAcc An object of Credit, holds info stored about a customer's credit account
     * @param initialAmountFirst The initial amount of funds on savings account before the transaction
     * @param initialAmountSecond The initial amount of funds on credit account before the transaction
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @param amountTransferred The specified amount of funds that has been transferred
     * @param endAmountFirst The resulting amount of funds on the savings account after the transaction
     * @param endAmountSecond The resulting amount of funds on the credit account after the transaction
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public static void transferLogFromSavings(Savings savingsAcc, Credit creditAcc, double initialAmountFirst, double initialAmountSecond, String firstName, String lastName, double amountTransferred, double endAmountFirst, double endAmountSecond) throws IOException, FileNotFoundException{
        try{
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+" "+lastName+" has transferred $"+ amountTransferred +" from their Savings Account ("+ savingsAcc.getAccountNumber() + ") to their Credit Account ("+ creditAcc.getAccountNumber()+")\n");
            fw.write("Their Savings now has "+ endAmountFirst +" from a balance of "+ initialAmountFirst +"\n");
            fw.write("Their Credit now has "+ endAmountSecond +" from a balance of "+ initialAmountSecond +"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /** 
     * <p>Will report on the file "Log.txt" that the currently logged in customer has paid another customer a specified amount of funds.
     * <br><br>This transaction will be performed by utilizing both customer's checking accounts.
     * </p>
     * @param checkingAcc1 An object of Checking, holds info stored about the first customer's checking account
     * @param checkingAcc2 An object of Checking, holds info stored about the second customer's checking account
     * @param initialAmountFirst The initial amount of funds on the first customer's checking account before the transaction
     * @param initialAmountSecond The initial amount of funds on the second customer's checking account before the transaction
     * @param firstNameFirst The first name of the first customer
     * @param firstNameSecond The first name of the second customer
     * @param lastNameFirst The last name of the first customer
     * @param lastNameSecond The last name of the second customer
     * @param amountTransferred The specified amount of funds that has been transferred
     * @param endAmountFirst The resulting amount of funds on the first customer's checking account after the transaction
     * @param endAmountSecond The resulting amount of funds on the second customer's checking account after the transaction
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public static void payLog(Checking checkingAcc1, Checking checkingAcc2, double initialAmountFirst, double initialAmountSecond, String firstNameFirst, String firstNameSecond, String lastNameFirst, String lastNameSecond, double amountTransferred, double endAmountFirst, double endAmountSecond) throws IOException, FileNotFoundException{
        try{
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstNameFirst+" "+lastNameFirst+" has paid $"+ amountTransferred +" to "+firstNameSecond+ " " + lastNameSecond + "\n");
            fw.write("Sender's Checking Account: ("+checkingAcc1.getAccountNumber()+")\nRecipient's Checking Account: ("+ checkingAcc2.getAccountNumber()+ ")\n");
            fw.write(firstNameFirst+" "+lastNameFirst+" now has "+ endAmountFirst +" from a balance of "+ initialAmountFirst +"\n");
            fw.write(firstNameSecond+" "+lastNameSecond+" now has "+ endAmountSecond +" from a balance of "+ initialAmountSecond +"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }   
    
    /** 
     * <p>Will report on the file "Log.txt" that the currently logged in customer has paid another customer a specified amount of funds.
     * <br><br>This transaction will be performed by utilizing both customer's savings accounts.
     * @param savingsAcc1 An object of Savings, holds info stored about the first customer's savings account
     * @param savingsAcc2 An object of Savings, holds info stored about the second customer's savings account
     * @param initialAmountFirst The initial amount of funds on the first customer's savings account before the transaction
     * @param initialAmountSecond The initial amount of funds on the second customer's savings account before the transaction
     * @param firstNameFirst The first name of the first customer
     * @param firstNameSecond The first name of the second customer
     * @param lastNameFirst The last name of the first customer
     * @param lastNameSecond The last name of the second customer
     * @param amountTransferred The specified amount of funds that has been transferred
     * @param endAmountFirst The resulting amount of funds on the first customer's savings account after the transaction
     * @param endAmountSecond The resulting amount of funds on the second customer's savings account after the transaction
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public static void payLog(Savings savingsAcc1, Savings savingsAcc2, double initialAmountFirst, double initialAmountSecond, String firstNameFirst, String firstNameSecond, String lastNameFirst, String lastNameSecond, double amountTransferred, double endAmountFirst, double endAmountSecond) throws IOException, FileNotFoundException{
        try{
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstNameFirst+" "+lastNameFirst+" has paid $"+ amountTransferred +" to "+firstNameSecond+ " " + lastNameSecond + "\n");
            fw.write("Sender's Savings Account: ("+savingsAcc1.getAccountNumber()+")\nRecipient's Savings Account: ("+ savingsAcc2.getAccountNumber()+ ")\n");
            fw.write(firstNameFirst+" "+lastNameFirst+" now has "+ endAmountFirst +" from a balance of "+ initialAmountFirst +"\n");
            fw.write(firstNameSecond+" "+lastNameSecond+" now has "+ endAmountSecond +" from a balance of "+ initialAmountSecond +"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /** 
     * <p>This method stores the contents of the input file "CS 3331 - Bank Users 2.csv" to an ArrayList of type Customer.
     * <br><br>It will utilize the ArrayList method add to push the information to the data structure.
     * <br><br>The data is pushed according to the fields of the Customer constructor.
     * </p>
     * 
     * @return bank
     * @throws Exception Exception thrown
     * @see Customer
     */
    public static ArrayList<Customer> fileRead()throws Exception{
            BufferedReader buffReader = new BufferedReader(new FileReader("CS 3331 - Bank Users 2.csv"));
            
            ArrayList<Customer> bank = new ArrayList<Customer>(); // Create an array list that will hold the information
            buffReader.readLine();
            String line;
            while ((line = buffReader.readLine()) != null) { 
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // String splits by instances of commas and ignores commas inside quotation marks
                Checking checkingTmp = new Checking(Integer.parseInt(tokens[6]), Double.parseDouble(tokens[9]));
                Savings savingsTmp = new Savings(Integer.parseInt(tokens[7]), Double.parseDouble(tokens[10]));
                Credit creditTmp = new Credit(Integer.parseInt(tokens[8]), Double.parseDouble(tokens[11]));
                bank.add(new Customer(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], checkingTmp, savingsTmp, creditTmp)); // The program will now save the information in accordance to their data types
            }
            buffReader.close();
            return bank;
    }
 
    
    /**
     * Will generate a file "Updated Balance Sheet.csv" that will reflect the updated account balances of every customer.
     * This method is executed if a customer chooses the log out selection on the main directory.
     * The balance sheet will show all information about every user.
     * @param customers An array list of type customer
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found it can also be thrown if the file is open when trying to be written to.
     */
    public static void generateUpdatedBalanceSheet(ArrayList<Customer> customers)throws IOException, FileNotFoundException{
        try{
            FileWriter fw = new FileWriter("Updated Balance Sheet.csv", false);
            fw.write("First Name,Last Name,Date Of Birth,Identification Number,Address,Phone Number,Checking Account Number,Savings Account Number,"+
                     "Credit Account Number,Checking Updated Balance,Savings Updated Balance,Credit Updated Balance");
            for(Customer iter: customers){
                fw.append(String.valueOf("\n" + iter.getFirstName())+",");
                fw.append(String.valueOf(iter.getLastName())+",");
                fw.append(String.valueOf(iter.getDateOfBirth())+",");
                fw.append(String.valueOf(iter.getIdNumber())+",");
                fw.append(String.valueOf(iter.getAddress())+",");
                fw.append(String.valueOf(iter.getPhoneNumber())+",");
                fw.append(String.valueOf(iter.getCheckingAccount().getAccountNumber())+",");
                fw.append(String.valueOf(iter.getSavingsAccount().getAccountNumber())+",");
                fw.append(String.valueOf(iter.getCreditAccount().getAccountNumber())+",");
                fw.append(String.valueOf(iter.getCheckingAccount().getStartingBalance())+",");
                fw.append(String.valueOf(iter.getSavingsAccount().getStartingBalance())+",");
                fw.append(String.valueOf(iter.getCreditAccount().getStartingBalance())+",");
            }
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found. Please close the updated balance sheet before writing to it again.");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }

    
    /** 
     * This method will print a welcome when a customer logs in.
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * 
     */

    public static void printWelcome(String firstName, String lastName){
        System.out.println("\nWelcome back " + firstName + " " + lastName+"!");
        System.out.println();
    }
    /**
     * <p>This method will print the main directory of the bank for a customer.
     * <br><br>It will ask the customer to select 1-5 to have the program perform a specified function.
     * </p>
     * 
     */

    public static void printMainDirectory(){
        System.out.println("\nSelect the desired action:");
        System.out.println("\n (1) \t Inquire Balance \n (2) \t Deposit Cash \n (3) \t Withdraw Cash \n (4) \t Transfer Money \n (5) \t Pay An Individual \n (0) \t Logout \n");
    }
    /**
     * This method will print the main directory of the bank for a manager. 
     */
    public static void printManagerMainDirectory(){
        System.out.println("\nSelect the desired action:");
        System.out.println("\n (1) \t Inquire Accounts \n (0) \t Logout\n");
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
        System.out.println(" (1) \t Full Name \n (2) \t Account Type & Account Number \n (3) \t List All Accounts \n (0) \t Go back \n");
    }

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
 * @throws InputMismatchException The input is not the same data type expected by compiler
 * @throws OutOfRangeException If the user inputs a number outside the range of selections
 * @throws FileNotFoundException If file is not found
 * @throws IOException InputOutputException
 * 
 */

    public static void inquireBalanceChoice(Checking checkingAcc, Savings savingsAcc, Credit creditAcc, String firstName, String lastName) throws InputMismatchException, OutOfRangeException, FileNotFoundException, IOException {
    try{
    Scanner sc = new Scanner(System.in);
    System.out.println("Which account balance would you like to see?");
    printAccountSelectionAll();
    int userChoice = sc.nextInt();
    if(userChoice >4 || userChoice < 0)
        throw new OutOfRangeException("Out of range");
    while (userChoice <= 4 || userChoice >= 0) {
        switch (userChoice) {
            case 1:
                inquireBalanceLog(checkingAcc,savingsAcc,creditAcc, firstName, lastName);
                System.out.println(firstName+" " + lastName + "'s Checking Account (" + checkingAcc.getAccountNumber() + ") Balance:"+ checkingAcc.getStartingBalance());
                System.out.println(firstName+" " + lastName + "'s Savings Account (" + savingsAcc.getAccountNumber() + ") Balance:"+ savingsAcc.getStartingBalance());
                System.out.println(firstName+" " + lastName +"'s Credit Account (" + creditAcc.getAccountNumber() + ") Balance:"+ creditAcc.getStartingBalance());
                printMainDirectory();
                break;
            case 2:
                inquireBalanceLog(checkingAcc, firstName, lastName);
                System.out.println(firstName+" " + lastName + "'s Checking Account (" + checkingAcc.getAccountNumber() + ") Balance:"+ checkingAcc.getStartingBalance());
                printMainDirectory();
                break;
            case 3:
                inquireBalanceLog(savingsAcc, firstName, lastName);
                System.out.println(firstName+" " + lastName + "'s Savings Account (" + savingsAcc.getAccountNumber() + ") Balance:"+ savingsAcc.getStartingBalance());
                printMainDirectory();
                break;
            case 4:
                inquireBalanceLog(creditAcc, firstName, lastName);
                System.out.println(firstName+" " + lastName + "'s Credit Account (" + creditAcc.getAccountNumber() + ") Balance:" + creditAcc.getStartingBalance());
                printMainDirectory();
                break;
            case 0:
                System.out.println("Going back...");
                printMainDirectory();
                break;
            }
            userChoice = -1;
            break;
    }
    } catch (InputMismatchException e1){
        System.out.println("Please enter either an integer or a decimal. Please try again.");
        printMainDirectory();
    } catch (OutOfRangeException e3){
        System.out.println("Please choose between the range of selections. Please try again.");
        printMainDirectory();
    }
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
     * @throws NegativeNumberException If the customer inputs a negative number
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws SubtractionOverflowException If the resulting operation yields the opposite signed value
     * @throws FileNotFoundException If file is not found
     * @throws IOException InputOutputException
     */
    public static void depositChoice(Checking checkingAcc, Savings savingsAcc, Credit creditAcc, String firstName, String lastName) throws NegativeNumberException, InputMismatchException, SubtractionOverflowException, FileNotFoundException, IOException {
    try{
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose account to deposit to.");
        printAccountSelectionNoAll();
        int userChoice = sc.nextInt();
        if (userChoice > 3 || userChoice < 0)
            throw new OutOfRangeException("Out of range");
        while (userChoice <= 3 || userChoice >= 0) {
            switch (userChoice) {
                case 1:// deposit to checking
                    sc = new Scanner(System.in);
                    System.out.println("You have chosen your checking account. How much would you like to deposit?");
                    double depositAmount = sc.nextDouble(); // asks user to input amount
                    System.out.println("\nDepositing $" + depositAmount + " into Checking Account (" + checkingAcc.getAccountNumber() + ")"); // confirmation on amount
                    double initialBalanceCh = checkingAcc.getStartingBalance(); // Saves user's initial balance before the transaction
                    
                    checkingAcc.setStartingBalance(depositFunction(initialBalanceCh, depositAmount));
                    System.out.println("\n Balance has been updated to: $" + checkingAcc.getStartingBalance() + "\n From the old balance of: $" + initialBalanceCh);
                    depositLog(checkingAcc, firstName, lastName, depositAmount, initialBalanceCh, checkingAcc.getStartingBalance());
                    printMainDirectory();
                    break;
    
                case 2:// deposit to savings
                    sc = new Scanner(System.in);
                    System.out.println("You have chosen your savings account. How much would you like to deposit?");
                    depositAmount = sc.nextDouble(); // asks user to input amount
                    System.out.println("\nDepositing $" + depositAmount); // confirmation on amount
                    double initialBalanceSa = savingsAcc.getStartingBalance(); // Saves user's initial balance before the transaction
                    savingsAcc.setStartingBalance(depositFunction(initialBalanceSa, depositAmount));
                    System.out.println("\n Balance has been updated to: $" + savingsAcc.getStartingBalance() + "\n From the old balance of: $" + initialBalanceSa);
                    depositLog(savingsAcc, firstName, lastName, depositAmount, initialBalanceSa, savingsAcc.getStartingBalance());
                    printMainDirectory();
                    break;
    
                case 3:// deposit to credit
                    sc = new Scanner(System.in);
                    System.out.println("You have chosen your credit account. How much would you like to deposit?");
                    depositAmount = sc.nextDouble(); // asks user to input amount
                    System.out.println("\nDepositing $" + depositAmount); // confirmation on amount
                    double initialBalanceCr = creditAcc.getStartingBalance(); // Saves user's initial balance before the transaction
                    creditAcc.setStartingBalance(creditAcc.depositFunction(initialBalanceCr, depositAmount));
                    System.out.println("\n Balance has been updated to: $" + creditAcc.getStartingBalance() + "\n From the old balance of: $" + initialBalanceCr);
                    depositLog(creditAcc, firstName, lastName, depositAmount, initialBalanceCr, creditAcc.getStartingBalance());
                    printMainDirectory();
                    break;
                case 0://go back
                    System.out.println("Going back...");
                    printMainDirectory();
                    break;
            }
            userChoice = -1;
            break;
        }
    } catch (NegativeNumberException e){
        System.out.println("Cannot deposit a negative amount. Please try again.");
        printMainDirectory();
    } catch (InputMismatchException e2){
        System.out.println("Enter either an integer or a decimal. Please try again.");
        printMainDirectory();
    } catch (OutOfRangeException e3){
        System.out.println("Please choose between the range of selections. Please try again.");
        printMainDirectory();
    } catch (SubtractionOverflowException e4){
        System.out.println("Cannot enter more money than currently held in your balance. Please Try again.");
        printMainDirectory();
    }
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
     * @throws NegativeNumberException If the customer inputs a negative number
     * @throws SubtractionOverflowException If the resulting operation yields the opposite signed value
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws OutOfRangeException If the user inputs a number outside the range of selections
     * @throws FileNotFoundException If file is not found
     * @throws IOException InputOutputException
     */
    public static void withdrawChoice(Checking checkingAcc, Savings savingsAcc, String firstName, String lastName) throws NegativeNumberException, SubtractionOverflowException, InputMismatchException, OutOfRangeException, FileNotFoundException, IOException {
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Choose account to withdraw from.");
            printAccountSelectionNoAllNoCredit();
            int userChoice = sc.nextInt();
            if (userChoice > 2 || userChoice < 0)
                throw new OutOfRangeException("Out of range");
            while (userChoice <= 2 || userChoice >= 0) {
                switch (userChoice) { 
                    case 1:
                        sc = new Scanner(System.in);
                        System.out.println("You have chosen your checking account. How much would you like to withdraw?");
                        double withdrawAmount = sc.nextDouble(); // asks user to input amount
                        System.out.println("\nWithdrawing $" + withdrawAmount + " from Checking Account ("+ checkingAcc.getAccountNumber() + ")"); // confirmation on amount
                        double initialBalanceCh = checkingAcc.getStartingBalance(); // Saves user's initial balance before the transaction
                        checkingAcc.setStartingBalance(withdrawFunction(initialBalanceCh, withdrawAmount));
                        System.out.println("\n Balance has been updated to: $" + checkingAcc.getStartingBalance()+ "\n From the old balance of: " + initialBalanceCh);
                        withdrawLog(checkingAcc, initialBalanceCh, firstName, lastName, withdrawAmount, checkingAcc.getStartingBalance());
                        printMainDirectory();
                        break;
    
                    case 2:
                        sc = new Scanner(System.in);
                        System.out.println("You have chosen your savings account. How much would you like to withdraw?");
                        withdrawAmount = sc.nextDouble(); //asks user to input amount
                        System.out.println("\nWithdrawing $" + withdrawAmount +" from Checking Account (" + savingsAcc.getAccountNumber() + ")"); //confirmation on amount
                        double initialBalanceSa = savingsAcc.getStartingBalance(); //Saves user's initial balance before the transaction
                        savingsAcc.setStartingBalance(withdrawFunction(initialBalanceSa, withdrawAmount));
                        System.out.println("\n Balance has been updated to:" + savingsAcc.getStartingBalance() +"\n From the old balance of: "+ initialBalanceSa);
                        withdrawLog(savingsAcc, initialBalanceSa, firstName, lastName, withdrawAmount, savingsAcc.getStartingBalance());
                        printMainDirectory();
                        break;
                    case 0:
                        System.out.println("Going back...");
                        printMainDirectory();
                        break;
                }
            userChoice = -1;
            break;
        }
        } catch (NegativeNumberException e){
            System.out.println("You cannot withdraw a negative amount. Please try again.");
            printMainDirectory();
        } catch (SubtractionOverflowException e2){
            System.out.println("You cannot withdraw more than current balance. Please try again.");
            printMainDirectory();
        } catch (InputMismatchException e3){
            System.out.println("Please enter either an integer or a decimal. Please try again.");
            printMainDirectory();
        } catch (OutOfRangeException e3){
            System.out.println("Please choose between the range of selections. Please try again.");
            printMainDirectory();
        }
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
     * @throws NegativeNumberException If the customer inputs a negative number
     * @throws SubtractionOverflowException If the resulting operation yields the opposite signed value
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws FileNotFoundException If file is not found
     * @throws IOException InputOutputException
     */
    public static void transferChoice(Checking checkingAcc, Savings savingsAcc, Credit creditAcc, String firstName, String lastName) throws NegativeNumberException, SubtractionOverflowException, InputMismatchException, FileNotFoundException, IOException {
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Choose account to transfer funds from.");
            printAccountSelectionNoAllNoCredit();
            int userChoice = sc.nextInt();
            if (userChoice > 2 || userChoice < 0)
                throw new OutOfRangeException("Out of range");
            while (userChoice <= 2 || userChoice >= 0) {
                switch (userChoice) { // choose to transfer from checking or savings
                    case 1:// transfer from checking
                        System.out.println("You have chosen to transfer from your checking account.");
                        transferChoiceNest1(checkingAcc, savingsAcc, creditAcc, firstName, lastName);
                        printMainDirectory();
                        break;
                    case 2:// transfer from savings
                        System.out.println("You have chosen to transfer from your savings account.");
                        transferChoiceNest2(checkingAcc, savingsAcc, creditAcc, firstName, lastName);
                        printMainDirectory();
                        break;
                    case 0://go back
                        System.out.println("Going back...");
                        printMainDirectory();
                        break;
                }
                userChoice = -1;
                break;
            }
        } catch (NegativeNumberException e1){
            System.out.println("You cannot withdraw a negative amount. Please try again.");
            printMainDirectory();
        } catch (SubtractionOverflowException e2){
            System.out.println("You cannot withdraw more than current balance. Please try again.");
            printMainDirectory();
        } catch (InputMismatchException e3){
            System.out.println("Please enter either an integer or a decimal. Please try again.");
            printMainDirectory();
        } catch (OutOfRangeException e4){
            System.out.println("Please choose between the range of selections. Please try again.");
            printMainDirectory();
        }
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
     * @throws NegativeNumberException If the customer inputs a negative number
     * @throws SubtractionOverflowException If the resulting operation yields the opposite signed value
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws OutOfRangeException If the user inputs a number outside the range of selections
     * @throws FileNotFoundException If file is not found
     * @throws IOException InputOutputException
     */
    public static void transferChoiceNest1(Checking checkingAcc, Savings savingsAcc, Credit creditAcc, String firstName, String lastName) throws NegativeNumberException, SubtractionOverflowException, InputMismatchException, OutOfRangeException, FileNotFoundException, IOException {
        try{
            Scanner sc = new Scanner(System.in);
        System.out.println("Which account will recieve the funds?");
        printAccountSelectionNoAllNoChecking();
        int nestedChoice = sc.nextInt();
        if (nestedChoice > 2 || nestedChoice < 0)
                throw new OutOfRangeException("Out of range");
        while(nestedChoice <= 2 || nestedChoice >= 0){ 
            switch(nestedChoice){ 
            case 1:
            System.out.println("You have chosen to transfer from your Checking account to your Savings account.\nPlease enter transfer amount.");
            double transferAmount = sc.nextDouble();
            double initialBalanceCh = checkingAcc.getStartingBalance(); //saves initial checking and savings amount
            double initialBalanceSa = savingsAcc.getStartingBalance();  
            checkingAcc.setStartingBalance(withdrawFunction(initialBalanceCh, transferAmount)); //performs withdraw on checking and deposit on savings
            savingsAcc.setStartingBalance(depositFunction(initialBalanceSa, transferAmount));
            System.out.println("Successfully transferred $"+ transferAmount+" from Checking Account (" + checkingAcc.getAccountNumber() + ") to Savings Account (" +savingsAcc.getAccountNumber() +")\n"); //confirmation
            System.out.println("Checking account funds updated to $"+checkingAcc.getStartingBalance() + " from the initial balance of $" +initialBalanceCh);
            System.out.println("Savings account funds updated to $"+savingsAcc.getStartingBalance() + " from the initial balance of $" +initialBalanceSa);
            transferLogFromChecking(checkingAcc, savingsAcc, initialBalanceCh, initialBalanceSa, firstName, lastName, transferAmount, checkingAcc.getStartingBalance(), savingsAcc.getStartingBalance());
            break;
            case 2://into credit
            System.out.println("You have chosen to transfer from your Checking account to your Credit account.\nPlease enter transfer amount.");
            transferAmount = sc.nextDouble();
            initialBalanceCh = checkingAcc.getStartingBalance(); //saves initial checking and savings amount
            double initialBalanceCr = creditAcc.getStartingBalance();
            checkingAcc.setStartingBalance(withdrawFunction(initialBalanceCh, transferAmount)); //performs withdraw on checking and deposit on credit
            creditAcc.setStartingBalance(creditAcc.depositFunction(initialBalanceCr, transferAmount));
            System.out.println("Successfully transferred $"+ transferAmount+" from Checking Account (" + checkingAcc.getAccountNumber() + ") to Credit Account (" +creditAcc.getAccountNumber() +")\n"); //confirmation
            System.out.println("Checking account funds updated to $"+checkingAcc.getStartingBalance() + " from the initial balance of $" +initialBalanceCh);
            System.out.println("Credit account funds updated to $"+creditAcc.getStartingBalance() + " from the initial balance of $" +initialBalanceCr);
            transferLogFromChecking(checkingAcc, creditAcc, initialBalanceCh, initialBalanceCr, firstName, lastName, transferAmount, checkingAcc.getStartingBalance(), creditAcc.getStartingBalance());
            break;
            case 0://go back
            System.out.println("Going back...");
            System.out.println("Choose account to transfer funds from.");
            printAccountSelectionNoAllNoCredit();
            break;
        }
            nestedChoice = -1;
            break;
    }
        } catch (NegativeNumberException e){
            System.out.println("You cannot withdraw a negative amount. Please try again.");
            printMainDirectory();
        } catch (SubtractionOverflowException e2){
            System.out.println("You cannot withdraw more than current balance. Please try again.");
            printMainDirectory();
        } catch (InputMismatchException e3){
            System.out.println("Please enter either an integer or a decimal. Please try again.");
        } 
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
     * @throws NegativeNumberException If the customer inputs a negative number
     * @throws SubtractionOverflowException If the resulting operation yields the opposite signed value
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws OutOfRangeException If the user inputs a number outside the range of selections
     * @throws FileNotFoundException If file is not found
     * @throws IOException InputOutputException
     */
    public static void transferChoiceNest2(Checking checkingAcc, Savings savingsAcc, Credit creditAcc, String firstName, String lastName) throws NegativeNumberException, SubtractionOverflowException, InputMismatchException, OutOfRangeException, FileNotFoundException, IOException {
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("Which account will recieve the funds?");
            printAccountSelectionNoAllNoSavings();
            int nestedChoice = sc.nextInt();
            if (nestedChoice > 2 || nestedChoice < 0)
                throw new OutOfRangeException("Out of range");
            while (nestedChoice <= 2 || nestedChoice >= 0) {
                switch (nestedChoice) {
            case 1:
            System.out.println("You have chosen to transfer from your Savings Account to your Checking account.\nPlease enter transfer amount.");
            double transferAmount = sc.nextDouble();
            double initialBalanceSa = savingsAcc.getStartingBalance(); //saves initial savings and checking
            double initialBalanceCh = checkingAcc.getStartingBalance();  
            savingsAcc.setStartingBalance(withdrawFunction(initialBalanceSa, transferAmount)); //performs withdraw savings and deposit on checking
            checkingAcc.setStartingBalance(depositFunction(initialBalanceCh, transferAmount));
            System.out.println("Successfully transferred $"+ transferAmount+" from Savings Account (" + savingsAcc.getAccountNumber() + ") to Checking Account (" +checkingAcc.getAccountNumber() +")\n"); //confirmation
            System.out.println("Savings account funds updated to $"+savingsAcc.getStartingBalance() + " from the initial balance of $" +initialBalanceSa);
            System.out.println("Checking account funds updated to $"+checkingAcc.getStartingBalance() + " from the initial balance of $" +initialBalanceCh);
            transferLogFromSavings(savingsAcc, checkingAcc, initialBalanceSa, initialBalanceCh, firstName, lastName, transferAmount, savingsAcc.getStartingBalance(), checkingAcc.getStartingBalance());
            break;
            case 2:
            System.out.println("You have chosen to transfer from your Savings Account to your Credit account.\nPlease enter transfer amount.");
            transferAmount = sc.nextDouble();
            initialBalanceSa = savingsAcc.getStartingBalance(); //saves initial savings and credit amounts
            double initialBalanceCr = creditAcc.getStartingBalance(); 
            savingsAcc.setStartingBalance(withdrawFunction(initialBalanceSa, transferAmount)); //performs withdraw on savings and deposit on credit(using the deposit from Credit)
            creditAcc.setStartingBalance(creditAcc.depositFunction(initialBalanceCr, transferAmount));  
            System.out.println("Successfully transferred $"+ transferAmount+" from Savings Account (" + savingsAcc.getAccountNumber() + ") to Credit Account (" +creditAcc.getAccountNumber() +")\n"); //confirmation
            System.out.println("Savings account funds updated to $"+savingsAcc.getStartingBalance() + " from the initial balance of $" +initialBalanceSa);
            System.out.println("Credit account funds updated to $"+creditAcc.getStartingBalance() + " from the initial balance of $" +initialBalanceCr);
            transferLogFromSavings(savingsAcc, creditAcc, initialBalanceSa, initialBalanceCr, firstName, lastName, transferAmount, savingsAcc.getStartingBalance(), creditAcc.getStartingBalance());
            break;
            case 0:
            System.out.println("Going back...");
            System.out.println("Choose account to transfer funds from.");
            printAccountSelectionNoAllNoCredit();
            break;
        }
        nestedChoice = -1;
        break;
        }
        } catch (NegativeNumberException e) {
        System.out.println("You cannot withdraw a negative amount. Please try again.");
        printMainDirectory();
        } catch (SubtractionOverflowException e2) {
        System.out.println("You cannot withdraw more than current balance. Please try again.");
        printMainDirectory();
        } catch (InputMismatchException e3) {
        System.out.println("Please enter either an integer or a decimal. Please try again.");
        printMainDirectory();
        }  catch (OutOfRangeException e4){
            System.out.println("Please choose between the range of selections. Please try again.");
            printMainDirectory();
        }
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
     * @throws NegativeNumberException If the customer inputs a negative number
     * @throws SubtractionOverflowException If the resulting operation yields the opposite signed value
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws OutOfRangeException If the user inputs a number outside the range of selections
     * @throws FileNotFoundException If file is not found
     * @throws IOException InputOutputException
     */
    public static void payNest(Checking checkingAcc1, Savings savingsAcc1, Credit creditAcc1, Checking checkingAcc2, Savings savingsAcc2, Credit creditAcc2, String firstName1, String firstName2, String lastName1, String lastName2) throws NegativeNumberException, SubtractionOverflowException, InputMismatchException, OutOfRangeException, FileNotFoundException, IOException {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Which account will recieve the funds?");
            printAccountSelectionNoAllNoCredit();
            int nestedChoice = sc.nextInt();
            if (nestedChoice > 2 || nestedChoice < 0)
                throw new OutOfRangeException("Out of range");
            while (nestedChoice <= 2 || nestedChoice >= 0) {
                switch (nestedChoice) { 
                    case 1:
                        System.out.println("You have chosen to pay their Checking account from your Checking account.\nPlease enter pay amount.");
                        double payAmount = sc.nextDouble();
                        double initialBalanceCh1 = checkingAcc1.getStartingBalance(); //saves initial balance of both users
                        double initialBalanceCh2 = checkingAcc2.getStartingBalance(); 
                        checkingAcc1.setStartingBalance(withdrawFunction(checkingAcc1.getStartingBalance(), payAmount)); //performs withdraw from checking account of the first user
                        checkingAcc2.setStartingBalance(depositFunction(checkingAcc2.getStartingBalance(), payAmount));  //performs deposit into checking account of the second user
                        System.out.println("Successfully transferred $"+ payAmount+" from your Checking Account (" + checkingAcc1.getAccountNumber() + ") to their Checking Account (" +checkingAcc2.getAccountNumber() +")\n"); //confirmation
                        System.out.println("Your checking account funds updated to $"+checkingAcc1.getStartingBalance() + " from the initial balance of $" +initialBalanceCh1);
                        System.out.println("Their checking account funds updated to $"+checkingAcc2.getStartingBalance() + " from the initial balance of $" +initialBalanceCh2);

                        payLog(checkingAcc1, checkingAcc2, initialBalanceCh1, initialBalanceCh2, firstName1, lastName1, firstName2, lastName2, payAmount, checkingAcc1.getStartingBalance(), checkingAcc2.getStartingBalance());
                        printMainDirectory();
                        break;
                    case 2:
                        System.out.println("You have chosen to pay their Savings account from your Savings account.\nPlease enter pay amount.");
                        payAmount = sc.nextDouble();
                        double initialBalanceSa1 = savingsAcc1.getStartingBalance(); //saves initial balance of both users
                        double initialBalanceSa2 = savingsAcc2.getStartingBalance();  
                        savingsAcc1.setStartingBalance(withdrawFunction(savingsAcc1.getStartingBalance(), payAmount)); //performs withdraw from savings account of the first user
                        savingsAcc2.setStartingBalance(depositFunction(savingsAcc2.getStartingBalance(), payAmount));  //performs deposit into savings account of the second user
                        System.out.println("Successfully transferred $"+ payAmount+" from your Checking Account (" + savingsAcc1.getAccountNumber() + ") to their Checking Account (" +savingsAcc2.getAccountNumber() +")\n"); //confirmation
                        System.out.println("Your checking account funds updated to $"+savingsAcc1.getStartingBalance() + " from the initial balance of $" +initialBalanceSa1);
                        System.out.println("Their checking account funds updated to $"+savingsAcc2.getStartingBalance() + " from the initial balance of $" +initialBalanceSa2);
                        payLog(savingsAcc1, savingsAcc2, initialBalanceSa1, initialBalanceSa2, firstName1, lastName1, firstName2, lastName2, payAmount, savingsAcc1.getStartingBalance(), savingsAcc2.getStartingBalance());
                        printMainDirectory();
                        break;
                    case 0:
                        System.out.println("Going back...");
                        printMainDirectory();
                        break;
                }
                nestedChoice = -1;
                break;
            }
        } catch (NegativeNumberException e) {
            System.out.println("You cannot withdraw a negative amount. Please try again.");
            printMainDirectory();
        } catch (SubtractionOverflowException e2) {
            System.out.println("You cannot withdraw more than current balance. Please try again.");
            printMainDirectory();
        } catch (InputMismatchException e3) {
            System.out.println("Please enter either an integer or a decimal. Please try again.");
            printMainDirectory();
        } catch (OutOfRangeException e4) {
            System.out.println("Please choose between the range of selections. Please try again.");
            printMainDirectory();
        }
}


    /** 
     * <p>This method handles the choice-making when a manager logs in.
     * <br><br>It will prompt the manager to select which service is available to them.
     * <br><br>Entering 1 will allow the manager to see the options for inquiring customer account information.
     * <br><br>Entering 0 will log the manager out.
     * </p>
     * @param customerObject An instance of an ArrayList of type Customer
     * @throws InputMismatchException The input is not the same data type expected by compiler
     * @throws OutOfRangeException If the user inputs a number outside the range of selections
     * @throws Exception Exception thrown
     */
    public static void managerChoice(ArrayList<Customer> customerObject) throws InputMismatchException, OutOfRangeException, Exception{
      try{
        Scanner sc = new Scanner(System.in);
        System.out.println("Logged in as a manager. What would you like to do?");
        printManagerMainDirectory();
        int userChoice = sc.nextInt();
        if(userChoice >1 || userChoice < 0)
            throw new OutOfRangeException("Out of range");
        while (userChoice <= 1 || userChoice >= 0) {
        switch (userChoice) {
            case 1:
                managerChoiceNest1(customerObject);
                printManagerMainDirectory();
                break;
            case 0:
                System.out.println("Successfully Logged out.");
                System.exit(0);
                break;
            }
            userChoice = -1;
            break;
        }
        } catch (InputMismatchException e1) {
        System.out.println("Please enter either an integer or a decimal. Please try again.");
            printManagerMainDirectory();
        } catch (OutOfRangeException e2){
            System.out.println("Please choose between the range of selections. Please try again.");
            printManagerMainDirectory();
        }
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
     * @throws Exception Exception thrown
     */
    public static void managerChoiceNest1(ArrayList<Customer> customerObject) throws Exception {
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println("How will you be inquiring accounts?");
            printManagerInquirySelection();
            int userChoice = sc.nextInt();
            if(userChoice >3 || userChoice < 0)
                throw new OutOfRangeException("Out of range");
            while (userChoice <= 3 || userChoice >= 0) {
            switch (userChoice) {
                case 1:
                System.out.println("Please enter individual's First Name.");
                String fName = sc.next();
                System.out.println("Please enter individual's Last Name.");
                String lName = sc.next();
                String nameInput = (fName + " "+ lName);
                for (Customer cTmp : customerObject) {
                    String toComp = (cTmp.getFirstName()+" "+ cTmp.getLastName());
                    if(nameInput.contentEquals(toComp)){// if true, saves values on current iteration
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
                        break;
                    }
                }
                break;
                case 2:
                    managerChoiceInquiryByAccNest(customerObject);
                    break;
                case 3:
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
                    System.out.println("Going back...");
                    printManagerMainDirectory();
                    break;
                }
                userChoice = -1;
                break;
            }
            } catch (InputMismatchException e1) {
            System.out.println("Please enter either an integer or a decimal. Please try again.");
                printManagerMainDirectory();
            } catch (OutOfRangeException e2){
                System.out.println("Please choose between the range of selections. Please try again.");
                printManagerMainDirectory();
            }
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
     * @throws Exception Exception thrown
     */
    public static void managerChoiceInquiryByAccNest(ArrayList<Customer> customerObject) throws Exception{
        try{
            Scanner sc = new Scanner(System.in); 
            System.out.println("Please choose account type.");
            printAccountSelectionNoAll();
            int userChoice = sc.nextInt();
            if(userChoice >3 || userChoice < 0)
                throw new OutOfRangeException("Out of range");
            while (userChoice <= 3 || userChoice >= 0) {
            switch (userChoice) {
                case 1:
                    System.out.println("Please enter individual's checking account number.");
                    int accNumInput = sc.nextInt();
                    for (Customer chAcc : customerObject) {
                        if(accNumInput == chAcc.getCheckingAccount().getAccountNumber()){ // if true, saves values on current iteration
                            System.out.println("User found successfully.");
                            String name = (chAcc.getFirstName()+ " " + chAcc.getLastName());
                            String info =("\nDate of Birth: \t"+ chAcc.getDateOfBirth() + "\nIdentification Number: \t" + chAcc.getIdNumber()+ "\nAddress: \t" + chAcc.getAddress() + "\nPhone Number: \t" + chAcc.getPhoneNumber());
                            int accInfoCh =(chAcc.getCheckingAccount().getAccountNumber());
                            int accInfoSa =(chAcc.getSavingsAccount().getAccountNumber());
                            int accInfoCr =(chAcc.getCreditAccount().getAccountNumber());
                            double accBalCh = (chAcc.getCheckingAccount().getStartingBalance());
                            double accBalSa = (chAcc.getSavingsAccount().getStartingBalance());
                            double accBalCr = (chAcc.getCreditAccount().getStartingBalance());
                            System.out.println("Name: \t" + name + info +"\nChecking account Number & Balance: \t" + accInfoCh+ ", "+ accBalCh +"\nSavings account Number & Balance: \t" + accInfoSa+ ", "+ accBalSa+"\nCredit account Number & Balance: \t" + accInfoCr+ ", "+ accBalCr);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Please enter individual's savings account number.");
                    accNumInput = sc.nextInt();
                    for (Customer saAcc : customerObject) {
                        if(accNumInput == saAcc.getSavingsAccount().getAccountNumber()){ // if true, saves values on current iteration
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
                    }
                    break;
                case 3:
                System.out.println("Please enter individual's savings account number.");
                    accNumInput = sc.nextInt();
                    for (Customer crAcc : customerObject) {
                        if(accNumInput == crAcc.getCreditAccount().getAccountNumber()){ // if true, saves values on current iteration
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
                    }    
                    break;
                case 0://go back
                    System.out.println("Going back...");
                    printManagerInquirySelection();
                    break;
                }
                userChoice = -1;
                break;
            }
            } catch (InputMismatchException e1) {
            System.out.println("Please enter either an integer or a decimal. Please try again.");
                printManagerMainDirectory();
            } catch (OutOfRangeException e2){
                System.out.println("Please choose between the range of selections. Please try again.");
                printManagerMainDirectory();
            }
        }

    public static void main(String[] args) throws Exception, InputMismatchException, SubtractionOverflowException, NegativeNumberException, NoSuchElementException {
        try{
            Scanner sc = new Scanner(System.in); //Create scanner
            ArrayList<Customer> bank = fileRead(); //Create an array list that will hold the information
            System.out.println("\nWelcome to the Bank of the Miners! \nWhich type of user are you? \n(1) Customer \n(2) Bank Manager"); //asks if user is customer or manager
            int userChoice = sc.nextInt(); // saves user type selection
            if(userChoice >2 || userChoice < 0)
                throw new OutOfRangeException("Out of range");
            while (userChoice == 1) { // boolean operation most of the code runs off of
                System.out.println("\nEnter Identification Number"); // ask user to enter id
                String idNum = sc.next();
                // -----------------------LOGIN------------------------------
                for (Customer firstUser : bank) { // The program will verify if the user exists in the bank
                    if (idNum.compareTo(firstUser.getIdNumber()) == 0) {
                        loginLog(firstUser.getFirstName(), firstUser.getLastName());
                        printWelcome(firstUser.getFirstName(), firstUser.getLastName()); // prints welcome
                        printMainDirectory(); // prints main choice directory
                        int choice = sc.nextInt();
                        if(userChoice >5 || userChoice < 0)
                            throw new OutOfRangeException("Out of range");
                        while (choice <= 5 && choice >= 0) { // As long as the user selects a choice belonging to the menu the program will stay running
                            switch (choice) {
                                case 0: // Will log the user out
                                    logoutLog(firstUser.getFirstName(), firstUser.getLastName());
                                    System.out.println("Successfully logged out");
                                    generateUpdatedBalanceSheet(bank);
                                    System.exit(0);
                                    break;
                                case 1:// Will inquire a balance
                                    inquireBalanceChoice(firstUser.getCheckingAccount(), firstUser.getSavingsAccount(), firstUser.getCreditAccount(), firstUser.getFirstName(), firstUser.getLastName());//calls nested method
                                    choice = sc.nextInt();
                                    if(choice >5 || choice < 0)
                                        throw new OutOfRangeException("Out of range");
                                    break;
                                case 2:// Will deposit money into user's account
                                    depositChoice(firstUser.getCheckingAccount(), firstUser.getSavingsAccount(), firstUser.getCreditAccount(), firstUser.getFirstName(), firstUser.getLastName());//calls nested method
                                    choice = sc.nextInt();
                                    if(choice >5 || choice < 0)
                                        throw new OutOfRangeException("Out of range");
                                    break;
                                case 3:// Will withdraw money from a user's account
                                    withdrawChoice(firstUser.getCheckingAccount(), firstUser.getSavingsAccount(), firstUser.getFirstName(), firstUser.getLastName());//calls nested method
                                    choice = sc.nextInt(); // resets the choice for the next prompt
                                    if(choice >5 || choice < 0)
                                        throw new OutOfRangeException("Out of range");
                                    break;

                                case 4:// Will move money from account to account
                                    transferChoice(firstUser.getCheckingAccount(), firstUser.getSavingsAccount(), firstUser.getCreditAccount(), firstUser.getFirstName(), firstUser.getLastName());
                                    choice = sc.nextInt(); //resets the choice for the next prompt
                                    if(choice >5 || choice < 0)
                                        throw new OutOfRangeException("Out of range");
                                    break;
                                case 5:// Will send money to another person's specified account
                                    System.out.println("Please enter individual's First Name.");
                                    String fName = sc.next();
                                    System.out.println("Please enter individual's Last Name.");
                                    String lName = sc.next();
                                    String nameInput = (fName + " " + lName);
                                    for (Customer secondUser : bank) { // Verifies if the second user exists
                                        String toComp = secondUser.getFirstName()+" "+secondUser.getLastName();
                                        if (nameInput.equalsIgnoreCase(toComp)) {
                                            payNest(firstUser.getCheckingAccount(), firstUser.getSavingsAccount(), firstUser.getCreditAccount(), secondUser.getCheckingAccount(), secondUser.getSavingsAccount(), secondUser.getCreditAccount(), firstUser.getFirstName(), firstUser.getLastName(), secondUser.getFirstName(), secondUser.getLastName());
                                            break;
                                        }
                                    }
                                    choice = sc.nextInt(); // resets the choice for the next prompt
                                    if (choice > 5 || choice < 0)
                                        throw new OutOfRangeException("Out of range");
                                    break;
                            }
                        }
                        choice = -1;
                    }
                }
                userChoice = -1;
            }
            while (userChoice==2){ //manager code execution
                managerChoice(bank);
                userChoice = -1;
            }

        } catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        } catch (IOException e2) {
            System.out.print("IOException");
        } catch (InputMismatchException e3) {
            System.out.println("Input mismatch, try again.");
        } catch (SubtractionOverflowException e4) {
            System.out.println("Not enough funds. Please try again.");
        } catch (NegativeNumberException e5) {
            System.out.println("Cannot subtract");
        } catch (NoSuchElementException e6) {
            System.out.println("NoSuchElementException");
        } catch (OutOfRangeException e7){
            System.out.println("Choose a number in the range of selections. Please try again.");
        }
    }

}