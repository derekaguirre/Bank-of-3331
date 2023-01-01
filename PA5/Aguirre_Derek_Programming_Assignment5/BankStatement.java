import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.SimpleDateFormat;  
import java.util.Date; 
/**
 * Taken from Anthony Jasso
 * 
 * The BankStatement class will be used generate a customer's bank statement.
 *
 * @author Anthony Jasso
 * @version 2.0
 * @since 11/11/2020
 * @see UserConsole
 */
public class BankStatement{
    
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
    public static Date currentDate = new Date();

    
    
    /** 
     * Taken from Anthony Jasso
     * 
     * This method will write a bank statement for a customer. Will be used by a bank manager.
     * 
     * @throws InputMismatchException If the input is not expected
     * @throws FileNotFoundException If the file is either open while being written to or does not exist
     * @throws IOException Input Output Exception
     */
    public static void writeStatement()throws InputMismatchException, FileNotFoundException, IOException{
        boolean isFound = false;
        do{
            try{
                Scanner sc = new Scanner(System.in);
                //-----------Create array lists to save info------------
                ArrayList<String> actionList = new ArrayList<String>();
                ArrayList<String> amountList = new ArrayList<String>();
                ArrayList<String> updatedBalanceList = new ArrayList<String>();
                ArrayList<String> prevBalanceList = new ArrayList<String>();
                ArrayList<String> dateList = new ArrayList<String>();
                //--------------Save Starting Balances of all accounts----------------------------------
                double startingBalanceCh = 0;
                double startingBalanceSa = 0;
                double startingBalanceCr = 0;
                
                //-------------Prompt manager for bank statement of desired user----------------------------------------

                System.out.println("Enter Full Name of Customer. (Case-Insensitive)"); //ask for customer for first and last names

                String nameInput = sc.next();
                nameInput += sc.nextLine();
                //------verify if the user exists inside the main input file array list-------------
                ArrayList<Customer> updated = UserConsole.fileRead("Updated Balance Sheet.csv");
                Customer records = UserConsole.findAccountByName(updated, nameInput);
                 // ----if the user not found---------------------------------------
                if(records == null){
                    isFound = false;
                    System.out.println("Input does not match records. Please try again.");
                }
               
                else{// names match. generates the statement
                    String toComp = (records.getFirstName() + " " + records.getLastName());
                    isFound = true;
                    FileWriter fw = new FileWriter(records.getFirstName() + records.getLastName() + "Statement.txt", false); // initializes the file writer with appending being true
                    System.out.println("User found successfully.\n\nGenerating Bank statement...\n"); // save fields for readability
                    // -----saves ALL information to variables------------------------
                    String name = (records.getFirstName() + " " + records.getLastName());

                    String info = ("\nDate of Birth: \t" + records.getDateOfBirth() + "\nIdentification Number: \t" + records.getIdNumber() + "\nAddress: \t" + records.getAddress() + "\nPhone Number: \t" + records.getPhoneNumber());

                    int accInfoCh = (records.getCheckingAccount().getAccountNumber()); // checking accNum
                    int accInfoSa = (records.getSavingsAccount().getAccountNumber()); // savings accNum
                    int accInfoCr = (records.getCreditAccount().getAccountNumber()); // credit accNum
                    ArrayList<Customer> originalUserInfo = UserConsole.fileRead("CS 3331 - Bank Users 5.csv");
                    for (Customer initial : originalUserInfo) { // iter through original input file to save initial balances
                        // ------------------if the name provided earlier exists then save initial values------------------------------------------
                        if (toComp.equalsIgnoreCase(initial.getFirstName() + " " + initial.getLastName())) {
                            startingBalanceCh = initial.getCheckingAccount().getStartingBalance();
                            startingBalanceSa = initial.getSavingsAccount().getStartingBalance();
                            startingBalanceCr = initial.getCreditAccount().getStartingBalance();
                        }
                    }
                    // ----------------------------------saves ending balance of all accounts  -----------------------------------
                    double endingBalanceCh = (records.getCheckingAccount().getStartingBalance()); // checking currentBal
                    double endingBalanceSa = (records.getSavingsAccount().getStartingBalance()); // savings currentBal
                    double endingBalanceCr = (records.getCreditAccount().getStartingBalance()); // credit currentBal

                    // --------------------------------Read the log file which contains all transactions----------------------------------------------------
                    BufferedReader buffReader = new BufferedReader(new FileReader("Log.txt"));
                    String line;
                    while ((line = buffReader.readLine()) != null) { // Read the log to format and use actions in the
                                                                     // statement
                        // -----------------------------program omits every line that contains the following vvvvvv ----------------------------------------
                        if (line.contains(toComp) && !line.contains("Login") && !line.contains("Logout") && !line.contains("Inquired")) { // if the name is found but does not contain login or logout
                            //---------------------------------creates new strings to save to later----------------------------------------------------
                            String lineFormat = line;
                            String date = "";
                            String action = "";
                            String amount = "";
                            String initialBal = "";
                            String updatedBal = "";

                            // ------------------------------------------removes name + tilde and space, tilde, and white space,------------------------------------------------
                            lineFormat = lineFormat.replaceAll(toComp + "~ ", "");
                            lineFormat = lineFormat.replaceAll("~", " ");
                            lineFormat = lineFormat.replaceAll("\\s*\\([^\\)]*\\)\\s*", " ");
                            // --------------------once lines are cleaned up, program checks for keywords for actions and saves entire line---------------------------------------------------
                            if (line.contains("Deposited") || line.contains("Withdrew") || line.contains("Transferred") || line.contains("Paid")) {
                                action = lineFormat;
                                if (action != null)
                                    actionList.add(action);
                            }
                            if (lineFormat.contains("Was Paid") && !(line.contains(toComp))) {
                                action = lineFormat;
                                if (action != null)
                                    actionList.add(action);
                            }
                            // ----------if contains amount, remove everything that is not a digit, or negative/period-----------------------------------------------
                            if (line.contains("Amount")) {
                                amount = lineFormat;
                                amount = amount.replaceAll("[^\\d.-]", "");
                                if (amount != null)
                                    amountList.add(amount);
                            }
                            if (line.contains("Initial Balance")) {
                                initialBal = lineFormat;
                                initialBal = updatedBal.replaceAll("[^\\d.-]", "");
                                if (initialBal != null)
                                    prevBalanceList.add(initialBal);
                            }
                            if (line.contains("Ending Balance")) {
                                updatedBal = lineFormat;
                                updatedBal = updatedBal.replaceAll("[^\\d.-]", "");
                                if (updatedBal != null)
                                    updatedBalanceList.add(updatedBal);
                            }
                            // keeps digits, spaces, colon, slash. removes everything else
                            if (line.contains("Date")) {
                                date = lineFormat;
                                date = date.replaceAll("[^\\s\\d\\s:/]", "");
                                if (date != null)
                                    dateList.add(date);
                            }

                        }

                    }
                    buffReader.close();

                    // ----------------------------START OF THE BANK STATEMENT-----------------------------------------
                    fw.write("Bank of Disney Bank Statement \t\t\t\t\t Date of Request: " + dateFormat.format(currentDate) + "\n"); // header of the statement
                    fw.write("Name: \t" + name + info + "\n");
                    
                    if(accInfoCh == -1 & accInfoCr != -1){//checking does not exist
                        fw.write("Savings Account (" + accInfoSa + ") Balance Prior to Statement Period: " + startingBalanceSa+ "\n");
                        fw.write("Credit Account (" + accInfoCr + ") Balance Prior to Statement Period: " + startingBalanceCr + "\n\n\n");
                    }
                     else if(accInfoCh != -1 && accInfoCr == -1){//credit does not exist
                        fw.write("Checking Account (" + accInfoCh + ") Balance Prior to Statement Period: " + startingBalanceCh + "\n"); // resulting balances
                        fw.write("Savings Account (" + accInfoSa + ") Balance Prior to Statement Period: " + startingBalanceSa + "\n\n\n");    
                     } 
                     else if(accInfoCh==-1 && accInfoCr ==-1){//only savings
                        fw.write("Savings Account (" + accInfoSa + ") Balance Prior to Statement Period: " + startingBalanceSa + "\n\n\n");
                     }
                     else if(accInfoCh != -1 && accInfoCr != -1){ // all accounts exist
                        fw.write("Checking Account (" + accInfoCh + ") Balance Prior to Statement Period: " + startingBalanceCh + "\n"); // resulting balances
                        fw.write("Savings Account (" + accInfoSa + ") Balance Prior to Statement Period: " + startingBalanceSa + "\n");
                        fw.write("Credit Account (" + accInfoCr + ") Balance Prior to Statement Period: " + startingBalanceCr + "\n\n\n");
                     }

                    fw.write("  Date:\t\t\t\t\tAction:\t\t\t\t\t\tAmount:\t\t\t\t\t\tBalance\n"); // actions used in statement
                    // ----------------every iteration is accessed at the same time to print side by side------------------------------------------------
                    for (int i = 0; i < actionList.size(); i++) {
                        fw.write(dateList.get(i) + "\t\t\t" + actionList.get(i) + "\t\t\t\t$" + amountList.get(i) + "\t\t\t\t\t\t$" + updatedBalanceList.get(i) + "\n\n");
                    }
                    if(accInfoCh == -1 & accInfoCr != -1){//checking does not exist
                        fw.write("Savings Account (" + accInfoSa + ") Current Balance in Statement Period: " + endingBalanceSa + "\n");
                        fw.write("Credit Account (" + accInfoCr + ") Current Balance in Statement Period:: " + endingBalanceCr + "\n\n\n");
                    }
                     else if(accInfoCh != -1 && accInfoCr == -1){//credit does not exist
                        fw.write("Checking Account (" + accInfoCh + ") Current Balance in Statement Period: " + endingBalanceCh + "\n"); // resulting balances
                        fw.write("Savings Account (" + accInfoSa + ") Current Balance in Statement Period: " + endingBalanceSa + "\n\n\n");    
                     } 
                     else if(accInfoCh==-1 && accInfoCr ==-1){//only savings
                        fw.write("Savings Account (" + accInfoSa + ") Current Balance in Statement Period: " + endingBalanceSa + "\n\n\n");
                     }
                     else if(accInfoCh != -1 && accInfoCr != -1){ // all accounts exist
                        fw.write("Checking Account (" + accInfoCh + ") Current Balance in Statement Period: " + endingBalanceCh + "\n"); // resulting balances
                        fw.write("Savings Account (" + accInfoSa + ") Current Balance in Statement Period: " + endingBalanceSa + "\n");
                        fw.write("Credit Account (" + accInfoCr + ") Current Balance in Statement Period: " + endingBalanceCr + "\n\n\n");
                     }
                    
                    System.out.println("Bank Statement Generated Successfully.\n\nSee: " + records.getFirstName()+ records.getLastName() + "Statement.txt\n");
                    fw.close();
                }                   
            } catch (InputMismatchException e1) {
                System.out.println("Please follow the instructions for expected information in the fields.");
            } catch (FileNotFoundException e2) {
                System.out.println("Failed to retrieve the file. Please check the name or close the file.");
            } catch (IOException e3) {
                System.out.println("IOException");
            }

        } while (isFound == false);
    }  
}