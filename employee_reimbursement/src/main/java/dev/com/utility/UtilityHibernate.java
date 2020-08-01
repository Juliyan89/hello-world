package dev.com.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UtilityHibernate {
	
private static SessionFactory sf;
	
	private UtilityHibernate() {};
	
	public static SessionFactory getSessionFactory() {
		
		if(sf == null) {
			
			Configuration cfg = new Configuration();
			sf = cfg.configure().buildSessionFactory();
		}
		return sf;
	}
	
	

}

	
