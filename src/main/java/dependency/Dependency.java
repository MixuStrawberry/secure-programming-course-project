package dependency;

import java.util.List;
import java.util.Objects;

import vulnerability.Vulnerability;

/**
 * Stores dependency related information and vulnerabilities.
 */
public class Dependency {
	
	private String group;
	private String module;
	private String version;
	private List<Vulnerability> vulnerabilities;
	
	
	public String getGroup() {
		return group;
	}
	
	public void setGroup(String group) {
		this.group = group;
	}
	
	public String getModule() {
		return module;
	}
	
	public void setModule(String module) {
		this.module = module;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}

	public List<Vulnerability> getVulnerabilities() {
		return vulnerabilities;
	}

	public void setVulnerabilities(List<Vulnerability> vulnerabilities) {
		this.vulnerabilities = vulnerabilities;
	}

	@Override
	public int hashCode() {
		return Objects.hash(group, module, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dependency other = (Dependency) obj;
		return Objects.equals(group, other.group) && Objects.equals(module, other.module)
				&& Objects.equals(version, other.version);
	}
	
}
