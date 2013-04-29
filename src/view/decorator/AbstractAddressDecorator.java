package view.decorator;

import model.IAbstractAddress;
import model.spring.AbstractAddress;

abstract public class AbstractAddressDecorator extends AbstractAddress {
	
	private static final long serialVersionUID = -3472763825157173472L;
	protected IAbstractAddress decorated;
	
	public AbstractAddressDecorator(IAbstractAddress decorated) {
		this.decorated = decorated;
	}
	
	public String toString() {
		return this.decorated.toString();
	}
	
	public IAbstractAddress getAddress() {
		return this.decorated;
	}

}
