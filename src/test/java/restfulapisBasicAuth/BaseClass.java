package restfulapisBasicAuth;

import org.testng.annotations.BeforeClass;
import io.restassured.RestAssured;


public class BaseClass {
	String uName = "ToolsQA";
	String pwd = "TestPassword";
	
	@BeforeClass
	public void setup() {
		RestAssured.authentication = RestAssured.preemptive().basic(uName, pwd);
		RestAssured.baseURI= "http://restapi.demoqa.com";
	}
}
