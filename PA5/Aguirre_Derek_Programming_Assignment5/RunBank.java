import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
Derek Aguirre, Anthony Jasso
10/19/2020
CS 3331  Advanced Object-Oriented Programming  Fall 2020
Daniel Mejia
Programming Assignment 5

The purpose of this lab is to merge code between partners and fix previous code that was not implemented well or was not working.
Additional tasks that were specific to this lab include: Implementing an interface, Implementing a password verification for customers.

I confirm that the work of this assignment is completely our own. By turning in this assignment, I declare that I did not receive unauthorized assistance. 
Moreover, all deliverables including, but not limited to the source code, lab report and output files were written and produced by my partner and I, alone.

*/

/**
 * 
 * Taken from Derek Aguirre
 * 
 * The RunBank class is what will be used to run most of the code for this
 * assignment. A user can log in as a Customer or as a Bank manager. Both
 * choices have different functions.
 *
 * @author Derek Aguirre
 * @version 3.0
 * @since 11/11/2020
 */

public class RunBank {
    public static void main(String[] args)throws FileNotFoundException, IOException, InputMismatchException, OutOfRangeException {
        Logger singletonLogger = Logger.getInstance();
        UserConsole console = new UserConsole();
        Menu menuObject = new Menu();
        boolean mainCondition = true;
        do {
            try {
                Scanner sc = new Scanner(System.in); // Create scanner
                ArrayList<Customer> bank = UserConsole.fileRead("CS 3331 - Bank Users 5.csv"); // data structure
                singletonLogger.print(bank);
                menuObject.printBankIntro();
                menuObject.printUserChoice();
                int userChoice = sc.nextInt(); // saves user type selection
                if (userChoice > 3 || userChoice < 0) // range check
                    throw new OutOfRangeException("Please select a number inside the range of selections.");
                while (userChoice <= 3 || userChoice >= 0) {// if choice is in range
                    switch (userChoice) {
                        case 1:// if user is customer
                            console.customerChoice(bank);
                            break;
                        case 2:// if user is bank manager
                            console.managerChoice(bank);
                            break;
                        case 3:// if user selects transaction reader
                            console.transactionActions("Transaction Actions.csv", bank);
                            break;
                        case 0: // exit program7
                            System.out.println("Successfully exited program. Have a good day!");
                            mainCondition = false;
                            singletonLogger.exitProgramLog();
                            break;
                    }
                    if (mainCondition == true) {
                        menuObject.printBankIntro();
                        menuObject.printUserChoice();
                        userChoice = sc.nextInt(); // saves user type selection
                        while (userChoice > 3 || userChoice < 0) {
                            System.out.println("Please select a value inside the range.");
                            menuObject.printUserChoice();
                            userChoice = sc.nextInt();
                        }
                    } else
                        break;
                }
                
            } catch (FileNotFoundException e1) {
                System.out.println("Input file not found. Please double check the name.");
                break;
            } catch (IOException e2) {
                System.out.println("IOException: "+e2);
            } catch (OutOfRangeException e3) {
                System.out.println("Please choose a value inside the range.");
            } catch (InputMismatchException e3) {
                System.out.println("Please enter an integer.");
            }
        } while (mainCondition == true);
      
    }
}