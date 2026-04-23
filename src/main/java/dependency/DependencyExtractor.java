package dependency;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Archive.JavaArchiveReader;

public class DependencyExtractor {
	
	private static final Logger logger = LoggerFactory.getLogger(DependencyExtractor.class);

	public Set<Dependency> extractDependencies(String input) {
		logger.info("Beginning to scan the project");
		if(input.endsWith(".jar")) {
			return transformInputToDependency(input);
		}
		JavaArchiveReader reader = new JavaArchiveReader();
		List<File> projectFiles = reader.findAllFiles(input);
		Set<Dependency> projectDependencies = new HashSet<>();
		for(File file : projectFiles) {
			logger.debug(file.toString());
			if("build.gradle".equals(file.getName())) {
				GradleDependencyController gradleDependencyController = new GradleDependencyController();
				projectDependencies.addAll(gradleDependencyController.findDependencies(input));
			} else if(file.getName().endsWith(".pom")) {
				// TODO Käytä Maven projektin riippuvuusskanneria
				MavenDependencyController mavenDependencyController = new MavenDependencyController();
				projectDependencies.addAll(mavenDependencyController.findDependencies(file.getAbsolutePath()));
			}
		}
		return projectDependencies;
	}
	
	
	private Set<Dependency> transformInputToDependency(String input) {
		Dependency dependency = new Dependency();
		dependency.setFilePath(input);
		return new HashSet<>(Arrays.asList(dependency));
	}
}
