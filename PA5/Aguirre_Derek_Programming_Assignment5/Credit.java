/**
 * Taken from Anthony Jasso
 * 
 * The Credit class will be used hold all information relating to a credit account.
 * It will extend the Account class and will be used to hold information about credit accounts.
 *
 * @author Anthony Jasso
 * @version 3.0
 * @since 11/11/2020
 * @see Account
 */

public class Credit extends Account {
 private int creditMax;
 
 public Credit(int accNum, double strtBal) {
  super(accNum,strtBal);
 }
 
 public void setCredMax(int credMax) {
  this.creditMax = credMax;
 }
 
 public int getCredMax() {
  return creditMax;
 }

  /** 
     * 
  * Taken from Anthony Jasso
  * 
  * This method will handle the depositing for credit accounts because depositing to credit accounts is handled more like a subtraction.
     * @param money The balance of the credit account before the operation
     * @throws NegativeNumberException If the customer inputs a negative deposit amount
     * @throws SubtractionOverflowException If the withdraw amount is negative
     */
  public void depositFunction(double money) throws NegativeNumberException, SubtractionOverflowException { // Method that deposits money from the customer's account
      try {
          if (money < 0) // Checks if the user input is negative
              throw new NegativeNumberException("Error: Cannot deposit negative amount. Please retry again with a positive amount.");
          else if (money > Math.abs(this.getStartingBalance()))
              throw new SubtractionOverflowException("Error: Cannot accept more money than the Credit account balance. Please try again.");
          else {
              this.setStartingBalance(this.getStartingBalance() + money); // Updating customer's account balance
              System.out.println("Success!\n");
          }
      } catch (NegativeNumberException e1) {
          System.out.println(e1);
      } catch (SubtractionOverflowException e2) {
          System.out.println(e2);

      }
  }

 /** 
  * 
  * Taken from Anthony Jasso
  * 
     * This method will polymorphically handle the withdraw function inherited by Account because withdrawing under normal circumstances behaves more like addition.
     * @param money withdrawAmount the amount to withdraw
     */
    public void withdrawFunction(double money) throws NegativeNumberException, SubtractionOverflowException{
        try{
            if (money < 0)
            throw new NegativeNumberException("Cannot withdraw a negative amount.");
        else if (money > this.getStartingBalance())
            throw new SubtractionOverflowException("Cannot withdraw more than your current balance.");
    else
  this.setStartingBalance(this.getStartingBalance() - money); //Updating customer's account balance
  System.out.println("Success!");
        } catch (NegativeNumberException e1){
            System.out.println(e1);
        } catch (SubtractionOverflowException e2){
            System.out.println(e2);
        }
}


    /** 
     * Taken from Anthony Jasso
     * 
     * This method will polymorphically handle the transfer function inherited by Account by not allowing it to function for Credit.
     */
    public static void transferFunction(){
        System.out.println("Cannot pay another individual from Credit Account");
    }
}

