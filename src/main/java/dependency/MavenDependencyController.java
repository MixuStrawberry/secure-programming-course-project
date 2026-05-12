package dependency;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MavenDependencyController {
	
	private static final Logger log = LoggerFactory.getLogger(MavenDependencyController.class);
	
	public List<Dependency> findDependencies(String pomFilePath) {
		List<Dependency> dependencies = new ArrayList<>();
		DocumentBuilder builder;
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(new File(pomFilePath));
			NodeList nodeList = doc.getElementsByTagName("dependency");
			for(int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
			}
		} catch (Exception e) {
			log.error("Could not resolve maven dependencies!");
		}
		return dependencies;
	}

}
