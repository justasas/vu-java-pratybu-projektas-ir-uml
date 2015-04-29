/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import shop.Shop;
import shop.Item;
import shop.Order;
import shop.exceptions.AddException;
import shop.exceptions.ShopClassHandlesException;
import shop.exceptions.ShopIOException;
import shop.person.Client;
import shop.person.VIPClient;

/**
 *
 * @author Justas
 */


public class MainWindow {
    private static JFrame jFrame = new JFrame();
    Shop shop;
    JTabbedPane tabbedPane;
    ShopPanel<Item> itemPanel;
    ShopPanel<Order> orderPanel;
    ShopPanel<Client> clientPanel;
    ShopPanel<VIPClient> vipclientPanel;

    public MainWindow(Shop shop) {
            this.shop = shop;
    }

    public static void errorMessage(String msg) {
            JOptionPane.showMessageDialog(jFrame, msg);
    }

    public void createAndShowGui() {
        jFrame.setSize(800, 600);
        addWindowListener();
        //jFrame.setLocale(l);
        jFrame.setTitle("Muzikos prekiu parduotuve");
        Dimension dim;
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setLocation(dim.width / 2 - jFrame.getSize().width / 2,
                        dim.height / 2 - jFrame.getSize().height / 2);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabbedPane = new JTabbedPane();
        jFrame.add(tabbedPane);
 
        try {
            itemPanel = new ShopPanel<Item>(jFrame, shop,
                        shop.getItems(), Item.class);           
            clientPanel = new ShopPanel<Client>(jFrame, shop,
                        shop.getClients(), Client.class);
            orderPanel = new ShopPanel<Order>(jFrame,
                        shop, shop.getOrders(), Order.class);

        } catch (ShopClassHandlesException e) {
            String message = e.getMessage();
            if (e.getMethodName() != null) {
                    message += "\nIn: " + e.getMethodName();
            }
            MainWindow.errorMessage(message);
        }

        tabbedPane.add("Prekes", itemPanel);
        tabbedPane.add("Klientai", clientPanel);
        tabbedPane.add("Užsakymai", orderPanel);
      
        jFrame.setVisible(true);
       
    }

    public void addWindowListener() {
        WindowListener windowListener = new WindowListener() {

            @Override
            public void windowActivated(WindowEvent arg0) {

            }

            @Override
            public void windowClosed(WindowEvent arg0) {
                    // TODO Auto-generated method stub

            }

            @Override
            public void windowClosing(WindowEvent arg0) {
                try {
                    shop.save();
                } catch (ShopIOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void windowDeactivated(WindowEvent arg0) {
                    // TODO Auto-generated method stub
            }

            @Override
            public void windowDeiconified(WindowEvent arg0) {
            }

            @Override
            public void windowIconified(WindowEvent arg0) {
            }

            @Override
            public void windowOpened(WindowEvent arg0) {
                try {
                    shop.load();
                } catch (AddException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                itemPanel.updateTableRows();
                clientPanel.updateTableRows();
                orderPanel.updateTableRows();
            }
        };
        jFrame.addWindowListener(windowListener);
    }
}