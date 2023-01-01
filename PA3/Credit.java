/**
 * The Credit class will be used hold all information relating to a credit account.
 * It will extend the Account class and will be used to hold information about credit accounts.
 *
 * @author Derek Aguirre
 * @version 2.0
 * @since 10/10/2020
 * @see Account
 */

public class Credit extends Account {
    private int creditMax;

    public Credit(int accountNumber, double startingBalance, int creditMax) {
        super(accountNumber, startingBalance);
        this.creditMax = creditMax;
    }
    public Credit(int accountNumber, double startingBalance) {
        super(accountNumber, startingBalance);
    }
    

    /** 
     * @return The max amount of credit allowed on a customer's account.
     */
    public int getCreditMax() {
        return creditMax;
    }

    /** 
     * @param creditMax The max amount of credit allowed on a customer's account.
     */
    public void setCreditMax(int creditMax) {
        this.creditMax = creditMax;
    }

    /** 
     * This method will handle the depositing for credit accounts because depositing to credit accounts is handled more like a subtraction.
     * @param startingBalanceInput The balance of the credit account before the operation
     * @param depositAmount Specified amount of funds to deposit
     * @return double
     * @throws NegativeNumberException If the customer inputs a negative deposit amount
     * @throws SubtractionOverflowException If the withdraw amount is negative
     */


     public double depositFunction(double startingBalanceInput, double depositAmount) throws NegativeNumberException, SubtractionOverflowException {
         try {
             if (depositAmount < 0)
                 throw new NegativeNumberException("Cannot deposit a negative amount.");
             else if (startingBalanceInput + depositAmount >= 0)
                 throw new SubtractionOverflowException("Cannot deposit more than current balance.");
         } catch (NegativeNumberException e1) {
             System.out.println(e1);
         } catch (SubtractionOverflowException e2) {
             System.out.println(e2);
         }
         return startingBalanceInput + depositAmount;
     }

    /** 
     * This method will polymorphically handle the withdraw function inherited by Account by not allowing it to function for Credit.
     * @param startingBalanceInput the starting balance before the transaction
     * @param withdrawAmount the amount to withdraw
     * @param creditMax the credit limit of a user
     * @return startingBalanceInput-withdrawAmount
     */

    public double withdrawFunction(double startingBalanceInput, double withdrawAmount, int creditMax) throws NegativeNumberException, SubtractionOverflowException{
        try{
            if (withdrawAmount < 0)
            throw new NegativeNumberException("Cannot withdraw a negative amount.");
        else if (withdrawAmount > startingBalanceInput)
            throw new SubtractionOverflowException("Cannot withdraw more than your current balance.");
        
        } catch (NegativeNumberException e1){
            System.out.println(e1);
        } catch (SubtractionOverflowException e2){
            System.out.println(e2);
        }
        return startingBalanceInput - withdrawAmount;
}

    
    /** 
     * This method will polymorphically handle the payFunction inherited by Account by not allowing it to function for Credit.
     */

    public static void transferFunction(){
        System.out.println("Cannot pay another individual from Credit Account");
    }

    
}