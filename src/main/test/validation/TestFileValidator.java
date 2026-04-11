package validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class TestFileValidator {
	
	@TempDir
	private Path temporaryDirectory;
	
	@Test
	public void testCorrectFilePath() throws IOException {
		Path tempFile = Files.createFile(temporaryDirectory.resolve("temp"));
		
		assertTrue(Files.exists(tempFile));
	}
	
	@Test
	public void testIncorrectFilePath() {
		assertFalse(Files.exists(Paths.get("@£5rsaj3h54")));
	}

}
