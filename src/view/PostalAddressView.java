package view;

import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.AbstractCommand;

import model.AbstractAddress;
import model.PostalAddress;

@SuppressWarnings("serial")
public class PostalAddressView extends AbstractAddressView {

	public PostalAddressView(AbstractAddress address, AbstractCommand command) {
		super(address, command);
	}

	private JTextField streetTextField;
	private JTextField numberTextField;
	private JTextField zipTextField;
	private JTextField townTextField;
	
	@Override
	protected void init() {
		super.init();
		
		JLabel streetLabel = new JLabel("Stra§e");
		streetTextField = new JTextField();
	
		JLabel numberLabel = new JLabel("Hausnummer");
		numberTextField = new JTextField();
		
		JLabel zipLabel = new JLabel("PLZ");
		zipTextField = new JTextField();
		
		JLabel townLabel = new JLabel("Ort");
		townTextField = new JTextField();
	
		upperPanel.add(streetLabel);
		upperPanel.add(streetTextField);
		upperPanel.add(numberLabel);
		upperPanel.add(numberTextField);
		upperPanel.add(zipLabel);
		upperPanel.add(zipTextField);
		upperPanel.add(townLabel);
		upperPanel.add(townTextField);
	}

	@Override
	protected void populateFields() {
		super.populateFields();
		PostalAddress postalAddress = (PostalAddress) this.address;
		streetTextField.setText(postalAddress.getStreet());
		numberTextField.setText(postalAddress.getNumber());
		zipTextField.setText(postalAddress.getZip());
		townTextField.setText(postalAddress.getTown());
	}	
	
	@Override
	protected void retrieveFields() {
		super.retrieveFields();
		PostalAddress postalAddress = (PostalAddress) this.address;
		postalAddress.setStreet(streetTextField.getText());
		postalAddress.setNumber(numberTextField.getText());
		postalAddress.setZip(zipTextField.getText());
		postalAddress.setTown(townTextField.getText());
	}
	
}
