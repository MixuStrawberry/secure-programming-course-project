package Archive;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class JavaArchiveReader {
	
	
	public List<File> findAllFiles(String archivePath) {
		List<File> resultFiles = new ArrayList<>();
		File archiveFile = new File(archivePath);
		File[] files = archiveFile.listFiles();
	    for (File file : files) {
	       if (file.isDirectory()) {
	            List<File> projectDependencies = findAllFiles(file.getAbsolutePath());
	            for(File projectFile : projectDependencies) {
	            	if(projectFile.isFile()) {
	            		resultFiles.add(projectFile);
	            	}
	            }
	        } else {
	        	resultFiles.add(file);
	        }
	    }
	    return resultFiles;
	}

}
