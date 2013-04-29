package view;


import controller.AbstractCommand;
import model.IEmailonlyAddress;

@SuppressWarnings("serial")
public class EmailonlyAddressView extends AbstractAddressView {
	
	public EmailonlyAddressView(IEmailonlyAddress address, AbstractCommand command) {
		super(address, command);
	}	
	
}
