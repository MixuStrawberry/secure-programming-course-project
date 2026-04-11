package validation;

import java.io.File;

public class FileValidator {

	/**
	 * Validates the given input String. Validation checks if the given input String exists as a File or folder.
	 * @param input - String path to a file or a folder.
	 * @return true or false
	 */
	public boolean validateInput(String input){
		File file = new File(input);
		
		if(file.exists()) {
			return true;
		}
		
		return false;
	}
}
