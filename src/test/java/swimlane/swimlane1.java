package swimlane;

import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class swimlane1 {
	String token ="";
	String URL = "https://qa-practical.swimlane.io";
	String randAlphabetic ="";
	
	@SuppressWarnings("unchecked")
	@BeforeClass
	public void LoginToApp() {
		RestAssured.baseURI = URL;
		JSONObject params = new JSONObject();
		params.put("username", "qaanalyst");params.put("password", "AuToMaTeN0w!");
		Response resp = 
				given()
				.contentType("application/json")
				.body(params.toJSONString())
//				.log()
//				.all()
				.when()
				.post("/api/user/login");
		System.out.println("Response is "+ resp.asString());
		System.out.println("Token is "+ resp.jsonPath().get("token"));
		token =  resp.jsonPath().get("token");
	}
	
	public String genRandAlphabetic(int alphaCount) {
		return RandomStringUtils.randomAlphabetic(alphaCount);
	}
	
	@SuppressWarnings({ "unused", "static-access" })
	@Test
	public void createUser() throws IOException {	
		RestAssured.baseURI = URL;
		String name1 = "USER240";String name2 = "LUSER240";
		randAlphabetic = genRandAlphabetic(17);
		String add1 = "USER240 Address";
		int empID = 30; int pinCode=28;
		ModifyJson modFile = new ModifyJson();
		modFile.modify("src/test/resources/Payload/sampleRequest.json", "acCmcIfWkTmdRzp5g", 240);
		
		
//	    File file = new File(getClass().getClassLoader().getResource("Payload/sampleRequest.json").getFile());
//		File file = new File("src/test/resources/Payload/sampleRequest.json");
		FileInputStream fis = new FileInputStream("src/test/resources/Payload/sampleRequest.json");
	   Response resp = 
	    		given()
	    		.auth()
	    		.oauth2(token)
	    		.contentType("application/json")
	    		.body(fis)
	    		.log()
				.all()
				.when()
				.post("/api/app/ad3EgmX56uq8FC5SJ/record");
	   
	    		System.out.println("Response is "+ resp.asString());
	    		System.out.println("Tracking ID is "+ resp.jsonPath().get("trackingId"));
	    		System.out.println("Tracking Name is "+ resp.jsonPath().get("name"));
	    		System.out.println("Tracking Full ID is "+ resp.jsonPath().get("trackingFull"));

	}
	
	
	
}
