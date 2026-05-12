package report;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dependency.Vulnerability;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

public class ScanningResultHtmlReportWriter {

	private static final Logger log = LoggerFactory.getLogger(ScanningResultHtmlReportWriter.class);
	
	private String reportPath;
	private File file;

	public ScanningResultHtmlReportWriter(String reportPath) throws URISyntaxException {
		this.reportPath = reportPath;
		URL url = ScanningResultHtmlReportWriter.class.getClassLoader().getResource("templates/template.ftl");
		file = new File(url.toURI()).getParentFile();
	}
	
	public void buildHtmlReport(List<Vulnerability> vulnerabilities) {
		Configuration cfg = new Configuration(new Version(2, 3, 34));
		cfg.setClassForTemplateLoading(Vulnerability.class, "/templates");
		try {
			Template template = cfg.getTemplate("template.ftl");
			
			Map<String, Object> data = new HashMap<>();
			
			data.put("vulnerabilities", vulnerabilities);
			
			 Writer fileWriter = new FileWriter(new File(reportPath, "result.html"));
			 template.process(data, fileWriter);
			 
			 fileWriter.close();
		} catch (Exception e) {
			log.error("Could not load template from: " + file.getAbsolutePath());
		}
	}
}
