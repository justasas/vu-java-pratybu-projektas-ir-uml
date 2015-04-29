package shop.gui.buttonsHandler;

import shop.Shop;
import shop.exceptions.SetterException;
import shop.gui.MainWindow;
import shop.person.Client;
import shop.person.VIPClient;

/**
 *
 * @author Justas
 */
public class ShopCreateClient implements Creatable<Client> {
	private final int COLUMNS_COUNT = 3;

        @Override
	public Client getInstance(Shop shop, String[] values)
            throws SetterException {
		try {
                    if (values[2] != "0")
                    {
                        Client clientObj = new VIPClient("sdasd", "asd", 1);
                        clientObj.setName(values[0]);
                        clientObj.setSurname(values[1]);
                        VIPClient cp = (VIPClient) clientObj;
                        cp.setDiscount(Integer.parseInt(values[2]));
                        
                        return clientObj;
                    }
                    else {
                        Client clientObj = new Client("sdasd", "asd");
                        clientObj.setName(values[0]);
                        clientObj.setSurname(values[1]);
                        return clientObj;
                    }
                } catch (NumberFormatException e) {
                    MainWindow.errorMessage("ERROR: wrong " + e.getMessage());
		}
		return null;
	}

	@Override
	public String[] getColumnNames() {
            String[] columnNames = new String[COLUMNS_COUNT];              
            columnNames[0] = "Vardas";
            columnNames[1] = "Pavarde";
            columnNames[2] = "Nuolaida";
            return columnNames;
	}        
}