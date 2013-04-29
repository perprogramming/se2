package view.decorator;

import model.IAbstractAddress;

public class DirtyFlagDisplay extends AbstractAddressDecorator {

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

	@Override
	public DirtyFlagDisplay clone() throws CloneNotSupportedException {
		return new DirtyFlagDisplay(decorated.clone());
	}

}
