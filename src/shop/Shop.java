package shop;

import shop.person.Client;
import java.util.*;
import shop.exceptions.AddException;
import shop.exceptions.ShopIOException;
import shop.io.FileIO;

/**
 * Implementation of a Shop
 * 
 * @author      Justas Rutkauskas, <rujustas @ gmail.com>
 * @lastChange  2014 - 05 - 28
 */

public class Shop {
    private ArrayList<Client> clients = new ArrayList<Client>();
    private ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<Order> orders = new ArrayList<Order>();
    FileIO fileIO = new FileIO(this);
    /**
    * getter for client at position
    * @param pos - index in the list
    * @return client object from list
    */
    public Client getClientAt(int pos)
    {
        if ((pos >= 0) && (pos < clients.size()))
            return clients.get(pos);
        else throw new ArrayIndexOutOfBoundsException("index out of bounds");
    }

    /**
    * getter for item at position
    * @param pos - index in the list
    * @return item object from list
    */
    public Item getItemAt(int pos)
    {
        if ((pos >= 0) && (pos < items.size()))
        {
            Iterator<Item> iterator = items.iterator();
            for (int i = 0; i < pos; i++)
            {
                iterator.next();
            }
            return iterator.next();
        }
        else throw new ArrayIndexOutOfBoundsException("index out of bounds");
    }

    /**
    * getter for order at position
    * @param pos
    * @return order object from list
    */
    public Order getOrderAt(int pos)
    {
        if ((pos >= 0) && (pos < orders.size()))
            return orders.get(pos);
        else throw new ArrayIndexOutOfBoundsException("index out of bounds");
    }
    
    public void load() throws AddException {
        fileIO.read(items, "Items", Item.class);
        fileIO.read(clients, "Clients", Client.class);
        fileIO.read(orders, "Orders", Order.class);
        fileIO.link("links");
    }

    public void save() throws ShopIOException {
        fileIO.write(items, "Items", Item.class);
        fileIO.write(clients, "Clients", Client.class);
        fileIO.write(orders, "Orders", Order.class);
        fileIO.saveLinks("links");
    }
    
    public ArrayList<Item> getItems()
    {
        return items;
    }
    
    public ArrayList<Order> getOrders()
    {
        return orders;
    }
        
    public ArrayList<Client> getClients()
    {
        return clients;
    }
    
    /**
    * Adds Client to Shop.
    * @param c Reference to Client class object
    */
    public boolean add(Client c)
    {
        if (!clients.contains(c))
        {
            clients.add(c);
            return true;
        }
        else return false;
    }

    /**
    * Adds item to shop.
    * @param i Reference to Item class object
    */
    public boolean add(Item i)
    {
        if (!items.contains(i))
        {
            items.add(i);
            return true;
        }
        else return false;    
    }

    /**
    * Adds order to shop.
    * If item in order doesn't exist in shop, 
    * item is automatically added to shop.
    * 
    * @param o Reference to Order class object
    */
    public boolean add(Order o) throws AddException 
    {
        if (o.getOwner() != null && !o.getItemsList().isEmpty())
        {
            orders.add(o);
        } else { 
            throw new AddException("Order does not have an owner or any item");
        }

        for(int i = 0; i < o.getItemsList().size(); i++)
        {
            if (!items.contains(o.getItemsList().get(i)))
                items.add(o.getItemsList().get(i));
        }
        return true;
    }
    
    /**
    * Removes Item from shop.
    * @param i Reference to Item class object
    */
    public void removeItem(Item i)
    {
        items.remove(i);   
    }
    
    /**
    * Removes Order from shop.
    * @param o Reference to Order class object
    */
    public void removeOrder(Order o)
    {
        orders.remove(o);
    }     
    
    /**
    * Removes Client from Shop. 
    * @param c Reference to Client class object
    */
    public void removeClient(Client c)
    {
        clients.remove(c);
    }

    public <T> boolean add(T obj) throws AddException {
        if (obj == null){
                return false;
        }
        if (obj.getClass().getName().indexOf("Item") > 0) {

                return this.add((Item) obj);
        }
        if (obj.getClass().getName().indexOf("Client") > 0) {

                return this.add((Client) obj);
        }
        if (obj.getClass().getName().indexOf("Order") > 0) {

                return this.add((Order) obj);
        }

        return false;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Shop cloned;
        cloned = (Shop)super.clone();

        ArrayList<Client> clientsClone = new ArrayList<Client>();
        ArrayList<Item> itemsClone = new ArrayList<Item>();
        ArrayList<Order> ordersClone = new ArrayList<Order>();

        cloned.items = itemsClone;
        cloned.clients = clientsClone;
        cloned.orders = ordersClone;
        
        Iterator<Item> iterator = itemsClone.iterator();
        for (int i = 0; i < items.size(); i++)
        {
            cloned.items.add((Item) iterator.next().clone());
        }

        Iterator<Client> iterator2 = clientsClone.iterator();
        for (int i = 0; i < clients.size(); i++)
        {
            cloned.clients.add((Client) iterator2.next().clone());
        }

        Iterator<Order> iterator3 = ordersClone.iterator();
        for (int i = 0; i < orders.size(); i++)
        {
            cloned.orders.add((Order) iterator3.next().clone());
        }
        
        return cloned;
    }
}