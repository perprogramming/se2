package controller;

import model.AbstractAddress;
import model.AddressList;

public class AddAddressCommand extends AbstractCommand {
	
	public AddAddressCommand(CommandHistory commandHistory, AbstractAddress address) {
		super(commandHistory, address);
	}

	protected void doExecute() {
		AddressList.getInstance().add(address);
	}
	
	protected void doUndo() {
		AddressList.getInstance().remove(address);
	}

}
