package controller;

import model.AbstractAddress;
import model.AddressList;

public class UpdateAddressCommand extends AddAddressCommand {
	
	protected AbstractAddress previousAddress;
	
	public UpdateAddressCommand(CommandHistory commandHistory, AddressList addressList, AbstractAddress address) {
		super(commandHistory, addressList, address);
		try {
			this.previousAddress = address.clone();
		} catch (CloneNotSupportedException e) {
		}
	}

	protected void doUndo() {
		addressList.remove(address);
		addressList.add(previousAddress);
	}

}
