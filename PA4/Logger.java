import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * Taken from Derek Aguirre
 * 
 * The Logger class will be used hold all operations involving logging user actions to a text file.
 * Only require one instance of logger so it is made to be singleton.
 *
 * @author Derek Aguirre
 * @version 1.0
 * @since 10/10/2020
 * @see RunBank
 */

public class Logger implements Printable{
    private static Logger SingletonLogger = new Logger();

    private Logger(){

    }

    public static Logger getInstance(){
        return SingletonLogger;
    }

    public static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
    
    /** 
     * 
     * Taken from Derek Aguirre
     * 
     * Will report on the file "Log.txt" that a user has entered the program. This method
     * resets the log when executed. 
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public void print(ArrayList<Customer> custList)throws IOException, FileNotFoundException{
        
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", false);
            fw.write("Program~ Started\n");
            fw.write("Program~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }

    /** 
     * 
     * Taken from Derek Aguirre
     * 
     * Will report on the file "Log.txt" that a user has exited the program. 

     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */

    public void exitProgramLog()throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write("Program~ Exited\n");
            fw.write("Program~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }


    /**
     * 
     * Taken from Anthony Jasso
     * 
     * Will report on the file "Log.txt" that a user has logged in as a customer. 
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    
    public void reportLogin(String firstName, String lastName)throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName + " " + lastName + "~ Login\n");
            fw.write(firstName + " " + lastName + "~ Login Date~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /** 
     * 
     * Taken from Anthony Jasso
     * 
     * Will report on the file "Log.txt" that a user has logged out as a customer. 
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public void reportLogout(String firstName, String lastName)throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName + " " + lastName + "~ Logout\n");
            fw.write(firstName + " " + lastName + "~ Logout Date~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /**
     * 
     * Taken from Derek Aguirre
     *  
     * Will report on the file "Log.txt" that the currently logged in customer has inquired to check the balance of all of their accounts at once.
     * @param checkingAcc An object of Checking, holds info stored about a customer's checking account
     * @param savingsAcc An object of Savings, holds info stored about a customer's savings account
     * @param creditAcc An object of Credit, holds info stored about a customer's credit account
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public void inquireBalanceLog(Checking checkingAcc, Savings savingsAcc, Credit creditAcc, String firstName, String lastName)throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+ " " + lastName + "~ Inquired All Balances \n"); 
            fw.write("Checking Account (" + checkingAcc.getAccountNumber()+ ") Balance~ $"+ checkingAcc.getStartingBalance()+"\n");
            fw.write("Savings Account (" + savingsAcc.getAccountNumber() + ") Balance~ $"+ savingsAcc.getStartingBalance()+"\n");
            fw.write("Credit Account (" + creditAcc.getAccountNumber() + ") Balance~ $"+ creditAcc.getStartingBalance()+"\n");
            fw.write(firstName + " " + lastName + "~ Inquired Date~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /** 
     * 
     * Taken from Derek Aguirre
     * 
     * Will report on the file "Log.txt" that the currently logged in customer has inquired to check the balance of their checking account.
     * @param checkingAcc An object of Checking, holds info stored about a customer's checking account
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public void inquireBalanceLog(Checking checkingAcc, String firstName, String lastName)throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+ " " + lastName + "~ Inquired Checking Account Balance\n");
            fw.write("Checking Account (" + checkingAcc.getAccountNumber()+ ") Balance~ $"+ checkingAcc.getStartingBalance()+"\n");
            fw.write(firstName + " " + lastName + "~ Inquired Date~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /** 
     * 
     * Taken from Derek Aguirre
     * 
     * Will report on the file "Log.txt" that the currently logged in customer has inquired to check the balance of their savings account.
     * @param savingsAcc An object of Savings, holds info stored about a customer's savings account
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public void inquireBalanceLog(Savings savingsAcc, String firstName, String lastName)throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+ " " + lastName + "~ Inquired Savings Account Balance\n"); 
            fw.write("Savings Account (" + savingsAcc.getAccountNumber() + ") Balance~ $"+ savingsAcc.getStartingBalance()+"\n");
            fw.write(firstName + " " + lastName + "~ Inquired Date~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /** 
     * 
     * Taken from Derek Aguirre
     * 
     * Will report on the file "Log.txt" that the currently logged in customer has inquired to check the balance of their credit account.
     * @param creditAcc An object of Credit, holds info stored about a customer's credit account
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public void inquireBalanceLog(Credit creditAcc, String firstName, String lastName)throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+ " " + lastName + "~ Inquired Credit Account Balance\n");
            fw.write("Credit Account (" + creditAcc.getAccountNumber() + ") Balance~ $"+ creditAcc.getStartingBalance()+"\n");
            fw.write(firstName + " " + lastName + "~ Inquired Date~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /** 
     * 
     * Taken from Derek Aguirre
     * 
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
    public void depositLog(Checking checkingAcc, String firstName, String lastName, double moneyInput, double initial, double endAmount) throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+" "+lastName +"~ Deposited into Checking\n");
            fw.write(firstName+" "+lastName +"~ Deposit Amount~ $" + moneyInput+"\n");
            fw.write(firstName+" "+lastName +"~ Checking Account Initial Balance~ $"+ initial+"\n");
            fw.write(firstName+" "+lastName +"~ Checking Account Ending Balance~ $"+ endAmount+"\n");
            fw.write(firstName + " " + lastName + "~ Date~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        } 
    }
    
    /** 
     * 
     * Taken from Derek Aguirre
     * 
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
    public void depositLog(Savings savingsAcc, String firstName, String lastName, double moneyInput, double initial, double endAmount) throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+" "+lastName +"~ Deposited into Savings\n");
            fw.write(firstName+" "+lastName +"~ Deposit Amount~ $" + moneyInput+"\n");
            fw.write(firstName+" "+lastName +"~ Savings Account Initial Balance~ $"+ initial+"\n");
            fw.write(firstName+" "+lastName +"~ Savings Account Ending Balance~ $"+ endAmount+"\n");
            fw.write(firstName + " " + lastName + "~ Date~ "+ dateFormat.format(currentDate)+"\n\n");

            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        } 
    }
    
    /** 
     * 
     * Taken from Derek Aguirre
     * 
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
    public void depositLog(Credit creditAcc, String firstName, String lastName, double moneyInput, double initial, double endAmount) throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+" "+lastName +"~ Deposited into Credit\n");
            fw.write(firstName+" "+lastName +"~ Deposit Amount~ $" + moneyInput+"\n");
            fw.write(firstName+" "+lastName +"~ Credit Account Initial Balance~ $"+ initial+"\n");
            fw.write(firstName+" "+lastName +"~ Credit Account Ending Balance~ $"+ endAmount+"\n");
            fw.write(firstName + " " + lastName + "~ Date~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        } 
    }
    
    /** 
     * 
     * Taken from Derek Aguirre
     * 
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
    public void withdrawLog(Checking checkingAcc, double initial, String firstName, String lastName, double withdrawFunds, double endAmount) throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+" "+lastName +"~ Withdrew from Checking\n");
            fw.write(firstName+" "+lastName +"~ Withdraw Amount~ $" + withdrawFunds+"\n");
            fw.write(firstName+" "+lastName +"~ Checking Account Initial Balance~ $"+ initial+"\n");
            fw.write(firstName+" "+lastName +"~ Checking Account Ending Balance~ $"+ endAmount+"\n");
            fw.write(firstName + " " + lastName + "~ Date~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /** 
     * 
     * Taken from Derek Aguirre
     * 
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
    public void withdrawLog(Savings savingsAcc, double initial, String firstName, String lastName, double withdrawFunds, double endAmount) throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+" "+lastName +"~ Withdrew from Savings\n");
            fw.write(firstName+" "+lastName +"~ Withdraw Amount~ $" + withdrawFunds+"\n");
            fw.write(firstName+" "+lastName +"~ Savings Account Initial Balance~ $"+ initial+"\n");
            fw.write(firstName+" "+lastName +"~ Savings Account Ending Balance~ $"+ endAmount+"\n");
            fw.write(firstName + " " + lastName + "~ Date~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    
    /** 
     * 
     * Taken from Derek Aguirre
     * 
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
    public void transferLogFromChecking(Checking checkingAcc, Savings savingsAcc, double initialAmountFirst, double initialAmountSecond, String firstName, String lastName, double amountTransferred, double endAmountFirst, double endAmountSecond) throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+" "+lastName+"~ Transferred from Checking Account ("+ checkingAcc.getAccountNumber() + ")\n");
            fw.write(firstName+" "+lastName+"~ Transferred to Savings Account ("+savingsAcc.getAccountNumber()+")\n");

            fw.write(firstName+" "+lastName+"~ Amount Sent~ $" + amountTransferred+"\n");
            fw.write(firstName+" "+lastName+"~ Amount Received~ $" + amountTransferred+"\n");

            fw.write(firstName+" "+lastName+"~ Checking Account ("+ checkingAcc.getAccountNumber() +") Initial Balance~ $" + initialAmountFirst+"\n"); 
            fw.write(firstName+" "+lastName+"~ Savings Account ("+ savingsAcc.getAccountNumber() +") Initial Balance~ $" + initialAmountSecond+"\n");

            fw.write(firstName+" "+lastName+"~ Checking Account ("+ checkingAcc.getAccountNumber() +") Ending Balance~ $" + endAmountFirst+"\n"); 
            fw.write(firstName+" "+lastName+"~ Savings Account ("+ savingsAcc.getAccountNumber() +") Ending Balance~ $" + endAmountSecond+"\n");

            fw.write(firstName + " " + lastName + "~ Date Sent~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.write(firstName + " " + lastName + "~ Date Received~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /** 
     * 
     * Taken from Derek Aguirre
     * 
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
    public void transferLogFromChecking(Checking checkingAcc, Credit creditAcc, double initialAmountFirst, double initialAmountSecond, String firstName, String lastName, double amountTransferred, double endAmountFirst, double endAmountSecond) throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+" "+lastName+"~ Transferred from Checking Account ("+ checkingAcc.getAccountNumber() + ")\n");
            fw.write(firstName+" "+lastName+"~ Transferred to Credit Account ("+creditAcc.getAccountNumber()+")\n");

            fw.write(firstName+" "+lastName+"~ Amount Sent~ $" + amountTransferred+"\n");
            fw.write(firstName+" "+lastName+"~ Amount Received~ $" + amountTransferred+"\n");

            fw.write(firstName+" "+lastName+"~ Checking Account ("+ checkingAcc.getAccountNumber() +") Initial Balance~ $" + initialAmountFirst+"\n"); 
            fw.write(firstName+" "+lastName+"~ Credit Account ("+ creditAcc.getAccountNumber() +") Initial Balance~ $" + initialAmountSecond+"\n");

            fw.write(firstName+" "+lastName+"~ Checking Account ("+ checkingAcc.getAccountNumber() +") Ending Balance~ $" + endAmountFirst+"\n"); 
            fw.write(firstName+" "+lastName+"~ Credit Account ("+ creditAcc.getAccountNumber() +") Ending Balance~ $" + endAmountSecond+"\n");

            fw.write(firstName + " " + lastName + "~ Date Sent~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.write(firstName + " " + lastName + "~ Date Received~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /** 
     * 
     * Taken from Derek Aguirre
     * 
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
    public void transferLogFromSavings(Savings savingsAcc, Checking checkingAcc, double initialAmountFirst, double initialAmountSecond, String firstName, String lastName, double amountTransferred, double endAmountFirst, double endAmountSecond) throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+" "+lastName+"~ Transferred from Savings Account ("+ savingsAcc.getAccountNumber() + ")\n");
            fw.write(firstName+" "+lastName+"~ Transferred to Checking Account ("+checkingAcc.getAccountNumber()+")\n");

            fw.write(firstName+" "+lastName+"~ Amount Sent~ $" + amountTransferred+"\n");
            fw.write(firstName+" "+lastName+"~ Amount Received~ $" + amountTransferred+"\n");

            fw.write(firstName+" "+lastName+"~ Savings Account ("+ savingsAcc.getAccountNumber() +") Initial Balance~ $" + initialAmountFirst+"\n"); 
            fw.write(firstName+" "+lastName+"~ Checking Account ("+ checkingAcc.getAccountNumber() +") Initial Balance~ $" + initialAmountSecond+"\n");

            fw.write(firstName+" "+lastName+"~ Savings Account ("+ savingsAcc.getAccountNumber() +") Ending Balance~ $" + endAmountFirst+"\n"); 
            fw.write(firstName+" "+lastName+"~ Checking Account ("+ checkingAcc.getAccountNumber() +") Ending Balance~ $" + endAmountSecond+"\n");

            fw.write(firstName + " " + lastName + "~ Date Sent~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.write(firstName + " " + lastName + "~ Date Received~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /** 
     * 
     * Taken from Derek Aguirre
     * 
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
    public void transferLogFromSavings(Savings savingsAcc, Credit creditAcc, double initialAmountFirst, double initialAmountSecond, String firstName, String lastName, double amountTransferred, double endAmountFirst, double endAmountSecond) throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstName+" "+lastName+"~ Transferred from Savings Account ("+ savingsAcc.getAccountNumber() + ")\n");
            fw.write(firstName+" "+lastName+"~ Transferred to Credit Account ("+creditAcc.getAccountNumber()+")\n");

            fw.write(firstName+" "+lastName+"~ Amount Sent~ $" + amountTransferred+"\n");
            fw.write(firstName+" "+lastName+"~ Amount Received~ $" + amountTransferred+"\n");

            fw.write(firstName+" "+lastName+"~ Savings Account ("+ savingsAcc.getAccountNumber() +") Initial Balance~ $" + initialAmountFirst+"\n"); 
            fw.write(firstName+" "+lastName+"~ Credit Account ("+ creditAcc.getAccountNumber() +") Initial Balance~ $" + initialAmountSecond+"\n");

            fw.write(firstName+" "+lastName+"~ Savings Account ("+ savingsAcc.getAccountNumber() +") Ending Balance~ $" + endAmountFirst+"\n"); 
            fw.write(firstName+" "+lastName+"~ Credit Account ("+ creditAcc.getAccountNumber() +") Ending Balance~ $" + endAmountSecond+"\n");

            fw.write(firstName + " " + lastName + "~ Date Sent~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.write(firstName + " " + lastName + "~ Date Received~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
    
    /** 
     * 
     * Taken from Derek Aguirre
     * 
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
    public void payLog(Checking checkingAcc1, Checking checkingAcc2, double initialAmountFirst, double initialAmountSecond, String firstNameFirst, String firstNameSecond, String lastNameFirst, String lastNameSecond, double amountTransferred, double endAmountFirst, double endAmountSecond) throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstNameFirst+" "+lastNameFirst+"~ Paid an Individual\n");
            fw.write(firstNameSecond+" "+lastNameSecond+"~ Was Paid\n\n");

            fw.write(firstNameFirst+" "+lastNameFirst+"~ Pay Amount~ $" + amountTransferred+"\n");
            fw.write(firstNameSecond+" "+lastNameSecond+"~ Amount Received~ $" + amountTransferred+"\n\n");

            fw.write(firstNameFirst+" "+lastNameFirst+"~ Checking Account~ ("+checkingAcc1.getAccountNumber()+") Initial Balance~ $" + initialAmountFirst +"\n");
            fw.write(firstNameSecond+" "+lastNameSecond+"~ Checking Account~ ("+checkingAcc2.getAccountNumber()+") Initial Balance~ $" + initialAmountSecond +"\n\n");

            fw.write(firstNameFirst+" "+lastNameFirst+"~ Checking Account~ ("+checkingAcc1.getAccountNumber()+") Ending Balance~ $" + endAmountFirst +"\n");
            fw.write(firstNameSecond+" "+lastNameSecond+"~ Checking Account~ ("+checkingAcc2.getAccountNumber()+") Ending Balance~ $" + endAmountSecond +"\n\n");

            fw.write(firstNameFirst + " " + lastNameFirst + "~ Date Sent~ "+ dateFormat.format(currentDate)+"\n");
            fw.write(firstNameSecond + " " + lastNameSecond + "~ Date Received~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }   
    
    /** 
     * 
     * Taken from Derek Aguirre
     * 
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
    public void payLog(Savings savingsAcc1, Savings savingsAcc2, double initialAmountFirst, double initialAmountSecond, String firstNameFirst, String firstNameSecond, String lastNameFirst, String lastNameSecond, double amountTransferred, double endAmountFirst, double endAmountSecond) throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
            fw.write(firstNameFirst+" "+lastNameFirst+"~ Paid an Individual\n");
            fw.write(firstNameSecond+" "+lastNameSecond+"~ Was Paid\n\n");

            fw.write(firstNameFirst+" "+lastNameFirst+"~ Pay Amount~ $" + amountTransferred+"\n");
            fw.write(firstNameSecond+" "+lastNameSecond+"~ Amount Received~ $" + amountTransferred+"\n\n");

            fw.write(firstNameFirst+" "+lastNameFirst+"~ Savings Account~ ("+savingsAcc1.getAccountNumber()+") Initial Balance~ $" + initialAmountFirst +"\n");
            fw.write(firstNameSecond+" "+lastNameSecond+"~ Savings Account~ ("+savingsAcc2.getAccountNumber()+") Initial Balance~ $" + initialAmountSecond +"\n\n");

            fw.write(firstNameFirst+" "+lastNameFirst+"~ Savings Account~ ("+savingsAcc1.getAccountNumber()+") Ending Balance~ $" + endAmountFirst +"\n");
            fw.write(firstNameSecond+" "+lastNameSecond+"~ Savings Account~ ("+savingsAcc2.getAccountNumber()+") Ending Balance~ $" + endAmountSecond +"\n\n");
            
            fw.write(firstNameFirst + " " + lastNameFirst + "~ Date Sent~ "+ dateFormat.format(currentDate)+"\n");
            fw.write(firstNameSecond + " " + lastNameSecond + "~ Date Received~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }
}