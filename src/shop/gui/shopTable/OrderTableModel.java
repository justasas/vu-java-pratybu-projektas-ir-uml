package shop.gui.shopTable;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import shop.Shop;
import shop.exceptions.ShopTableException;
import shop.gui.MainWindow;
import shop.Order;
import shop.exceptions.SetterException;

@SuppressWarnings("serial")
public class OrderTableModel extends AbstractTableModel implements Fireable {

	private ArrayList<Order> data = null;
	private String[] columnNames = null;
	private final int COLUMNS_COUNT = 5;
	private Shop shop;

	public OrderTableModel(Shop shop, ArrayList<Order> dataList) {
		this.data = dataList;
		this.shop = shop;
		initialiazeColumnNames();
	}

	private void initialiazeColumnNames() {
            columnNames = new String[COLUMNS_COUNT];
            columnNames[0] = "ID (Užsakymo)";
            columnNames[1] = "ID (Kliento)";
            columnNames[2] = "Vardas ir pavardė";
            columnNames[3] = "Prekės";
            columnNames[4] = "Suma";
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
               
			Order orderObj = data.get(rowIndex);
			switch (columnIndex) {
			case 2:
				tempValue = (String) value;
				if (!tempValue.isEmpty()) {
					orderObj.addItem(shop.getItemAt(Integer.parseInt(tempValue)));
				} else {
					orderObj.setOwner(null);
				}
			case 1:
				tempValue = (String) value;
				if (!tempValue.isEmpty()) {
					orderObj.setOwner(shop.getClientAt(Integer.parseInt(tempValue)));
				} else {
					orderObj.setOwner(null);
				}
				fireTableCellUpdated(rowIndex,1);
				fireTableCellUpdated(rowIndex,2);
				break;
			}

			fireTableCellUpdated(rowIndex, columnIndex);

		} catch (Exception e) {
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
		Order orderObj = data.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return orderObj.getID();
		case 1:
			if (orderObj.getOwner() != null) {
				return (orderObj.getOwner().getID());
			}
		case 2:
			if (orderObj.getOwner() != null) {
				return (orderObj.getOwner().getName() + " " + orderObj
						.getOwner().getSurname());
			}
			return "";
                case 3:
                        if (!orderObj.getItemsList().isEmpty())
                        {
                            String items = "";
                            for (int i = 0; i < orderObj.getItemsList().size(); i++)
                                items += orderObj.getItemsList().get(i).getName() + ",";
                            return items;
                        }
                case 4: 
                        if (orderObj != null)
                        {
                                return orderObj.getPrice();
                        }
		default:
			return "";
		}
	}
}