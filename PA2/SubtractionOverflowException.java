/**
 * The SubtractionOverflowException class functions as a custom exception to throw when a subtraction reaches into the negatives.
 * It also extends ArithmeticException.
 *
 * @author Derek Aguirre
 * @version 1.0
 * @since 9/27/2020
 * @see ArithmeticException
 */
public class SubtractionOverflowException extends ArithmeticException{
    public SubtractionOverflowException(String s){
      super(s);
    }
  }