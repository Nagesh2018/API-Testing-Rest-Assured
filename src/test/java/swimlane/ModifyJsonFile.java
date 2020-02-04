package swimlane;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ModifyJsonFile {


	public static void main(String[] args) {
		ModifyJsonFile modFile = new ModifyJsonFile();
		modFile.updateJsonIntField("src/test/java/testdata/TC_11.json", "values.acCmcIfWkTmdRzp5g", 240);
//		modFile.getP();
	}
	
	public void getP() {
		Path currentDir = Paths.get(".");
		System.out.println(currentDir.toAbsolutePath());
	}
	
	@SuppressWarnings("unchecked")
	public void updateJsonIntField(String filePath, String varToChange, int varVal) {
		String fileName = "src/test/java/testdata/TC_11.json";
		try (Reader reader = new FileReader(filePath)) {
			// Read JSON file
			JSONParser parser = new JSONParser();
			JSONObject data = (JSONObject) parser.parse(reader);
			 if (data.containsKey(varToChange)) {
				 System.out.println("Has Required Data");
					data.put(varToChange, varVal);
					@SuppressWarnings("resource")
					FileWriter file = new FileWriter(filePath);
					file.write(data.toJSONString());
					file.flush();
			 }
			 else {
				 System.out.println("Required Key Not Found");
			 }
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void modifyjson() {
		String fileName = "src/test/java/testdata/TC_11.json";
		try (Reader reader = new FileReader(fileName)) {
			// Read JSON file
			JSONParser parser = new JSONParser();
			JSONObject data = (JSONObject) parser.parse(reader);
			data.put("key1", 4566);
			@SuppressWarnings("resource")
			FileWriter file = new FileWriter(fileName);
			file.write(data.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}
}
