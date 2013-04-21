package view.decorator;

import model.AbstractAddress;

public class DirtyFlagDisplay extends AbstractAddressDecorator {

	private static final long serialVersionUID = 6750214071385822319L;
	
	public DirtyFlagDisplay(AbstractAddress decorated) {
		super(decorated);
	}

	public String toString() {
		String display = super.toString();
		if (!this.getAddress().dirty) {
			return ">  ".concat(display);
		} else {
			return display;
		}
	}

}
