package controller;

import model.IAbstractAddress;
import model.IAddressList;

public class UpdateAddressCommand extends AddAddressCommand {
	
	protected IAbstractAddress previousAddress;
	
	public UpdateAddressCommand(CommandHistory commandHistory, IAddressList addressList, IAbstractAddress address) {
		super(commandHistory, addressList, address);
		address.setDirty(true);
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
