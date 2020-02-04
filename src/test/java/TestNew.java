
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class TestNew {
	String token = "";
	String baseURL = "https://reqres.in/api";

	@SuppressWarnings("unchecked")
	@Test
	public void simpleGet() {
		RestAssured.baseURI = baseURL;
		JSONObject request = new JSONObject();
		request.put("email", "eve.holt@reqres.in");
		request.put("password", "pistol");
		Response resp = 
				given()
				.contentType("application/json")
				.body(request.toJSONString())
				.expect()
				.statusCode(200)
				.expect()
				.statusLine("HTTP/1.1 200 OK")
				.log().all().response()
				.when()
				.post("/register");
		int statusCode = resp.getStatusCode();
		if (statusCode == 200) {
			JsonPath jpath = resp.jsonPath();
			token = jpath.get("token");
			Assert.assertNotNull(token, "Token is Null");
		}
		System.out.println("Token is "+ token);
	}

	public void createUser() {
		
	}
	
}
