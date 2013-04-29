package controller;

import model.IAbstractAddress;
import model.IAddressList;
import model.spring.AddressList;

public class UpdateAddressCommand extends AddAddressCommand {
	
	protected IAbstractAddress previousAddress;
	
	public UpdateAddressCommand(CommandHistory commandHistory, IAbstractAddress address) {
		super(commandHistory, address);
		address.setDirty(true);
		try {
			this.previousAddress = address.clone();
		} catch (CloneNotSupportedException e) {
		}
	}

	protected void doUndo() {
		IAddressList addressList = AddressList.getInstance();
		addressList.remove(address);
		addressList.add(previousAddress);
	}

}
