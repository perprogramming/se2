package model;

public interface IAddressList extends Iterable<IAbstractAddress> {

	public abstract void addObserver(IAddressListObserver observer);

	public abstract boolean add(IAbstractAddress address);

	public abstract boolean remove(Object o);

	public abstract void save();

	public abstract void read();

}