package com.thinktimetechno.projects.website.Shrido.stepdefinitions;

import java.io.IOException;


import com.thinktimetechno.Shrido.endpoints.User_EndPoints;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

public class UserStepDefinitions {
	
	private User_EndPoints user;
	
	public UserStepDefinitions(User_EndPoints user) {
		this.user = user;
	}

	
	@Given("The User sets the base URL as {string}")
	public void the_user_sets_the_base_url_as(String baseUrl) {
		 RestAssured.baseURI = baseUrl;
	    
	}
	@When("The User sends a POST request with the request body from {string} and captures the response body")
	public void the_user_sends_a_post_request_with_the_request_body_from_and_captures_the_response_body(String jsonFile) throws IOException {
		user.sendPostRequestWithPayload(jsonFile);
	}
	
	@When("The User sends a GET request from {string} and captures the response body")
	public void the_user_sends_a_get_request_from_and_captures_the_response_body(String name) {
		user.sendGetRequest(name);
	}
	
	@Then("The response code should be {string}")
	public void the_response_code_should_be(String value) {
		int intValue = Integer.parseInt(value);
		user.verifyResponseStatusValue(user.result, intValue);
	}
	@Then("The response body should contain the following key-value pairs:")
	public void the_response_body_should_contain_the_following_key_value_pairs(DataTable dataTable) {
        user.verifyResponseKeyValuePairs(dataTable, user.result);
	}
}
