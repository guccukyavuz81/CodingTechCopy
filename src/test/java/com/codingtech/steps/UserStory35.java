package com.codingtech.steps;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;

import org.junit.Assert;

import com.codingtech.API.utils.APIConstants;
import com.codingtech.API.utils.PayloadConstants;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class UserStory35 {
	
	String BaseURI = RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";
	RequestSpecification request;
	Response response;
	public static String employeeID;
  
    public static String empID;
    public static String updatedEmpID;
    
	@Given("A request is prepared to create an employee")
	public void A_request_is_prepared_to_create_an_employee() {
	System.out.println(TokenGenerationSteps.token);

		request=given().header("Content-Type", "application/json").header("Authorization", TokenGenerationSteps.token).body(PayloadConstants.createEmployeePayload2());
	}

	@Given("A POST call is made to create an employee")
	public void A_POST_call_is_made_to_create_an_employee() {
	    response= request.when().post(APIConstants.CREATE_EMPLOYEE_URI_ENDPOINT);
	}

	@Then("The status code for creating an employee is {int}")
	public void The_status_code_for_creating_an_employee_is(int statusCode) {
	    response.then().assertThat().statusCode(statusCode);
	}

	@Then("The employee is created and response contains key {string} and value {string}")
	public void The_employee_is_created_and_response_contains_key_and_value(String key, String value) {
	   response.then().assertThat().body(key, equalTo(value));
	}

	@Given("The employee ID {string} is stored as a global variable to be used for other calls")
	public void The_employee_ID_is_stored_as_a_global_variable_to_be_used_for_other_calls(String createdID) {
	  employeeID=response.body().jsonPath().getString(createdID);
	  System.out.println(employeeID);
	}

	@When("Storing response employeeID into empID")
	public void storing_response_employeeID_into_empID() {
	   request=given().header("Content-Type", "application/json").header("Authorization", TokenGenerationSteps.token).queryParam("employee_id", employeeID);
	   System.out.println(employeeID);
	   response= request.when().get(APIConstants.GET_ONE_EMPLOYEE_ENDPOINT);
	   empID=response.body().jsonPath().getString("employee[0].employee_id");
	   System.out.println(empID);
	   
	}

	@Then("Verifying that globally stored ID is matching the exact response ID")
	public void verifying_that_globally_stored_employeeID_is_matching_the_exact_response_empID_s() {
		
	   Assert.assertTrue(empID.contentEquals(employeeID));
	}
	
	@Then("Update created employee using the stored global empID")
	public void update_the_created_employee_using_the_stored_global_empID() {
	   request=given().header("Content-Type", "application/json").header("Authorization", TokenGenerationSteps.token).body(PayloadConstants.updateCreatedEmployeePayload2());
	   
	   response=request.when().put(APIConstants.UPDATE_EMPLOYEE_ENDPOINT);
	   updatedEmpID=response.body().jsonPath().getString("employee[0].employee_id");
	   Assert.assertTrue(updatedEmpID.contentEquals(employeeID));
	   response.then().assertThat().body("Message", equalTo("Entry updated"));
	   response.then().assertThat().statusCode(201);
	   System.out.println("Assertion passed");
	   
	}
	
	@Then("Partially update the updated employee using the same global ID")
	public void partially_update_the_updated_employee_using_the_same_global_ID() {
		request=given().header("Content-Type", "application/json").header("Authorization", TokenGenerationSteps.token).body(PayloadConstants.PupdateCreatedEmployeePayload35());
		response=request.when().patch(APIConstants.PARTIAL_UPDATE_EMPLOYEE_ENDPOINT);
		String partiallyUpdatedEmployeeID = response.body().jsonPath().getString("employee[0].employee_id");
		Assert.assertTrue(partiallyUpdatedEmployeeID.contentEquals(employeeID));
		response.then().assertThat().body("Message", equalTo("Entry updated")); 
	}

	@Then("Delete the partially updated employee using the same global ID")
	public void delete_the_partially_updated_employee_using_the_same_global_ID() {
		request=given().header("Content-Type", "application/json").header("Authorization", TokenGenerationSteps.token).queryParam("employee_id", employeeID);
		response=request.when().delete(APIConstants.DELETE_EMPLOYEE_ENDPOINT);
		response.then().assertThat().body("message", equalTo("Entry deleted"));
		response.then().assertThat().statusCode(201);
	}

}
