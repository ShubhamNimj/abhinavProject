package org.abhinav.controller.services;

import java.util.Collection;
import java.util.List;

import org.abhinav.include.Config;
import org.abhinav.listener.AbhinavSessionListener;
import org.abhinav.model.Patients;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Service;

@Service
public class PatientsServices {
	
	public static boolean addPatients(Patients patients)
	{
		Session ses = AbhinavSessionListener.getDatabaseSession();
		ses.beginTransaction();
		ses.save(patients);
		ses.getTransaction().commit();
		return true;
	}
	
	
	public void getPatientsList() 
	{
		 Session ses=AbhinavSessionListener.getDatabaseSession();
	     ses.beginTransaction();
	     Query query = ses.createQuery("from Patients where status>=1");
	     List<Patients> patientList=query.list(); //ProductList is fetching object of product from database
	     ses.getTransaction().commit();
	     Config.patientsHashMap.clear();// every time  it will clear/refresh map
	     for(Patients patient:patientList)//product is variable and we are adding object of product one by one from ProductList
	     {
	    	Config.patientsHashMap.put(patient.getPhone(),patient); //storing id as a key and employee as Value in HashMap
	     }
	}
	
	public static String getPatients()
	{
		try {
			Collection<Patients> patientsValues = Config.patientsHashMap.values();
			String str="";
			for(Patients emp:patientsValues)
			{
			str=str.concat("'"+emp.getPhone()+"',");	
			}
			if(!str.isEmpty())
			{
				str=str.substring(0,str.length()-1);
			}
//			System.out.println(str);
			return str;
		} catch (Exception e) {
			return null;
		}
	}
}
