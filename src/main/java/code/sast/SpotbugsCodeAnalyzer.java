package code.sast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.minlog.Log;

import edu.umd.cs.findbugs.BugCollectionBugReporter;
import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.DetectorFactoryCollection;
import edu.umd.cs.findbugs.FindBugs2;
import edu.umd.cs.findbugs.Priorities;
import edu.umd.cs.findbugs.Project;
import edu.umd.cs.findbugs.config.UserPreferences;

public class SpotbugsCodeAnalyzer {
	
	private static final Logger logger = LoggerFactory.getLogger(SpotbugsCodeAnalyzer.class);
	
	public static void main(String[] args) {
		SpotbugsCodeAnalyzer analyzer = new SpotbugsCodeAnalyzer();
		analyzer.checkProject("C:\\Users\\miksu\\COMPSEC300\\repo\\secure-programming-course-project\\build\\libs\\secure-programming-course-project.jar");
	}
	
	/**
	 * Checks the given path to project source.jar with Spotbugs static code analysis tool
	 * @param pathToProjectSource - path to the project source.jar / main.jar
	 */
	public String checkProject(String pathToProjectSource) {
		Project project = new Project();
		
		project.addFile(pathToProjectSource);
		
		// ══════════ AI generated example (Using Copilot), which is modified ══════════
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
			Log.error("Could not read given project jar file.", e);
		}
        
        // Using StringBuilder to collect bugs with their information to a string
        StringBuilder builder = new StringBuilder();
        for(BugInstance bug : spotbugsEngine.getBugReporter().getBugCollection().getCollection()) {
        	builder.append(bug.getPrimaryClass().toString());
        	builder.append(" - ");
        	builder.append(bug.getMessageWithPriorityTypeAbbreviation());
        	builder.append("\n");
        	
        }
        System.out.println(builder.toString());
        // Spotbugs engine needs to be closed
        spotbugsEngine.close();
        
        return builder.toString();
	}

}
