package report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextReportBuilder implements IReportBuilder {
	
	private static final String REPORTS_STATIC_CODE_ANALYSIS_REPORT_TXT = "reports/staticCodeAnalysisReport.txt";

	private static final Logger logger = LoggerFactory.getLogger(TextReportBuilder.class);
	
	private String reportString;;

	public TextReportBuilder(String reportString) {
		this.reportString = reportString;
	}

	@Override
	public String build() {
//	    BufferedWriter writer = new BufferedWriter(new FileWriter(REPORTS_STATIC_CODE_ANALYSIS_REPORT_TXT));
		return null;
	}
}
