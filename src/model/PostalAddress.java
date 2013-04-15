package model;

import controller.AbstractCommand;

@SuppressWarnings("serial")
public class PostalAddress extends AbstractAddress {

	private String street;
	private String number;
	private String zip;
	private String town;
	
	@Override
	protected void init() {
		super.init();
		this.street = "";
		this.number = "";
		this.zip = "";
		this.town = "";
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getZip() {
		return zip;
	}
	
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String getTown() {
		return town;
	}
	
	public void setTown(String town) {
		this.town = town;
	}	
	
	public PostalAddress clone() throws CloneNotSupportedException {
		return (PostalAddress) super.clone();
	}
	
	@Override
	public String toString() { 
		return super.toString() + " " + street + " " + number + " " + zip + " " + town;
	}

}
