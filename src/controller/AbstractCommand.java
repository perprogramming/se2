package controller;

import model.AbstractAddress;
import model.AddressList;

abstract public class AbstractCommand {
	
	protected CommandHistory history;
	protected AddressList addressList;
	protected AbstractAddress address;
	
	public AbstractCommand(CommandHistory history, AddressList addressList, AbstractAddress address) {
		this.history = history;
		this.addressList = addressList;
		this.address = address;
	}
	
	public void execute() {
		this.history.add(this);
		doExecute();
	}
	
	public void undo() {
		doUndo();
		this.history.remove(this);
	}
	
	abstract protected void doExecute();
	abstract protected void doUndo();

}
