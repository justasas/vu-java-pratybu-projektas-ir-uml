package shop.gui.shopTable;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

import shop.exceptions.SetterException;
import shop.exceptions.ShopTableException;
import shop.gui.MainWindow;
import shop.person.Client;
import shop.person.VIPClient;

/**
 *
 * @author Justas
 */
@SuppressWarnings("serial")
public class ClientTableModel extends AbstractTableModel implements Fireable {

	private ArrayList<Client> data = null;
	private String[] columnNames = null;
	private final int COLUMNS_COUNT = 4;

	public ClientTableModel(ArrayList<Client> dataList) {
            this.data = dataList;
            initialiazeColumnNames();
	}

	private void initialiazeColumnNames() {
            columnNames = new String[COLUMNS_COUNT];
            columnNames[0] = "ID";
            columnNames[1] = "Vardas";
            columnNames[2] = "Pavardė";
            columnNames[3] = "Nuolaida";
	}

	public void setColumnNames(String[] columnNames) throws ShopTableException {
		if (columnNames.length != COLUMNS_COUNT) {
			throw new ShopTableException("Wrong column names count");
		}
		this.columnNames = columnNames;
	}

	public Class<?> getColumnClass(int c) {
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
		if (col == 0) {
			return false;
		}
		return true;
	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		try {
                    Client ClientObj = data.get(rowIndex);
                    if (value != null) {
                            switch (columnIndex) {
                            case 1:
                                    ClientObj.setName((String) value);
                                    break;
                            case 2:
                                    ClientObj.setSurname((String) value);
                                    break;
                            case 3: VIPClient cp = (VIPClient) ClientObj;
                                    cp.setDiscount((int) value);
                            }
                            fireTableCellUpdated(rowIndex, columnIndex);
                    }
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
		Client clientObj = data.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return clientObj.getID();
		case 1:
			return clientObj.getName();
		case 2:
			return clientObj.getSurname();
                case 3:
                        VIPClient cp = (VIPClient) clientObj;
                        return cp.getDiscount();
		default:
			return "";
		}
	}
}