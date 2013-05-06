package model.hibernatespring;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import model.IPostalAddress;

@SuppressWarnings("serial")
@Entity
@Table(name="POSTALADDRESSES")
public class PostalAddress extends AbstractAddress implements IPostalAddress {

	@Column(name="STRASSE", length=50)
	private String street;
	@Column(name="PLZ", length=50)
	private String zip;
	@Column(name="ORT", length=50)
	private String town;
	
	@Override
	protected void init() {
		super.init();
		this.street = "";
		this.zip = "";
		this.town = "";
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
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
	
	public IPostalAddress clone() throws CloneNotSupportedException {
		return (PostalAddress) super.clone();
	}
	
	@Override
	public String toString() { 
		return super.toString() + " " + street + " " + zip + " " + town;
	}

}
