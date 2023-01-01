/**
 * 
 * Taken from Anthony Jasso
 * 
 * The Customer class will be used hold all information from Person, Checking, Savings, and Credit classes.
 * It extend the Person class and will be able to be instantiated so the attributes can be manipulated.
 *
 * @author Anthony Jasso
 * @version 3.0
 * @since 10/30/2020
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
    private String password;

    public Customer(){
        super();
    }

    public Customer(String firstNameIn, String lastNameIn, String dateOfBirthIn, String addressIn, String phoneNumberIn) {
        super(firstNameIn, lastNameIn, dateOfBirthIn, addressIn, phoneNumberIn);
    }

    public Customer(String firstNameIn, String lastNameIn, String dateofBirthIn, String addressIn, String phoneNumberIn, int idNumberIn) {
        super(firstNameIn, lastNameIn, dateofBirthIn, addressIn, phoneNumberIn);
        this.idNumber = idNumberIn;
    }
        
    public Customer(String firstNameIn, String lastNameIn, String dateofBirthIn, String addressIn, String phoneNumberIn, int idNumberIn, String pass, Checking checkingAccount, Savings savingsAccount, Credit creditAccount) {
        super(firstNameIn, lastNameIn, dateofBirthIn, addressIn, phoneNumberIn);
        this.idNumber = idNumberIn;
        this.checkingAccount = checkingAccount;
        this.savingsAccount = savingsAccount;
        this.creditAccount = creditAccount;
        this.password = pass;
    }
    public Customer(String firstNameIn, String lastNameIn, String dateofBirthIn, String addressIn, String phoneNumberIn, int idNumberIn, Savings savingsAccount) {
        super(firstNameIn, lastNameIn, dateofBirthIn, addressIn, phoneNumberIn);
        this.idNumber = idNumberIn;
        this.savingsAccount = savingsAccount;
    }
    public Customer(String firstNameIn, String lastNameIn, String dateofBirthIn, String addressIn, String phoneNumberIn, int idNumberIn, Checking checkingAccount, Savings savingsAccount) {
        super(firstNameIn, lastNameIn, dateofBirthIn, addressIn, phoneNumberIn);
        this.idNumber = idNumberIn;
        this.checkingAccount = checkingAccount;
        this.savingsAccount = savingsAccount;
    }
    public Customer(String firstNameIn, String lastNameIn, String dateofBirthIn, String addressIn, String phoneNumberIn, int idNumberIn, Savings savingsAccount, Credit creditAccount) {
        super(firstNameIn, lastNameIn, dateofBirthIn, addressIn, phoneNumberIn);
        this.idNumber = idNumberIn;
        this.savingsAccount = savingsAccount;
        this.creditAccount = creditAccount;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public  Checking getCheckingAccount() {
        return checkingAccount;
    }

    public void setCheckingAccount( Checking checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    public  Savings getSavingsAccount() {
        return savingsAccount;
    }

    public void setSavingsAccount( Savings savingsAccount) {
        this.savingsAccount = savingsAccount;
    }

    public  Credit getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount( Credit creditAccount) {
        this.creditAccount = creditAccount;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    
}