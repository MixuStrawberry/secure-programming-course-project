package connection;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.owasp.dependencycheck.Engine;
import org.owasp.dependencycheck.exception.ExceptionCollection;
import org.owasp.dependencycheck.exception.ReportException;
import org.owasp.dependencycheck.utils.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NvdNistConnector {
	
	private static final Logger logger = LoggerFactory.getLogger(NvdNistConnector.class);

	private String apiKey;
	
	public NvdNistConnector(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public void buildQuery(Set<dependency.Dependency> dependencies, String htmlReportPath) {
		// Providing settings for the NVD API.
        Settings settings = new Settings();
        settings.setStringIfNotEmpty("data.directory", "dc-data");
        settings.setStringIfNotEmpty("nvd.api.key", apiKey);
        settings.setStringIfNotEmpty("odc.autoupdate", "true");
        
        Engine engine = null;

        try {
        	// Initialize the engine with the settings created above. 
            engine = new Engine(settings);
            
            List<String> jarFiles = new ArrayList<>();
            for(dependency.Dependency dependency : dependencies) {
            	jarFiles.add(dependency.getFilePath());
            }
            
            // Transforming an ArrayList into String array.
            String[] jarStringArray = jarFiles.toArray(new String[0]);
            
            //<--- AI GENERATED CODE
            // Scan the dependencies
            engine.scan(jarStringArray);

            // Analyze dependencies (this triggers CVE matching)
            try {
				engine.analyzeDependencies();
	            engine.writeReports("MyApp", new File(htmlReportPath), "XML", new ExceptionCollection());
	            // END OF AI GENERATED CODE --->
			} catch (Exception e) {
				logger.error("Error while trying to scan project dependencies for vulnerabilities.");
			} 
        } finally {
        	// After the scan is complete and reports are built close the engine.
            if (engine != null) {
                engine.close();
            }
        }
	}
}
