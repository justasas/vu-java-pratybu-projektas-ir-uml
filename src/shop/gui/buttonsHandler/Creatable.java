package shop.gui.buttonsHandler;

import shop.Shop;
import shop.exceptions.SetterException;

public interface Creatable <T> {
	public String [] getColumnNames();
	T getInstance(Shop shop, String[] values)
			throws SetterException;
}

