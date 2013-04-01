package model;

import java.io.Serializable;

public class Address implements Serializable {

	private static final long serialVersionUID = 6780532528672421304L;

	private String name;
	private String emailaddress;

	public Address() {
		init();
	}

	private void init() {
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
