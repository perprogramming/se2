import model.AddressList;
import view.AddressListView;

public class Starter {
	public static void main(String[] args) {
		AddressList addressList = new AddressList();
		new AddressListView(addressList);
	}
}
