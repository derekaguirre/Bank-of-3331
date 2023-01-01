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
 * The BankStatement class will be used generate a customer's bank statement.
 *
 * @author Derek Aguirre
 * @version 1.0
 * @since 10/10/2020
 * @see UserConsole
 */
public class BankStatement{
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
    public static Date currentDate = new Date();

    
    
    /** 
     * This method will write a bank statement for a customer. Will be used by a bank manager.
     * 
     * @param customerObject ArrayList of type customer
     * @throws InputMismatchException If the input is not expected
     * @throws FileNotFoundException If the file is either open while being written to or does not exist
     * @throws IOException Input Output Exception
     */
    public static void writeStatement(ArrayList<Customer> customerObject)throws InputMismatchException, FileNotFoundException, IOException{
        boolean isFound = false;
        do{
            try{
                Scanner sc = new Scanner(System.in);
                
                ArrayList<String> actionList = new ArrayList<String>();
                ArrayList<String> amountList = new ArrayList<String>();
                ArrayList<String> updatedBalanceList = new ArrayList<String>();
                ArrayList<String> prevBalanceList = new ArrayList<String>();
                ArrayList<String> dateList = new ArrayList<String>();
                
                double startingBalanceCh = 0;
                double startingBalanceSa = 0;
                double startingBalanceCr = 0;


                System.out.println("Enter First Name of Customer. (Case-Insensitive)"); //ask for customer for first and last names
                String fName = sc.next();
                System.out.println("Enter First Name of Customer. (Case-Insensitive)");
                String lName = sc.next();
                String nameInput = (fName + " " + lName);
                for (Customer updated : customerObject) { // for loop that iterates through all names
                    
                String toComp = (updated.getFirstName() + " " + updated.getLastName());
                    if(nameInput.equalsIgnoreCase(toComp)){// if match, generates the statement
                        isFound = true;
                        FileWriter fw = new FileWriter(updated.getFirstName() + updated.getLastName()+"Statement.txt", false); // initializes the file writer with appending being true

                        System.out.println("User found successfully.\n\nGenerating Bank statement...\n"); //save fields for readability
                        String name = (updated.getFirstName()+" "+ updated.getLastName());

                        String info =("\nDate of Birth: \t"+ updated.getDateOfBirth() + 
                                      "\nIdentification Number: \t" + updated.getIdNumber()+ 
                                      "\nAddress: \t" + updated.getAddress() + 
                                      "\nPhone Number: \t" + updated.getPhoneNumber());

                        int accInfoCh =(updated.getCheckingAccount().getAccountNumber()); //checking accNum
                        int accInfoSa =(updated.getSavingsAccount().getAccountNumber());  // savings accNum
                        int accInfoCr =(updated.getCreditAccount().getAccountNumber());   //  credit accNum

                        ArrayList<Customer> originalUserInfo = RunBank.fileRead("CS 3331 - Bank Users 3.csv");
                        for (Customer initial: originalUserInfo) { //iter through original input file to save initial balances
                            if(toComp.equalsIgnoreCase(initial.getFirstName()+" "+initial.getLastName())){
                                startingBalanceCh = initial.getCheckingAccount().getStartingBalance();
                                startingBalanceSa = initial.getSavingsAccount().getStartingBalance();
                                startingBalanceCr = initial.getCreditAccount().getStartingBalance();
                            }
                        }
                        
                        double endingBalanceCh = (updated.getCheckingAccount().getStartingBalance()); //checking currentBal
                        double endingBalanceSa = (updated.getSavingsAccount().getStartingBalance());  // savings currentBal
                        double endingBalanceCr = (updated.getCreditAccount().getStartingBalance());   //  credit currentBal


                        BufferedReader buffReader = new BufferedReader(new FileReader("Log.txt"));
                        String line;
                        while ((line = buffReader.readLine()) != null) { //Read the log to format and use actions in the statement
                            if(line.contains(toComp)&&!line.contains("Login")&&!line.contains("Logout")&&!line.contains("Inquired")){ //if the name is found but does not contain login or logout
                                
                                String date = "";
                                String action = "";
                                String amount = "";
                                String initialBal = "";
                                String updatedBal = "";
                                String lineFormat = line;
                                lineFormat = lineFormat.replaceAll(toComp+"~ ", "");
                                lineFormat = lineFormat.replaceAll("~"," ");
                                lineFormat = lineFormat.replaceAll("\\s*\\([^\\)]*\\)\\s*"," ");

                                if (line.contains("Deposited") || line.contains("Withdrew") || line.contains("Transferred") || line.contains("Paid")) {
                                    action = lineFormat;
                                    if (action != null)
                                        actionList.add(action);        
                                }
                                if(lineFormat.contains("Was Paid") && !(line.contains(toComp))){
                                    action = lineFormat;
                                    if (action != null)
                                        actionList.add(action); 
                                }
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
                                if (line.contains("Date")) {
                                    date = lineFormat;
                                    date = date.replaceAll("[^\\s\\d\\s:/]", "");
                                    if (date != null)
                                        dateList.add(date);
                                }
                                 
                            }
                            
                        }
                        buffReader.close();

                        fw.write("Bank of Miners \t\t\t\t\t Date of Request: "+ dateFormat.format(currentDate) +"\n"); //header of the statement
                        fw.write("Name: \t"+ name + info +"\n");
                        fw.write("Checking Account ("+ accInfoCh +") Balance Prior to Statement Period: " + startingBalanceCh+"\n");
                        fw.write("Savings Account ("+ accInfoSa +") Balance Prior to Statement Period: " + startingBalanceSa+"\n");
                        fw.write("Credit Account ("+ accInfoCr +") Balance Prior to Statement Period: " + startingBalanceCr+"\n\n\n");

                    
                        fw.write("  Date:\t\t\t\t\tAction:\t\t\t\t\t\t\t\tAmount:\t\t\tBalance\n");  //actions used in statement

                        for (int i = 0; i < actionList.size(); i++) {
                            fw.write(dateList.get(i)+"\t\t\t"+actionList.get(i)+"\t\t\t\t\t\t$"+amountList.get(i)+"\t\t\t$"+updatedBalanceList.get(i)+"\n\n");
                        }

                        fw.write("Checking Account ("+ accInfoCh +") Balance Currently: " + endingBalanceCh+"\n"); //resulting balances
                        fw.write("Savings Account ("+ accInfoSa +") Balance Currently: " + endingBalanceSa+"\n");
                        fw.write("Credit Account ("+ accInfoCr +") Balance Currently: " + endingBalanceCr+"\n\n\n");

                        
                        System.out.println("Bank Statement Generated Successfully.\n\nSee: " + updated.getFirstName() + updated.getLastName()+"Statement.txt\n");
                        fw.close();
                    } 
                }
                if (isFound==false){
                    System.out.println("Input does not match records. Please try again.");
                }
            }catch (InputMismatchException e1) {
                System.out.println("Please follow the instructions for expected information in the fields.");
            }catch (FileNotFoundException e2){
                System.out.println("Failed to retrieve the file. Please check the name or close the file.");
            }catch (IOException e3){
                System.out.println("IOException");
            }

        } while(isFound==false);
    }
    
}