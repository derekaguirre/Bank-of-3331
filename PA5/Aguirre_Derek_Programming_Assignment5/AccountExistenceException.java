/**
 * The AccountExistenceException class functions as a custom exception to throw when a subtraction reaches into the negatives.
 * It also extends ArithmeticException.
 *
 * @author Derek Aguirre
 * @version 2.0
 * @since 11/11/2020
 * @see ArithmeticException
 */

@SuppressWarnings("serial")
public class AccountExistenceException extends NullPointerException{
  
    public AccountExistenceException(String s){
      super(s);
    }
  }