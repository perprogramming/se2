package model;

public interface IAbstractAddress {

	public abstract String toString();

	public abstract IAbstractAddress clone() throws CloneNotSupportedException;
	
	public void setName(String name);
	public String getName();
	
	public void setEmailaddress(String emailaddress);
	public String getEmailaddress();
	
	public void setDirty(boolean dirty);
	public boolean getDirty();

}