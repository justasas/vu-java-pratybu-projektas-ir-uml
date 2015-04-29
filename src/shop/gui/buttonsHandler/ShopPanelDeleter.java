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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import shop.Shop;
import shop.exceptions.ShopClassHandlesException;
import shop.gui.MainWindow;
import shop.gui.ShopPanel;
import shop.gui.shopTable.ShopTable;

/**
 *
 * @author Justas
 */
public class ShopPanelDeleter<T> {

	private JTextField textField = null;
	private ArrayList<T> list;
	private int intancesNumber = -1;
	private static boolean instancesPositions[] = new boolean[10];
	private JDialog dialog;
	private Shop shop;
	private final int classID;
	private JFrame frame;
	private ShopTable shopTable;

	public ShopPanelDeleter(JFrame frame, Shop shop, ShopTable shopTable,
			ArrayList<T> list, Class<T> clazz) throws ShopClassHandlesException {
		try {
			classID = ShopPanel.getClassID(clazz);
			this.list = list;
			this.frame = frame;
			this.shop = shop;
			this.shopTable = shopTable;
			prepareAndShowDialog();
		} catch (ShopClassHandlesException e) {
			throw new ShopClassHandlesException(e.getMessage(),
					"ShopPanelDeleteListener<" + clazz.getName() + ">");
		}
	}

	class InputHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {

			try {
				String value = textField.getText();
				int ID = Integer.parseInt(value);
                                ID--;
				Object obj;
				boolean deleted = false;
				switch (classID) {
				case ShopPanel.CLASS_ITEM:
					obj = shop.getItemAt(ID);
					deleted = list.remove(obj);
					break;
				case ShopPanel.CLASS_CLIENT:
					obj = shop.getClientAt(ID);
					deleted = list.remove(obj);
					break;
				case ShopPanel.CLASS_ORDER:
					obj = shop.getOrderAt(ID);
					deleted = list.remove(obj);
					break;
				}
				if (deleted) {
					shopTable.fireTableRowsDeleted();
					instancesPositions[intancesNumber] = false;
					dialog.setVisible(false);
				} else {
					MainWindow.errorMessage("cannot delete specified object");
				}
			} catch (NumberFormatException e) {
				MainWindow.errorMessage(e.getMessage());
			}
		}

	};

	private void prepareAndShowDialog() {

		InputHandler iHandler = new InputHandler();
		countInstances();

		JPanel panel = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		JButton button = new JButton("Ištrinti");
		button.addActionListener(iHandler);
		buttonPanel.add(button);

		//Create and populate the panel.
		JPanel form = new JPanel(new SpringLayout());

		JLabel l = new JLabel("Įveskite ID", JLabel.TRAILING);
		form.add(l);
		textField = new JTextField(20);
		textField.addActionListener(iHandler);
		l.setLabelFor(textField);
		form.add(textField);

		//Lay out the panel.
		SpringUtilities.makeCompactGrid(form, 1, 2, //rows, cols
				5, 5, //initX, initY
				5, 5); //xPad, yPad

		//Create and set up the window.
		panel.add(form, BorderLayout.NORTH);
		panel.add(buttonPanel);

		// set Dialog Name
		String dialogName = "Ištrint ";
		switch (classID) {
		case ShopPanel.CLASS_ITEM:
			dialogName += "prekę";
			break;
		case ShopPanel.CLASS_CLIENT:
			dialogName += "klientą";
			break;
		case ShopPanel.CLASS_ORDER:
			dialogName += "darbdavį";
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