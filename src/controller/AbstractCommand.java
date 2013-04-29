package controller;

import model.IAbstractAddress;
import model.IAddressList;

abstract public class AbstractCommand {
	
	protected CommandHistory history;
	protected IAddressList addressList;
	protected IAbstractAddress address;
	
	public AbstractCommand(CommandHistory history, IAddressList addressList, IAbstractAddress address) {
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
