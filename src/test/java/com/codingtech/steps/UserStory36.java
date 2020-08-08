package com.codingtech.steps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.entity.ContentType;
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


public class UserStory36 {
	
	String BaseURI = RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";
	RequestSpecification request;
	Response response;
	public static String employeeID;

	
	@Given("a request is prepared to create an employee")
	public void a_request_is_prepared_to_create_an_employee() {
		request = given().header("Content-Type", "application/json").header("Authorization", TokenGenerationSteps.token)
				.body(PayloadConstants.createEmployeePayload());
	}

	@When("a POST call is made to create an employee")
	public void a_POST_call_is_made_to_create_an_employee() {
		response = request.when().post(APIConstants.CREATE_EMPLOYEE_URI_ENDPOINT);

	}

	@Then("the status code for creating an employee is {int}")
	public void the_status_code_for_creating_an_employee_is(int statusCode) {
		response.then().assertThat().statusCode(statusCode);
	}


//	@Then("the employee is created and response contains key {string} and value {string}")
//	public void the_employee_is_created_and_response_contains_key_and_value(String key, String value) {
//		response.then().assertThat().body(key, equalTo(value));
//
//	}

	@Then("the employee is created and response contains key {string} and value {string}")
	public void the_employee_is_created_and_response_contains_key_and_value(String key, String value) {
		response.then().assertThat().body(key, equalTo(value));

	}


	@Then("the employee ID {string} is stored as a global variable to be used for other calls")
	public void the_employee_ID_is_stored_as_a_global_variable_to_be_used_for_other_calls(String empID) {
		employeeID = response.body().jsonPath().getString(empID);
		System.out.println(employeeID);

	}

	@Given("a request is prepared to retrieve the created employee")
	public void a_request_is_prepared_to_retrieve_the_created_employee() {

		request = given().header("Content-Type", ContentType.APPLICATION_JSON)
				.header("Authorization", TokenGenerationSteps.token).queryParam("employee_id", employeeID);

	}

	@When("a GET call is made to retrieve the created employee")
	public void a_GET_call_is_made_to_retrieve_the_created_employee() {
		response = request.when().get(APIConstants.GET_ONE_EMPLOYEE_ENDPOINT);

	}

	@Then("the status code for retrieving the created employee is {int}")
	public void the_status_code_for_retrieving_the_created_employee_is(int StatusCode) {

		response.then().assertThat().statusCode(StatusCode);

	}

	@Then("the retrieved emplyee ID matches the globally stored employee ID")
	public void the_retrieved_emplyee_ID_at_matches_the_globally_stored_employee_ID() {
		String empID = response.body().jsonPath().getString("employee[0].employee_id");
		Assert.assertTrue(empID.contentEquals(employeeID));

	}

	@Then("the retrieved data matches the data used to create an employee with employee ID")
	public void the_retrieved_data_at_matches_the_data_used_to_create_an_employee_with_employee_ID(DataTable dataTable) {
		
		List<Map<String,String>> expectedData = dataTable.asMaps();
		List<Map<String,String>> actualData = response.body().jsonPath().get("employee");
		

		int index = 0;
		
	for (Map<String, String> map : expectedData) {
			Set<String> keys = map.keySet();
			for (String key : keys) {
				String expectedValue = expectedData.get(index).get(key);
				String acutalValue = actualData.get(index).get(key);
				System.out.println(expectedValue+"  "+acutalValue);
				Assert.assertEquals(expectedValue, acutalValue);
		}
			index++;
		}
		String ID = response.body().jsonPath().getString("employee[0].employee_id");
		Assert.assertTrue(ID.contentEquals(employeeID));

	}

	@Given("a request is prepared to update the created employee")
	public void a_request_is_prepared_to_update_the_created_employee() {

		request = given().header("Content-Type", "application/json").header("Authorization", TokenGenerationSteps.token)
				.body(PayloadConstants.updateCreatedEmployeePayload());

	}

	@When("a PUT call is made to update the created employee")
	public void a_PUT_call_is_made_to_update_the_created_employee() {
		response = request.when().put(APIConstants.UPDATE_EMPLOYEE_ENDPOINT);
	}


//	@Then("the status code for putting the created employee is {int}")
//	public void the_status_code_for_putting_the_created_employee_is(int StatusCode) {
//
//		response.then().assertThat().statusCode(StatusCode);
//		response.then().assertThat().body("Message", equalTo("Entry updated"));
//	}

	@Then("the status code for putting the created employee is {int}")
	public void the_status_code_for_putting_the_created_employee_is(int StatusCode) {

		response.then().assertThat().statusCode(StatusCode);
		response.then().assertThat().body("Message", equalTo("Entry updated"));
	}


	@Given("a request is prepared to partially update the created employee")
	public void a_request_is_prepared_to_partially_update_the_created_employee() {

		request = given().header("Content-Type", "application/json").header("Authorization", TokenGenerationSteps.token)
				.body(PayloadConstants.PupdateCreatedEmployeePayload());
	}

	@When("a PATCH call is made to partially update the created employee")
	public void a_PATCH_call_is_made_to_partially_update_the_created_employee() {

		response = request.when().patch(APIConstants.PARTIAL_UPDATE_EMPLOYEE_ENDPOINT);

	}

	@Then("the status code for putting the partially updated employee is {int}")
	public void the_status_code_for_putting_the_partially_updated_employee_is(int StatusCode) {

		response.prettyPrint();
		response.then().assertThat().body("employee[0].employee_id", equalTo(employeeID));

		response.then().assertThat().statusCode(StatusCode);
		response.then().assertThat().body("Message", equalTo("Entry updated"));

	}

	@Given("a request is prepared to delete the created employee")
	public void a_request_is_prepared_to_delete_the_created_employee() {

		request = given().header("Content-Type", "application/json").header("Authorization", TokenGenerationSteps.token)
				.queryParam("employee_id", employeeID);
	}

	@When("a DELETE call is made to delete the created employee")
	public void a_DELETE_call_is_made_to_delete_the_created_employee() {

		response = request.when().delete(APIConstants.DELETE_EMPLOYEE_ENDPOINT);

	}

	@Then("the status code for deleting the created employee is {int}")
	public void the_status_code_for_deleting_the_created_employee_is(int StatusCode) {

		response.then().assertThat().statusCode(StatusCode);
		response.then().assertThat().body("message", equalTo("Entry deleted"));

	}
	


}
