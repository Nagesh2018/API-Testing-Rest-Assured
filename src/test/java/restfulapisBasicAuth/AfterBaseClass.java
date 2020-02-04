package restfulapisBasicAuth;

import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class AfterBaseClass extends BaseClass {

	@Test
	public void simpleGet() {
		int respCode = RestAssured.given()
				.get("/authentication/CheckForAuthentication")
				.getStatusCode();

		System.out.println("Response Code is " + respCode);
	}

}
