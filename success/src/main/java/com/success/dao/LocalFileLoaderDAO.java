package com.success.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.springframework.core.io.ClassPathResource;


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
	    StringBuilder sb = new StringBuilder();

	    // Read data from the file
	    
	        ClassPathResource resource = new ClassPathResource(filePath);
	        InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
	        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	     
	        String inputLine = bufferedReader.readLine();
	        while (inputLine != null) {
	            sb.append(inputLine);
	            inputLine = bufferedReader.readLine();
	        }
	    

	    return sb.toString();
	}

	
}
