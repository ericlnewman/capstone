package com.success.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import org.springframework.stereotype.Component;

import com.success.dao.SuccessForAllDAO;
import com.success.dto.ConcernsDTO;
import com.success.dto.UserDTO;
import com.success.pii.UserPII;

@Component
public class SuccessForAllServiceImpl implements SuccessForAllService {
	
	
	private SuccessForAllDAO successForAllDAO;
	
	@Override
	public UserDTO getSuccessForAllId(Long id) {
	    UserDTO userDTO = new UserDTO();
	    UserPII userPII = new UserPII();
	    Long newId = 123456789L;
	    userDTO.setId(newId);
	    userDTO.setEmail("jerry.springer@ymail.com");
	    userPII.setFirstName("Jerry");
	    userPII.setLastName("Springer");
	    userPII.setAge(65);
	    userDTO.setPassword("IamJerrry@1");
	    userDTO.setConcerns("adhd");
		return userDTO;
	}
	@Override
	public UserDTO newSuccessForAllUser() {
	    UserDTO userDTO = new UserDTO();
	    Long newId = unsignedLong();
	    userDTO.setId(newId);
	    userDTO.setEmail("example@exmaple.com");
	    // Check if the email is valid
	    String email = userDTO.getEmail();
	    if(isValidEmail(email)) {
	    	String password = userDTO.getPassword();
	    	if(isValidPassword(password)) {
	    		return userDTO;
	    	}else {
	    		throw new IllegalArgumentException("Invalid password");
	    	}
	    } else {
	    	 throw new IllegalArgumentException("Invalid email");
	    }
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
	
	// functions
		private boolean isValidEmail(String email) {
			
			if (email == null) {
		        return false;
		    }
			
			// hardcoded email stubs, not the best solution
			List<String> isValidEmailStubs = new ArrayList<>();
			
			isValidEmailStubs.add("@gmail.com");
			isValidEmailStubs.add("@gmail.de");
			isValidEmailStubs.add("@gmail.fr");
			isValidEmailStubs.add("@gmail.co.uk");
			isValidEmailStubs.add("@gmail.co.jp");
			isValidEmailStubs.add("@gmail.com.br");
			isValidEmailStubs.add("@gmail.com.au");
			isValidEmailStubs.add("@gmail.ca");
			isValidEmailStubs.add("@gmail.it");
			isValidEmailStubs.add("@gmail.es");
			isValidEmailStubs.add("@ymail.com");
			isValidEmailStubs.add("@yahoo.com");
			isValidEmailStubs.add("@yahoo.co.uk");
			isValidEmailStubs.add("@yahoo.ca");
			isValidEmailStubs.add("@yahoo.com.au");
			isValidEmailStubs.add("@yahoo.in");
			isValidEmailStubs.add("@outlook.com");
			isValidEmailStubs.add("@outlook.co.uk");
			isValidEmailStubs.add("@outlook.fr");
			isValidEmailStubs.add("@outlook.de");
			isValidEmailStubs.add("@hotmail.co.uk");
			isValidEmailStubs.add("@hotmail.fr");
			isValidEmailStubs.add("@hotmail.de");
			isValidEmailStubs.add("@aol.com");
			isValidEmailStubs.add("@icloud.com");
			isValidEmailStubs.add("@protonmail.com");
			isValidEmailStubs.add("@zoho.com");
			isValidEmailStubs.add("@mail.com");
			isValidEmailStubs.add("@live.com");
			isValidEmailStubs.add("@gmx.com");
			isValidEmailStubs.add("@yandex.com");
			isValidEmailStubs.add("@qq.com");
			isValidEmailStubs.add("@163.com");
			isValidEmailStubs.add("@sina.com");
			isValidEmailStubs.add("@sohu.com");
			isValidEmailStubs.add("@126.com");
			isValidEmailStubs.add("@yeah.net");
			isValidEmailStubs.add("@aliyun.com");
			isValidEmailStubs.add("@tom.com");
			isValidEmailStubs.add("@foxmail.com");
			isValidEmailStubs.add("@139.com");
			
			 for (String stub : isValidEmailStubs) {
			        if (email.endsWith(stub)) {
			            return true;
			    }
			 }
		   return false;
		}// end isEmailValid

		/* *************************************************************************************************
	     * As a reminder on regular expressions:
	     * A regular expression (regex) defines a search pattern for strings.
	     * Quantifiers define how often an element can occur:  ?, *, + and {}
	     * a $ signifies the end of the expression
	     * a * means it occurs zero or more times, while . will match any character
	     * so .*  finds any character sequence. A ? occurs no or one times, so *?
	     * tries to find the smallest match. This makes the regular expression stop
	     * and at the first match.
	     * anything within [] is a set definition, can match the letters
	     * anything within [1-4] or [a-c] sets a range for the definition
	     *
	     * Example: "(?.=*[a-z])(?.=*[A-Z].+$"
	     * There are two parts to this within the () and both are positive look ahead assertion.
	     * The first (?=.*[a-z]). Within this the ?= can be read as 'Look ahead and assert that the
	     * following pattern exists', while .* can be read as 'match any number of characters
	     * (except a newline)'. The characters are within the range of all 26 lower case letters.
	     * The same applies for the second assertion.
	     **************************************************************************************************/
	    public boolean isValidPassword(String password) {
	        if (password.length() < 8 || password.length() > 25) {
	            return false;
	        }

	        if (!password.matches("(?=.*[a-z])(?=.*[A-Z]).+$")) {
	            return false;
	        }

	        if (!password.matches("(?=.*\\d).+$")) {
	            return false;
	        }

	        if (!password.matches("(?=.*[!@#_+=$%^&*:?\\-]).+$")) {
	            return false;
	        }

	        return true;
	    }

	/*
	 * This method creates a random unsigned long number as the userId,
	 * a better and less possible collision that could mess up the database is to use
	 * a UUID, however it was difficult to implement with mysql, despite having the UUID
	 * format in mysql.
	 * */
	private Long unsignedLong() {
	    Random random = new Random();
	    long unsignedLong = random.nextLong() & 0x7FFFFFFFFFFFFFFFL;
	    long positiveValue = unsignedLong - Long.MIN_VALUE;
	    return positiveValue;
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
	    // Return an empty list if the email is not valid
	    //return Collections.emptyList();
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