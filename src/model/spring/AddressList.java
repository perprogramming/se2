package model.spring;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;

import model.IAbstractAddress;
import model.IAddressListObserver;
import model.IAddressList;

public class AddressList extends LinkedList<IAbstractAddress> implements Serializable, IAddressList {

	private static final long serialVersionUID = -8436170099085318899L;
	private static String filename = "address_system.dat";
	private static AddressList instance = null;
	
	private transient ArrayList<IAddressListObserver> observers = new ArrayList<IAddressListObserver>();
	
	private AddressList() {
		super();
	}
	
	public static IAddressList getInstance() {
		if (instance == null) {
			instance = new AddressList();
		}
		return instance;
	}
	
	@Override
	public void addObserver(IAddressListObserver observer) {
		observers.add(observer);
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
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		Iterator<IAbstractAddress> iterator = this.iterator();
		while (iterator.hasNext()) {
			iterator.next().setDirty(false);
		}
		notifyObservers();
	}
	
	@Override
	public void read() {
		AddressList addressList = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
		    fis = new FileInputStream(filename);
		    in = new ObjectInputStream(fis);
		    addressList = (AddressList)in.readObject();
		    in.close();
		} catch(IOException ex){
		    ex.printStackTrace();
		} catch(ClassNotFoundException ex){
		    ex.printStackTrace();
		}
		instance = addressList;
		instance.observers = observers;
		
		Iterator<IAbstractAddress> iterator = instance.iterator();
		while (iterator.hasNext()) {
			iterator.next().setDirty(false);
		}
		
		instance.notifyObservers();
	}
	
	protected void notifyObservers() {
		for (IAddressListObserver observer : observers) {
			observer.onListChanged(this);
		}
	}
	
}
