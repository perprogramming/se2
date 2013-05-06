package model.hibernatespring;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;

import model.IAbstractAddress;
import model.IAddressListObserver;
import model.IAddressList;

@SuppressWarnings("serial")
public class AddressList extends LinkedList<IAbstractAddress> implements IAddressList {

	private transient ArrayList<IAddressListObserver> observers = new ArrayList<IAddressListObserver>();
	private transient AddressDAO addressDao;
	
	@Override
	public void addObserver(IAddressListObserver observer) {
		observers.add(observer);
	}
	
	public void setAddressDao(AddressDAO addressDao) {
		this.addressDao = addressDao;
	}

	@Override
	public boolean add(IAbstractAddress address) {
		boolean result = true;
		if (!contains(address)) {
			result = super.add(address);
		}
		notifyObservers();
		return result;
	}
	
	@Override
	public boolean remove(Object o) {
		boolean result = super.remove(o);
		notifyObservers();
		return result;
	}
	
	@Override
	public void save() {
		Iterator<IAbstractAddress> iterator = this.iterator();
		while (iterator.hasNext()) {
			IAbstractAddress address = iterator.next();
			this.addressDao.saveAddress(address);
			address.setDirty(false);
		}
		notifyObservers();
	}
	
	@Override
	public void read() {
		this.clear();
		for (IAbstractAddress address : this.addressDao.listAddresses()) {
			address.setDirty(false);
			this.add(address);
		}
	}
	
	protected void notifyObservers() {
		for (IAddressListObserver observer : observers) {
			observer.onListChanged(this);
		}
	}
	
}
