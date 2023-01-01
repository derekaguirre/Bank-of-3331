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
     * @param custList An ArrayList of type customer that holds all the data about every Customer
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
     * @param customerObject Object of a customer populated with values parsed from the input file
     * @param accountFlag Used to determine which account to retrieve information from
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public void inquireBalanceLog(Customer customerObject, String accountFlag)throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
            if(accountFlag.equalsIgnoreCase("All")){
                fw.write(customerObject.getFirstName()+ " " + customerObject.getLastName() + "~ Inquired All Balances \n"); 
                fw.write("Checking Account (" + customerObject.getCheckingAccount().getAccountNumber()+ ") Balance~ $"+ customerObject.getCheckingAccount().getStartingBalance()+"\n");
                fw.write("Savings Account (" + customerObject.getSavingsAccount().getAccountNumber() + ") Balance~ $"+ customerObject.getSavingsAccount().getStartingBalance()+"\n");
                fw.write("Credit Account (" + customerObject.getCreditAccount().getAccountNumber() + ") Balance~ $"+ customerObject.getCreditAccount().getStartingBalance()+"\n");
            }
            else if(accountFlag.equalsIgnoreCase("Checking")){
                fw.write(customerObject.getFirstName()+ " " + customerObject.getLastName() + "~ Inquired Checking Account Balance \n"); 
                fw.write("Checking Account (" + customerObject.getCheckingAccount().getAccountNumber()+ ") Balance~ $"+ customerObject.getCheckingAccount().getStartingBalance()+"\n");
            }
            else if(accountFlag.equalsIgnoreCase("Savings")){
                fw.write(customerObject.getFirstName()+ " " + customerObject.getLastName() + "~ Inquired Savings Account Balance \n");
                fw.write("Savings Account (" + customerObject.getSavingsAccount().getAccountNumber() + ") Balance~ $"+ customerObject.getSavingsAccount().getStartingBalance()+"\n");
            }
            else if(accountFlag.equalsIgnoreCase("Credit")){
                fw.write(customerObject.getFirstName()+ " " + customerObject.getLastName() + "~ Inquired Credit Account Balance \n"); 
                fw.write("Credit Account (" + customerObject.getCreditAccount().getAccountNumber() + ") Balance~ $"+ customerObject.getCreditAccount().getStartingBalance()+"\n");
            }
            
            fw.write(customerObject.getFirstName() + " " + customerObject.getLastName() + "~ Inquired Date~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.close();
        }catch (FileNotFoundException e1) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print("Input Output Exception");
        }
    }
    
    /** 
     * 
     * Taken from Derek Aguirre
     * 
     * Will report on the file "Log.txt" that the currently logged in customer has deposited a specified amount of funds into their checking account.
     * @param customerObject Object of a customer populated with values parsed from the input file
     * @param accountFlag Used to determine which account to retrieve information from
     * @param moneyInput The amount of money that the user is depositing
     * @param initial The initial amount of funds before the transaction
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public void depositLog(Customer customerObject, String accountFlag,  double moneyInput, double initial) throws IOException, FileNotFoundException{
        try{
            FileWriter fw = new FileWriter("Log.txt", true);
            Date currentDate = new Date();
            
            if(accountFlag.equalsIgnoreCase("Checking")){
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName() +"~ Deposited into Checking\n");
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName() +"~ Checking Account Initial Balance~ $"+ initial+"\n");
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName() +"~ Checking Account Ending Balance~ $"+ customerObject.getCheckingAccount().getStartingBalance()+"\n");
            }
            else if(accountFlag.equalsIgnoreCase("Savings")){
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName() +"~ Deposited into Savings\n");
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName() +"~ Savings Account Initial Balance~ $"+ initial+"\n");
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName() +"~ Savings Account Ending Balance~ $"+ customerObject.getSavingsAccount().getStartingBalance()+"\n");
            }
            else if(accountFlag.equalsIgnoreCase("Credit")){
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName() +"~ Deposited into Credit\n");
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName() +"~ Credit Account Initial Balance~ $"+ initial+"\n");
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName() +"~ Credit Account Ending Balance~ $"+ customerObject.getCreditAccount().getStartingBalance()+"\n");
            }
            fw.write(customerObject.getFirstName()+" "+customerObject.getLastName() +"~ Deposit Amount~ $" + moneyInput+"\n");
            fw.write(customerObject.getFirstName()+" "+customerObject.getLastName() + "~ Date~ "+ dateFormat.format(currentDate)+"\n\n");
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
     * @param customerObject Object of a customer populated with values parsed from the input file
     * @param accountFlag Used to determine which account to retrieve information from
     * @param withdrawFunds The amount of money that the user is withdrawing
     * @param initial The initial amount of funds before the transaction
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public void withdrawLog(Customer customerObject, String accountFlag, double withdrawFunds, double initial) throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
                        
            if(accountFlag.equalsIgnoreCase("Checking")){
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName() +"~ Withdrew from Checking\n");
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName() +"~ Checking Account Initial Balance~ $"+ initial+"\n");
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName() +"~ Checking Account Ending Balance~ $"+ customerObject.getCheckingAccount().getStartingBalance()+"\n");
            }
            else if(accountFlag.equalsIgnoreCase("Savings")){
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName() +"~ Withdrew from Savings\n");
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName() +"~ Savings Account Initial Balance~ $"+ initial+"\n");
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName() +"~ Savings Account Ending Balance~ $"+ customerObject.getSavingsAccount().getStartingBalance()+"\n");    
            }
            fw.write(customerObject.getFirstName()+" "+customerObject.getLastName() +"~ Withdraw Amount~ $" + withdrawFunds+"\n"); 
            fw.write(customerObject.getFirstName() + " " + customerObject.getLastName() + "~ Date~ "+ dateFormat.format(currentDate)+"\n\n");
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
     * @param customerObject of a customer populated with values parsed from the input file
     * @param accountFlag The account that will receive the funds
     * @param initialBal1 The initial amount of funds on checking account before the transaction
     * @param initialBal2 The initial amount of funds on savings account before the transaction
     * @param transferAmount The specified amount of funds that has been transferred
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public void transferLogFromChecking(Customer customerObject, String accountFlag, double initialBal1, double initialBal2, double transferAmount) throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
            if(accountFlag.equalsIgnoreCase("Savings")){
            fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Transferred from Checking Account ("+ customerObject.getCheckingAccount().getAccountNumber() + ")\n");
            fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Transferred to Savings Account ("+customerObject.getSavingsAccount().getAccountNumber()+")\n");

            fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Amount Sent~ $" + transferAmount+"\n");
            fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Amount Received~ $" + transferAmount+"\n");

            fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Checking Account ("+ customerObject.getCheckingAccount().getAccountNumber() +") Initial Balance~ $" + initialBal1+"\n"); 
            fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Savings Account ("+ customerObject.getSavingsAccount().getAccountNumber() +") Initial Balance~ $" + initialBal2+"\n");

            fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Checking Account ("+ customerObject.getCheckingAccount().getAccountNumber() +") Ending Balance~ $" + customerObject.getCheckingAccount().getStartingBalance()+"\n"); 
            fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Savings Account ("+ customerObject.getSavingsAccount().getAccountNumber() +") Ending Balance~ $" + customerObject.getSavingsAccount().getStartingBalance()+"\n");
            }
            else if(accountFlag.equalsIgnoreCase("Credit")){
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName() +"~ Transferred from Checking Account ("+ customerObject.getCheckingAccount().getAccountNumber() + ")\n");
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Transferred to Credit Account ("+customerObject.getCreditAccount().getAccountNumber()+")\n");
    
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Amount Sent~ $" + transferAmount+"\n");
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Amount Received~ $" + transferAmount+"\n");
    
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Checking Account ("+ customerObject.getCheckingAccount().getAccountNumber() +") Initial Balance~ $" + initialBal1+"\n"); 
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Credit Account ("+ customerObject.getCreditAccount().getAccountNumber() +") Initial Balance~ $" + initialBal2+"\n");
    
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Checking Account ("+ customerObject.getCheckingAccount().getAccountNumber() +") Ending Balance~ $" + customerObject.getCheckingAccount().getStartingBalance()+"\n"); 
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Credit Account ("+ customerObject.getCreditAccount().getAccountNumber() +") Ending Balance~ $" + customerObject.getCreditAccount().getStartingBalance()+"\n");
    
                
            }
                fw.write(customerObject.getFirstName() + " " + customerObject.getLastName() + "~ Date Sent~ "+ dateFormat.format(currentDate)+"\n");
                fw.write(customerObject.getFirstName() + " " + customerObject.getLastName() + "~ Date Received~ "+ dateFormat.format(currentDate)+"\n\n");
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
     * @param customerObject of a customer populated with values parsed from the input file
     * @param accountFlag The account that will receive the funds
     * @param initialBal1 The initial amount of funds on savings account before the transaction
     * @param initialBal2 The initial amount of funds on checking account before the transaction
     * @param transferAmount The specified amount of funds that has been transferred
     * @throws IOException InputOutputException InputOutput Exception
     * @throws FileNotFoundException If file is not found If the file is not found
     */
    public void transferLogFromSavings(Customer customerObject, String accountFlag, double initialBal1, double initialBal2, double transferAmount) throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
            if(accountFlag.equalsIgnoreCase("Checking")) {
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Transferred from Savings Account ("+ customerObject.getSavingsAccount().getAccountNumber() + ")\n");
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Transferred to Checking Account ("+customerObject.getCheckingAccount().getAccountNumber()+")\n");

                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Amount Sent~ $" + transferAmount+"\n");
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Amount Received~ $" + transferAmount+"\n");

                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Savings Account ("+ customerObject.getSavingsAccount().getAccountNumber() +") Initial Balance~ $" + initialBal1+"\n"); 
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Checking Account ("+ customerObject.getCheckingAccount().getAccountNumber() +") Initial Balance~ $" + initialBal2+"\n");

                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Savings Account ("+ customerObject.getSavingsAccount().getAccountNumber() +") Ending Balance~ $" + customerObject.getSavingsAccount().getStartingBalance()+"\n"); 
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Checking Account ("+ customerObject.getCheckingAccount().getAccountNumber() +") Ending Balance~ $" + customerObject.getCheckingAccount().getStartingBalance()+"\n");
            }
            else if(accountFlag.equalsIgnoreCase("Credit")) {
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Transferred from Savings Account ("+ customerObject.getSavingsAccount().getAccountNumber() + ")\n");
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Transferred to Credit Account ("+ customerObject.getCreditAccount().getAccountNumber() +")\n");

                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Amount Sent~ $" + transferAmount+"\n");
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Amount Received~ $" + transferAmount+"\n");

                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Savings Account ("+ customerObject.getSavingsAccount().getAccountNumber() +") Initial Balance~ $" + initialBal1+"\n"); 
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Credit Account ("+ customerObject.getCreditAccount().getAccountNumber() +") Initial Balance~ $" + initialBal2+"\n");

                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Savings Account ("+ customerObject.getSavingsAccount().getAccountNumber() +") Ending Balance~ $" + customerObject.getSavingsAccount().getStartingBalance()+"\n"); 
                fw.write(customerObject.getFirstName()+" "+customerObject.getLastName()+"~ Credit Account ("+ customerObject.getCreditAccount().getAccountNumber() +") Ending Balance~ $" + customerObject.getCreditAccount().getStartingBalance()+"\n");
            }
                
            fw.write(customerObject.getFirstName() + " " + customerObject.getLastName() + "~ Date Sent~ "+ dateFormat.format(currentDate)+"\n");
            fw.write(customerObject.getFirstName() + " " + customerObject.getLastName() + "~ Date Received~ "+ dateFormat.format(currentDate)+"\n\n");
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
     * @param customerObject1 An object of Customer, holds info stored about all of the first customer's accounts
     * @param customerObject2 An object of Customer, holds info stored about all of the second customer's accounts
     * @param accountFlag A string that determines which account the pay log will write information about
     * @param initialBal1 The initial amount of funds on the first customer's checking account before the transaction
     * @param initialBal2 The initial amount of funds on the second customer's checking account before the transaction
     * @param payAmount The specified amount of funds that has been transferred
     * @throws IOException InputOutputException
     * @throws FileNotFoundException If file is not found
     */
    public void payLog(Customer customerObject1, Customer customerObject2, String accountFlag, double initialBal1, double initialBal2, double payAmount) throws IOException, FileNotFoundException{
        try{
            Date currentDate = new Date();
            FileWriter fw = new FileWriter("Log.txt", true);
            if(accountFlag.equalsIgnoreCase("Checking")){
                fw.write(customerObject1.getFirstName()+" "+customerObject1.getLastName()+"~ Paid an Individual\n");
                fw.write(customerObject2.getFirstName()+" "+customerObject2.getLastName()+"~ Was Paid\n\n");

                fw.write(customerObject1.getFirstName()+" "+customerObject1.getLastName()+"~ Pay Amount~ $" + payAmount+"\n");
                fw.write(customerObject2.getFirstName()+" "+customerObject2.getLastName()+"~ Amount Received~ $" + payAmount+"\n\n");

                fw.write(customerObject1.getFirstName()+" "+customerObject1.getLastName()+"~ Checking Account~ ("+customerObject1.getCheckingAccount().getAccountNumber()+") Initial Balance~ $" + initialBal1 +"\n");
                fw.write(customerObject2.getFirstName()+" "+customerObject2.getLastName()+"~ Checking Account~ ("+customerObject2.getCheckingAccount().getAccountNumber()+") Initial Balance~ $" + initialBal2 +"\n\n");

                fw.write(customerObject1.getFirstName()+" "+customerObject1.getLastName()+"~ Checking Account~ ("+customerObject1.getCheckingAccount().getAccountNumber()+") Ending Balance~ $" + customerObject1.getCheckingAccount().getStartingBalance() +"\n");
                fw.write(customerObject2.getFirstName()+" "+customerObject2.getLastName()+"~ Checking Account~ ("+customerObject2.getCheckingAccount().getAccountNumber()+") Ending Balance~ $" + customerObject2.getCheckingAccount().getStartingBalance() +"\n\n");
            }
            else if(accountFlag.equalsIgnoreCase("Savings")){
                fw.write(customerObject1.getFirstName()+" "+customerObject1.getLastName()+"~ Paid an Individual\n");
                fw.write(customerObject2.getFirstName()+" "+customerObject2.getLastName()+"~ Was Paid\n\n");

                fw.write(customerObject1.getFirstName()+" "+customerObject1.getLastName()+"~ Pay Amount~ $" + payAmount+"\n");
                fw.write(customerObject2.getFirstName()+" "+customerObject2.getLastName()+"~ Amount Received~ $" + payAmount+"\n\n");

                fw.write(customerObject1.getFirstName()+" "+customerObject1.getLastName()+"~ Savings Account~ ("+customerObject1.getSavingsAccount().getAccountNumber()+") Initial Balance~ $" + initialBal1 +"\n");
                fw.write(customerObject2.getFirstName()+" "+customerObject2.getLastName()+"~ Savings Account~ ("+customerObject2.getSavingsAccount().getAccountNumber()+") Initial Balance~ $" + initialBal2 +"\n\n");

                fw.write(customerObject1.getFirstName()+" "+customerObject1.getLastName()+"~ Savings Account~ ("+customerObject1.getSavingsAccount().getAccountNumber()+") Ending Balance~ $" + customerObject1.getSavingsAccount().getStartingBalance() +"\n");
                fw.write(customerObject2.getFirstName()+" "+customerObject2.getLastName()+"~ Savings Account~ ("+customerObject2.getSavingsAccount().getAccountNumber()+") Ending Balance~ $" + customerObject2.getSavingsAccount().getStartingBalance() +"\n\n");
            }
            fw.write(customerObject1.getFirstName() + " " + customerObject1.getLastName() + "~ Date Sent~ "+ dateFormat.format(currentDate)+"\n");
            fw.write(customerObject2.getFirstName() + " " + customerObject2.getLastName() + "~ Date Received~ "+ dateFormat.format(currentDate)+"\n\n");
            fw.close();
        }catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        }catch (IOException e2) {
            System.out.print(e2);
        }
    }    
    
}