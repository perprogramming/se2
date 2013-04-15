package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.ArrayList;

public class AddressList extends LinkedList<AbstractAddress> implements Serializable {

	private static final long serialVersionUID = -8436170099085318899L;
	
	private ArrayList<AddressListObserver> observers = new ArrayList<AddressListObserver>();
	
	public void addObserver(AddressListObserver observer) {
		observers.add(observer);
	}

	public boolean add(AbstractAddress address) {
		boolean result = true;
		if (!contains(address)) {
			result = super.add(address);
		}
		notifyObservers();
		return result;
	}
	
	public boolean remove(Object o) {
		boolean result = super.remove(o);
		notifyObservers();
		return result;
	}
	
	protected void notifyObservers() {
		for (AddressListObserver observer : observers) {
			observer.onListChanged(this);
		}
	}
	
}
