/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.io;

/**
 *
 * @author Justas
 */
import shop.exceptions.SetterException;
import shop.exceptions.ShopIOException;
import shop.gui.MainWindow;
import shop.person.Client;
import shop.person.VIPClient;

public class ClientConverter implements Convertable<Client> {

    /**
     *
     * @param obj
     * @return
     * @throws ShopIOException
     */
    @Override
	public String getData(Client obj) throws ShopIOException {
		if (obj == null){
			throw new ShopIOException("Client object is null");
		}
		
		String dataString = "";
		dataString+= obj.getID()+",";
		dataString+= obj.getName()+",";
		dataString+= obj.getSurname()+",";
		dataString+= obj.getDiscount();
                
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
	public Client readData(String str) throws ShopIOException, NumberFormatException, SetterException {
            String[] data = str.split(",",-1);		


            if (data.length != 4){
                    throw new ShopIOException("String of Client contains wrong amount of parameters", Client.class);
            }

            if (data[3] != "0")
            {
                Client obj = new VIPClient(data[1], data[2], Integer.parseInt(data[3]));
                obj.setName(data[1]);
                obj.setSurname(data[2]);
                VIPClient cp = (VIPClient) obj;
                cp.setDiscount(Integer.parseInt(data[3]));
                return obj;
            }
            else
            {
                Client obj = new Client(data[1], data[2]);
                obj.setName(data[1]);
                obj.setSurname(data[2]);
                return obj;  
            }
            
	}
}