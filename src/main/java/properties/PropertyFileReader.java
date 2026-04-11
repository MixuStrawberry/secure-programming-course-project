package properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader {
	
	public static Properties readProperties(String propertiesFileName) throws IOException {
		Properties properties = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = loader.getResourceAsStream(propertiesFileName);
		if(inputStream != null) {
			properties.load(inputStream);
		}
		return properties;
	}
}
