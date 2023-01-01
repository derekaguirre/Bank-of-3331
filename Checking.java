/*Derek Aguirre
  8/28/2020
  CS 3331
  Dr. Mejia
  Programming Assignment 1
  
  This assignment serves as a refresher to object-oriented programming and tasks students with creating a bank using objects and everything learned from previous CS courses

  I confirm that the work of this assignment is completely my own. By turning in this assignment, I declare that I did not receive unauthorized assistance.
  Moreover, all deliverables including, but not limited to the source code, lab report and output files were written and produced by me alone.
*/

import java.io.*;
import java.util.*;

public class Checking {
    private String firstName, lastName;
    private int accountNumber;
    private boolean checking, saving;
    private double startingBalance, interestRate;

    public Checking() {
    }

    public Checking(String firstNameIn, String lastNameIn, int accountNumberIn, boolean checkingIn,
            double startingBalanceIn) {
        this.firstName = firstNameIn;
        this.lastName = lastNameIn;
        this.accountNumber = accountNumberIn;
        this.checking = checkingIn;
        this.startingBalance = startingBalanceIn;
    }

    public Checking(String firstNameIn, String lastNameIn, int accountNumberIn, boolean checkingIn, boolean savingIn,
            double startingBalanceIn, double interestRateIn) {
        this.firstName = firstNameIn;
        this.lastName = lastNameIn;
        this.accountNumber = accountNumberIn;
        this.checking = checkingIn;
        this.saving = savingIn;
        this.startingBalance = startingBalanceIn;
        this.interestRate = interestRateIn;
    }

    // Setters and Getters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastNameIn) {
        this.lastName = lastNameIn;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getStartingBalance() {
        return startingBalance;
    }

    public void setStartingBalance(double startingBalance) {
        this.startingBalance = startingBalance;
    }

    public boolean isChecking() {
        return checking;
    }

    public void setChecking(boolean checking) {
        this.checking = checking;
    }

    public boolean isSaving() {
        return saving;
    }

    public void setSaving(boolean saving) {
        this.saving = saving;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    // Methods that perform operations

    public double depositFunction(double startingBalanceInput, double depositAmount) throws NegativeNumberException {
        if (depositAmount < 0)
            throw new NegativeNumberException("Cannot deposit a negative amount.");
        double depositResult = startingBalanceInput + depositAmount;
        return depositResult;
    }

    public double withdrawFunction(double startingBalanceInput, double withdrawAmount)
            throws NegativeNumberException, SubtractionOverflowException {
        if (withdrawAmount < 0)
            throw new NegativeNumberException("Cannot withdraw a negative amount.");
        else if (withdrawAmount > startingBalanceInput)
            throw new SubtractionOverflowException("Cannot withdraw more than your current balance.");
        double withdrawResult = startingBalanceInput - withdrawAmount;
        return withdrawResult;
    }

    // Methods that perform the logging on to a txt file
    public void loginLog(String firstName, String lastName) throws IOException, FileNotFoundException {
        try {
            FileWriter fr = new FileWriter("Log.txt", false);
            fr.write(firstName + " " + lastName + " has logged in. \n\n");
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        } catch (IOException e2) {
            System.out.print(e2);
        }
    }

    public void logoutLog(String firstName, String lastName) throws IOException, FileNotFoundException {
        try {
            FileWriter fr = new FileWriter("Log.txt", true);
            fr.write(firstName + " " + lastName + " has logged out. \n\n");
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        } catch (IOException e2) {
            System.out.print(e2);
        }
    }

    public void inquireBalanceLog(double balanceIn, String firstName, String lastName)
            throws IOException, FileNotFoundException {
        try {
            FileWriter fr = new FileWriter("Log.txt", true);
            fr.write(firstName + " " + lastName + " has requested to see their balance of $" + balanceIn + "\n\n");
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        } catch (IOException e2) {
            System.out.print(e2);
        }
    }

    public void depositLog(double initial, String firstName, String lastName, double moneyInput, double endAmount)
            throws IOException, FileNotFoundException {
        try {
            FileWriter fr = new FileWriter("Log.txt", true);
            fr.write(firstName + " " + lastName + " with an initial balance of $" + initial + " deposited $"
                    + moneyInput + " totaling $" + endAmount + "\n\n");
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        } catch (IOException e2) {
            System.out.print(e2);
        }
    }

    public void withdrawLog(double initial, String firstName, String lastName, double withdrawFunds, double endAmount)
            throws IOException, FileNotFoundException {
        try {
            FileWriter fr = new FileWriter("Log.txt", true);
            fr.write(firstName + " " + lastName + " with an initial balance of $" + initial + " withdrew $"
                    + withdrawFunds + " totaling $" + endAmount + "\n\n");
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        } catch (IOException e2) {
            System.out.print(e2);
        }
    }

    public void transferLog(double initialAmountFirst, double initialAmountSecond, String firstNameFirst,
            String lastNameFirst, String firstNameSecond, String lastNameSecond, double amountTransferred,
            double endAmountFirst, double endAmountSecond) throws IOException, FileNotFoundException {
        try {
            FileWriter fr = new FileWriter("Log.txt", true);
            fr.write(firstNameFirst + " " + lastNameFirst + " has transferred $" + amountTransferred + " to "
                    + firstNameSecond + " " + lastNameSecond + ".\n\n");
            fr.write(firstNameFirst + " " + lastNameFirst + " now has " + endAmountFirst + " from a balance of "
                    + initialAmountFirst + "\n\n");
            fr.write(firstNameSecond + " " + lastNameSecond + " now has " + endAmountSecond + " from a balance of "
                    + initialAmountSecond + "\n\n");
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.print("The file is not found");
        } catch (IOException e2) {
            System.out.print(e2);
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException, InputMismatchException,
            SubtractionOverflowException, NegativeNumberException {
        try {
            BufferedReader buffReader = new BufferedReader(new FileReader("CS 3331 - Bank Users.csv"));
            ArrayList<Checking> bank = new ArrayList<Checking>(); // Create an array list that will hold the information
            buffReader.readLine();
            String line;
            while ((line = buffReader.readLine()) != null) { // Buffered reader will read the information from the file
                                                             // line by line
                String[] tokens = line.split(","); // The program will split the information up for every instance of a
                                                   // comma
                bank.add(new Checking(tokens[0], tokens[1], Integer.parseInt(tokens[2]), // The program will now save
                                                                                         // the information in
                                                                                         // accordance to their data
                                                                                         // types
                        Boolean.parseBoolean(tokens[3]), Double.parseDouble(tokens[5])));
            }
            buffReader.close();

            Scanner sc = new Scanner(System.in);

            // Main code that runs the bank
            System.out.println("\nEnter Account Number");
            int accNum = sc.nextInt();
            for (Checking firstUser : bank) { // The program will verify if the user exists in the bank
                if (accNum == firstUser.getAccountNumber()) {
                    firstUser.loginLog(firstUser.getFirstName(), firstUser.getLastName());
                    System.out.println("\nWelcome back " + firstUser.getFirstName() + " " + firstUser.getLastName() + "\nSelect the desired action: \n");
                    System.out.println(" (1) \t Inquire Balance \n (2) \t Pay an Individual \n (3) \t Deposit cash \n (4) \t Withdraw cash \n (0) \t Exit\n");

                    int choice = sc.nextInt();
                    while (choice < 5 && choice >= 0) { // As long as the user selects a choice belonging to the menu
                                                        // the program will stay running
                        switch (choice) {
                            case 0: // Will log the user out
                                firstUser.logoutLog(firstUser.getFirstName(), firstUser.getLastName());
                                System.out.println("Successfully logged out.");
                                System.exit(0);
                            case 1:// Will inquire a balance
                                System.out.println("Your balance is: " + firstUser.getStartingBalance());
                                System.out.println("\n (1) \t Inquire Balance \n (2) \t Pay an Individual \n (3) \t Deposit cash \n (4) \t Withdraw cash \n (0) \t Exit\n");
                                choice = sc.nextInt();
                                firstUser.inquireBalanceLog(firstUser.getStartingBalance(), firstUser.getFirstName(), firstUser.getLastName());
                                break;
                            case 2:// Will send money from one person to another
                                System.out.println("Account number of recipient: \n");
                                int recipientAccNum = sc.nextInt();

                                Checking secondUser = new Checking();
                                for (Checking whatever : bank) { // Verifies if the second user exists
                                    if (recipientAccNum == whatever.getAccountNumber()
                                            && recipientAccNum != firstUser.getAccountNumber())
                                        secondUser = whatever;
                                }
                                if (recipientAccNum == secondUser.getAccountNumber()
                                        && recipientAccNum != firstUser.getAccountNumber() && recipientAccNum > 0) { // User inputs a correct number

                                    System.out.println(
                                            "How much would you like to send to " + secondUser.getAccountNumber() + ": " + secondUser.getFirstName() + " " + secondUser.getLastName());
                                    double sendingAmount = sc.nextDouble();
                                    double initialAmountFirst = firstUser.getStartingBalance(); // Saves the initial amount of money each person had before the transaction
                                    double initialAmountSecond = secondUser.getStartingBalance();
                                    firstUser.setStartingBalance(
                                            firstUser.withdrawFunction(firstUser.getStartingBalance(), sendingAmount)); // Updates the first person's account balance
                                    secondUser.setStartingBalance(
                                            secondUser.depositFunction(secondUser.getStartingBalance(), sendingAmount));// Updates the second person's account balance
                                    System.out.println("\nSending funds of $" + sendingAmount + " to "
                                            + secondUser.getAccountNumber() + ": " + secondUser.getFirstName() + " "
                                            + secondUser.getLastName());
                                    firstUser.transferLog(initialAmountFirst, initialAmountSecond,
                                            firstUser.getFirstName(), firstUser.getLastName(),
                                            secondUser.getFirstName(), secondUser.getLastName(), sendingAmount,
                                            firstUser.getStartingBalance(), secondUser.getStartingBalance());
                                    System.out.println("\nYou now have a total of $" + firstUser.getStartingBalance());
                                } else if (recipientAccNum == firstUser.getAccountNumber()) { // If the user enters their own account number
                                    System.out.println("Please enter another person's account number.");
                                    break;
                                }

                                else { // If the user enters in an account number that does not exist
                                    System.out
                                            .println("The provided account number does not exist. Please try again.\n");
                                    break;
                                }

                                System.out.println(
                                        "\n (1) \t Inquire Balance \n (2) \t Pay an Individual \n (3) \t Deposit cash \n (4) \t Withdraw cash \n (0) \t Exit\n");
                                choice = sc.nextInt();
                                break;

                            case 3:// Will deposit money into user's account
                                System.out.println("How much would you like to deposit?");

                                double depositAmount = sc.nextDouble();
                                System.out.println("\nDepositing $" + depositAmount);
                                double initialBalance = firstUser.getStartingBalance(); // Saves user's initial balance before the transaction
                                firstUser.setStartingBalance(
                                        firstUser.depositFunction(firstUser.getStartingBalance(), depositAmount));// Updates the user's balance
                                firstUser.depositLog(initialBalance, firstUser.getFirstName(), firstUser.getLastName(),
                                        depositAmount, firstUser.getStartingBalance());
                                System.out.println("You now have a total of " + firstUser.getStartingBalance());
                                System.out.println(" (1) \t Inquire Balance \n (2) \t Pay an Individual \n (3) \t Deposit cash \n (4) \t Withdraw cash \n (0) \t Exit\n");
                                choice = sc.nextInt();
                                break;
                            case 4:// Will withdraw money from a user's account
                                System.out.println("How much would you like to withdraw?");

                                double withdrawAmount = sc.nextDouble();
                                System.out.println("\nWithdrawing $" + withdrawAmount);
                                // Saves balance before transaction
                                initialBalance = firstUser.getStartingBalance();
                                
                                //Updates the user's balance 
                                firstUser.setStartingBalance(firstUser.withdrawFunction(firstUser.getStartingBalance(), withdrawAmount)); 
                                firstUser.withdrawLog(initialBalance, firstUser.getFirstName(), firstUser.getLastName(), withdrawAmount, firstUser.getStartingBalance());
                                System.out.println("You now have a total of " + firstUser.getStartingBalance() + "\n(1) \t Inquire Balance \n (2) \t Pay an Individual \n (3) \t Deposit cash \n (4) \t Withdraw cash \n (0) \t Exit\n\n");
                                choice = sc.nextInt();
                                break;
                        }
                    }
                }

            }

            sc.close();
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
        }
    }
}