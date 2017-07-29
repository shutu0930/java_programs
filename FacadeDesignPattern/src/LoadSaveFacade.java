import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoadSaveFacade {
	
	/*
	 * LoadSaveFacade - this is a simple Facade for accessing java.io for loading and saving small text files.
	 * Eric McCreath 2015 GPL
	 */
	
	
/*
 * load - loads a small file and returns the contents of this file as a string.
 * A null is returned if there is a problem loading.
 */
	static String load(String filename) {
		try {
			BufferedReader reader = Files.newBufferedReader(
					Paths.get(filename), Charset.forName("US-ASCII"));
			StringBuffer res = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				res.append(line + "\n");
			}
			return res.toString();
		} catch (IOException x) {
			return null;
		}
	}

	/*
	 * save - saves the "text" into a file with name "filename"
	 * returns true if the project saved without error, and return false if 
	 * there was a problem. 
	 */
	static boolean save(String filename, String text) {
		try {
			BufferedWriter writer = Files.newBufferedWriter(
					Paths.get(filename), Charset.forName("US-ASCII"));
			writer.append(text);
			writer.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	

	
	
	

}
