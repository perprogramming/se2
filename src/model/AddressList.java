package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.ArrayList;

public class AddressList extends LinkedList<Address> implements Serializable {
	private static final long serialVersionUID = -8436170099085318899L;
	
	private ArrayList<AddressListObserver> observers = new ArrayList<AddressListObserver>();
	
	public void addObserver(AddressListObserver observer) {
		observers.add(observer);
	}

	public boolean add(Address address) {
		boolean result = super.add(address);
		for (AddressListObserver observer : observers) {
			observer.onListChanged(this);
		}
		return result;
	}
	
}
