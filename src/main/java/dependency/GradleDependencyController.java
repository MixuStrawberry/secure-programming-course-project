package dependency;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.model.idea.IdeaDependency;
import org.gradle.tooling.model.idea.IdeaModule;
import org.gradle.tooling.model.idea.IdeaProject;


/**
 * Handles dependencies from a Gradle project.
 */
public class GradleDependencyController {
	
	public List<Dependency> findDependencies(String pathToProject) {
		
    	List<Dependency> resultDependencies = new ArrayList<>();
    	
    	// ══════════ AI generated example (Using Copilot), which is modified ══════════
		 ProjectConnection connection = GradleConnector.newConnector()
	                .forProjectDirectory(new File(pathToProject))
	                .connect();	 
        try {
        	// Using IDE based project dependency listing
            IdeaProject project = connection.getModel(IdeaProject.class);
    		// ══════════ End of AI generated example ══════════
            for(IdeaModule module : project.getModules()) {
            	for(IdeaDependency ideaDependency : module.getDependencies()) {
                    Dependency dependency = parseIdeaDependencyString(ideaDependency);
                    resultDependencies.add(dependency);
            	}
            }

        } finally {
            connection.close();
        }
		return resultDependencies;
	}
	
	
	/**
	 * Utility method which transforms the IdeaDependency objects into human readable format.
	 * @param dependency - IdeaDependency
	 * @return - Dependency object
	 */
	public Dependency parseIdeaDependencyString(IdeaDependency dependency)  {
		String dependencyString = dependency.toString();
		// Parsing group from the dependency String.
		int gourpStartIndex = dependencyString.indexOf("group='") + "group='".length();
		int groupEndIndex = dependencyString.indexOf(", name=") - 1;
		String group = dependencyString.substring(gourpStartIndex, groupEndIndex);
		Dependency resultDependency = new Dependency();
		resultDependency.setGroup(group);
		// Parsing name from the dependency String.
		int nameStartIndex = dependencyString.indexOf(", name='") + ", name='".length();
		int nameEndIndex = dependencyString.indexOf(", version=") - 1;
		String name = dependencyString.substring(nameStartIndex, nameEndIndex);
		resultDependency.setModule(name);
		// Parsing version from the dependency String.
		int versionStartIndex = dependencyString.indexOf(", version='") + ", version='".length();
		int versionEndIndex = dependencyString.indexOf("'}'}");
		String version = dependencyString.substring(versionStartIndex, versionEndIndex);
		resultDependency.setVersion(version);
		// Parsing jar file location.
		int fileLocationStartIndex = dependencyString.indexOf("{file=") + "{file=".length();
		int fileLocationEndIndex = dependencyString.indexOf(", source=");
		String fileLocation = dependencyString.substring(fileLocationStartIndex, fileLocationEndIndex);
		resultDependency.setFilePath(fileLocation);
		return resultDependency;
	}

}
