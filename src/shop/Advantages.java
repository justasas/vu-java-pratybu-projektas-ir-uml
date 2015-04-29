/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shop;

import shop.exceptions.SetterException;

/**
 * Interface defining methods needed to implement
 * 
 * @author      Justas Rutkauskas, <rujustas @ gmail.com>
 * @lastChange  2014 - 05 - 28
 */
public interface Advantages {
    /**
    * sets discount for new orders
    * @param discount - discount for new orders
    * @throws SetterException
    */
    public void setDiscount(int discount) throws SetterException;
    
    /**
    * gets order discount
    * @return discount of a client
    */
    public int getDiscount();
}