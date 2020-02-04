package oAuth2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import org.apache.commons.io.output.WriterOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.response.Response;


public class BaseClassOauth2 {
    public static Logger logger = LogManager.getLogger(BaseClassOauth2.class);
	public FileInputStream fis;
	public Properties prop;
	String token="";


	
	public BaseClassOauth2() {
		try {
			fis = new FileInputStream("EnvironmentDetails.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		prop = new Properties();
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeTest
	public void testDataSetup() {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("./logging.log");
		} catch (IOException e) {
			e.printStackTrace();
		}
		@SuppressWarnings("deprecation")
		PrintStream printStream = new PrintStream(new WriterOutputStream(fileWriter), true);
	    RestAssured.config = RestAssured.config().logConfig(LogConfig.logConfig().defaultStream(printStream));
	}
	
	/*
	 * @BeforeClass public void getToken() { Response response =
	 * RestAssured.given().formParam("client_id", "4TestOAuth")
	 * .formParam("client_secret", "c1ebc9e69008f3a9695811e653246bd0")
	 * .formParam("grant_type", "client_credentials")
	 * .post("http://coop.apps.symfonycasts.com/token"); token =
	 * response.jsonPath().get("access_token"); }
	 */
	
	
	@BeforeClass
	@Parameters("environment")
	public void getTokenEnv(String env) {
		RestAssured.baseURI = prop.getProperty(env+"_BaseURI");
//		getEnvDetails();
		Response response = RestAssured.given().formParam("client_id", prop.getProperty(env+"_client_id"))
				.formParam("client_secret", prop.getProperty(env+"_client_secret"))
				.formParam("grant_type", prop.getProperty(env+"_grant_type"))
				.post(prop.getProperty(env+"_URL"));
//		System.out.println("Respo is " + response.asString());
		token = response.jsonPath().get("access_token");
		System.out.println("Token is " + token);
	}

	
	@AfterSuite
	public void tearDown() throws IOException {
		fis.close();
	}

}
