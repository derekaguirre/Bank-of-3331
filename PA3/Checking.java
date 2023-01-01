/**
 * The Checking class will be used hold all information relating to a checking account.
 * It will extend the Account class and will be used to hold information about checking accounts.
 *
 * @author Derek Aguirre
 * @version 2.0
 * @since 10/10/2020
 * @see Account
 */

public class Checking extends Account {
    
    public Checking(int accountNumber, double startingBalance) {
        super(accountNumber, startingBalance);
    }

    /** 
     * This method performs account updating when paying another customer.
     * It will send from checking to checking.
     * 
     * @param acc1 The Checking account of the sending customer
     * @param acc2 The Checking account of the recipient customer
     * @param payAmount The amount of funds that will be paid from one customer to the other
     * @throws NegativeNumberException Will be thrown if the pay amount is less than zero
     * @throws SubtractionOverflowException Will be thrown whenever the withdraw function handles a number flipping to another sign
     */
    
    public void transferFunction(Checking acc1, Checking acc2, double payAmount) throws NegativeNumberException, SubtractionOverflowException {
        try {
            if (payAmount < 0)
                throw new NegativeNumberException("Cannot deposit a negative amount.");
            else {
                double initialBalanceCh1 = acc1.getStartingBalance(); //saves initial balance of both users
                double initialBalanceCh2 = acc2.getStartingBalance(); 
                acc1.setStartingBalance(acc1.withdrawFunction(acc1.getStartingBalance(), payAmount));
                acc2.setStartingBalance(acc2.depositFunction(acc2.getStartingBalance(), payAmount));
                System.out.println("Successfully transferred $"+ payAmount+" from your Checking Account (" + acc1.getAccountNumber() + ") to Checking Account (" +acc2.getAccountNumber() +")\n"); //confirmation
                System.out.println("Your checking account funds updated to $"+acc1.getStartingBalance() + " from the initial balance of $" +initialBalanceCh1);
                System.out.println("Their checking account funds updated to $"+acc2.getStartingBalance() + " from the initial balance of $" +initialBalanceCh2);
            }
            
        } catch (NegativeNumberException e1) {
            System.out.println(e1);
        } catch (SubtractionOverflowException e2){
            System.err.println(e2);
        }
    }

    /** 
     * This method performs account updating when paying another customer.
     * It will send from checking to savings.
     * 
     * @param acc1 The Checking account of the sending customer
     * @param acc2 The Savings account of the recipeint customer
     * @param payAmount The amount of funds that will be paid from one customer to the other
     * @throws NegativeNumberException Will be thrown if the pay amount is less than zero
     * @throws SubtractionOverflowException Will be thrown whenever the withdraw function handles a number flipping to another sign
     */
    
    public void transferFunction(Checking acc1, Savings acc2, double payAmount) throws NegativeNumberException, SubtractionOverflowException {
        try {
            if (payAmount < 0)
                throw new NegativeNumberException("Cannot deposit a negative amount.");
            else {
                double initialBalanceCh = acc1.getStartingBalance(); //saves initial balance of both users
                double initialBalanceSa = acc2.getStartingBalance(); 
                acc1.setStartingBalance(acc1.withdrawFunction(acc1.getStartingBalance(), payAmount));
                acc2.setStartingBalance(acc2.depositFunction(acc2.getStartingBalance(), payAmount));
                System.out.println("Successfully transferred $"+ payAmount+" from your Checking Account (" + acc1.getAccountNumber() + ") to Savings Account (" +acc2.getAccountNumber() +")\n"); //confirmation
                System.out.println("Your Checking account funds updated to $"+acc1.getStartingBalance() + " from the initial balance of $" +initialBalanceCh);
                System.out.println("Their Savings account funds updated to $"+acc2.getStartingBalance() + " from the initial balance of $" +initialBalanceSa);
            }
            
        } catch (NegativeNumberException e1) {
            System.out.println(e1);
        } catch (SubtractionOverflowException e2){
            System.err.println(e2);
        }
    }

    /** 
     * This method performs account updating when paying another customer.
     * It will send from checking to savings.
     * 
     * @param acc1 The Checking account of the sending customer
     * @param acc2 The Credit account of the recipient customer
     * @param payAmount The amount of funds that will be paid from one customer to the other
     * @throws NegativeNumberException Will be thrown if the pay amount is less than zero
     * @throws SubtractionOverflowException Will be thrown whenever the withdraw function handles a number flipping to another sign
     */
    
    public void transferFunction(Checking acc1, Credit acc2, double payAmount) throws NegativeNumberException, SubtractionOverflowException {
        try {
            if (payAmount < 0)
                throw new NegativeNumberException("Cannot deposit a negative amount.");
            else {
                double initialBalanceCh = acc1.getStartingBalance(); //saves initial balance of both users
                double initialBalanceCr = acc2.getStartingBalance(); 
                acc1.setStartingBalance(acc1.withdrawFunction(acc1.getStartingBalance(), payAmount));
                acc2.setStartingBalance(acc2.depositFunction(acc2.getStartingBalance(), payAmount));
                System.out.println("Successfully transferred $"+ payAmount+" from your Checking Account (" + acc1.getAccountNumber() + ") to Credit Account (" +acc2.getAccountNumber() +")\n"); //confirmation
                System.out.println("Your Checking account funds updated to $"+acc1.getStartingBalance() + " from the initial balance of $" +initialBalanceCh);
                System.out.println("Their Credit account funds updated to $"+acc2.getStartingBalance() + " from the initial balance of $" +initialBalanceCr);
            }
            
        } catch (NegativeNumberException e1) {
            System.out.println(e1);
        } catch (SubtractionOverflowException e2){
            System.err.println(e2);
        }
    }

}