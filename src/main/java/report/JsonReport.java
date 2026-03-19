package report;

import org.json.JSONObject;

import dependency.Dependency;

public class JsonReport implements IReportBuilder {
	
	private JSONObject jsonObject;
	private Dependency dependency;
	
	public JsonReport(Dependency dependency) {
		this.dependency = dependency;
	}

	@Override
	public String build() {
		
		jsonObject = new JSONObject();
		
		jsonObject.put("dependency", dependency.getGroup() + dependency.getModule() + dependency.getVersion());
		
		return jsonObject.toString();
	}
	

}
