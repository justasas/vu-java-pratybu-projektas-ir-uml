package shop.person;

import shop.Advantages;
import shop.Advantages;
import shop.exceptions.SetterException;
import shop.person.Client;

/**
 * Implementation of a VIPClient
 * 
 * @author      Justas Rutkauskas, <rujustas @ gmail.com>
 * @lastChange  2014 - 05 - 13
 */
public final class VIPClient extends Client implements Advantages {
    
    /**
     * discount for a client
     */
    private int discount;
    
    /** 
    * Class constructor.
    * @param name - name of a new VIP client
    * @param surname - surname of a new VIP client
    * @param discount - discount for orders
    */
    public VIPClient(String name, String surname, int discount) throws SetterException
    {
        super(name, surname);
        setDiscount(discount);
    }
    

    @Override
    /**
    * sets discount for new orders
    * @param discount - discount for new orders
    */
    public void setDiscount(int discount) throws SetterException 
    {
    	if (discount >= 0)
    	{
            this.discount = discount;
        } else { 
            throw new SetterException("discount must be positive");
        }
    }
        
    @Override
    /**
    * gets order discount
    * @return discount of a client
    */
    public int getDiscount()
    {
    	return this.discount;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}