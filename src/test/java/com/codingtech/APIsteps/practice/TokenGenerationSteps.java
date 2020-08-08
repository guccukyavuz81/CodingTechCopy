package com.codingtech.APIsteps.practice;

import static io.restassured.RestAssured.*;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TokenGenerationSteps {
	
	public static String token;
	/**
	 * BaseURI
	 */
	String BaseURI = RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";
	
	@Given("a JWT is generated")
	public void a_JWT_is_generated() {
	    
		RequestSpecification generateTokenRequest = given().header("Content-Type", "application/json")
				.body("{\r\n" + 
						"  \"email\": \"ilyas@gmail.com\",\r\n" + 
						"  \"password\": \"ilyas123\"\r\n" + 
						"}");
		
		Response generateTokenResponse = generateTokenRequest.when().post("/generateToken.php");
		
		//generateTokenResponse.prettyPrint();
		
		/**
		 * Retrieving token from generate response body and concatenating "Bearer "
		 */
		token ="Bearer "+ generateTokenResponse.body().jsonPath().getString("token");
		
		/**
		 * Optional to print out token
		 */
		//System.out.println(token);
		
	}
}
