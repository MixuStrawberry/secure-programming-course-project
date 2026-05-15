package report;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.minlog.Log;

import dependency.Vulnerability;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

public class ScanningResultHtmlReportWriter {

	private static final Logger log = LoggerFactory.getLogger(ScanningResultHtmlReportWriter.class);
	
	private String reportPath;

	private URL templateUrl;

	public ScanningResultHtmlReportWriter(String reportPath) {
		this.reportPath = reportPath;
	}
	
	public void buildHtmlReport(List<Vulnerability> vulnerabilities) {
		Configuration cfg = new Configuration(new Version(2, 3, 34));
		cfg.setClassForTemplateLoading(Vulnerability.class, "/templates");
		FileWriter fileWriter = null;
		try {
			log.info("Creating HTML file.");
			templateUrl = ScanningResultHtmlReportWriter.class.getClassLoader().getResource("templates/template.ftl");
			log.info("Got template resources.");
			
			log.info("Saved template directory!");
			Template template = cfg.getTemplate("template.ftl");
			log.info("Loaded template.");
			Map<String, Object> data = new HashMap<>();
			
			data.put("vulnerabilities", vulnerabilities);
			
			fileWriter = new FileWriter(new File(reportPath, "result.html"), StandardCharsets.UTF_8);
			log.info("Settings up fileWriter");
			template.process(data, fileWriter);
		} catch (Exception e) {
			log.error("Could not load template from: " + templateUrl.toString());
		} finally {
		    if (fileWriter != null) {
		        try {
					log.info("Making sure fileWriter is closed");
		        	fileWriter.close();                
		        } catch (Exception e) {
		           log.error("Could not close java.io.FileWriter.");
		        }
		    }
		}
	}
}
