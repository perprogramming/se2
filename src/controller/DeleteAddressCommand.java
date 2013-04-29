package controller;

import model.IAbstractAddress;
import model.spring.AddressList;

public class DeleteAddressCommand extends AbstractCommand {
	
	public DeleteAddressCommand(CommandHistory commandHistory, IAbstractAddress address) {
		super(commandHistory, address);
	}

	protected void doExecute() {
		AddressList.getInstance().remove(address);
	}
	
	protected void doUndo() {
		AddressList.getInstance().add(address);
	}

}
