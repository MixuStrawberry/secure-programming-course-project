package connection;

import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.owasp.dependencycheck.Engine;
import org.owasp.dependencycheck.exception.ExceptionCollection;
import org.owasp.dependencycheck.exception.ReportException;
import org.owasp.dependencycheck.utils.Settings;


public class NvdNistConnector {

	private static final String NVD_URL = "https://services.nvd.nist.gov/rest/json/cves/2.0";

	private String apiKey;
	
	public NvdNistConnector(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public void queryVulnerabilities(Set<dependency.Dependency> dependencies) {
		for(dependency.Dependency dependency : dependencies) {
			String url = NVD_URL + "?keyWordSearch=" + dependency.getModule();
		
			HttpClient client = HttpClient.newHttpClient();
	        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url)).header("User-Agent", "Java NVD Client").build();
			
		    HttpResponse<String> resp;
			try {
				resp = client.send(req, HttpResponse.BodyHandlers.ofString());
			    System.out.println("Status: " + resp.statusCode());
			    System.out.println(resp.body());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
            
         // 2. Collect JAR files
            List<String> jarFiles = dependencies.stream()
                    .map(dep -> dep.getFilePath())
                    .collect(Collectors.toList());

//            // 4. Wrap JAR files as OWASP dependencies
//            List<Dependency> dcDeps = jarFiles.stream()
//                    .map(Dependency::new)
//                    .collect(Collectors.toList());
//            
            
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

//            List<Dependency> nvdDependencies = Arrays.asList(engine.getDependencies());
            

//            for (Dependency dep : nvdDependencies) {
//            	if(dep.getVulnerabilitiesCount() > 0) {
//	                System.out.println("Dependency: " + dep.getFileName());
//	                for(Vulnerability vulnerability : dep.getVulnerabilities()) {
//	                	String severity = vulnerability.getUnscoredSeverity();
//	                	if(severity != null && allowedVulnerabilities.contains(severity.toLowerCase())) {
//	                		System.out.println("  CVE: " + vulnerability.getName());
//		                    System.out.println("  Severity: " + vulnerability.getUnscoredSeverity());
//		                    System.out.println("  Description: " + vulnerability.getDescription());
//		                    System.out.println("------------------------------------------------");
//	                	}
//	                }
//            	}
//            }

        } finally {
            if (engine != null) {
                engine.close();
            }
        }
	}
}
