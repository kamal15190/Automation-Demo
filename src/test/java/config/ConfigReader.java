package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	static Properties prop;
	String propFileName = "Config.properties";
	String value;

	public String getConfigValue(String key) throws IOException {

		String filePath = System.getProperty("user.dir")+"\\src\\test\\java\\config\\"+propFileName;

		try {
			FileInputStream fis = new FileInputStream(filePath);

			prop = new Properties();

			prop.load(fis);

			value = prop.getProperty(key);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;

	}

}
