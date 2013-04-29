package controller;

import model.IAbstractAddress;
import model.IAddressList;

public class AddAddressCommand extends AbstractCommand {
	
	public AddAddressCommand(CommandHistory commandHistory, IAddressList addressList, IAbstractAddress address) {
		super(commandHistory, addressList, address);
	}

	protected void doExecute() {
		addressList.add(address);
	}
	
	protected void doUndo() {
		addressList.remove(address);
	}

}
