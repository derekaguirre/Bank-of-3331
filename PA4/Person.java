/**
 * Taken from Anthony Jasso
 * 
 * The Person abstract class will be used hold all information relating to a person.
 * Since it is an abstract class, this class cannot be instantiated. It will primarily be used for inheriting attributes
 *
 * @author Anthony Jasso
 * @version 2.0
 * @since 10/30/2020
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

    public Person() {
	}

	public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}