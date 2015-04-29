/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.io;

import shop.exceptions.SetterException;
import shop.exceptions.ShopIOException;

/**
 *
 * @author Justas
 */
public interface Convertable <T>{
    String getData(T obj) throws ShopIOException;
    T readData(String str) throws ShopIOException, NumberFormatException, SetterException; 
}