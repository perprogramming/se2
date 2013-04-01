package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import model.Address;
import model.AddressList;
import model.AddressListObserver;

@SuppressWarnings("serial")
public class AddressListView extends JFrame implements AddressListObserver {

	private AddressList addressList;
	private DefaultListModel listModel;

	public AddressListView(AddressList addressList) {
		this.addressList = addressList;
		addressList.addObserver(this);
		init();
		populateFields();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void init() {
		this.setTitle("Address-List");

		this.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.weighty = 0.9;

		listModel = new DefaultListModel();
		JList list = new JList(listModel);
		JScrollPane scrollpane = new JScrollPane(list);

		this.add(scrollpane, constraints);

		JButton addButton = new JButton("Add address");
		addButton.addActionListener(new ActionListener() {
			/**
			 * Wählen Sie für diese anonyme Klasse AddButtonActionListener als
			 * Klassenamen.
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Address address = new Address();
				new AddressView(address, addressList);
			}
		});

		constraints.weighty = 0.1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		this.add(addButton, constraints);

		JButton saveButton = new JButton("Save all");

		saveButton.addActionListener(new ActionListener() {
			/**
			 * Wählen Sie für diese anonyme Klasse SaveButtonActionListener als
			 * Klassenamen.
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileOutputStream fos = null;
				ObjectOutputStream out = null;
				try {
					fos = new FileOutputStream((new Date().getTime() + ".ser"));
					out = new ObjectOutputStream(fos);
					out.writeObject(addressList);
					out.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});

		constraints.gridx = 1;
		constraints.gridy = 1;
		this.add(saveButton, constraints);

		this.pack();
	}

	private void populateFields() {
		refreshAddressList();
	}
	
	public void onListChanged(AddressList addressList) {
		if (addressList == this.addressList) {
			refreshAddressList();
		}
	}

	private void refreshAddressList() {
		listModel.removeAllElements();
		for (Address address : addressList) {
			listModel.addElement(address.toString());
		}
	}
}
