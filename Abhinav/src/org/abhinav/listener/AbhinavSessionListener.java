package org.abhinav.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.abhinav.include.Config;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class AbhinavSessionListener implements HttpSessionListener {

	private static SessionFactory sf = null;
	private static Session ses = null;
	
	@Override
	public void sessionCreated(HttpSessionEvent se)
	{
		se.getSession().setMaxInactiveInterval(1800);
		openSession();
		
		HttpSession httpSession = se.getSession();
		Config.patientsHashMap.put(se.getSession().getId(), null);
		Config.doctorHashMap.put(se.getSession().getId(), null);
	}

	private static void openSession() {
		if(sf==null)
		{
		sf = new Configuration().configure("org/abhinav/listener/hibernate.cfg.xml").buildSessionFactory();
		}
		if(ses==null)
		{
		ses = sf.openSession();
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {

		// System.out.println("Before Destroy: "+Config.userLoginHashMap);
		String sessionId = se.getSession().getId();
		if (Config.patientsHashMap.containsKey(sessionId)) {
		}
		
		if (Config.doctorHashMap.containsKey(sessionId)) {
		}
		
		// System.out.println("After Destroy: "+Config.userLoginHashMap);
		System.out.println("Session Destroy");
	}

	public static Session getDatabaseSession() {
		if (ses!=null)
		{
			return ses;
		}
		else {
			openSession();
			return ses;
		}
	}
}
