package model;

import java.io.Serializable;

public abstract class AbstractAddress implements Serializable {

	private static final long serialVersionUID = -7865459721335090510L;
	private String name;
	private String emailaddress;

	public AbstractAddress() {
		init();
	}

	protected void init() {
		this.name = "";
		this.emailaddress = "";
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

	public String toString() {
		return name + " " + emailaddress;
	}

}