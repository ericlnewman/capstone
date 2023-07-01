package com.success.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

/**
 * This class reads the local JSON files.
 */
@Component
public class LocalFileLoaderDAO {

	/**
	 * Return the data found at the given filepath from local files
	 * @param filePath within the local files
	 * @return all data as one string
	 * @throws Exception
	 */
	public String requestFromLocal(String filePath) throws IOException {
	    StringBuilder sb = new StringBuilder();

	    // Read data from the file
	    try (InputStream inputStream = getClass().getResourceAsStream(filePath);
	         InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	         BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

	        String inputLine = bufferedReader.readLine();
	        while (inputLine != null) {
	            sb.append(inputLine);
	            inputLine = bufferedReader.readLine();
	        }
	    } catch (IOException e) {
	        // Handle the exception or rethrow it
	        throw new IOException("Failed to read file from the classpath: " + filePath, e);
	    }

	    return sb.toString();
	}
}
