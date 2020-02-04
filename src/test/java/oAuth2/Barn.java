package oAuth2;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class Barn extends BaseClassOauth2{
	@Test(priority = 1)
	public void collectEggs() {
		ValidatableResponse resp = 
		RestAssured.given()
		.log()
		.all()
		.auth()
		.oauth2(token)		
		.post("/api/656/eggs-count")
		.then()
		.log()
		.all();
		System.out.println(resp.extract().asString());
		int collectEggs = resp.extract().jsonPath().get("data");
		Assert.assertTrue(collectEggs > 9 );
		System.out.println("***********************************");

	}
}
