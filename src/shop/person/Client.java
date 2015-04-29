/**
 * @author      Justas Rutkauskas, <rujustas @ gmail.com>
 * @lastChange  2014 - 03 - 23
 */

package shop.person;

import shop.person.Person;
import shop.exceptions.SetterException;

/**
 * Implementation of a client
 * 
 * @author      Justas Rutkauskas, <rujustas @ gmail.com>
 * @lastChange  2014 - 05 - 13
 */
public class Client extends Person {
    

    /** 
    * Class constructor.
    * @param name - name of a new client
    * @param surname - surname of a new client
    * @throws SetterException
    */
    public Client(String name, String surname) throws SetterException	
    {	
        super(name, surname);
    }
        
    /**
    * getter for discount field
    * @return given discount for client
    */
    public int getDiscount()
    {
    	return 0;
    }
    
    // End of getters.
    
    @Override
    public String toString()
    {
        return this.getName()  + " " + this.getSurname();
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}