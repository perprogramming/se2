package view.decorator;

import model.AbstractAddress;

abstract public class AbstractAddressDecorator extends AbstractAddress {
	
	private static final long serialVersionUID = -3472763825157173472L;
	protected AbstractAddress decorated;
	
	public AbstractAddressDecorator(AbstractAddress decorated) {
		this.decorated = decorated;
	}
	
	public String toString() {
		return this.decorated.toString();
	}
	
	public AbstractAddress getAddress() {
		return this.decorated;
	}

}
