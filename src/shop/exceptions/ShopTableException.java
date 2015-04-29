/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.exceptions;

@SuppressWarnings("serial")
public class ShopTableException extends Exception {

	public ShopTableException() {
		super();
	}
	
	public ShopTableException(String msg) {
		super(msg);
	}
	
	public ShopTableException(String msg, Throwable cause) {
		super(msg, cause);
	}	
}