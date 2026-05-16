package help;

/**
 * A helper class, which is responsible for building out help information.
 */
public class HelpBuilder {

	/**
	 * Utility method, which returns help information as String.
	 * @return - String consisting of help information.
	 */
	public static String createHelpString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("--------------------\n");
		stringBuilder.append("Help for Secure Programming (COMP.SEC.300) course project: \n");
		stringBuilder.append("To start the program: type or paste a Gradle or Maven main project folder \nor a single Jar file.\n");
		stringBuilder.append("--------------------\n");
		stringBuilder.append("NOTICE! Remember to configure dependencyScanner.properties file!\n");
		stringBuilder.append("Properties file should at least include your own NVD API key and path where the results are saved.\nNew NVD API key can be requested from https://nvd.nist.gov/developers/request-an-api-key\n");
		stringBuilder.append("--------------------\n");
		stringBuilder.append("For Gradle projects remember to run: gradle classes before using this program.\n");
		stringBuilder.append("For Maven projects remember to run: mvn compile and mvn dependency:copy-dependencies before using this program.\n");
		stringBuilder.append("--------------------\n");
		
		return stringBuilder.toString();
	}
}
