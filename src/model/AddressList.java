package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.ArrayList;

public class AddressList extends LinkedList<AbstractAddress> implements Serializable {

	private static final long serialVersionUID = -8436170099085318899L;
	private static String filename = "address_system.dat";
	private static AddressList instance = null;
	
	private transient ArrayList<AddressListObserver> observers = new ArrayList<AddressListObserver>();
	
	private AddressList() {
		super();
	}
	
	public static AddressList getInstance() {
      if (instance == null) {
         instance = new AddressList();
      }
      return instance;
   }
	
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
	}
	
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
		instance.notifyObservers();
	}
	
	protected void notifyObservers() {
		for (AddressListObserver observer : observers) {
			observer.onListChanged(this);
		}
	}
	
}
