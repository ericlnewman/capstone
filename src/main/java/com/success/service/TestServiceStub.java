package com.success.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.success.dao.SuccessForAllDAO;
import com.success.dto.ConcernsDTO;
import com.success.dto.UserDTO;
import com.success.model.UserAddress;
import com.success.model.UserInformation;
import com.success.model.UserPersonOfConcern;

@Component
/**
 * This is an initial implementation of the service layer interface for unit testing
 */
public class TestServiceStub implements SuccessForAllService {
	
	
	private SuccessForAllDAO successForAllDAO;
	
	@Override
	public UserDTO getSuccessForAllId(Long id) {
		 UserDTO userDTO = new UserDTO();
	     userDTO.setPassword("P@ssword");
		 userDTO.setUserName("gary.b@gmail.com");
		 userDTO.setConcerns("adhd");
		 
	     UserAddress userAddress = new UserAddress();
		 userAddress.setUserStreet("123 Main St");
		 userAddress.setUserTown("New York");
		 userAddress.setUserState("NY");
		 userAddress.setUserZip("12345");
		 userAddress.setUserCountry("USA");
		 userDTO.setUserAddress(userAddress);
		 
		 UserInformation userInformation = new UserInformation();
		 userInformation.setFirstName("Gerry");
		 userInformation.setLastName("Brooke");
		 userInformation.setParentOrGuardian("parent");
		 userInformation.setGender("male");
		 userInformation.setNumberOfChildren(2);
		 userDTO.setUserInformation(userInformation);
		 
		 UserPersonOfConcern personOfConcern = new UserPersonOfConcern();
		 personOfConcern.setPersonOfConcernName("Gary");
		 personOfConcern.setAgeOfPersonOfConcern(7);
	    
		return userDTO;
	}
	@Override
	public UserDTO newSuccessForAllUser() {
	    return null;
	}

	@Override
	public boolean save(UserDTO userDTO) throws Exception {
		boolean result = successForAllDAO.save(userDTO);
		return result;
	}

	@Override
	public SuccessForAllDAO getSuccessForAllDAO() {
		return successForAllDAO;
	}
	@Override
	public void setSuccessForAllDAO(SuccessForAllDAO successForAllDAO) {
		this.successForAllDAO = successForAllDAO;
	}
	
	
	
	@Override
	public List<ConcernsDTO> fetchConcerns(String searchTerm) {
		// stub out featchUserSigningin
	    List<ConcernsDTO> correctInfo = new ArrayList<ConcernsDTO>();
	    
	    if(searchTerm.contains("adhd")|| searchTerm.contains("behavior")) {
	    	ConcernsDTO concerns = new ConcernsDTO();
	    	
	    	concerns.setConcernCategory("behavior");
	    	concerns.setConcernSubject("family problems");
	    	concerns.setConcernsName("adhd");
	    	concerns.setConcernTopic("roaming");
	    	correctInfo.add(concerns);	    	
	    }
	    return correctInfo;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean value = false;
		if(obj instanceof UserDTO) {
			UserDTO incomingUser = (UserDTO) obj;
			value = incomingUser.getId() == incomingUser.getId();
		}
		
		return value;
	}
	
	
}