package com.success.dao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkDAO {

	/**
	 * Return the data found at the given endpoint from a url
	 * @param endpoint a URL or other location where data can be found
	 * @return all of the data returned as one string
	 * @throws Exception
	 */
	public String requestFromWeb(String endpoint) throws Exception{
		// string builder is more efficient when concatenating strings, and ideal when traversing one thread
		StringBuilder sb = new StringBuilder();
	
		/* converting from just a string, a representation of just characters
		* to a url which is better at understanding what a url is.
		* */
		URL url = new URL(endpoint);
		
		//which can then open a connection to this URL, to find out what data is there
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		
		/* Now use the input stream (just reading the bytes without knowing what it is),
		 * buffered inputstream (reads it into a memory location, and back filling with more data for better performance),
		 * inputstreamreader (takes the buffered inputstream and make them characters)
		 * and bufferedreader (takes the inputstreamreader and stores them in local memory
		 * for better performance)
		 * to actually read data from the connection
		 * use a try finally to end the connection regardless of exceptions, so
		 * if an exception is thrown it goes to finally, and disconnects */
		try {
			// read data as bytes
			InputStream inputStream = urlConnection.getInputStream();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
			
			// read data as characters
			InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			//read one line at a time
			String inputLine = bufferedReader.readLine();
			while(inputLine != null) {
				// add this to the output
				sb.append(inputLine);
				// reading the next line
				inputLine = bufferedReader.readLine();
			}
		} finally {
			urlConnection.disconnect();
		}
		return sb.toString();
	}
}
