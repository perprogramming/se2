package model.hibernatespring;

import javax.persistence.Entity;
import javax.persistence.Table;

import model.IEmailonlyAddress;

@Entity
@Table(name="EMAILADDRESSES")
public class EmailonlyAddress extends AbstractAddress implements IEmailonlyAddress {
	
	public IEmailonlyAddress clone() throws CloneNotSupportedException {
		return (EmailonlyAddress) super.clone();
	}
	
}
