package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Address;
import model.AddressList;

@SuppressWarnings("serial")
public class AddressView extends JFrame {

	private Address address;
	private AddressList addressList;

	private JTextField nameTextField;
	private JTextField emailaddressTextField;

	public AddressView(Address address, AddressList addressList) {
		this.address = address;
		this.addressList = addressList;
		init();
		populateFields();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void init() {
		this.setTitle("Address");
		this.setLayout(new BorderLayout());

		JPanel upperPanel = new JPanel();
		upperPanel.setBorder(BorderFactory.createTitledBorder("Addressinformationen"));

		upperPanel.setLayout(new GridLayout(2, 2, 5, 5));

		JLabel nameLabel = new JLabel("Name");
		nameTextField = new JTextField();

		JLabel emailaddressLabel = new JLabel("Emailadresse");
		emailaddressTextField = new JTextField();

		upperPanel.add(nameLabel);
		upperPanel.add(nameTextField);
		upperPanel.add(emailaddressLabel);
		upperPanel.add(emailaddressTextField);

		this.add(upperPanel, BorderLayout.CENTER);

		JButton speicherButton = new JButton("Hinterlegen");

		speicherButton.addActionListener(new ActionListener() {
			/**
			 * Wählen Sie für diese anonyme Klasse SpeichernButtonActionListener
			 * als Klassenamen.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				retrieveFields();

				addressList.add(address);

				Container container = (Container) e.getSource();
				while (!(container instanceof JFrame)) {
					container = container.getParent();
				}
				((JFrame) container).setVisible(false);
				((JFrame) container).dispose();
			}
		});

		this.add(speicherButton, BorderLayout.SOUTH);

		this.pack();
	}

	private void populateFields() {
		nameTextField.setText(address.getName());
		emailaddressTextField.setText(address.getEmailaddress());
	}

	private void retrieveFields() {
		address.setName(nameTextField.getText());
		address.setEmailaddress(emailaddressTextField.getText());
	}
}
