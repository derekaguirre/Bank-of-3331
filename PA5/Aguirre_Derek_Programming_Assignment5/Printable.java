import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * Taken from Anthony Jasso
 *  
 * The Printable interface creates two methods to be implemented both of them report when a user logs in and logs out.
 *
 * @author Anthony Jasso
 * @version 2.0
 * @since 11/11/2020
 * @see Menu
 * @see Logger
 */

interface Printable{

    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>
     * <br>
     * This method will function as a print whenever implemented
     * <br>
     * It will ask the user what their business is.
     * </p>
     * 
     * @param custList An ArrayList of type Customer that contains the information of all customers
     * @throws FileNotFoundException If the file is not found 
     * @throws IOException Input Output Exception 
     * 
     */
    void print(ArrayList<Customer> custList) throws FileNotFoundException, IOException;
    
    /**
     * 
     * Taken from Derek Aguirre
     * 
     * <p>
     * <br>
     * This method will function as a way to report a logout when implemented 
     * <br>
     * </p>
     * @param firstName First name of the customer
     * @param lastName Last name of the customer
     * @throws FileNotFoundException If the file is not found 
     * @throws IOException Input Output Exception
     * 
     */
    void reportLogin(String firstName, String lastName) throws FileNotFoundException, IOException;

    /** 
     * 
     * Taken from Derek Aguirre
     * 
     * <p>
     * <br>
     * This method will function as a way to report a login when implemented 
     * <br>
     * </p>
     * @param firstName First name of the customer
     * @param lastName Last name of the customer
     * @throws FileNotFoundException If the file is not found 
     * @throws IOException Input Output Exception
     * 
     */
    void reportLogout(String firstName, String lastName) throws FileNotFoundException, IOException;
}