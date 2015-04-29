package shop.exceptions;

/**
 * @author      Justas Rutkauskas, <rujustas @ gmail.com>
 * @lastChange  2014 - 05 - 28
 */
public class SetterException extends Exception{

    /** 
    * Class constructor.
    */
    public SetterException() {
        super();
    }

    /** 
    * Class constructor with 1 argument.
    * @param msg - message of an exception
    */ 
    public SetterException(String msg) {
        super(msg);
    }

    public SetterException(String msg, Throwable cause) {
        super(msg, cause);
    }	
	
}