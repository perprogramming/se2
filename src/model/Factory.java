package model;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Factory {

	ApplicationContext context;
	
	public Factory() {
		context = new FileSystemXmlApplicationContext("spring.xml", "springhibernate.xml");
	}
	
	public IAddressList createAddressList() {
		return context.getBean(IAddressList.class);
	}
	
	public IPostalAddress createPostalAddress() {
		return context.getBean(IPostalAddress.class);
	}
	
	public IEmailonlyAddress createEmailonlyAddress() {
		return context.getBean(IEmailonlyAddress.class);
	}
	
}
