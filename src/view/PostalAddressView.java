package view;

import javax.swing.JLabel;
import javax.swing.JTextField;

import model.IPostalAddress;

import controller.AbstractCommand;

@SuppressWarnings("serial")
public class PostalAddressView extends AbstractAddressView {

	public PostalAddressView(IPostalAddress address, AbstractCommand command) {
		super(address, command);
	}

	private JTextField streetTextField;
	private JTextField zipTextField;
	private JTextField townTextField;
	
	@Override
	protected void init() {
		super.init();
		
		JLabel streetLabel = new JLabel("Stra§e, Hsnr");
		streetTextField = new JTextField();
	
		JLabel zipLabel = new JLabel("PLZ");
		zipTextField = new JTextField();
		
		JLabel townLabel = new JLabel("Ort");
		townTextField = new JTextField();
	
		upperPanel.add(streetLabel);
		upperPanel.add(streetTextField);
		upperPanel.add(zipLabel);
		upperPanel.add(zipTextField);
		upperPanel.add(townLabel);
		upperPanel.add(townTextField);
	}

	@Override
	protected void populateFields() {
		super.populateFields();
		IPostalAddress postalAddress = (IPostalAddress) this.address;
		streetTextField.setText(postalAddress.getStreet());
		zipTextField.setText(postalAddress.getZip());
		townTextField.setText(postalAddress.getTown());
	}	
	
	@Override
	protected void retrieveFields() {
		super.retrieveFields();
		IPostalAddress postalAddress = (IPostalAddress) this.address;
		postalAddress.setStreet(streetTextField.getText());
		postalAddress.setZip(zipTextField.getText());
		postalAddress.setTown(townTextField.getText());
	}
	
}
