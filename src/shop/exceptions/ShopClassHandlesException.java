/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.exceptions;

/**
 *
 * @author Justas
 */

public class ShopClassHandlesException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 3429872409925526964L;
    private String methodName = null;

    public ShopClassHandlesException() {
            // TODO Auto-generated constructor stub
    }

    public ShopClassHandlesException(String msg) {
            super(msg);
    }

    public ShopClassHandlesException(String msg, String methodName) {
            super(msg);
            this.methodName = methodName;
    }

    public ShopClassHandlesException(Throwable obj) {
            super(obj);
    }

    public ShopClassHandlesException(String msg, Throwable obj) {
            super(msg, obj);
    }

    public String getMethodName(){
            return methodName;
    }
}