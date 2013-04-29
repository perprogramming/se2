import model.Factory;
import view.AddressListView;

public class Starter {
	public static void main(String[] args) {
		Factory factory = new Factory();
		new AddressListView(
			factory.createAddressList(),
			factory
		);
	}
}
