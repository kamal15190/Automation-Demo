package test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import config.ConfigReader;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SpaceXAPIScenarios {

	Response response;
	Scenario scenarioContext;
	RequestSpecification request;
	ConfigReader config;
	public static final String BASE_URI = "https://api.spacexdata.com";
	
	/* All the value which is not going change we can get from Config.properties file */

	@Test(priority = 0)
	public void get_SpaceX_Latest_Launch_Details() throws IOException {

		config = new ConfigReader();
		RestAssured.baseURI = BASE_URI;
		request = RestAssured.given();

		request.header("Content-type", "Application/json");
		request.header("accept", "Application/json");

		response = request.get("/v4/launches/latest");

		Assert.assertEquals(response.getStatusCode(), 200, "Status code must be 200 for successfull..!");

	}

	@Test(priority = 2)
	public void validate_SpaceX_Launchpad_Detail() {

		JsonPath jsonPath = response.jsonPath();

		int date_unix = jsonPath.getInt("date_unix");
		String launchpad = jsonPath.get("launchpad");

		int flight_number = jsonPath.getInt("flight_number");
		String name = jsonPath.getString("name");

		/* All the assertion expected value can be read from Config file */

		Assert.assertEquals(date_unix, 1639029600, "Date Unix value is not Correct..!");

		Assert.assertEquals(launchpad, "5e9e4502f509094188566f88", "Launchpad value is not correct..!");

		Assert.assertEquals(flight_number, 140, "Flight Number is not Correct..!");

		Assert.assertEquals(name, "Starlink 4-3 (v1.5)", "Name is not Correct..!");

	}

	@Test(priority = 3)
	public void validate_SpaceX_Successfull_Launch() {

		JsonPath jsonPath = response.jsonPath();

		boolean successBooleanValue = jsonPath.get("success");
		String rocketValue = jsonPath.get("rocket");

		Assert.assertEquals(successBooleanValue, true, "Successfull boolean value is False..!");

		Assert.assertEquals(rocketValue, "5e9d0d95eda69973a809d1ec", "Rocket Value is worng..!");

	}

	@Test(priority = 4)
	public void validate_SpaceX_Live_Streaming_Sources() {

		JsonPath jsonPath = response.jsonPath();

		String webcast = jsonPath.get("links.webcast");
		System.out.println(webcast);
		String youtube_id = jsonPath.get("links.youtube_id");
		String wikipedia = jsonPath.get("links.wikipedia");
		String presskit = jsonPath.get("links.presskit");

		/* All the expected value must be taken from Config File */

		Assert.assertEquals(webcast, "https://youtu.be/CpmHsN5GUn8", "WebCast URL is wrong..!");

		Assert.assertEquals(youtube_id, "CpmHsN5GUn8", "Youtube Id is wrong..!");

		Assert.assertEquals(wikipedia, "https://en.wikipedia.org/wiki/IXPE", "Wikipedia URL is wrong..!");

		Assert.assertEquals(presskit, null, "Presskit is not Null..!");

	}

}
