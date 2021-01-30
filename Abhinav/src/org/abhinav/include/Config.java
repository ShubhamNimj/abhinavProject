package org.abhinav.include;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.abhinav.model.DoctorList;
import org.abhinav.model.Patients;

public class Config {

	public static Patients loginUser=null;
	public static HashMap<String, Patients> patientsHashMap=new HashMap();
	
	public static DoctorList docAvail=null;
	public static HashMap<String, DoctorList> doctorHashMap =new HashMap();
}
