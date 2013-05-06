package model.hibernatespring;

import java.util.ArrayList;
import java.util.List;

import model.IAbstractAddress;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AddressDAO {

	private Session session;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.session = sessionFactory.openSession();
	}
	
	public void saveAddress(IAbstractAddress address) {
		this.session.saveOrUpdate(address);
	}
	
	@SuppressWarnings("unchecked")
	public List<IAbstractAddress> listAddresses() {
		List<IAbstractAddress> addresses = new ArrayList<IAbstractAddress>();
		addresses.addAll(this.session.createQuery("from EmailonlyAddress").list());
		addresses.addAll(this.session.createQuery("from PostalAddress").list());	
		return addresses;
	}
	
}
