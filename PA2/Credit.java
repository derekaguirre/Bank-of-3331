/**
 * The Credit class will be used hold all information relating to a credit account.
 * It will extend the Account class and will be used to hold information about credit accounts.
 *
 * @author Derek Aguirre
 * @version 1.0
 * @since 9/27/2020
 * @see Account
 */

public class Credit extends Account {

    public Credit(int accountNumber, double startingBalance) {
        super(accountNumber, startingBalance);
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
        if (depositAmount < 0)
            throw new NegativeNumberException("Cannot deposit a negative amount.");
        else if (startingBalanceInput + depositAmount >= 0)
            throw new SubtractionOverflowException("Cannot deposit more than current balance.");
        return startingBalanceInput + depositAmount;
    }
}