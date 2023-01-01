
/**
 * The Person abstract class will be used hold all information relating to a person.
 * Since it is an abstract class, this class cannot be instantiated. It will primarily be used for inheriting attributes
 *
 * @author Derek Aguirre
 * @version 1.0
 * @since 9/27/2020
 */

public abstract class Person {
    private String firstName, lastName, dateOfBirth, address, phoneNumber;

    public Person(String firstNameIn, String lastNameIn, String dateOfBirthIn, String addressIn, String phoneNumberIn) {
        this.firstName = firstNameIn;
        this.lastName = lastNameIn;
        this.dateOfBirth = dateOfBirthIn;
        this.address = addressIn;
        this.phoneNumber = phoneNumberIn;
    }

    public Person(String firstNameIn) {
        this.firstName = firstNameIn;
    }

    
    /** 
     * Returns the first name of a person
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    
    /** 
     * Sets the first name of a person
     * @param firstName First name of a person
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    
    /** 
     * Returns the last name of a person
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    
    /** 
     * Sets the last name of a person
     * @param lastName Last name of a person
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    /** 
     * Returns the date of birth of a person
     * @return String
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    
    /** 
     * Sets the date of birth of a person
     * @param dateOfBirth Date of birth of a person
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    
    /** 
     * Returns the address of a person
     * 
     * @return String
     */
    public String getAddress() {
        return address;
    }

    
    /** 
     * Sets the address of a person
     * @param address Address of a person
     */
    public void setAddress(String address) {
        this.address = address;
    }

    
    /** 
     * Returns the phone number of a person
     * 
     * @return String
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    
    /** 
     * Sets the phone number of a person
     * @param phoneNumber Phone number of a person
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}