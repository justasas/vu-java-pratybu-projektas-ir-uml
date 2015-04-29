/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.gui.shopTable;

/**
 *
 * @author Justas
 */
public interface Fireable {
    public void fireTableRowsInserted(); 
    public void fireTableRowsDeleted();
    public void fireTableDataChanged();
    public void fireTableRowsUpdated();
    public void fireTableCellUpdated(int row, int column);
    public void fireTableStructureChanged();
}