//package code.sast;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.PrintStream;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.esotericsoftware.minlog.Log;
//
//import edu.umd.cs.findbugs.DetectorFactoryCollection;
//import edu.umd.cs.findbugs.FindBugs2;
//import edu.umd.cs.findbugs.Priorities;
//import edu.umd.cs.findbugs.Project;
//import edu.umd.cs.findbugs.XMLBugReporter;
//import edu.umd.cs.findbugs.config.UserPreferences;
//
//public class SpotbugsCodeAnalyzer {
//	
//	private static final Logger logger = LoggerFactory.getLogger(SpotbugsCodeAnalyzer.class);
//	
//	/**
//	 * Checks the given path to project source.jar with Spotbugs static code analysis tool
//	 * @param pathToProjectSource - path to the project source.jar / main.jar
//	 */
//	public void checkProject(String pathToProjectSource, String reportPath) {
//		Project project = new Project();
//		
//		project.addFile(pathToProjectSource);
//		
//		// ══════════ AI generated example (Using Copilot), which is modified and checked ══════════
//		DetectorFactoryCollection detectorFactoryCollection =
//		        DetectorFactoryCollection.instance();
//		
//		FindBugs2 spotbugsEngine = new FindBugs2();
//		
//		spotbugsEngine.setDetectorFactoryCollection(detectorFactoryCollection);
//		
//        // Configure analysis
//		UserPreferences config = UserPreferences.createDefaultUserPreferences();
//		spotbugsEngine.setUserPreferences(config);
//		
//		// Setting project to the spotbugs engine
//        spotbugsEngine.setProject(project);
//        // ══════════ End of AI generated example ══════════
//        
//        
//        // Setting a priority which tells spotbugs to show bugs rated Normal or higher
//		int normalPriority = Priorities.NORMAL_PRIORITY;
//		
//		// Creating a new BugReported in order to specify the priority threshold
//        XMLBugReporter bugCollectionBugReporter = new XMLBugReporter(project);
//        try (PrintStream out = new PrintStream(new File(reportPath))) {
//            bugCollectionBugReporter.setOutputStream(out);
//        } catch(Exception e) {
//        	Log.error("Could not write report into: " + reportPath);
//        }
//        bugCollectionBugReporter.setPriorityThreshold(normalPriority);
//        
//        // Settings the new BugReported to the spotbugs engine
//        spotbugsEngine.setBugReporter(bugCollectionBugReporter);
//        
//        try {
//        	// Executing engine, this method starts the actual bug check
//			spotbugsEngine.execute();
//		} catch (Exception e) {
//			logger.error("Could not read given project jar file.", e);
//		}
//        
//        try {
//			spotbugsEngine.getBugReporter().getBugCollection().writeXML(reportPath);
//		} catch (IOException e) {
//			Log.error("Could not write report into: " + reportPath);
//		}
//        // Spotbugs engine needs to be closed
//        spotbugsEngine.close();
//
//	}
//
//}
package code.sast;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.minlog.Log;

import dependency.Vulnerability;
import edu.umd.cs.findbugs.BugCollection;
import edu.umd.cs.findbugs.BugCollectionBugReporter;
import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.DetectorFactoryCollection;
import edu.umd.cs.findbugs.FindBugs2;
import edu.umd.cs.findbugs.Priorities;
import edu.umd.cs.findbugs.Project;
import edu.umd.cs.findbugs.config.UserPreferences;

public class SpotbugsCodeAnalyzer {
	
	private static final Logger logger = LoggerFactory.getLogger(SpotbugsCodeAnalyzer.class);
	
	/**
	 * Checks the given path to project source.jar with Spotbugs static code analysis tool
	 * @param pathToProjectSource - path to the project source.jar / main.jar
	 */
	public List<Vulnerability> checkProject(String pathToProjectSource) {
		Project project = new Project();
		
		project.addFile(pathToProjectSource);
		
		// ══════════ AI generated example (Using Copilot), which is modified and checked ══════════
		DetectorFactoryCollection detectorFactoryCollection =
		        DetectorFactoryCollection.instance();
		
		FindBugs2 spotbugsEngine = new FindBugs2();
		
		spotbugsEngine.setDetectorFactoryCollection(detectorFactoryCollection);
		
        // Configure analysis
		UserPreferences config = UserPreferences.createDefaultUserPreferences();
		spotbugsEngine.setUserPreferences(config);
		
		// Setting project to the spotbugs engine
        spotbugsEngine.setProject(project);
        // ══════════ End of AI generated example ══════════
        
        
        // Setting a priority which tells spotbugs to show bugs rated Normal or higher
		int normalPriority = Priorities.NORMAL_PRIORITY;
		
		// Creating a new BugReported in order to specify the priority threshold
        BugCollectionBugReporter bugCollectionBugReporter = new BugCollectionBugReporter(project);
        bugCollectionBugReporter.setPriorityThreshold(normalPriority);
        
        // Settings the new BugReported to the spotbugs engine
        spotbugsEngine.setBugReporter(bugCollectionBugReporter);
        
        try {
        	// Executing engine, this method starts the actual bug check
			spotbugsEngine.execute();
		} catch (Exception e) {
			logger.error("Could not read given project jar file.", e);
		}
        
        BugCollection bugCollection = spotbugsEngine.getBugReporter().getBugCollection();
        List<Vulnerability> projectIssues = new ArrayList<>();
        if(bugCollection != null) {
	        for(BugInstance bug : bugCollection.getCollection()) {
	        	String priorityString = priorityIntegerToString(bug.getPriority());
	        	projectIssues.add(new Vulnerability(
	        			"Spotbugs",
	        			bug.getType(),
	        			priorityString,
	        			bug.getPrimaryClass().toString(),
	        			bug.getMessageWithoutPrefix()));
	        	
	        }
        }
        // Spotbugs engine needs to be closed
        spotbugsEngine.close();
        
        return projectIssues;
	}
	
	
	private String priorityIntegerToString(int priority) {
		if(priority < 4) {
			return "HIGH";
		} else if(priority > 4 && priority < 10) {
			return "MEDIUM";
		} else if(priority > 9 && priority < 15) {
			return "LOW";
		} else if(priority > 14 && priority < 21) {
			return "INFORMATION";
		} else {
			return "UKNOWN";
		}
	}

}
