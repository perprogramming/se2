package model.hibernatespring;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import model.IAbstractAddress;

@MappedSuperclass
public abstract class AbstractAddress implements Serializable, Cloneable, IAbstractAddress {

	@Id @Column(name="ID")
	private long id;
	private static final long serialVersionUID = -7865459721335090510L;
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
		return name + " " + emailaddress;
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