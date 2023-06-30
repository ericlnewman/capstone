package com.success.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.success.dto.ConcernsDTO;

@Component
public class ConcernsDAOImpl implements ConcernsDAO {

	@Autowired
	LocalFileLoaderDAO localFileLoaderDAO;
	
	@Override
	public List<ConcernsDTO> fetch(String searchFilter) throws Exception{
		List<ConcernsDTO> allConcerns = new ArrayList<ConcernsDTO>();
		
			// get the JSON as one string from the request method
			String rawJSON = localFileLoaderDAO.requestFromLocal("/resources/JSON/concerns.json");		
			
			//create a JSON object for the JSON string
			JSONObject root = new JSONObject(rawJSON);
			
			// make the JSON object into an array
			JSONArray concerns = root.getJSONArray("concerns");
			
			// iterate over the data within that array
			for(int i = 0; i < concerns.length(); i++) {
				// JSON data
				JSONObject jsonConcerns = concerns.getJSONObject(i);
				// Concern object populated with JSON data
				ConcernsDTO concern = new ConcernsDTO();
				int guid = jsonConcerns.getInt("concernsId");
				String concernsName = jsonConcerns.getString("concernsName");
				String concernCategory = jsonConcerns.getString("concernCategory");
				String concernSubject = jsonConcerns.getString("concernSubject");
				String concernTopic = jsonConcerns.getString("concernTopic");
				String concernDescription = jsonConcerns.getString("concernDescription");
			
			// populate DTO with this informatison
				concern.setConcernsId(guid);
				concern.setConcernsName(concernsName);
				concern.setConcernCategory(concernCategory);
				concern.setConcernSubject(concernSubject);
				concern.setConcernTopic(concernTopic);
				concern.setConcernDescription(concernDescription);
			
				// add the populated concerns to the collection
				allConcerns.add(concern);
			
			}
		return allConcerns;
	}
}
