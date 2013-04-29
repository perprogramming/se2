package view;


import controller.AbstractCommand;
import model.spring.EmailonlyAddress;

@SuppressWarnings("serial")
public class EmailonlyAddressView extends AbstractAddressView {
	
	public EmailonlyAddressView(EmailonlyAddress address, AbstractCommand command) {
		super(address, command);
	}	
	
}
