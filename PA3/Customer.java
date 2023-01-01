/**
 * The Customer class will be used hold all information from Person, Checking, Savings, and Credit classes.
 * It extend the Person class and will be able to be instantiated so the attributes can be manipulated.
 *
 * @author Derek Aguirre
 * @version 2.0
 * @since 10/10/2020
 * @see Person
 * @see Checking
 * @see Savings
 * @see Credit
 */
public class Customer extends Person{
    private int idNumber;
    private Checking checkingAccount;
    private Savings savingsAccount;
    private Credit creditAccount;

    public Customer(String firstNameIn, String lastNameIn, String dateofBirthIn, int idNumberIn, String addressIn, String phoneNumberIn, Checking checkingAccount, Savings savingsAccount, Credit creditAccount) {
        super(firstNameIn, lastNameIn, dateofBirthIn, addressIn, phoneNumberIn);
        this.idNumber = idNumberIn;
        this.checkingAccount = checkingAccount;
        this.savingsAccount = savingsAccount;
        this.creditAccount = creditAccount;
    }

    public Customer(String firstNameIn, String lastNameIn, String dateOfBirthIn, String addressIn, String phoneNumberIn) {
        super(firstNameIn, lastNameIn, dateOfBirthIn, addressIn, phoneNumberIn);
    }

    public Customer(String firstNameIn, String lastNameIn, String dateofBirthIn, String addressIn, String phoneNumberIn, int idNumberIn) {
        super(firstNameIn, lastNameIn, dateofBirthIn, addressIn, phoneNumberIn);
        this.idNumber = idNumberIn;
    }
        
    
    public Customer(String firstNameIn, String lastNameIn, String dateofBirthIn, int idNumberIn, String addressIn, String phoneNumberIn, Savings savingsAccount) {
        super(firstNameIn, lastNameIn, dateofBirthIn, addressIn, phoneNumberIn);
        this.idNumber = idNumberIn;
        this.savingsAccount = savingsAccount;
    }
    public Customer(String firstNameIn, String lastNameIn, String dateofBirthIn, int idNumberIn, String addressIn, String phoneNumberIn, Checking checkingAccount, Savings savingsAccount) {
        super(firstNameIn, lastNameIn, dateofBirthIn, addressIn, phoneNumberIn);
        this.idNumber = idNumberIn;
        this.checkingAccount = checkingAccount;
        this.savingsAccount = savingsAccount;
    }
    public Customer(String firstNameIn, String lastNameIn, String dateofBirthIn, int idNumberIn, String addressIn, String phoneNumberIn, Savings savingsAccount, Credit creditAccount) {
        super(firstNameIn, lastNameIn, dateofBirthIn, addressIn, phoneNumberIn);
        this.idNumber = idNumberIn;
        this.savingsAccount = savingsAccount;
    }

    
    /** 
     * @return String
     */
    public int getIdNumber() {
        return idNumber;
    }

    
    /** 
     * @param idNumberIn ID number of a customer
     */
    public void setIdNumber(int idNumberIn) {
        this.idNumber = idNumberIn;
    }

    
    /** 
     * @return Checking
     */
    public Checking getCheckingAccount() {
        return checkingAccount;
    }

    
    /** 
     * @param checkingAccount Checking account of a customer
     */
    public void setCheckingAccount(Checking checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    
    /** 
     * @return Savings
     */
    public Savings getSavingsAccount() {
        return savingsAccount;
    }

    
    /** 
     * @param savingsAccount Savings account of a customer
     */
    public void setSavingsAccount(Savings savingsAccount) {
        this.savingsAccount = savingsAccount;
    }

    
    /** 
     * @return Credit
     */
    public Credit getCreditAccount() {
        return creditAccount;
    }

    
    /** 
     * @param creditAccount Credit account of a customer
     */
    public void setCreditAccount(Credit creditAccount) {
        this.creditAccount = creditAccount;
    }


}