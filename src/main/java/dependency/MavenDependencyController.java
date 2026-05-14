package dependency;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MavenDependencyController {
	
	private static final Logger log = LoggerFactory.getLogger(MavenDependencyController.class);
	
	/**
	 * Extracting Maven project dependencies.
	 * Requires mvn dependency:copy-dependencies to be run.
	 * @param pathToProject - Path to the project main folder
	 * @return - List<String> dependencies
	 */
	public List<Dependency> findDependencies(String pathToProject) {
		List<Dependency> dependencies = new ArrayList<>();
		File dependencyFolder = new File(pathToProject, "target/dependency");
		
		// Logging an error to the user, which indicates that mvn dependency:copy-dependencies has not been run.
		if(!dependencyFolder.exists()) {
			log.error("Maven project did not cotain: " + dependencyFolder.getAbsolutePath() + 
					"\nRemember to run mvn dependecy:copy-dependencies.");
		}
		
		// Going through every file in the given project main folder.
		File[] dependencyFiles = dependencyFolder.listFiles();
		for(File file : dependencyFiles) {
			if(file.getName().endsWith(".jar")) {
				Dependency dependency = new Dependency();
				// We only need the path to the file.
				dependency.setFilePath(file.getAbsolutePath());
				dependencies.add(dependency);
			}
		}
		
		return dependencies;
	}

}
