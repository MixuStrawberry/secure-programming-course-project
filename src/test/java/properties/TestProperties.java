package properties;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.junit.jupiter.api.Test;

public class TestProperties {

	@Test
	public void testPropertiesShouldContainScanForVulnerabilitiesKey() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("testDependencyScanner.properties").getFile());
			Properties testProperties = PropertyFileReader.readProperties(file.toString());
			assertTrue(testProperties.containsKey("scanForVulnerabilities"));
		} catch (IOException e) {
			fail("Could not read properties.");
		}
	
	}
	
}
