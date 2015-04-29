/**
 * @author      Justas Rutkauskas, <rujustas @ gmail.com>
 * @lastChange  2014 - 05 - 28
 */

package shop;

import shop.exceptions.SetterException;

/**
 * Implementation for an Item
 * 
 * @author      Justas Rutkauskas, <rujustas @ gmail.com>
 * @lastChange  2014 - 05 - 13
 */
public class Item implements Comparable<Item>, Cloneable {

    /**
    * item's ID
    */
    private static int maxID;
    /**
    * item's ID
    */
    private int ID;
    /**
    * item's name
    */
    private String name;
    /**
    * item's price
    */
    private double price;
    
    /** 
    * Class constructor.
    * @param name - item's name
    * @param price - price of a new item
    * @throws SetterException
    */
    public Item(String name, double price) throws SetterException			
    {
        maxID++;
        ID = maxID;
        setName(name);
        setPrice(price);
    }
    
    // Setters:
 
    /**
    * sets item name
    * @param name - name of item
    * @throws SetterException
    */
    public void setName(String name) throws SetterException
    {
    	if (name.length() > 0)
    	{
            this.name = name;
        } else { 
            throw new SetterException("Item's name string is empty");
        }
    }

    /**
    * sets item price
    * @param price - price of item
    * @throws SetterException
    */
    public void setPrice(double price) throws SetterException
    {
    	if (price > 0)
    	{
            this.price = price;
        } else { 
            throw new SetterException("Item price must be positive");
        }
    }
    
    // Getters:

    /**
    * getter for ID field
    * @return item's ID
    */
    public int getID()
    {
        return this.ID;
    }
    
    /**
    * getter for name field
    * @return item's name
    */
    public String getName()
    {
        return this.name;
    }

    /**
    * getter for price field
    * @return item's price
    */
    public double getPrice()
    {
    	return this.price;
    }
    
    // End of getters.
    
    @Override
    public String toString()
    {
        return this.name  + " " + this.price + " ";
    }
    
    @Override
    public int compareTo(Item other)
    {
        return this.name.compareTo(other.getName());
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        Item objClone = (Item)super.clone();
        return objClone;
    }
}