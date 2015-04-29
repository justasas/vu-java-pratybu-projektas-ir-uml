package shop.gui.buttonsHandler;

import shop.Shop;
import shop.exceptions.SetterException;
import shop.gui.MainWindow;
import shop.Order;

public class ShopCreateOrder implements Creatable<Order> {
	private final int COLUMNS_COUNT = 2;

	@Override
	public Order getInstance(Shop shop, String[] values)
			throws SetterException {
		try {
			Order orderObj = new Order();

			if (!values[0].isEmpty()) {
                            orderObj.setOwner(shop.getClientAt(
						Integer.parseInt(values[0])-1));
			}
                        
			if (!values[1].isEmpty()) {
				String[] ids = values[1].split(" ");
				for (int i = 0; i < ids.length; i++) {
					orderObj.addItem(shop.getItemAt(
							Integer.parseInt(ids[i])-1));
				}
			}
               
			return orderObj;
		} catch (NumberFormatException e) {
			MainWindow.errorMessage("ERROR: wrong " + e.getMessage());
		}
		return null;

	}

	@Override
	public String[] getColumnNames() {
            String[] columnNames = new String[COLUMNS_COUNT];
            columnNames[0] = "ID (Kliento)";
            columnNames[1] = "Prekių ID";
            return columnNames;
	}
        
}