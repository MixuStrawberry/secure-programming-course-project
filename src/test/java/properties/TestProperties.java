package properties;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.Properties;

import org.junit.jupiter.api.Test;

public class TestProperties {

	@Test
	public void testPropertiesShouldContainScanForVulnerabilitiesKey() {
		try {
			Properties testProperties = PropertyFileReader.readProperties("testDependencyScanner.properties");
			assertTrue(testProperties.containsKey("scanForVulnerabilities"));
		} catch (IOException e) {
			fail("Could not read properties.");
		}
	
	}
	
}
