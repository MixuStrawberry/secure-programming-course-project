package report;

import java.util.List;

import dependency.Dependency;

public class TextReportBuilder implements IReportBuilder {
	
	private List<Dependency> dependencies;

	public TextReportBuilder(List<Dependency> dependencies) {
		this.dependencies = dependencies;
	}

	@Override
	public String build() {
		for(Dependency dependency : dependencies) {
			
		}
		return null;
	}
}
