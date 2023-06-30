package com.success.dao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

@Component
public class LocalFileLoaderDAO {

	/**
	 * Return the data found at the given filepath from local files
	 * @param filePath within the local files
	 * @return all data as one string
	 * @throws Exception
	 */
	public String requestFromLocal(String filePath) throws Exception {
		// string builder is more efficient when concatenating strings, and ideal when traversing one thread
		StringBuilder sb = new StringBuilder();

		/* converting from just a string, a representation of just characters
		* to a file which is better at understanding what a file is.
		* */
	    File file = new File(filePath);

	    /* Now use the Fileinput stream (just reading the bytes without knowing what it is),
		 * buffered inputstream (reads it into a memory location, and back filling with more data for better performance),
		 * inputstreamreader (takes the buffered inputstream and make them characters)
		 * and bufferedreader (takes the inputstreamreader and stores them in local memory
		 * for better performance)
		 * to actually read data from the file */
	    try (
	    	// read data as bytes
	    	FileInputStream fileInputStream = new FileInputStream(file);
	        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
	    	
	    	// read data as characters
	    	InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
	        BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

	        String inputLine = bufferedReader.readLine();
	        while (inputLine != null) {
	        	// add this to the output
	            sb.append(inputLine);
	            // reading the next line
	            inputLine = bufferedReader.readLine();
	        }
	    }

	    return sb.toString();
	}
	
	
}
