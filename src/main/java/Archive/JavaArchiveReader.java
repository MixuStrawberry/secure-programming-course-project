package Archive;

import java.io.File;
import java.nio.file.Files;

public class JavaArchiveReader {
	
	private String archivePath;

	public String getArchivePath() {
		return archivePath;
	}

	public void setArchivePath(String arhivePath) {
		this.archivePath = arhivePath;
	}
	
	public File[] findProjectDependencies() {
		File[] files = new File(archivePath).listFiles();
		
		return null;
	}

}
