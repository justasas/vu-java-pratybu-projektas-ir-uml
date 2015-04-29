/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.gui.buttonsHandler;

import shop.Item;
import shop.Shop;
import shop.exceptions.SetterException;
import shop.gui.MainWindow;

/**
 *
 * @author Justas
 */
public class ShopCreateItem implements Creatable<Item> {
	private final int COLUMNS_COUNT = 2;

	@Override
	public Item getInstance(Shop shop, String[] values)
			throws SetterException {
		try {
			Item itemObj = new Item("sdasd", 999);
			itemObj.setName(values[0]);
			if (!values[1].isEmpty()) {
				itemObj.setPrice(Double.parseDouble(values[1]));
                        }
			return itemObj;
		} catch (NumberFormatException e) {
			MainWindow.errorMessage("ERROR: wrong " + e.getMessage());
		}
		return null;

	}

	@Override
	public String[] getColumnNames() {
		String[] columnNames = new String[COLUMNS_COUNT];              
		columnNames[0] = "Pavadinimas";
		columnNames[1] = "Kaina";
		return columnNames;
	}
        
}