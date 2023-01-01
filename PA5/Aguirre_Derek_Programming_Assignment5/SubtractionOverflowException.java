/**
 * The SubtractionOverflowException class functions as a custom exception to throw when a subtraction reaches into the negatives.
 * It also extends ArithmeticException.
 *
 * @author Derek Aguirre
 * @version 2.0
 * @since 11/11/2020
 * @see ArithmeticException
 */

@SuppressWarnings("serial")
public class SubtractionOverflowException extends ArithmeticException{
    public SubtractionOverflowException(String s){
      super(s);
    } 
  }