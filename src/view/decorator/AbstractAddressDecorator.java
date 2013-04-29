package view.decorator;

import model.IAbstractAddress;

abstract public class AbstractAddressDecorator implements IAbstractAddress {
	
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
	
	abstract public IAbstractAddress clone() throws CloneNotSupportedException;
	
	@Override
	public void setName(String name) {
		decorated.setName(name);
	}

	@Override
	public String getName() {
		return decorated.getName();
	}

	@Override
	public void setEmailaddress(String emailaddress) {
		decorated.setEmailaddress(emailaddress);
	}

	@Override
	public String getEmailaddress() {
		return decorated.getEmailaddress();
	}

	@Override
	public void setDirty(boolean dirty) {
		decorated.setDirty(dirty);	
	}

	@Override
	public boolean getDirty() {
		return decorated.getDirty();
	}

}
