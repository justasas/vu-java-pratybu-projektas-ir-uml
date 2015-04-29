/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shop.gui.buttonsHandler;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import shop.Item;
import shop.Shop;
import shop.exceptions.AddException;
import shop.exceptions.SetterException;
import shop.exceptions.ShopClassHandlesException;
import shop.gui.MainWindow;
import shop.gui.ShopPanel;
import shop.gui.shopTable.ShopTable;

/**
 *
 * @author Justas
 */
public class ShopPanelInserter<T> {

	private JTextField[] textFields = null;
	private int columnCount = 0;
	private int intancesNumber = -1;
	private static boolean instancesPositions[] = new boolean[10];
	private JDialog dialog;
	private Shop shop;
	private final int classID;
	private Creatable<T> creator;
	private JFrame frame;
	private ShopTable shopTable;

	@SuppressWarnings("unchecked")
	public ShopPanelInserter(JFrame frame, Shop shop, ShopTable shopTable,
			ArrayList<T> list, Class<T> clazz) throws ShopClassHandlesException {
		try {
			classID = ShopPanel.getClassID(clazz);
			this.frame = frame;
			this.shop = shop;
			this.shopTable = shopTable; 
			creator = getCreator();
			prepareAndShowDialog(creator.getColumnNames());
		} catch (ShopClassHandlesException e) {
			throw new ShopClassHandlesException(e.getMessage(),
					"ShopPanelInsertListener<" + clazz.getName() + ">");
		}
	}

	@SuppressWarnings("rawtypes")
	private Creatable getCreator() throws ShopClassHandlesException {
		switch (classID) {
		case ShopPanel.CLASS_ITEM:
                    return new ShopCreateItem();
		case ShopPanel.CLASS_CLIENT:
                    return new ShopCreateClient();
		case ShopPanel.CLASS_ORDER:
			return new ShopCreateOrder();
		}
		throw new ShopClassHandlesException(
				"Cannot create new Creatable instance");

	}

	private void prepareAndShowDialog(String[] columnNames) {
		columnCount = columnNames.length;
		textFields = new JTextField[columnCount];
		InputHandler iHandler = new InputHandler();
		countInstances();

		JPanel panel = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		JButton button = new JButton("Įterpti");
		button.addActionListener(iHandler);
		buttonPanel.add(button);

		//Create and populate the panel.
		JPanel form = new JPanel(new SpringLayout());
		for (int i = 0; i < columnCount; i++) {
			JLabel l = new JLabel(columnNames[i], JLabel.TRAILING);
			form.add(l);
			textFields[i] = new JTextField(20);
			textFields[i].addActionListener(iHandler);
			l.setLabelFor(textFields[i]);
			form.add(textFields[i]);
		}

		//Lay out the panel.
		SpringUtilities.makeCompactGrid(form, columnCount, 2, //rows, cols
				5, 5, //initX, initY
				5, 5); //xPad, yPad

		//Create and set up the window.
		panel.add(form, BorderLayout.NORTH);
		panel.add(buttonPanel);

		// set Dialog Name
		String dialogName = "Įterpti ";
		switch (classID) {
		case ShopPanel.CLASS_ITEM:
			dialogName += "prekę";
			break;
		case ShopPanel.CLASS_CLIENT:
			dialogName += "klientą";
			break;
		}
		dialog = new JDialog(frame, dialogName);

		//Set up the content pane.
		form.setOpaque(true); //content panes must be opaque
		dialog.setContentPane(panel);

		//Display the window.
		dialog.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		dialog.setLocation(dim.width / 2 - dialog.getSize().width / 2 + 20
				* intancesNumber, dim.height / 2 - dialog.getSize().height / 2
				+ 20 * intancesNumber);
		this.addWindowListener();
		dialog.setVisible(true);
		dialog.setResizable(false);
		dialog.setFocusable(true);
	}

	class InputHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String[] values = new String[columnCount];
			for (int i = 0; i < textFields.length; i++) {
				values[i] = textFields[i].getText();
			}
			try {
				if(shop.add( (T) creator.getInstance(shop, values))){
					instancesPositions[intancesNumber] = false;
					dialog.setVisible(false);
					shopTable.fireTableRowsInserted();
				}
			} catch (SetterException e) {
				MainWindow.errorMessage(e.getMessage());
				return;
			} catch (AddException ex) {
                        Logger.getLogger(ShopPanelInserter.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}

	};

	private void countInstances() {
		for (int i = 0; i < instancesPositions.length; i++) {
			if (!instancesPositions[i]) {
				intancesNumber = i;
				instancesPositions[i] = true;
				break;
			}
		}
		if (intancesNumber == -1) {
			intancesNumber = 0;
			for (int i = 1; i < instancesPositions.length; i++) {
				instancesPositions[i] = false;
			}
		}
	}

	public void addWindowListener() {
            WindowListener windowListener = new WindowListener() {

                @Override
                public void windowActivated(WindowEvent arg0) {

                }

                @Override
                public void windowClosed(WindowEvent arg0) {
                }

                @Override
                public void windowClosing(WindowEvent arg0) {
                        instancesPositions[intancesNumber] = false;
                }

                @Override
                public void windowDeactivated(WindowEvent arg0) {

                }

                @Override
                public void windowDeiconified(WindowEvent arg0) {
                }

                @Override
                public void windowIconified(WindowEvent arg0) {
                }

                @Override
                public void windowOpened(WindowEvent arg0) {

                }

            };
            dialog.addWindowListener(windowListener);
	}
}