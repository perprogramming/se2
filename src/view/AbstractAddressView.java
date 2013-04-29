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

import controller.AbstractCommand;
import model.IAbstractAddress;

public abstract class AbstractAddressView extends JFrame {

	private static final long serialVersionUID = -7851292034985707916L;
	protected IAbstractAddress address;
	protected AbstractCommand command;
	private JTextField nameTextField;
	private JTextField emailaddressTextField;
	protected JPanel upperPanel;
	
	public AbstractAddressView(IAbstractAddress address, AbstractCommand command) {
		this.address = address;
		this.command = command;
		initabstract();
		populateFields();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	protected void initabstract() {
		this.setTitle("Address");
		this.setLayout(new BorderLayout());
	
		this.upperPanel = new JPanel();
		upperPanel.setBorder(BorderFactory.createTitledBorder("Addressinformationen"));
	
		upperPanel.setLayout(new GridLayout(2, 2, 5, 5));
	
		this.init();
	
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
	
				command.execute();
	
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

	protected void init() {
		JLabel nameLabel = new JLabel("Name");
		nameTextField = new JTextField();
	
		JLabel emailaddressLabel = new JLabel("Emailadresse");
		emailaddressTextField = new JTextField();
	
		upperPanel.add(nameLabel);
		upperPanel.add(nameTextField);
		upperPanel.add(emailaddressLabel);
		upperPanel.add(emailaddressTextField);
	}

	protected void populateFields() {
		nameTextField.setText(address.getName());
		emailaddressTextField.setText(address.getEmailaddress());
	}

	protected void retrieveFields() {
		address.setName(nameTextField.getText());
		address.setEmailaddress(emailaddressTextField.getText());
	}

}