package org.abhinav.controller;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.abhinav.controller.services.DoctorServices;
import org.abhinav.controller.services.EmailServices;
import org.abhinav.controller.services.PatientsServices;
import org.abhinav.include.Config;
import org.abhinav.model.DoctorList;
import org.abhinav.model.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;






@Controller
public class AppointmentController {
	
	@Autowired
	protected PatientsServices patientService;
	
	@Autowired
	protected DoctorServices doctorService;
	
	@Autowired
	protected EmailServices emailService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getappointmentPage(HttpServletRequest req, HttpServletResponse res) {
		Map m = new HashMap();
		patientService.getPatientsList();
		doctorService.getDocList();
		
		m.put("doctorList", Config.doctorHashMap.values());
		
		m.put("patientList", Config.patientsHashMap.values());
		
		
		
		return new ModelAndView("appointment", m);
	}
	
	@RequestMapping(value = "/appointment", method = RequestMethod.POST)
	public ModelAndView appointmentPage(HttpServletRequest req, HttpServletResponse res) {
		Map m = new HashMap();
		String name=req.getParameter("name");
		String age=req.getParameter("age");
		String dob=req.getParameter("dob");
		String docId = req.getParameter("docList");
		String bloodgroup=req.getParameter("bloodgroup");
		String address=req.getParameter("address");
		String phone=req.getParameter("phone");
		String email=req.getParameter("email");
		String doa=req.getParameter("doa");
		System.out.println("docId"+docId);
		HttpSession session = req.getSession();
		HttpSession httpSession = req.getSession();
		
		if(name.isEmpty()||age.isEmpty()||dob.isEmpty()||bloodgroup.isEmpty()||address.isEmpty()||phone.isEmpty()||email.isEmpty()||doa.isEmpty()|| docId.isEmpty())
		{
			m.put("errormsg", "All Fields Are Mandatory");
			m.put("doctorList", Config.doctorHashMap.values());
			
			m.put("patientList", Config.patientsHashMap.values());
			return new ModelAndView("appointment", m);
			
		}
		else 
		{
			Patients patient = new Patients();
			patient.setName(name);
			patient.setAge(age);
			patient.setDOB(dob);
			patient.setBloodgroup(bloodgroup);
			patient.setAddress(address);
			patient.setPhone(phone);
			patient.setEmail(email);
			patient.setDOA(doa);
			patient.setStatus(1);
			patient.setCreated(new Date());
			patient.setModified(new Date());
			patientService.addPatients(patient);
			
			
			String[] tempDate = patient.getDOA().split("-");
			Calendar calendar = Calendar.getInstance();
		      calendar.set(Integer.parseInt(tempDate[0]),Integer.parseInt(tempDate[1]) , Integer.parseInt(tempDate[2]));
			String day = calendar.getTime().toString();
			System.out.println(day);
			String[] dayOfweek = day.split(" "); 
			Collection<DoctorList> docList=Config.doctorHashMap.values();
			boolean flag = false;
			for(DoctorList dr:docList)
			{
				if((Integer.parseInt(docId) == dr.getId()) && (dr.getDoc_avail_days().indexOf(dayOfweek[0]) > -1) )
				{
						flag = true;
						break;
				}
			}
			
			if(flag == true){
				m.put("succcessMsg", "Record Submitted Successfully");
				patientService.getPatientsList();
				m.put("patientList", Config.patientsHashMap.values());
				
				String ProposalNumber="810007889";//"820247948";
				String[] all_files =null;
				String AgentID="1073900" ;
				String E_Reference_Id="E10739001212021171446234" ;
				
				String TDSFileSample="C:/Users/Mayur/workspace/Abhinav/src/org/abhinav/sampleFile/"+name+"_"+phone+"_Details.pdf" ;
				String TDSFilePath="C:/Users/Mayur/workspace/Abhinav/src/org/abhinav/Report/Output/"+name+"_"+phone+"_Appointment_Details.pdf";
				CreateUOBTDS objPDF = new CreateUOBTDS("C:/Users/Mayur/workspace/Abhinav/src/org/abhinav/Report/",
						"C:/Users/Mayur/workspace/Abhinav/src/org/abhinav/Font/","C:/Users/Mayur/workspace/Abhinav/src/org/abhinav/Report/"+"Output"+"/",AgentID);  // 1006256
				String response_full="1~"+name+"~"+age+"~"+dob+"~"+bloodgroup+"~"+address+"~"+phone+"~"+email+"~"+doa;
						objPDF.createTransactionDetailsPDFUOB(TDSFileSample,TDSFilePath,response_full,AgentID,E_Reference_Id);
				
				emailService.sendMail(email, "Hello Sir, \n Your appointment has been confirmed \n Doctor Available on your Date \n please be available on same date ", "Conformation Mail");
				
			}else{
				System.out.println("Doctor is not avilable");
				patientService.getPatientsList();
				m.put("patientList", Config.patientsHashMap.values());
				return new ModelAndView("appointment", m);
			}
			
		}
		
		return new ModelAndView("appointment", m);
	}
}
