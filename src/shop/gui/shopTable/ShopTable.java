/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.gui.shopTable;

import javax.swing.JTable;
import javax.swing.table.TableModel;
import shop.exceptions.ShopClassHandlesException;
import shop.gui.ShopPanel;

/**
 *
 * @author Justas
 */
@SuppressWarnings("serial")
public class ShopTable extends JTable {


	private TableModel tableModel;

	public <T extends TableModel & Fireable> ShopTable(final T tableModel,
            int widths_layout) throws ShopClassHandlesException {
                super(tableModel);
                this.tableModel = tableModel;
		switch (widths_layout) {
                    case ShopPanel.CLASS_ITEM:
			initialiazeItemColumnWith();
			break;
                    case ShopPanel.CLASS_CLIENT:
			initialiazeClientColumnWith();
			break;

		//case DBPanel.CLASS_UNEMPLOYED:
		//	initialiazeUnemployedColumnWith();
		//	break;
		}

	}

	public void fireTableRowsInserted() {
		((Fireable) tableModel).fireTableRowsInserted();
	}

	public void fireTableRowsDeleted() {
		((Fireable) tableModel).fireTableRowsDeleted();
	}

	public void fireTableDataChanged() {
		((Fireable) tableModel).fireTableDataChanged();
	}

	public void fireTableRowsUpdated() {
		((Fireable) tableModel).fireTableRowsUpdated();
	}

	public void fireTableCellUpdated(int row, int column) {
		((Fireable) tableModel).fireTableCellUpdated(row, column);
	}

	public void fireTableStructureChanged() {
		((Fireable) tableModel).fireTableStructureChanged();
	}

	private void initialiazeItemColumnWith() throws ShopClassHandlesException {
		if(this.getColumnCount() != 3){
			throw new ShopClassHandlesException("Wrong number of columns in table model\ncheck classes handling");
		}
                this.getColumnModel().getColumn(0).setPreferredWidth(20);
		this.getColumnModel().getColumn(1).setPreferredWidth(200);
		this.getColumnModel().getColumn(2).setPreferredWidth(20);
	}

	private void initialiazeClientColumnWith() throws ShopClassHandlesException {
		if(this.getColumnCount() != 4){
			throw new ShopClassHandlesException("Wrong number of columns in table model\ncheck classes handling");
		}
		this.getColumnModel().getColumn(0).setPreferredWidth(20);
		this.getColumnModel().getColumn(1).setPreferredWidth(200);
		this.getColumnModel().getColumn(2).setPreferredWidth(200);
                this.getColumnModel().getColumn(3).setPreferredWidth(200);
	}
	
      
}