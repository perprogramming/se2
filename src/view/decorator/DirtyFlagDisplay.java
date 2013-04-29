package view.decorator;

import model.IAbstractAddress;

public class DirtyFlagDisplay extends AbstractAddressDecorator {

	private static final long serialVersionUID = 6750214071385822319L;
	
	public DirtyFlagDisplay(IAbstractAddress decorated) {
		super(decorated);
	}

	public String toString() {
		String display = super.toString();
		if (!this.getAddress().getDirty()) {
			return ">  ".concat(display);
		} else {
			return display;
		}
	}

}
