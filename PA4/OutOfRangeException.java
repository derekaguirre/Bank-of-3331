/**
 * Taken from Derek Aguirre
 * 
 * The OutOfRangeException class functions as a custom exception to throw when a customer inputs a number outside of the range of selections in a menu.
 * It also extends RuntimeException.
 *
 * @author Derek Aguirre
 * @version 1.0
 * @since 9/27/2020
 * @see RuntimeException
 */
public class OutOfRangeException extends RuntimeException{
    public OutOfRangeException(String s){
      super(s);
    }
  }