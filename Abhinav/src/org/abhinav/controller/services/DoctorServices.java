package org.abhinav.controller.services;

import java.util.Collection;
import java.util.List;

import org.abhinav.include.Config;
import org.abhinav.listener.AbhinavSessionListener;
import org.abhinav.model.DoctorList;
import org.abhinav.model.Patients;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Service;

@Service
public class DoctorServices {

	public void getDocList() 
	{
		 Session ses=AbhinavSessionListener.getDatabaseSession();
	     ses.beginTransaction();
	     Query query = ses.createQuery("from DoctorList where status>=1");
	     List<DoctorList> docList=query.list(); //ProductList is fetching object of product from database
	     ses.getTransaction().commit();
	     Config.doctorHashMap.clear();// every time  it will clear/refresh map
	     for(DoctorList doctorList:docList)//product is variable and we are adding object of product one by one from ProductList
	     {
	    	Config.doctorHashMap.put(doctorList.getDocName(),doctorList); //storing id as a key and employee as Value in HashMap
	     }
	}
	
	public static String getDoctor()
	{
		try {
			Collection<DoctorList> docValues = Config.doctorHashMap.values();
			String str="";
			for(DoctorList doc:docValues)
			{
			str=str.concat("'"+doc.getDocName()+"',");
			str=str.concat("'"+doc.getId()+"',");
			str=str.concat("'"+doc.getDoc_avail_days()+"',");
			}
			if(!str.isEmpty())
			{
				str=str.substring(0,str.length()-1);
			}
			//System.out.println(str);
			return str;
		} catch (Exception e) {
			return null;
		}
	}
}
