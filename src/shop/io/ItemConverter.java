/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.io;

/**
 *
 * @author Justas
 */
import shop.Item;
import shop.exceptions.SetterException;
import shop.exceptions.ShopIOException;

public class ItemConverter implements Convertable<Item> {

     /**
     *
     * @param obj
     * @return
     * @throws ShopIOException
     */
    @Override
	public String getData(Item obj) throws ShopIOException {
            if (obj == null){
                throw new ShopIOException("Item object is null");
            }

            String dataString = "";
            dataString+= obj.getID()+",";
            dataString+= obj.getName()+",";
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
	public Item readData(String str) throws ShopIOException, NumberFormatException, SetterException {
            String[] data = str.split(",",-1);		

            if (data.length != 3){
                    throw new ShopIOException("String of Client contains wrong amount of parameters", Item.class);
            }

            Item obj = new Item(data[1], Double.parseDouble(data[2]));
            obj.setName(data[1]);
            obj.setPrice(Double.parseDouble(data[2]));

            return obj;
        }
}