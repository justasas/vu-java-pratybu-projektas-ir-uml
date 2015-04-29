/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.exceptions;

@SuppressWarnings("serial")
public class ShopIOException extends Exception {

	private Class<?> dbClass = null;
	
	public ShopIOException() {

	}

	public ShopIOException(String msg) {
		super(msg);
	}
	
	public ShopIOException(String msg, Throwable cause) {
		super(msg, cause);
	}	
	
	public ShopIOException(String msg, Class<?> dbClass) {
		super(msg);
		this.dbClass = dbClass;
	}
	
	public Class<?> getDBClass(){
		return dbClass;
	}
}