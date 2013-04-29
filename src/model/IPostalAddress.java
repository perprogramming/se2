package model;

public interface IPostalAddress extends IAbstractAddress {
	
	public IPostalAddress clone() throws CloneNotSupportedException;
	
	public String getStreet();	
	public void setStreet(String street);
	
	public String getNumber();
	public void setNumber(String number);
	
	public String getZip();	
	public void setZip(String zip);
	
	public String getTown();	
	public void setTown(String town);

}