package utils;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {
	
	private static JsonNode testData;
	
	static {
		try {
			ObjectMapper mapper=new ObjectMapper();
			testData=mapper.readTree(new File("src/test/resources/testdata.json"));
		}
		catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static String getValue(String section,String key) {
		return testData.get(section).get(key).asText();
	}

}
