import java.text.*;
/**
 * Taken from Anthony Jasso
 * 
 * The Account abstract class will be used deal with all information and operations relating to a customer account.
 * Since it is an abstract class, this class cannot be instantiated. It will be used for inheriting attributes
 * and operations that accounts will be using for Depositing, Withdrawing, and Paying
 *
 * @author Anthony Jasso
 * @version 3.0
 * @since 11/11/2020
 */
public abstract class Account {

    private int accountNumber;
    private double startingBalance;


    public  Account(int accNum, double startingBal) {
        this.accountNumber = accNum;
        this.startingBalance = startingBal;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getStartingBalance() {
        DecimalFormat df = new DecimalFormat("#.##");
        double formatted = Double.parseDouble(df.format(startingBalance));
        return formatted;
    }

    public void setStartingBalance(double startingBalance) {
        DecimalFormat df = new DecimalFormat("#.##");
        double formatted = Double.parseDouble(df.format(startingBalance));
        this.startingBalance = formatted;
    }  
 
    /**
     * Taken from Anthony Jasso
     * 
     * This method performs account updating when paying another customer.
     * It will send from checking to savings.
     * 
     * @param money The amount of funds that will be paid from one customer to the other
     * @param receiver The account of the recipient
     * @throws NegativeNumberException Will be thrown if the pay amount is less than zero
     * @throws SubtractionOverflowException Will be thrown whenever the withdraw function handles a number flipping to another sign
     */
 public void transferFunction(double money, Account receiver) throws NegativeNumberException, SubtractionOverflowException { // Method that pays another bank member
        try {
            if (money < 0) // Checks if the user input is negative
                throw new NegativeNumberException("Error: Cannot pay negative amount. Please retry again with a positive amount.");
            else if (this.getStartingBalance() - money < 0) // Checking if the payer has enough money in their account to pay the recipient
                throw new SubtractionOverflowException("Error: You do not have the sufficient funds in your account for that transaction.");
            else {
                double initialBal1 = this.getStartingBalance();
                double initialBal2 = receiver.getStartingBalance();
                this.setStartingBalance(this.getStartingBalance() - money); // Updating the payers account balance
                receiver.setStartingBalance(receiver.getStartingBalance() + money); // Updating the recipient's account balance
                System.out.println("Account Successfully transferred $"+ money+" from Account (" + this.getAccountNumber() + ") to Account (" +receiver.getAccountNumber()+")"); //confirmation
                System.out.println("Sender account funds updated to $"+this.getStartingBalance() + " from the initial balance of $" +initialBal1);
                System.out.println("Recipient account funds updated to $"+receiver.getStartingBalance() + " from the initial balance of $" +initialBal2 +"\n");
            }
        } catch (NegativeNumberException e1) {
            System.out.println(e1);
        } catch (SubtractionOverflowException e2) {
            System.err.println(e2);
        }
    }
    

 /** 
     * Taken from Anthony Jasso
     * 
     * This method handles withdrawing funds from a customer's account
     * @param money The amount of money that this transaction will handle.
     * @throws NegativeNumberException If the withdraw amount is negative
     * @throws SubtractionOverflowException If the resulting operation returns a negative
     */
 public void withdrawFunction(double money) throws NegativeNumberException, SubtractionOverflowException { //Method that withdraws money from the customer's account
  try{
            if (money < 0)
                throw new NegativeNumberException("Error: Cannot withdraw negative amount. Please retry again with a positive amount.");
            else if(this.getStartingBalance() - money < 0)
                throw new SubtractionOverflowException("Error: You do not have the sufficient funds in your account for that transaction.");
            else {
                this.setStartingBalance(this.getStartingBalance() - money); //Updating customer's account balance
                System.out.println("Success!\n");
            }
        } catch (NegativeNumberException e1){
            System.out.println(e1);
        } catch (SubtractionOverflowException e2){
            System.out.println(e2);
        }
    }
    /** 
     * Taken from Anthony Jasso
     * 
     * This method handles depositing funds to a customer's account.
     * @param money Specified amount to deposit
     * @throws NegativeNumberException If the withdraw amount is negative
     */
 public void depositFunction(double money) { //Method that deposits money from the customer's account
  try{
            if (money <= 0)
                throw new NegativeNumberException("Error: Cannot deposit negative amount. Please retry again with a positive amount.");

            else {
                this.setStartingBalance(this.getStartingBalance() + money); //Updating customer's account balance
                System.out.println("Success!\n");
            }
        } catch (NegativeNumberException e1){
            System.out.println(e1);
        }
 }

    

    
    
}
 