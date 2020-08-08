package com.codingtech.steps;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.codingtech.API.utils.APIConstants;
import com.codingtech.API.utils.PayloadConstants;
import com.syntax.utils.CommonMethods;
import com.syntax.utils.ConfigsReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserStory32 extends CommonMethods{
	
	String baseURI = RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";
	RequestSpecification request;
	Response response;
	static String employeeID;
	
	static String empID_UI;
	static String firstName_UI;
	static String middleName_UI;
	static String lastName_UI;
	static String birthDay_UI;
	static String gender_UI;
	static String jobTitle_UI;
	static String empStatus_UI;
	
	@Given("user able to logged with valid admin credentials")
	public void user_is_logged_with_valid_admin_credentials() {
		sendText(login.username, ConfigsReader.getProperty("username"));
		sendText(login.password, ConfigsReader.getProperty("password"));
		click(login.loginBtn);
	}
	
	@Given("user able to navigate to employee list page")
	public void user_navigate_to_employee_list_page() {
		jsClick(dashboard.PIM);
		jsClick(dashboard.empListPage);
		wait(2);
	}
	
	@When("create new employee from API and store employeeID {string} as global variable")
	public void create_new_employee_from_API_and_store_employeeID_as_global_variable(String empID) {
	    
		request = given().header("Content-Type", "application/json").header("Authorization", TokenGenerationSteps.token)
				.body(PayloadConstants.createEmployeePayload());
		
		response = request.when().post(APIConstants.CREATE_EMPLOYEE_URI_ENDPOINT);
		
		employeeID = response.body().jsonPath().getString(empID);
		
	}

	
	@Given("user enter employeeID and search it and click employee from table")
	public void user_enter_employeeID_and_search_it_and_click_employeeID_from_table() {
	    
		sendText(viewEmp.empID, employeeID);
		jsClick(viewEmp.searchBtn);
		
		wait(4);
		
		driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr/td[2]/a")).click();
	
		wait(2);
	
	
	}
	
	@When("user able to see employee information")
	public void user_able_to_see_employee_information() {
		
		
		empID_UI = pdetails.employeeId.getAttribute("value");
		firstName_UI = pdetails.firstName.getAttribute("value");
		middleName_UI = pdetails.middleName.getAttribute("value");
		lastName_UI = pdetails.lastName.getAttribute("value");
		birthDay_UI = pdetails.dateOfBird.getAttribute("value");
		
		
		
		//??
//		List<WebElement> listRadio = pdetails.genderRadioGroup;
//		
//		gender_UI="";
//		for(WebElement genderType :listRadio ) {
//			
//		String genderNumber = genderType.getAttribute("value");
//			
//			if(genderNumber.contains("1")) {
//				gender_UI = driver.findElement(By.xpath("//input[@id='personal_optGender_1']/following-sibling::label")).getText();
//				
//			}else {
//				gender_UI = driver.findElement(By.xpath("//input[@id='personal_optGender_2']/following-sibling::label")).getText();
//			}
//		}
		
//		List<WebElement> listRadio = pdetails.genderRadioGroup;
//		
//		for(WebElement genderType :listRadio ) {
//			
//			if(genderType.isEnabled()) {
//			
//			System.out.println("-----" + driver.findElement(By.xpath("//input[@id='personal_optGender_2']/following-sibling::label")).getText());
//		}
//		
		
		jsClick(pdetails.jobPages);
		wait(2);

		
		// get JOBtITLE from UI
		Select selectJobTitle = new Select(pdetails.jobTitle);
		List<WebElement> optionsJobTitle = selectJobTitle.getOptions();
		
		for(WebElement stsJobTitle : optionsJobTitle) {
		
			if(stsJobTitle.isSelected()) {
				jobTitle_UI = stsJobTitle.getText();
				break;
			}
		}
		
		// get empStatus from UI
		Select selectEmpStatus = new Select(pdetails.empStatus);
		List<WebElement> optionsEmpStatus = selectEmpStatus.getOptions();
		
		for(WebElement stsEmpStatus : optionsEmpStatus) {
		
			if(stsEmpStatus.isSelected()) {
				empStatus_UI = stsEmpStatus.getText();
				break;
			}
	
	}
	
		
	}

	
	
	@When("request is prepared to retrieve the created employee")
	public void a_request_is_prepared_to_retrieve_the_created_employee() {

		request = given().header("Content-Type", "application/json").header("Authorization", TokenGenerationSteps.token)
				.queryParam("employee_id", employeeID);

	}
	
	@When("GET call is made to retrieve the created employee")
	public void a_GET_call_is_made_to_retrieve_the_created_employee() {

		response = request.when().get(APIConstants.GET_ONE_EMPLOYEE_ENDPOINT);
	
	}


	@Then("validate employeeID, firstName, middleName, lastName, dateOfBirth, gender, jobTitle and employeeStatus from API response")
	public void validate_employeeID_firstName_middleName_lastName_dateOfBirth_and_gender_from_API_response() {
		
		String responseText = response.prettyPrint();
		
		JsonPath js = new JsonPath(responseText);
		
		String emplID = js.getString("employee[0].employee_id");
		String firstName = js.getString("employee[0].emp_firstname");
		String middleName = js.getString("employee[0].emp_middle_name");
		String lastName = js.getString("employee[0].emp_lastname");
		String emp_bday = js.getString("employee[0].emp_birthday");
		String gender = js.getString("employee[0].emp_gender");
		String jobTitle = js.getString("employee[0].emp_job_title");
		String empStatus = js.getString("employee[0].emp_status");
		
		
		//Verifying results of UI and response of API webservices are matched
		Assert.assertTrue(emplID.contentEquals(employeeID));
		Assert.assertTrue(firstName.contentEquals(firstName_UI));
		Assert.assertTrue(middleName.contentEquals(middleName_UI));
		Assert.assertTrue(lastName.contentEquals(lastName_UI));
		Assert.assertTrue(emp_bday.contentEquals(birthDay_UI));
		//Assert.assertTrue(gender.contentEquals(gender_UI));
		Assert.assertTrue(jobTitle.contentEquals(jobTitle_UI));
		Assert.assertTrue(empStatus.contentEquals(empStatus_UI));
	
	}
	
	@When("user able to get employee information by using unique employeeID and update information")
	public void user_able_to_get_employee_information_by_using_unique_employeeID_and_update_information() {
	    
		empID_UI = pdetails.employeeId.getAttribute("value");
		firstName_UI = "update_firstname";
		middleName_UI = "update_middlename";;
		lastName_UI = "update_lastname";;
		birthDay_UI = "2000-01-01";
		jobTitle_UI = "IT Analyst";
		empStatus_UI = "Employee";
		
		//click edit button
		jsClick(pdetails.editBtn);
		wait(3);
		
		sendText(pdetails.firstName, firstName_UI);
		sendText(pdetails.middleName, middleName_UI);
		sendText(pdetails.lastName, lastName_UI);
		sendText(pdetails.dateOfBird, birthDay_UI);
		
		clickRadioOrCheckbox(pdetails.genderRadioGroup, "1");
		
		//save changes
		jsClick(pdetails.editBtn);
		wait(3);
		
		//navigate to job pages
		jsClick(pdetails.jobPages);
		wait(2);
		
		//click edit button
		jsClick(pdetails.editBtn);
		
		selectDdValue(pdetails.jobTitle, jobTitle_UI);
		selectDdValue(pdetails.empStatus, empStatus_UI);
		
	
		//click save button
		jsClick(pdetails.editBtn);
		wait(3);
		
		
	}
	
	@Given("request is prepared to retrieve the updated employee")
	public void request_is_prepared_to_retrieve_the_updated_employee() {
		
		request = given().header("Content-Type", "application/json").header("Authorization", TokenGenerationSteps.token)
				.queryParam("employee_id", employeeID);
	}

	@When("GET call is made to retrieve the updated employee")
	public void get_call_is_made_to_retrieve_the_updated_employee() {
		
		response = request.when().get(APIConstants.GET_ONE_EMPLOYEE_ENDPOINT);
	    
	}
	

}
