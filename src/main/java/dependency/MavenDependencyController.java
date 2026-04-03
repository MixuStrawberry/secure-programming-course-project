package dependency;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MavenDependencyController {
	public static void main(String[] args) {
		MavenDependencyController controller = new MavenDependencyController();
		controller.findDependencies("C:\\Users\\miksu\\COMPSEC300\\repo\\secure-programming-course-project\\junit-jupiter-api-6.0.3.pom.xml");
	}
	
	public List<Dependency> findDependencies(String pomFilePath) {
		List<Dependency> dependencies = new ArrayList<>();
		DocumentBuilder builder;
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(new File(pomFilePath));
			NodeList nodeList = doc.getElementsByTagName("dependency");
			for(int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				
				System.err.println(node.getAttributes().getNamedItem("group"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dependencies;
	}

}
