package dependency;

import java.io.File;
import java.util.List;

import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.model.idea.IdeaProject;


/**
 * Handles dependencies from a Gradle project.
 */
public class GradleDependencyController {
	
	public List<Dependency> findDependencies(String pathToProject) {
		
		// This part is gotten from ChatGpt.
		 ProjectConnection connection = GradleConnector.newConnector()
	                .forProjectDirectory(new File(pathToProject))
	                .connect();

	        try {
	        	// Using IDE based project dependency listing
	            IdeaProject project = connection.getModel(IdeaProject.class);

	            project.getModules().forEach(module -> {
	                module.getDependencies().forEach(dep -> {
	                    System.out.println(dep);
	                });
	            });

	        } finally {
	            connection.close();
	        }
		return null;
	}

}
