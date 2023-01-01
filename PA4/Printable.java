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
 * @version 3.0
 * @since 10/30/2020
 * @see Menu
 * @see Logger
 */

interface Printable{

    void print(ArrayList<Customer> custList) throws IOException, FileNotFoundException;

    void reportLogin(String firstName, String lastName) throws IOException, FileNotFoundException;

    void reportLogout(String firstName, String lastName) throws IOException, FileNotFoundException;
}