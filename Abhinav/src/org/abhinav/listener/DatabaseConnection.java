package org.abhinav.listener;

import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;


public class DatabaseConnection implements ServletContextListener{
	private static SessionFactory sf = null;
	private static Session ses = null;
	private Thread loginThread = null;

	// whenever server start it will get execute
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		 System.out.println("Context Initialized Method Getting Call");
		sf = new Configuration().configure("org/abhinav/listener/hibernate.cfg.xml").buildSessionFactory();
		ses = sf.openSession();
		

	}

//	// whenever server stop it will get execute
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// System.out.println("Context Destroyed Method Getting Call");
		if (ses != null) {
			ses.close();
		}
	if (sf != null) {
			sf.close();
		}
	}

	public static Session getDatabaseSession() {
		return ses;
	}
}
