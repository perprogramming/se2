package controller;

import model.AbstractAddress;
import model.AddressList;

public class UpdateAddressCommand extends AddAddressCommand {
	
	protected AbstractAddress previousAddress;
	
	public UpdateAddressCommand(CommandHistory commandHistory, AbstractAddress address) {
		super(commandHistory, address);
		address.dirty = true;
		try {
			this.previousAddress = address.clone();
		} catch (CloneNotSupportedException e) {
		}
	}

	protected void doUndo() {
		AddressList addressList = AddressList.getInstance();
		addressList.remove(address);
		addressList.add(previousAddress);
	}

}
