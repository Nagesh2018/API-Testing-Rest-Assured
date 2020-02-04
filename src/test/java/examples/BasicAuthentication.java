package examples;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class BasicAuthentication{

	@Test
	public void basicAuth() {
		String uName ="ToolsQA";
		String pwd = "TestPassword";
		RestAssured.baseURI = "http://restapi.demoqa.com";
		
		ValidatableResponse resp =
		RestAssured.given()
		.log()
		.all()
		.auth()
		.basic(uName, pwd)
		.when()
		.get("/authentication/CheckForAuthentication")
		.then()
		.log()
		.all()
		.statusCode(200);
		
		System.out.println(" Response is " + resp.extract().asString());

	}
	
}
