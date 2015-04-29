/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import shop.Item;
import shop.Order;
import shop.Shop;
import shop.exceptions.ShopClassHandlesException;
import shop.gui.buttonsHandler.ShopPanelInserter;
import shop.gui.shopTable.ShopTable;
import shop.person.Client;
import shop.gui.shopTable.ItemTableModel;
import shop.gui.buttonsHandler.ShopPanelDeleter;
import shop.gui.shopTable.ClientTableModel;
import shop.gui.shopTable.OrderTableModel;

/**
 *
 * @author Justas
 */
@SuppressWarnings("serial")
public class ShopPanel<T> extends JPanel {
    private ShopTable table;
    private final int classID;
    private JFrame frame;
    private ArrayList<T> list;
    private Class<T> clazz;
    private Shop shop;

    public static final int CLASS_ITEM = 1;
    public static final int CLASS_CLIENT = 2;
    public static final int CLASS_ORDER = 3;

    @SuppressWarnings("unchecked")
    protected ShopPanel(JFrame frame, Shop shop, ArrayList<T> list, Class<T> clazz) 
            throws ShopClassHandlesException 
    {
        super();
        this.setLayout(new BorderLayout());
        classID = getClassID(clazz);
        this.frame = frame;
        this.list = list;
        this.clazz = clazz;
        this.shop = shop;
           
        switch (classID) {
        case 1:
            table = new ShopTable(new ItemTableModel(shop,(ArrayList<Item>) list), ShopPanel.CLASS_ITEM);
            break;
        case 2:
            table = new ShopTable(new ClientTableModel((ArrayList<Client>) list), ShopPanel.CLASS_CLIENT);
            break;
        case 3:
            table = new ShopTable(new OrderTableModel(shop, (ArrayList<Order>) list), ShopPanel.CLASS_ORDER);
            break;
        default: throw new ShopClassHandlesException("No such predifined class: "
                        + clazz.getName());
        
        }

        this.add(table);
        this.add(new JScrollPane(table));

        JPanel buttonsPannel = new JPanel();
        GridLayout gridLayout = new GridLayout(1, 1);
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);
        buttonsPannel.setLayout(gridLayout);
       
        String objectName = "";
        switch (classID) {
        case CLASS_ITEM:
                objectName = "prekę";
                break;
        case CLASS_CLIENT:
                objectName = "klientą";
                break;
        case CLASS_ORDER:
                objectName = "užsakymą";
                break;
        }

        JButton insertButton = new JButton("Įterpti naują " + objectName);
        JButton deleteButton = new JButton("Ištrinti esamą " + objectName);
        insertButton
                        .addActionListener(new ShopPanelButtonListener(ShopPanelButtonListener.INSERT));
        deleteButton
                        .addActionListener(new ShopPanelButtonListener(ShopPanelButtonListener.DELETE));

        buttonsPannel.add(insertButton);
        buttonsPannel.add(deleteButton);
        this.add(buttonsPannel, BorderLayout.SOUTH);
    }

    public void updateTableRows() {
            this.table.fireTableRowsInserted();
    }

    public static int getClassID(Class<?> clazz) throws ShopClassHandlesException {
            if (clazz.getName().indexOf("Item") > 0) {
                    return ShopPanel.CLASS_ITEM;
            }
            if (clazz.getName().indexOf("Client") > 0) {
                    return ShopPanel.CLASS_CLIENT;
            }
            if (clazz.getName().indexOf("Order") > 0) {
                    return ShopPanel.CLASS_ORDER;
            }
            throw new ShopClassHandlesException("No such predifined class: "
                            + clazz.getName());
    }

    private class ShopPanelButtonListener implements ActionListener{
        public static final int INSERT = 1;
        public static final int DELETE = 2;
        private int mode;


        public ShopPanelButtonListener(int mode){
            this.mode = mode;
        }

        @SuppressWarnings({ "unchecked", "rawtypes" })
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                switch (mode) {
                case INSERT:
                    new ShopPanelInserter(frame, shop, table, list, clazz);
                        table.fireTableRowsInserted();
                        break;
                case DELETE:
                    new ShopPanelDeleter(frame, shop, table, list, clazz);
                    table.fireTableRowsDeleted();
                    break;
                default:
                    MainWindow
                        .errorMessage("Wrong ShopPanel button listener");
                    System.exit(1);
                    break;
                }
            } catch (ShopClassHandlesException e) {
                MainWindow.errorMessage(e.getMessage());
            }
        }	
    }
}