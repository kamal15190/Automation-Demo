package stepdefinition;

import org.testng.Assert;

import config.ConfigReader;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetLatestLaunchDetails {

	Response response;
	Scenario scenarioContext;
	RequestSpecification request;
	ConfigReader config;

	@Given("I have Resources {string} and Header {string}")
	public void i_have_resources_and_header(String hostURL, String header) {
		// Write code here that turns the phrase above into concrete actions

		RestAssured.baseURI = hostURL;
		request = RestAssured.given();

		request.header("Content-type", "Application/json");
		request.header("accept", "Application/json");

	}

	@Given("I send  a {string} call")
	public void i_send_a_call(String string) {

		response = request.get();

	}

	@Then("I expect status code {string}")
	public void i_expect_status_code(String statusCodeParam) {

		JsonPath jsonPath = response.jsonPath();

		int statuscode = Integer.parseInt(statusCodeParam);
		int statusCodeFromResponse = response.getStatusCode();

		Assert.assertEquals(statuscode, statusCodeFromResponse);

	}

}
