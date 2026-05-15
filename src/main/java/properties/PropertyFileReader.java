package properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyFileReader {
	
	private static final Logger logger = LoggerFactory.getLogger(PropertyFileReader.class);
	
	public static Properties readProperties(String propertiesFileName) throws IOException {
		Properties properties = new Properties();
		try(FileInputStream inputStream = new FileInputStream(propertiesFileName)) {
			properties.load(inputStream);
		} catch(IOException e) {
			logger.error("Could not read dependencyScanner.properties.");
		}
		return properties; 
	}
}
