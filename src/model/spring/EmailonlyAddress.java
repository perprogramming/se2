package model.spring;

import model.IEmailonlyAddress;

@SuppressWarnings("serial")
public class EmailonlyAddress extends AbstractAddress implements IEmailonlyAddress {
	
	public IEmailonlyAddress clone() throws CloneNotSupportedException {
		return (EmailonlyAddress) super.clone();
	}
	
}
