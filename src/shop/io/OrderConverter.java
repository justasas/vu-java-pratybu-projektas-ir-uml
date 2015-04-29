/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.io;

/**
 *
 * @author Justas
 */
import shop.Order;
import shop.Shop;
import shop.exceptions.SetterException;
import shop.exceptions.ShopIOException;

public class OrderConverter implements Convertable<Order> {

    Shop shop;
    public OrderConverter(Shop shop)
    {
        this.shop = shop;
    }
     /**
     *
     * @param obj
     * @return
     * @throws ShopIOException
     */
    @Override
	public String getData(Order obj) throws ShopIOException {
            if (obj == null){
                throw new ShopIOException("Item object is null");
            }

            String dataString = "";
            dataString += obj.getID()+",";
            dataString += obj.getOwner().getID()+",";
            dataString += obj.getOwner().getName() + obj.getOwner().getSurname() + ",";
            for (int i = 0; i < obj.getItemsList().size(); i++)
                dataString+= obj.getItemsList().get(i).getID() + " ";
            dataString+= ",";
            dataString+= obj.getPrice();
            return dataString;
	}

    /**
     *
     * @param str
     * @return
     * @throws ShopIOException
     * @throws NumberFormatException
     * @throws SetterException
     */
    @Override
	public Order readData(String str) throws ShopIOException, NumberFormatException, SetterException {
            String[] data = str.split(",",-1);		

            if (data.length <= 2){
                    throw new ShopIOException("String of Order contains wrong amount of parameters", Order.class);
            }

            Order obj = new Order();
            obj.setOwner(shop.getClientAt(Integer.parseInt(data[1])-1));
            
            String[] items = data[3].split(" ", -1);
            for (int i = 0; i < items.length-1; i++)
            {
                obj.addItem(shop.getItemAt(Integer.parseInt(items[i])-1));
            }
            return obj;
        }
}