package Archive;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JavaArchiveReader {
	
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
		} catch (NullPointerException exception) {
			System.err.println("A null archivePath given. Please use an existing archive path. \n" + exception.getStackTrace());
		}
	    return resultFiles;
	}

}
