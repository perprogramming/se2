package model.hibernatespring;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import model.IAbstractAddress;

@MappedSuperclass
public abstract class AbstractAddress implements Cloneable, IAbstractAddress {

	@Id @GeneratedValue @Column(name="ID")
	private long id;
	@Column(name="NAME", length=50)
	private String name;
	@Column(name="EMAIL", length=50)
	private String emailaddress;
	private transient boolean dirty;

	public AbstractAddress() {
		init();
	}

	protected void init() {
		this.name = "";
		this.emailaddress = "";
		this.dirty = true;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	@Override
	public String toString() {
		return name.trim() + " " + emailaddress.trim();
	}
	
	@Override
	public IAbstractAddress clone() throws CloneNotSupportedException {
		return (AbstractAddress) super.clone();
	}
	
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	
	public boolean getDirty() {
		return this.dirty;
	}

}