package controller;

import model.IAbstractAddress;
import model.IAddressList;

public class DeleteAddressCommand extends AbstractCommand {
	
	public DeleteAddressCommand(CommandHistory commandHistory, IAddressList addressList, IAbstractAddress address) {
		super(commandHistory, addressList, address);
	}

	protected void doExecute() {
		addressList.remove(address);
	}
	
	protected void doUndo() {
		addressList.add(address);
	}

}
