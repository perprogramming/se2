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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.AbstractAddress;
import model.EmailonlyAddress;
import model.AddressList;
import model.AddressListObserver;
import model.PostalAddress;

@SuppressWarnings("serial")
public class AddressListView extends JFrame implements AddressListObserver {

	private AddressList addressList;
	private AbstractAddress selectedAddress;
	private DefaultListModel listModel;
	private JList list;
	private JButton deleteButton;

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
		list = new JList(listModel);
		JScrollPane scrollpane = new JScrollPane(list);
		
		list.addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				selectedAddress = (AbstractAddress) list.getSelectedValue();
				if (selectedAddress == null) {
					deleteButton.setEnabled(false);					
				} else {
					deleteButton.setEnabled(true);
				}
			}
		});		

		this.add(scrollpane, constraints);

		JButton addButton = new JButton("Add email-only address");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EmailonlyAddress address = new EmailonlyAddress();
				new EmailonlyAddressView(address, addressList);
			}
		});

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		this.add(addButton, constraints);
		
		JButton addPostalButton = new JButton("Add postal address");
		addPostalButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PostalAddress address = new PostalAddress();
				new PostalAddressView(address, addressList);
			}
		});

		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		this.add(addPostalButton, constraints);
		
		deleteButton = new JButton("Delete address");
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (selectedAddress != null) {
					addressList.remove(selectedAddress);
				}				
			}
		});
		deleteButton.setEnabled(false);
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		this.add(deleteButton, constraints);

		JButton saveButton = new JButton("Save all");

		saveButton.addActionListener(new ActionListener() {
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
		constraints.gridwidth = 1;
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
		for (AbstractAddress address : addressList) {
			listModel.addElement(address);
		}
	}
}
