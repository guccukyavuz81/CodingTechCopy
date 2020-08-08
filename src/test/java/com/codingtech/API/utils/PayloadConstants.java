package com.codingtech.API.utils;

import org.json.JSONObject;

import com.codingtech.APIsteps.practice.HardcodedExamples;
import com.codingtech.steps.UserStory33;
import com.codingtech.steps.UserStory35;
import com.codingtech.steps.UserStory36;



public class PayloadConstants {
	/**
	 * Create employee body
	 * @return
	 */
	public static String createEmployeeBody() {
		String createEmployeeBody = "{\r\n" + 
				"  \"emp_firstname\": \"ilyas\",\r\n" + 
				"  \"emp_lastname\": \"unak\",\r\n" + 
				"  \"emp_middle_name\": \"middle\",\r\n" + 
				"  \"emp_gender\": \"M\",\r\n" + 
				"  \"emp_birthday\": \"2000-07-11\",\r\n" + 
				"  \"emp_status\": \"Worker\",\r\n" + 
				"  \"emp_job_title\": \"CEO\"\r\n" + 
				"}";
		return createEmployeeBody;
		
	}
	
	/**
	 * Creating payload using JSON
	 * @return
	 */
	public static String createEmployeePayload() {
		
		JSONObject obj = new JSONObject();
		
		obj.put("emp_firstname", "ilyas");
		obj.put("emp_lastname", "unak");
		obj.put("emp_middle_name", "middle");
		obj.put("emp_gender", "M");
		obj.put("emp_birthday", "2000-07-11");
		obj.put("emp_status", "Worker");
		obj.put("emp_job_title", "CEO");
		return obj.toString();
	}
	
public static String createEmployeePayload2() {
		
		JSONObject obj = new JSONObject();
		
		obj.put("emp_firstname", "Ali");
		obj.put("emp_lastname", "Kemal");
		obj.put("emp_middle_name", "Jawid");
		obj.put("emp_gender", "M");
		obj.put("emp_birthday", "2000-07-11");
		obj.put("emp_status", "Worker");
		obj.put("emp_job_title", "CEO");
		return obj.toString();
	}
	/**
	 * Created method to return payload - to reduce messy code
	 * @return
	 */
	public static String updateCreateEmpBody() {
		
		String updateBody = "{\r\n" + 
				"  \"employee_id\": \""+HardcodedExamples.employeeID +"\",\r\n" + 
				"  \"emp_firstname\": \"ilyasUpdate\",\r\n" + 
				"  \"emp_lastname\": \"unakUpdate\",\r\n" + 
				"  \"emp_middle_name\": \"Update\",\r\n" + 
				"  \"emp_gender\": \"M\",\r\n" + 
				"  \"emp_birthday\": \"2000-05-11\",\r\n" + 
				"  \"emp_status\": \"Internee\",\r\n" + 
				"  \"emp_job_title\": \"CTO\"\r\n" + 
				"}";
		
		return updateBody;
	}

	


		
		/**
	 * Created method to return payload - to reduce messy code
	 * @return
	 */
	public static String updateCreatedEmpBody() {		
		String updateBody = "{\n" + "  \"employee_id\": \"" + HardcodedExamples.employeeID + "\",\n"
				+ "  \"emp_firstname\": \"syntaxUpdatedFirstName\",\n"
				+ "  \"emp_lastname\": \"syntaxUpdatedLastName\",\n"
				+ "  \"emp_middle_name\": \"syntaxUpdatedMiddleName\",\n" + "  \"emp_gender\": \"F\",\n"
				+ "  \"emp_birthday\": \"2000-07-11\",\n" + "  \"emp_status\": \"Employee\",\n"
				+ "  \"emp_job_title\": \"Cloud Consultant\"\n" + "}";
				return updateBody;
	}
	
	public static String updateCreatedEmployeePayload() {
		
		JSONObject obj= new JSONObject();
		obj.put("employee_id", UserStory36.employeeID);

		obj.put("emp_firstname", "Jawid");
		obj.put("emp_lastname", "syntaxLastName");
		obj.put("emp_middle_name","syntaxMiddleName" );
		obj.put("emp_gender","F" );
		obj.put("emp_birthday", "2000-07-11");
		obj.put("emp_status","Employee" );
		obj.put("emp_job_title","Cloud Architect" );
		
	return	obj.toString();
	}

public static String updateCreatedEmployeePayload2() {
		
		JSONObject obj= new JSONObject();
		obj.put("employee_id", UserStory35.employeeID);
		obj.put("emp_firstname", "michaelupdated");
		obj.put("emp_lastname", "jordan");
		obj.put("emp_middle_name","ali" );
		obj.put("emp_gender","F" );
		obj.put("emp_birthday", "2000-07-11");
		obj.put("emp_status","Employee" );
		obj.put("emp_job_title","Cloud Architect" );
		
	return	obj.toString();
	}
	
public static String PupdateCreatedEmployeePayload() {
	
	JSONObject obj= new JSONObject();
	obj.put("employee_id", UserStory36.employeeID);
	obj.put("emp_firstname", "Jawid");
	obj.put("emp_lastname", "syntaxLastName");
	
	
return	obj.toString();
}

public static String PupdateCreatedEmployeePayload35() {
	
	JSONObject obj= new JSONObject();
	obj.put("employee_id", UserStory35.employeeID);
	obj.put("emp_firstname", "Jawid");
	obj.put("emp_lastname", "syntaxLastName");
		
return	obj.toString();
}

public static String updateCreatedEmployeePayload33() {
	
	JSONObject obj= new JSONObject();
	obj.put("employee_id", UserStory33.ID);
	obj.put("emp_firstname", "Abuzer");
	obj.put("emp_lastname", "Kilkuyruk");
	obj.put("emp_middle_name","Kulyutmaz" );
	obj.put("emp_gender","M" );
	obj.put("emp_birthday", "2000-01-01");
	obj.put("emp_status","Employee" );
	obj.put("emp_job_title","Cloud Architect" );
	
return	obj.toString();
}
	
	


}
