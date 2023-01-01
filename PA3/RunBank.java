import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
Derek Aguirre
10/19/2020
CS 3331 – Advanced Object-Oriented Programming – Fall 2020
Daniel Mejia
Programming Assignment 3

The lab's purpose was to refactor code from the previous assignment as well
as make sure the code is more robust when it comes to exception handling.
Additionally, the lab requires the extension of the bank manager functions.
They are supposed to be able to create new users from the terminal, as well
as generate a bank statement which comes from its own class for any user that
exists, given their full name. The code is also supposed to follow a design 
pattern for this assignment, I chose singleton for my logger class. Lastly, 
the program is supposed to be able to take in an input file of actions and
perform them all at the same time. 

I confirm that the work of this assignment is completely my own. By turning in this assignment, 
I declare that I did not receive unauthorized assistance. Moreover, all deliverables including, 
but not limited to the source code, lab report and output files were written and produced by me alone.
*/

/**
 * The RunBank class is what will be used to run most of the code for this
 * assignment. A user can log in as a Customer or as a Bank manager. Both
 * choices have different functions.
 *
 * @author Derek Aguirre
 * @version 2.0
 * @since 10/10/2020
 */

public class RunBank {

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

    

    /**
     * <p>
     * This method stores the contents of the input file "CS 3331 - Bank Users 3.csv" to an ArrayList of type Customer. 
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

            Checking checkingAcc = new Checking(Integer.parseInt(tokens[findIndex(titles, "Checking Account Number")]),
                                                Double.parseDouble(tokens[findIndex(titles, "Checking Starting Balance")]));

            Savings savingsAcc = new Savings(Integer.parseInt(tokens[findIndex(titles, "Savings Account Number")]),
                                             Double.parseDouble(tokens[findIndex(titles, "Savings Starting Balance")]));

            Credit creditAcc = new Credit(Integer.parseInt(tokens[findIndex(titles, "Credit Account Number")]),
                                          Double.parseDouble(tokens[findIndex(titles, "Credit Starting Balance")]),
                                          Integer.parseInt(tokens[findIndex(titles, "Credit Max")]));

            // The program will now save the information in accordance to their data types
            bank.add(new Customer(tokens[findIndex(titles, "First Name")], tokens[findIndex(titles, "Last Name")],
                                  tokens[findIndex(titles, "Date Of Birth")],
                                  Integer.parseInt(tokens[findIndex(titles, "Identification Number")]),
                                  tokens[findIndex(titles, "Address")],
                                  tokens[findIndex(titles, "Phone Number")], checkingAcc,
                                  savingsAcc, creditAcc));
        }
        buffReader.close();
        return bank;
    }

    /**
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
    public static void generateUpdatedBalanceSheet(ArrayList<Customer> customers)
            throws IOException, FileNotFoundException {
        try {
            FileWriter fw = new FileWriter("Updated Balance Sheet.csv", false);
            fw.write("First Name,Last Name,Date Of Birth,Identification Number,Address,Phone Number,Checking Account Number,Savings Account Number," + 
                     "Credit Account Number,Checking Updated Balance,Savings Updated Balance,Credit Updated Balance");

            for (Customer iter : customers) {
                fw.append(String.valueOf("\n" + iter.getFirstName()) + ",");
                fw.append(String.valueOf(iter.getLastName()) + ",");
                fw.append(String.valueOf(iter.getDateOfBirth()) + ",");
                fw.append(String.valueOf(iter.getIdNumber()) + ",");
                fw.append(String.valueOf(iter.getAddress()) + ",");
                fw.append(String.valueOf(iter.getPhoneNumber()) + ",");
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

    public static void main(String[] args)throws FileNotFoundException, IOException, InputMismatchException, OutOfRangeException {
        Logger singletonLogger = Logger.getInstance();
        UserConsole console = new UserConsole();
        int mainCondition = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in); // Create scanner
                ArrayList<Customer> bank = fileRead("CS 3331 - Bank Users 3.csv"); // data structure
                singletonLogger.enterProgramLog();
                Menu.printBankIntro();
                Menu.printUserChoice();
                int userChoice = sc.nextInt(); // saves user type selection
                if (userChoice > 3 || userChoice < 0) // range check
                    throw new OutOfRangeException("Please select a number inside the range of selections.");
                while (userChoice <= 3 || userChoice >= 0) {// if choice is in range
                    switch (userChoice) {
                        case 1:// if user is customer
                            while (userChoice == 1) { // boolean operation most of the code runs off of
                                System.out.println("\nEnter Identification number (Up to 3 Digits)");
                                int idNum = sc.nextInt(); //save user input
                                boolean isFound = false;
                                // -----------------------LOGIN------------------------------
                                for (Customer firstUser : bank) { // verify if the user exists in the bank
                                    
                                    if (idNum == firstUser.getIdNumber()) {// if exists
                                        isFound = true;
                                        generateUpdatedBalanceSheet(bank);
                                        singletonLogger.loginLog(firstUser.getFirstName(), firstUser.getLastName()); //log login
                                        Menu.printWelcome(firstUser.getFirstName(), firstUser.getLastName());
                                        Menu.printMainDirectory();
                                        int choice = sc.nextInt();
                                        if (choice > 5 || choice < 0) // range check for menu
                                            throw new OutOfRangeException("Out of range please try again.");
                                        int loopCondition = 0;
                                        do { 
                                            while (choice <= 5 && choice >= 0) { //in condition
                                                
                                                switch (choice) {
                                                    case 0:// Will log the user out
                                                    singletonLogger.logoutLog(firstUser.getFirstName(), firstUser.getLastName());
                                                        System.out.println("Successfully logged out");
                                                        generateUpdatedBalanceSheet(bank);
                                                        loopCondition = 1;
                                                        userChoice = -1;
                                                        break;
                                                    case 1:// Will inquire a balance
                                                        int x = 0;
                                                        do {
                                                            x = console.inquireBalanceChoice(firstUser.getCheckingAccount(), firstUser.getSavingsAccount(), firstUser.getCreditAccount(), firstUser.getFirstName(), firstUser.getLastName());
                                                        } while (x == 0);
                                                        break;
                                                    case 2:// Will deposit money into user's account
                                                        x = 0;
                                                        do {
                                                            x = console.depositChoice(firstUser.getCheckingAccount(), firstUser.getSavingsAccount(), firstUser.getCreditAccount(), firstUser.getFirstName(), firstUser.getLastName());
                                                        } while (x == 0);
                                                        break;
                                                    case 3:// Will withdraw money from a user's account
                                                        x = 0;
                                                        do {
                                                            x = console.withdrawChoice(firstUser.getCheckingAccount(), firstUser.getSavingsAccount(), firstUser.getFirstName(), firstUser.getLastName());
                                                        } while (x == 0);
                                                        break;
                                                    case 4:// Will move money from account to account
                                                        x = 0;
                                                        do {
                                                            x = console.transferChoice(firstUser.getCheckingAccount(),
                                                                                           firstUser.getSavingsAccount(),
                                                                                           firstUser.getCreditAccount(),
                                                                                           firstUser.getFirstName(),
                                                                                           firstUser.getLastName());
                                                        } while (x == 0);
                                                        break;
                                                    case 5:// Will send money to another person's specified account
                                                        x = 0;
                                                        do {
                                                            System.out.println("Please enter recipient's First Name. (Case-insensitive)");
                                                            String fName = sc.next();
                                                            System.out.println("Please enter recipient's Last Name. (Case-insensitive)");
                                                            String lName = sc.next();
                                                            String nameInput = (fName + " " + lName);
                                                            isFound = false;
                                                            boolean isSameName = false;
                                                            for (Customer secondUser : bank) {
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
                                                                    x = console.payNest(firstUser.getCheckingAccount(),
                                                                                            firstUser.getSavingsAccount(),
                                                                                            firstUser.getCreditAccount(),
                                                                                            secondUser.getCheckingAccount(),
                                                                                            secondUser.getSavingsAccount(),
                                                                                            secondUser.getCreditAccount(),
                                                                                            firstUser.getFirstName(),
                                                                                            firstUser.getLastName(),
                                                                                            secondUser.getFirstName(),
                                                                                            secondUser.getLastName());
                                                                    if (x == 1)
                                                                        break;
                                                                }
                                                                if (isFound) // if found stop looking
                                                                    break;
                                                            }
                                                            if (!isFound && isSameName)
                                                                System.out.println("Input does not match records. Please try again.");
                                                        } while (x == 0);
                                                        break;
                                                }
                                                if (loopCondition == 0) {
                                                    Menu.printWelcome(firstUser.getFirstName(),firstUser.getLastName());
                                                    Menu.printMainDirectory();
                                                    choice = sc.nextInt();
                                                
                                                    if (choice > 5 || choice < 0)
                                                        throw new OutOfRangeException(
                                                                "Please select a value inside the range.");
                                                } else
                                                    break;
                                            }
                                        } while (loopCondition == 0);
                                    }
                                    if(isFound)
                                       break; 
                                }
                                if(!isFound){
                                    System.out.println("Input does not match records. Please try again");
                                    break;
                                }
                            }
                            break;
                        case 2:// if user is bank manager
                            while (userChoice == 2) { // manager code execution
                                console.managerChoice(bank);
                                break;
                            }
                            break;
                        case 3://if user selects transaction reader
                                console.transactionReader("Transaction Actions.csv",bank);
                                break;
                        case 0: // exit program
                            System.out.println("Successfully exited program. Have a good day!");
                            mainCondition = 1;
                            singletonLogger.exitProgramLog();
                            break;
                    }
                    if (mainCondition == 0) {
                        Menu.printBankIntro();
                        Menu.printUserChoice();
                        userChoice = sc.nextInt(); // saves user type selection
                        while (userChoice > 3 || userChoice < 0) {
                            System.out.println("Please select a value inside the range.");
                            Menu.printUserChoice();
                            userChoice = sc.nextInt();
                        }
                    } else
                        break;
                }
            } catch (FileNotFoundException e1) {
                System.out.println("File not found. Please double check the name.");
            } catch (IOException e2) {
                System.out.println("IOException");
            } catch (OutOfRangeException e3) {
                System.out.println("Please choose a value inside the range.");
            } catch (InputMismatchException e3) {
                System.out.println("Please enter an integer.");
            }
        } while (mainCondition == 0);

    }
}