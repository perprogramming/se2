package controller;

import model.AbstractAddress;
import model.AddressList;

public class DeleteAddressCommand extends AbstractCommand {
	
	public DeleteAddressCommand(CommandHistory commandHistory, AddressList addressList, AbstractAddress address) {
		super(commandHistory, addressList, address);
	}

	protected void doExecute() {
		addressList.remove(address);
	}
	
	protected void doUndo() {
		addressList.add(address);
	}

}
