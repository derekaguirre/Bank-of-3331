/**
 * Taken from Derek Aguirre
 * 
 * The OutOfRangeException class functions as a custom exception to throw when a customer inputs a number outside of the range of selections in a menu.
 * It also extends RuntimeException.
 *
 * @author Derek Aguirre
 * @version 3.0
 * @since 11/11/2020
 * @see RuntimeException
 */

@SuppressWarnings("serial")
public class OutOfRangeException extends RuntimeException{
    public OutOfRangeException(String s){
      super(s);
    }
  }