<%@page import="org.abhinav.include.Config"%>
<%@page import="org.abhinav.model.Patients"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="res/text/javascript" src="js/jquery-3.5.1.min.js"></script>
<<script type="text/javascript">
<!--

//-->
function fnsetAge(id){
	var userinput = document.getElementById(id).value;  
    var dob = new Date(userinput);  
    if(userinput==null || userinput=='') {  
      document.getElementById("message").innerHTML = "**Choose a date please!";    
      return false;   
    } else {  
      
    //calculate month difference from current date in time  
    var month_diff = Date.now() - dob.getTime();  
      
    //convert the calculated difference in date format  
    var age_dt = new Date(month_diff);   
      
    //extract year from date      
    var year = age_dt.getUTCFullYear();  
      
    //now calculate the age of the user  
    var age = Math.abs(year - 1970);  
      
    //display the calculated age    
	 console.log("age",age)
	//display the calculated age  
	document.getElementById("age").value=age;
    }
}
function isNumberKey(evt,id){
    var charCode = (evt.which) ? evt.which : evt.keyCode
    if ((charCode > 31 && (charCode < 48 || charCode > 57)) || (document.getElementById("phone").value.length >10))
        return false;
    return true;
}

function onAppointmentDateChange(dateId){
	console.log("onAppointmentDateChange")
	var GivenDate = document.getElementById(dateId).value;
	console.log("onAppointmentDateChange",GivenDate)
	var CurrentDate = new Date();
	console.log("onAppointmentDateChange",CurrentDate)
	GivenDate = new Date(GivenDate);
	console.log("onAppointmentDateChange",GivenDate)

	if(GivenDate > CurrentDate){
	  //  alert('Given date is greater than the current date.');
	}else{
	    alert('Given date is not greater than the current date.');
	    document.getElementById(dateId).value = "";
	    return ;
	}
}
function setvalueTohidden(id){
	document.getElementById(id+"_hidden").value = document.getElementById(id).value;
}
</script>

<div class="row">
	<div class="container col-md-5" style="margin-top: 4%;">
		<div class="card"
			style="border: 0px solid transparent; box-shadow: 0 5px 16px 0 rgba(10, 10, 10, 0.6)">
			<div class="card-header bg-primary text-white"
				style="font-weight: bolder; font-size: 17px">Appointment Form</div>
			<div class="card-body">
				<form action="./appointment" method="post">
					<div class="row">
						<div class="col-md-12" style="color: black;">Enter Name:</div>
						<div class="col-md-12">
							<input class="form-control form-control-sm" type="text" id="name"
								name="name" maxlength="200">
						</div>
					</div>

					<div class="row">
						<div class="col-md-12" style="color: black;">Enter Age:</div>
						<div class="col-md-12">
							<input class="form-control form-control-sm" type="text" id="age"
								name="age" readonly maxlength="3">
						</div>
					</div>

					<div class="row">
						<div class="col-md-6">
							<div style="color: black;">Enter DOB:</div>
							<div>
								<input class="form-control form-control-sm" type="date"
									id="birthday" name="dob" onchange="fnsetAge(id)">
							</div>
						</div>
						<div class="col-md-6">
							<div style="color: black;">Blood Group:</div>
							<div>
								<input class="form-control form-control-sm" type="text" id="bloodgroup"
									name="bloodgroup" maxlength="3">
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-12" style="color: black;">Enter Address:</div>
						<div class="col-md-12">
							<input class="form-control form-control-sm" type="text"
								name="address" maxlength="300">
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-12" style="color: black;">Select Doctor Name:</div>
						<div class="col-md-12">
						<select name="docList" id="docList" style="width: 40%;" onselect="setvalueTohidden(id)">
							<c:forEach var="doc" items="${doctorList}">
							<option value= ${doc.getId()}>${doc.getDocName()}</option>
							
						</c:forEach>
						</select>
						</div>
					</div>

					<div class="row">
						<div class="col-md-12" style="color: black;">Enter Mobile
							No.</div>
						<div class="col-md-12">
							<input class="form-control form-control-sm" type="text"
								id="phone" name="phone" maxlength="11" onkeypress="return isNumberKey(event,id)">
						</div>
					</div>

					<div class="row">
						<div class="col-md-12" style="color: black;">Enter Email</div>
						<div class="col-md-12">
							<input class="form-control form-control-sm" type="email"
								id="email" name="email">
						</div>
					</div>

					<div class="row">
						<div class="col-md-12" style="color: black;">Date of
							Appointment:</div>
						<div class="col-md-12">
							<input class="form-control form-control-sm" type="date" id="doa"
								name="doa" onchange="onAppointmentDateChange(id)">
						</div>
					</div>

					<div class="modal-footer">
						<button type="submit" class="btn btn-primary btn-sm">Submit</button>
						<button type="reset" class="btn btn-primary btn-sm">Reset</button>
					</div>

					<div class="row">
						<div class="col-md-12">
							<b style="color: red;">${errormsg}</b>
						</div>
						<div class="col-md-12">
							<b style="color: green;">${succcessMsg}</b>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="container col-md-5" style="margin-top: 4%;display:none;">
		<div class="card"
			style="border: 0px solid transparent; box-shadow: 0 5px 16px 0 rgba(10, 10, 10, 0.6)">
			<div class="card-header bg-primary text-white"
				style="font-weight: bolder; font-size: 17px">Patients Details</div>
			<div class="card-body">
				<table class="table table-bordered  mb-0" id="tableScroll"
						style="overflow-y: scroll; overflow-x: scroll;">
						<thead class="thead-light">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">Name</th>
							<th scope="col">Age</th>
							<th scope="col">Phone</th>
							<th scope="col">Email</th>
							<th scope="col">Address</th>
						</tr>
					</thead>


					<tbody style="color: black;">
						<c:forEach var="Pati" items="${patientList}">
							<tr>
								<td>${Pati.getId()}</td>
								<td>${Pati.getName()}</td>
								<td>${Pati.getAge()}</td>
								<td>${Pati.getPhone()}</td>
								<td>${Pati.getEmail()}</td>
								<td>${Pati.getAddress()}</td>
							</tr>
						</c:forEach>

					</tbody>

				</table>
			</div>
		</div>
	</div>
</div>