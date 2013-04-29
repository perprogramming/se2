package controller;

import model.IAbstractAddress;
import model.spring.AddressList;

public class AddAddressCommand extends AbstractCommand {
	
	public AddAddressCommand(CommandHistory commandHistory, IAbstractAddress address) {
		super(commandHistory, address);
	}

	protected void doExecute() {
		AddressList.getInstance().add(address);
	}
	
	protected void doUndo() {
		AddressList.getInstance().remove(address);
	}

}
