package com.codingtech.steps;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;

import com.codingtech.API.utils.APIConstants;
import com.codingtech.API.utils.PayloadConstants;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserStory33 {

	String BaseURI = RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";
	RequestSpecification request;
	Response response;
	List<Map<String,String>> responseData;
	public static String ID;
	
	@Given("I prepare a put call to update an existing employee with employee ID {string}")
	public void i_prepare_a_put_call_to_update_an_existing_employee_with_employee_ID(String employeeID) {
	
		ID = employeeID;
	//	request=given().header("Content-Type", "application/json").header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1OTY2ODkyNjEsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTU5NjczMjQ2MSwidXNlcklkIjoiNzExIn0.AfuMesWrSxpGqmrphZH-lAq_n_ciz4BhlIvJ8ESgJYY").body(PayloadConstants.updateCreatedEmployeePayload33());
		request=given().header("Content-Type", "application/json").header("Authorization", TokenGenerationSteps.token).body(PayloadConstants.updateCreatedEmployeePayload33());

	}

	@Given("I make a put call to update the employee")
	public void i_make_a_put_call_to_update_the_employee() {
		
		response=request.when().put(APIConstants.UPDATE_EMPLOYEE_ENDPOINT);
	}

	@When("I get information on the updated employee")
	public void i_get_information_on_the_updated_employee() {
	    
	responseData =	response.body().jsonPath().get("employee");
	response.prettyPrint();
	}

	@Then("I need to be able to verify that the retrieved data matches the input")
	public void i_need_to_be_able_to_verify_that_the_retrieved_data_matches_the_input(DataTable dataTable) {
	    
	List<Map<String,String>> expectedData = dataTable.asMaps();
	
	int index=0;
	for(Map<String,String> mapData:expectedData) {
	Set<String>	keys= mapData.keySet();
	for(String key:keys) {
	String expectedValue = expectedData.get(index).get(key);
	String updatedValue = responseData.get(index).get(key);
	System.out.println(expectedValue+"  "+updatedValue);
	 Assert.assertTrue(expectedValue.contentEquals(updatedValue));
	}
	}
	index++;
		
	}


	
	
	
}
