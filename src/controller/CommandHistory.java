package controller;

import java.util.LinkedList;

public class CommandHistory extends LinkedList<AbstractCommand>{

	private static final long serialVersionUID = 8700440775009939104L;
	
	public void undo() {
		if (size() > 0) {
			this.getLast().undo();
		}
	}	

}
