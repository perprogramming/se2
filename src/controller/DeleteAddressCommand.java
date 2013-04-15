package controller;

import model.AbstractAddress;
import model.AddressList;

public class DeleteAddressCommand extends AbstractCommand {
	
	public DeleteAddressCommand(CommandHistory commandHistory, AbstractAddress address) {
		super(commandHistory, address);
	}

	protected void doExecute() {
		AddressList.getInstance().remove(address);
	}
	
	protected void doUndo() {
		AddressList.getInstance().add(address);
	}

}
