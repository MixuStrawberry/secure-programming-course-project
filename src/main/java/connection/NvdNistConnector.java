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


public class NvdNistConnector {

	// TODO Remove
//	private static final String NVD_URL = "https://services.nvd.nist.gov/rest/json/cves/2.0";

	private String apiKey;
	
	public NvdNistConnector(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public void buildQuery(Set<dependency.Dependency> dependencies) {
        Settings settings = new Settings();
        settings.setStringIfNotEmpty("data.directory", "dc-data");
        settings.setStringIfNotEmpty("nvd.api.key", apiKey);
        settings.setStringIfNotEmpty("odc.autoupdate", "false");
        
        Engine engine = null;

        try {
            // Initialize engine
            engine = new Engine(settings);
            
            List<String> jarFiles = new ArrayList<>();
            for(dependency.Dependency dependency : dependencies) {
            	jarFiles.add(dependency.getFilePath());
            }
               
            String[] jarStringArray = jarFiles.toArray(new String[0]);
            
            // Scan the dependencies
            engine.scan(jarStringArray);

            // Analyze dependencies (this triggers CVE matching)
            try {
				engine.analyzeDependencies();
	            engine.writeReports("MyApp", new File("C:\\Users\\miksu\\COMPSEC300\\repo\\secure-programming-course-project\\reports"), "HTML", new ExceptionCollection());
			} catch (ExceptionCollection e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ReportException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } finally {
        	// After the scan is complete and reports are built close the engine.
            if (engine != null) {
                engine.close();
            }
        }
	}
}
