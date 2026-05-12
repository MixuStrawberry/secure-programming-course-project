package report;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dependency.Vulnerability;

public class DependencyCheckXmlReportParser {
	
	private static final Logger log = LoggerFactory.getLogger(DependencyCheckXmlReportParser.class);

	public List<Vulnerability> parseXmlReport(String reportPath) {
		List<Vulnerability> projectIssues = new ArrayList<>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document document;
		try {
			builder = factory.newDocumentBuilder();
			document = builder.parse(new File(reportPath));
			document.getDocumentElement().normalize();
			
			NodeList nodeList = document.getElementsByTagName("dependency");
			for (int i = 0; i < nodeList.getLength(); i++) {
			    Node node = nodeList.item(i);
			    if (node.getNodeType() == Node.ELEMENT_NODE) {
			        Element dependency = (Element) node;
			        
			        NodeList vulnerabilities = dependency.getElementsByTagName("vulnerability");
			        for (int j = 0; j < vulnerabilities.getLength(); j++) {
			        	Node vulnerabilityNode = nodeList.item(j);
			        	if(vulnerabilityNode.getNodeType() == Node.ELEMENT_NODE) {
			        		Element vulnerability = (Element) vulnerabilities.item(j);
			        		projectIssues.add(new Vulnerability(
			        				"Dependency-Check",
			        				vulnerability.getElementsByTagName("name").item(0).getTextContent(),
			        				vulnerability.getElementsByTagName("severity").item(0).getTextContent(),
			        				dependency.getElementsByTagName("fileName").item(0).getTextContent(),
			        				vulnerability.getElementsByTagName("description").item(0).getTextContent()
			        				));
			        	}
			        }
			    }
			}
		} catch (Exception e) {
			log.error("Could not parse given XML file: " + reportPath);
		}	
		
		return projectIssues;
	}
}
