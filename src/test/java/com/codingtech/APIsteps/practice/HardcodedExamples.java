package com.codingtech.APIsteps.practice;
//Rest Assured static packages
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

//importing JUnit packages
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
//We may use below - please comment out for now
//import org.apache.hc.core5.http.ContentType;

import com.codingtech.API.utils.PayloadConstants;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * This @FixMethodOrder(MethodSorters.NAME_ASCENDING) will execute 
 * @Test annotation in ascending alphabetical order
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardcodedExamples {

	
	/**
	 * Rest Assured
	 * given - prepare your request
	 * when -you are making the call to the endpoint
	 * then -validating
	 * 
	 * @param args
	 */
	
	static String baseURI = RestAssured.baseURI="http://18.232.148.34/syntaxapi/api";
	static String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTU2ODk0MTksImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTU5NTczMjYxOSwidXNlcklkIjoiNjk0In0.w9lm5T2vJkjwNNMLRYtikDUVFBNE9O83WNwwrn_3kdU";
	public static String employeeID;
	
	public void sampleTestNotes() {
		
		/**
		 * BaseURI for all calls
		 */
		RestAssured.baseURI="http://18.232.148.34/syntaxapi/api";
		
		/**
		 * JWT required for all calls - expires every 12 hours
		 */
		String token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTU2ODk0MTksImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTU5NTczMjYxOSwidXNlcklkIjoiNjk0In0.w9lm5T2vJkjwNNMLRYtikDUVFBNE9O83WNwwrn_3kdU";
		
		/**
		 * Preparing /getOneEmployee.php  request 
		 */
		RequestSpecification getOneEmployeeRequest = given().header("Content-Type", "application/json").header("Authorization", token)
				.queryParam("employee_id", "16507A");   //.log().all();
		
		/**
		 * Storing response
		 */
		Response getOneEmployeeResponse = getOneEmployeeRequest.when().get("/getOneEmployee.php");
		
		/**
		 * Two ways to print response
		 */
		getOneEmployeeResponse.prettyPrint();
		//String response = getOneEmployeeResponse.body().asString();
		//System.out.println(response);
		
		/**
		 * Verifying response status code is 200
		 */
		getOneEmployeeResponse.then().assertThat().statusCode(200);
	}
	
	@Test
	public void aPOSTcreateEmployee() {
		
		/**
		 * Preparing request for /createEmployee.php
		 */
		RequestSpecification createEmployeeRequest = given().header("Content-Type", "application/json").header("Authorization", token)
				.body("{\r\n" + 
				"  \"emp_firstname\": \"ilyas\",\r\n" + 
				"  \"emp_lastname\": \"unak\",\r\n" + 
				"  \"emp_middle_name\": \"middle\",\r\n" + 
				"  \"emp_gender\": \"M\",\r\n" + 
				"  \"emp_birthday\": \"2000-07-11\",\r\n" + 
				"  \"emp_status\": \"Worker\",\r\n" + 
				"  \"emp_job_title\": \"CEO\"\r\n" + 
				"}"); //.log().all();
	
		/**
		 * Storing response into createEmployeeResponse
		 */
		Response createEmployeeResponse = createEmployeeRequest.when().post("/createEmployee.php");
		
		/**
		 * Printing response using prettyPrint() method
		 */
		createEmployeeResponse.prettyPrint();
		
		/**
		 * jsonPath() to view response body which lets us get the employee id 
		 * We store employee ID as a global variable so that we may we use it with our other calls
		 */
		employeeID = createEmployeeResponse.jsonPath().getString("Employee[0].employee_id");
		/**optional to print employee ID*/
		System.out.println(employeeID);
		
		/**
		 * Verifying response status code is 201
		 */
		createEmployeeResponse.then().assertThat().statusCode(201);
		
		/**
		 * Verifying message using equalTo() method - manually import static package
		 * import static org.hamcrest.Matchers.*;
		 */
		createEmployeeResponse.then().assertThat().body("Message", equalTo("Entry Created"));
		
		/**
		 * Verifying created first name 
		 */
		createEmployeeResponse.then().assertThat().body("Employee[0].emp_firstname", equalTo("ilyas"));
		
		/**
		 * Verifying server using then().header()
		 */
		createEmployeeResponse.then().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");
		
		/**
		 * Verifying Content-Type using assertThat().header()
		 */
		createEmployeeResponse.then().assertThat().header("Content-Type", "application/json");
		System.out.println("*****************************************");
	}
	
	@Test
	public void bGETcreatedEmployee() {
		
		/**
		 * Preparing request for /getOneEmployee.php
		 * Using log().all() to see all information being sent with request
		 */
		RequestSpecification getCreatedEmployeeRequest = given().header("Content-Type", "application/json")
				.header("Authorization", token).queryParam("employee_id", employeeID).log().all();
		/**
		 * Making call to retrieve created employee
		 */
		Response getCreatedEmployeeResponse = getCreatedEmployeeRequest.when().log().all().get("/getOneEmployee.php");
		/**
		 * Printing response using prettyPrint()
		 */
		String response = getCreatedEmployeeResponse.prettyPrint();
		
		/**
		 * Storing response employeeID into empID which will be used for verification
		 * against stored global employeeID
		 */
		String empID = getCreatedEmployeeResponse.body().jsonPath().getString("employee[0].employee_id");
		/**
		 * matching exact employeeID's
		 */
		boolean verifyingEmployeeIDsMatch = empID.contentEquals(employeeID);
		System.out.println("Employee ID's match: "+verifyingEmployeeIDsMatch);
		/**
		 * Asserting employee ID match
		 */
		Assert.assertTrue(verifyingEmployeeIDsMatch);
		
		/**
		 * Verifying status code
		 */
		getCreatedEmployeeResponse.then().assertThat().statusCode(200);
		
		/**
		 * Using JsonPath class to retrieve values from response as a String
		 */
		JsonPath js = new JsonPath(response);
		
		String emplID = js.getString("employee[0].employee_id");
		String firstName=js.getString("employee[0].emp_firstname");
		String middleName=js.getString("employee[0].emp_middle_name");
		String lastName=js.getString("employee[0].emp_lastname");
		String empBirthday=js.getString("employee[0].emp_birthday");
		String empGender=js.getString("employee[0].emp_gender");
		String empJobTitle=js.getString("employee[0].emp_job_title");
		String empStatus=js.getString("employee[0].emp_status");
		
		System.out.println(emplID);
		System.out.println(firstName);
		
		/**Verifying employee ID's match	 */
		Assert.assertTrue(emplID.contentEquals(employeeID));
		//Second way of asserting
		Assert.assertEquals(employeeID, emplID);
		
		/** Verifying expected first name matches actual first name		 */
		Assert.assertTrue(firstName.contentEquals("ilyas"));
		
		/** Verifying expected middle name matches actual middle name		 */
		Assert.assertTrue(middleName.contentEquals("middle"));
		
		/** Verifying expected last name matches actual last name		 */
		Assert.assertTrue(lastName.contentEquals("unak"));
		
		/** Verifying expected Birthday matches actual Birthday		 */
		Assert.assertTrue(empBirthday.contentEquals("2000-07-11"));
		
		/** Verifying expected Gender matches actual Gender		 */
		Assert.assertTrue(empGender.contentEquals("Male"));
		
		/** Verifying expected JobTitle matches actual JobTitle		 */
		Assert.assertTrue(empJobTitle.contentEquals("CEO"));
		
		/** Verifying expected emp Status matches actual emp Status		 */
		Assert.assertTrue(empStatus.contentEquals("Worker"));
		System.out.println("-----------------------------------------------");
	}
	@Test
	public void cGETallEmployee() {
		/**
		 * Preparing request for /getAllEmployee.php
		 */
		RequestSpecification getAllEmployeeRequest = given().header("Content-Type", "application/json").header("Authorization", token);
	
		/**
		 * Storing response into getAllEmployeeResponse
		 */
		Response getAllEmployeeResponse = getAllEmployeeRequest.when().get("/getAllEmployees.php");
		
		/**
		 * Printing all employees
		 */
		//getAllEmployeeResponse.prettyPrint();
		
		String allEmployees = getAllEmployeeResponse.body().asString();
		/**
		 * The below will pass but incorrect
		 */
		//allEmployees.contains(employeeID);
		
		/**
		 * ------DO RESEARCH--------
		 */
		//allEmployees.matches(employeeID);
		
		JsonPath js = new JsonPath(allEmployees);
		
		int sizeOfList = js.getInt("Employees.size()");
		System.out.println(sizeOfList);
		
		/**
		 * Print out all employee ID's
		 */
		
//		for(int i=0; i<=sizeOfList; i++) {
//			
//		String allEmployeeIDs =	js.getString("Employees["+ i + "].employee_id");
//		//System.out.println(allEmployeeIDs);
//		/**
//		 * 
//		 */
//		
//		if(allEmployeeIDs.contentEquals(employeeID)) {
//			
//			System.out.println("Employee ID: " +employeeID+ " is present in body");
//			String employeeFirstName =	js.getString("Employees["+ i + "].emp_firstname");
//			System.out.println(employeeFirstName);
//			
//			break;
//		}
//		}
		System.out.println("++++++++++++++++++++++++++++++++++");
	}
	@Test
	public void dPUTupdateCreateEmployee() {
		
		/**
		 * Preparing request to update create employee
		 * calling static method updateCreatedEmpBody() from HardcodedConstants class
		 */
		RequestSpecification updateCreateEmployeeRequest = given().header("Content-Type", "application/json").header("Authorization", token)
				.body(PayloadConstants.updateCreateEmpBody());
		
		/**
		 * Storing response into updateCreateEmployeeResponse
		 */
		Response updateCreateEmployeeResponse = updateCreateEmployeeRequest.when().put("/updateEmployee.php");
	
		/**
		 * Storing response into a string
		 */
		//String response = updateCreateEmployeeResponse.prettyPrint();
		/**
		 * Asserting using hamcrest matchers equalTo() method to verify employee is updates
		 */
		updateCreateEmployeeResponse.then().assertThat().body("Message", equalTo("Entry updated"));
		
		/**
		 * Retrieving response body employee ID using jsonPath()
		 */
		String empID = updateCreateEmployeeResponse.body().jsonPath().getString("employee[0].employee_id");
		
		/**
		 * Asserting that response body employee ID matches globally stored employee ID
		 */
		Assert.assertTrue(empID.contentEquals(employeeID));
		System.out.println( "##################################################");
	}
	
	@Test
	public void eGETUpdatedEmployee() {
		/**
		 * Preparing request to get updated employee
		 */
		
		RequestSpecification getUpdatedEmployeeRequest = given().header("Content-Type", "application/json").header("Authorization", token)
				.queryParam("employee_id", employeeID);
		/**
		 * Storing response into getUpdatedEmployeeResponse
		 */
		
		Response getUpdatedEmployeeResponse = getUpdatedEmployeeRequest.when().get("/getOneEmployee.php");
		/**
		 * Printing response
		 */
		//getUpdatedEmployeeResponse.prettyPrint();
		/**
		 * asserting expected first name
		 */
		getUpdatedEmployeeResponse.then().assertThat().body("employee[0].emp_firstname", equalTo("ilyasUpdate"));
		
		/**
		 * Verifying response employee ID is equal to globally stored employee ID
		 */
		getUpdatedEmployeeResponse.then().assertThat().body("employee[0].employee_id", equalTo(employeeID));
		System.out.println("------------------------------------");
		
	}
	
	@Test
	public void fPATCHpartiallyUpdateEmployee() {
		
		RequestSpecification partiallyUpdatingEmployeeRequest = given().header("Content-Type", "application/json").header("Authorization", token)
				.body("{\r\n" + 
						"  \"employee_id\": \""+ employeeID +"\",\r\n" + 
						"  \"emp_firstname\": \"ilyasPartiallyUpdated\"\r\n" + 
						"}");
		
		Response partiallyUpdatingEmployeeResponse = partiallyUpdatingEmployeeRequest.when().patch("/updatePartialEmplyeesDetails.php");
	
		partiallyUpdatingEmployeeResponse.prettyPrint();
		
		partiallyUpdatingEmployeeResponse.then().assertThat().statusCode(201);
		
		partiallyUpdatingEmployeeResponse.then().assertThat().body("Message", equalTo("Entry updated"));
		System.out.println("////////////////////////////////////////////////");
		}
	
	@Test
	public void gDELETEemployee() {
		
		RequestSpecification DeleteEmployeeRequest = given().header("Content-Type", "application/json").header("Authorization", token)
				.queryParam("employee_id", employeeID);
		
		Response DeleteEmployeeResponse = DeleteEmployeeRequest.when().delete("/deleteEmployee.php");
		
		DeleteEmployeeResponse.prettyPrint();
		DeleteEmployeeResponse.then().assertThat().body("message", equalTo("Entry deleted"));
	}
}
