package swimlane;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ModifyJson {
	public static void main(String[] args) throws IOException {
		modify("src/test/java/testdata/TC_11.json", "acCmcIfWkTmdRzp5g", 240);
    }

	public static void modify(String pathFile,String varToChange, int varVal) throws IOException {
        FileInputStream inFile = new FileInputStream(pathFile);
        String filePath = pathFile;
        byte[] str = new byte[inFile.available()];
        inFile.read(str);
        String string = new String(str);
        JSONObject json = ModifyJson.createJSONObject(string);
        System.out.println(ModifyJson.replacekeyInJSONObject(json,varToChange,varVal));
        FileWriter file = new FileWriter(filePath);
		file.write(json.toJSONString());
		file.flush();
		file.close();
        inFile.close();
	}
    @SuppressWarnings({ "unchecked" })
	private static JSONObject replacekeyInJSONObject(JSONObject jsonObject, String jsonKey,
            int jsonValue) {

        for (Object key : jsonObject.keySet()) {
            if (key.equals(jsonKey) && ((jsonObject.get(key) instanceof String)||(jsonObject.get(key) instanceof Number)||jsonObject.get(key) ==null)) {
                jsonObject.put(key, jsonValue);
                return jsonObject;
            } else if (jsonObject.get(key) instanceof JSONObject) {
                JSONObject modifiedJsonobject = (JSONObject) jsonObject.get(key);
                if (modifiedJsonobject != null) {
                    replacekeyInJSONObject(modifiedJsonobject, jsonKey, jsonValue);
                }
            }
        }
        return jsonObject;
    }

    private static JSONObject createJSONObject(String jsonString){
        JSONObject  jsonObject=new JSONObject();
        JSONParser jsonParser=new  JSONParser();
        if ((jsonString != null) && !(jsonString.isEmpty())) {
            try {
                jsonObject=(JSONObject) jsonParser.parse(jsonString);
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }
}
