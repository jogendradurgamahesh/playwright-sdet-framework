//package utils;
//
//public class ConfigReader {
//
//}

package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private static Properties property;
	//private  Properties property;

	static {
		try {
			FileInputStream fis=new FileInputStream("src/test/resources/config.properties");
			property=new Properties();
			property.load(fis);
			fis.close();

		}catch (IOException e) {
			e.printStackTrace();
		}	
	}

	  public static String getProperty(String key) {
	        return property.getProperty(key);
	    }
}
