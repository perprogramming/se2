package test.model;

import model.IAbstractAddress;
import model.spring.AbstractAddress;
import model.spring.EmailonlyAddress;
import view.decorator.DirtyFlagDisplay;
import junit.framework.TestCase;

public class ModelTest extends TestCase {
	
	protected AbstractAddress address;

	protected void setUp() throws Exception {
		super.setUp();
		
		address = new EmailonlyAddress();
		address.setName("a");
		address.setEmailaddress("b");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testWithDecorator() {
		IAbstractAddress decoratedAddress = new DirtyFlagDisplay(address);
        assertEquals("a b", decoratedAddress.toString());
        
        address.setDirty(false);
        assertEquals(">  a b", decoratedAddress.toString());        
	}
	
	public void testWithoutDecorator() {
		assertEquals("a b", address.toString());
	}

}
