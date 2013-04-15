package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import controller.AddAddressCommand;
import controller.CommandHistory;
import controller.DeleteAddressCommand;
import controller.UpdateAddressCommand;

import model.AbstractAddress;
import model.EmailonlyAddress;
import model.AddressList;
import model.AddressListObserver;
import model.PostalAddress;

@SuppressWarnings("serial")
public class AddressListView extends JFrame implements AddressListObserver {

	private CommandHistory commandHistory;
	private AddressList addressList;
	private AbstractAddress selectedAddress;
	private DefaultListModel listModel;
	private JList list;
	private JButton deleteButton;

	public AddressListView(AddressList addressList) {
		this.commandHistory = new CommandHistory();
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
				AddAddressCommand command = new AddAddressCommand(commandHistory, addressList, address);
				new EmailonlyAddressView(address, command);
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
				AddAddressCommand command = new AddAddressCommand(commandHistory, addressList, address);
				new PostalAddressView(address, command);
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
					DeleteAddressCommand command = new DeleteAddressCommand(commandHistory, addressList, selectedAddress);
					command.execute();
				}				
			}
		});
		deleteButton.setEnabled(false);
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		this.add(deleteButton, constraints);
		
		JButton undoButton = new JButton("Undo");
		undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				commandHistory.undo();				
			}
		});
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		this.add(undoButton, constraints);

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
		
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (selectedAddress != null) {
						UpdateAddressCommand command = new UpdateAddressCommand(commandHistory, addressList, selectedAddress);
						if (selectedAddress instanceof PostalAddress) {							
							new PostalAddressView((PostalAddress) selectedAddress, command);
						}
						if (selectedAddress instanceof EmailonlyAddress) {
							new EmailonlyAddressView((EmailonlyAddress) selectedAddress, command); 
						}					
					}
				}
			}
		});
		    	
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
