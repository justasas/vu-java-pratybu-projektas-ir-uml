package shop;

import shop.person.Client;
import shop.person.Person;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for Order class
 * 
 * @author      Justas Rutkauskas, <rujustas @ gmail.com>
 * @lastChange  2014 - 05 - 28
 */
public class Order implements Cloneable {
    private final int ID;
    private static int lastID;
    
    public Order()
    { 
        lastID++;
        ID = lastID;
    }
    /**
    * order name
    */
    private double price;
    /**
    * items list in the order
    */
    private List<Item> items = new ArrayList();
    /**
    * order owner
    */
    private Client client;

    
    // Setters:

    /**
    * sets an owner of order
    * @param c - reference to Client class object
    */
    public void setOwner(Client c)
    {
        client = c;
    }
    
    // Getters:
    
    /**
    * getter for price field
    * @return order price
    */
    public double getPrice()	
    {
        calculatePrice();
    	return this.price;
    }
    
    /**
    * getter for owner field
    * @return order owner
    */
    public Person getOwner()
    {
        return client;
    }
    
    /**
    * getter for items field
    * @return item's list
    */
    public List<Item> getItemsList()
    {
        return items;
    }
    
    // End of getters.

    /**
    * Adds item to order.
    * @param item Reference to Item class object
    */
    public void addItem(Item item)
    {
    	items.add(item);
    }

    /**
    * Adds 1 or more items to shop.
    * @param item Reference to Item class object
    * @param count - number of items to add
    */
    public void addItem(Item item, int count)
    {
    	if (count > 0)
    	{
            for (int i = 0; i < count; i++)
            {
    		items.add(item);
            }
    	}
    }

    /**
    * Removes item from order.
    * @param item Reference to Item class object which we want to remove
    */
    public void removeItem(Item item)
    {
        items.remove(item);
    }
    
    /**
    * Calculates total order price.
    */
    public void calculatePrice()
    {
        this.price = 0;
        if (!items.isEmpty()){
            for (int i = 0; i < items.size(); i++)
            {
                this.price += items.listIterator(i).next().getPrice();
            } 

            client.getDiscount();
            this.price = this.price * (100+client.getDiscount()) / 100;
        }
    }
    
    @Override
    public String toString()
    {
        calculatePrice();
        return this.price + " " + items.size();
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        Order cloned;
        cloned = (Order)super.clone();
        List<Item> newItems = new ArrayList();
        cloned.items = newItems;
        cloned.setOwner((Client) cloned.getOwner().clone());
        for (int i = 0; i < items.size(); i++)
        {
            cloned.items.add((Item) items.get(i).clone());
        }
        return cloned;
    }

    public Object getID() {
        return ID;
    }
}