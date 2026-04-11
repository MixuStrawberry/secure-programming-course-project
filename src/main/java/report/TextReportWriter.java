package report;

import java.io.BufferedWriter;
import java.io.FileWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextReportWriter {
	
	private static final Logger logger = LoggerFactory.getLogger(TextReportWriter.class);
	
	public void writeToFile(String fileName, String reportText) {
	    try {
		    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		    writer.write(reportText);
		    
		    writer.close();
	    } catch(Exception e) {
	    	logger.error("Could not write report to file: ", e);
	    }
		
	}
}
