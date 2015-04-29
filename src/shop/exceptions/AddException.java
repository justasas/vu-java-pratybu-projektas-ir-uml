package shop.exceptions;

/**
 * @author      Justas Rutkauskas, <rujustas @ gmail.com>
 * @lastChange  2014 - 05 - 28
 */
public class AddException extends Exception{
    
    /** 
    * Class constructor.
    */
    public AddException() {
        super();
    }

    /** 
    * Class constructor with 1 argument.
    * @param msg - message of an exception
    */ 
    public AddException(String msg) {
        super(msg);
    }

    /** 
    * Class constructor with 2 arguments.
    * @param msg - message telling what was wrong
    * @param cause - another throwable that caused this throwable to be constructed.
    */
    public AddException(String msg, Throwable cause) {
        super(msg, cause);
    }	
	
}