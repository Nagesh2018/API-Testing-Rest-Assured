package oAuth2;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class OAuth2 extends BaseClassOauth2 {

	@Test(priority = 1)
	public void validAPI() {
		/* Response response = RestAssured.given().formParam("client_id", "4TestOAuth")
		 * .formParam("client_secret", "c1ebc9e69008f3a9695811e653246bd0")
		 * .formParam("grant_type", "client_credentials")
		 * .post("http://coop.apps.symfonycasts.com/token"); String token =
		 * response.jsonPath().get("access_token");
		 */
		//		logger.info("Logger Started ");
		ValidatableResponse resp = RestAssured
				.given()
				.log()
				.all()
				.auth()
				.oauth2(token)
				.post("/api/656/barn-unlock")
				.then()
				.log()
				.all();
		Assert.assertEquals(resp.extract().statusCode(), 200);
		Assert.assertTrue(resp.extract().time()<3000);
		System.out.println("***********************************");
		logger.info("validAPI Response is " +  resp.extract().body().asString());
	}

	@Test(priority = 2)
	public void invalidAPI() {

		ValidatableResponse resp1 =  RestAssured
				.given()
				.log()
				.all()
				.auth()
				.oauth2(token)
				.post("/api/656/eggs-collect")
				.then()
				.log()
				.all();
		System.out.println("Status code is "+ resp1.extract().statusCode());
		System.out.println("Response2 is "+ resp1.extract().body().asString());
		//		logger.info("Logger Ended ");
		Assert.assertEquals(resp1.extract().statusCode(), 200);
		System.out.println("***********************************");
	}


}
