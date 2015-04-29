/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.gui.shopTable;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import shop.Item;
import shop.Shop;
import shop.exceptions.SetterException;
import shop.exceptions.ShopTableException;
import shop.gui.MainWindow;

/**
 *
 * @author Justas
 */
@SuppressWarnings("serial")
public class ItemTableModel extends AbstractTableModel implements Fireable {

	private ArrayList<Item> data = null;
	private String[] columnNames = null;
	private final int COLUMNS_COUNT = 3;
	private Shop shop;

	public ItemTableModel(Shop shop, ArrayList<Item> dataList) {
		this.data = dataList;
		this.shop = shop;
		initialiazeColumnNames();
	}

	private void initialiazeColumnNames() {
		columnNames = new String[COLUMNS_COUNT];
		columnNames[0] = "ID";
		columnNames[1] = "Pavadinimas";
		columnNames[2] = "Kaina";
	}

	public void setColumnNames(String[] columnNames) throws ShopTableException {
		if (columnNames.length != COLUMNS_COUNT) {
			throw new ShopTableException("Wrong column names count");
		}
		this.columnNames = columnNames;
	}

	public Class<?> getColumnClass(int c) {
		if (c == 4) return String.class;
		if (c == 6) return String.class;
		return getValueAt(0, c).getClass();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];

	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	public boolean isCellEditable(int row, int col) {
		if (col == 0 || col == 5 || col == 7) {
			return false;
		}
		return true;
	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		String tempValue;
		try {
                    Item itemObj = data.get(rowIndex);
                    switch (columnIndex) {
			case 1:
				if (value != null) {
					itemObj.setName((String) value);
					break;
				}
			case 2:
				if (value != null) {
					itemObj.setPrice((double) value);
					break;
				}
				fireTableCellUpdated(rowIndex,6);
				fireTableCellUpdated(rowIndex,7);
				break;
			}

			fireTableCellUpdated(rowIndex, columnIndex);

		} catch (SetterException e) {
			MainWindow.errorMessage(e.getMessage());
		}
	}

	public void fireTableRowsInserted() {
		super.fireTableRowsInserted(0, data.size());
	}

	public void fireTableRowsDeleted() {
		super.fireTableRowsDeleted(0, data.size());
	}

	public void fireTableDataChanged() {
		super.fireTableDataChanged();
	}

	public void fireTableRowsUpdated() {
		super.fireTableRowsUpdated(0, data.size());
	}

	public void fireTableCellUpdated(int row, int column) {
		super.fireTableCellUpdated(row, column);
	}

	public void fireTableStructureChanged() {
		super.fireTableStructureChanged();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
            Item itemObj = data.get(rowIndex);

            switch (columnIndex) {
		case 0:
			return itemObj.getID();
		case 1:
			return itemObj.getName();
		case 2:
			return itemObj.getPrice();
		default:
			return "";
            }
	}
}