package controller;

import model.AbstractAddress;
import model.AddressList;

public class AddAddressCommand extends AbstractCommand {
	
	public AddAddressCommand(CommandHistory commandHistory, AddressList addressList, AbstractAddress address) {
		super(commandHistory, addressList, address);
	}

	protected void doExecute() {
		addressList.add(address);
	}
	
	protected void doUndo() {
		addressList.remove(address);
	}

}
