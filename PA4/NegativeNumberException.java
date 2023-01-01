/**
 * 
 * Derek Aguirre
 * 
 * The NegativeNumberException class functions as a custom exception to throw when a customer inputs a negative number in a menu
 * or a function for manipulating their balance. This custom exception extends ArithmeticException.
 *
 * @author Derek Aguirre
 * @version 1.0
 * @since 9/27/2020
 * @see RuntimeException
 */
public class NegativeNumberException extends ArithmeticException{
  public NegativeNumberException(String s){
    super(s);
  }
}