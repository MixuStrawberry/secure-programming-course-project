package Archive;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaArchiveReader {
	
	private static final Logger logger = LoggerFactory.getLogger(JavaArchiveReader.class);
	
	/**
	 * Finds all files inside the given archive path.
	 * Does not list folders, only files inside subfolders. 
	 * @param archivePath - String path to a folder.
	 * @return List of File-types
	 */
	public List<File> findAllFiles(String archivePath) {
		List<File> resultFiles = new ArrayList<>();
		try {
			File archiveFile = new File(archivePath);
			File[] files = archiveFile.listFiles();
			if(files != null) {
			    for (File file : files) {
			       if (file.isDirectory()) {
			    	   // Going through the next sub-directory recursively.
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
			}
		} catch (Exception exception) {
			logger.error("A null archivePath given. Please use an existing archive path. \n");
		}
	    return resultFiles;
	}

}
