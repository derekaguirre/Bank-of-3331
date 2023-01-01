/**
 * 
 * Derek Aguirre
 * 
 * The NegativeNumberException class functions as a custom exception to throw when a customer inputs a negative number in a menu
 * or a function for manipulating their balance. This custom exception extends ArithmeticException.
 *
 * @author Derek Aguirre
 * @version 2.0
 * @since 11/11/2020
 * @see RuntimeException
 */

@SuppressWarnings("serial")
public class NegativeNumberException extends ArithmeticException{
  public NegativeNumberException(String s){
    super(s);
  }
}