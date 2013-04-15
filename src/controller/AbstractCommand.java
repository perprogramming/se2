package controller;

import model.AbstractAddress;

abstract public class AbstractCommand {
	
	protected CommandHistory history;
	protected AbstractAddress address;
	
	public AbstractCommand(CommandHistory history, AbstractAddress address) {
		this.history = history;
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
