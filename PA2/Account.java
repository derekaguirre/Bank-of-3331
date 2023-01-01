/**
 * The Account abstract class will be used deal with all information relating to a customer account.
 * Since it is an abstract class, this class cannot be instantiated. It will primarily be used for inheriting attributes
 *
 * @author Derek Aguirre
 * @version 1.0
 * @since 9/27/2020
 */
public abstract class Account {

    private int accountNumber;
    private double startingBalance;

    public Account(int accountNumberIn, double startingBalanceIn) {
        this.accountNumber = accountNumberIn;
        this.startingBalance = startingBalanceIn;
    }

    
    /** 
     * Returns the account number of an account
     * @return int
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    
    /** 
     * Sets the account number of an account
     * @param accountNumber Account number of an account
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    
    /** 
     * Returns the starting balance of an account
     * @return double
     */
    public double getStartingBalance() {
        return startingBalance;
    }

    
    /** 
     * Sets the starting balance of an account
     * @param startingBalance Starting balance of an account
     */
    public void setStartingBalance(double startingBalance) {
        this.startingBalance = startingBalance;
    }

    
    /** 
     * This method handles depositing funds to a customer's account.
     * @param startingBalanceInput The initial balance of the customer's account before the operation
     * @param depositAmount Specified amount to deposit
     * @return double
     * @throws NegativeNumberException If the withdraw amount is negative
     */
    public double depositFunction(double startingBalanceInput, double depositAmount) throws NegativeNumberException {
        if (depositAmount < 0)
            throw new NegativeNumberException("Cannot deposit a negative amount.");
        return startingBalanceInput + depositAmount;
    }

    
    /** 
     * This method handles withdrawing funds from a customer's account
     * @param startingBalanceInput The initial balance of the customer's account before the operation
     * @param withdrawAmount Specified amount to deposit
     * @return double
     * @throws NegativeNumberException If the withdraw amount is negative
     * @throws SubtractionOverflowException If the resulting operation returns a negative
     */
    public double withdrawFunction(double startingBalanceInput, double withdrawAmount) throws NegativeNumberException, SubtractionOverflowException {
        if (withdrawAmount < 0)
            throw new NegativeNumberException("Cannot withdraw a negative amount.");
        else if (withdrawAmount > startingBalanceInput)
            throw new SubtractionOverflowException("Cannot withdraw more than your current balance.");
        return startingBalanceInput - withdrawAmount;
    }

}