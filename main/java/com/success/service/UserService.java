package com.success.service;


import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.success.dao.ConcernsDAO;
import com.success.dao.TestDAO;
import com.success.dao.UserDAO;
import com.success.dto.ConcernsDTO;
import com.success.dto.UserDTO;
import com.success.repository.UserSFARepository;
import com.success.security.IUserService;
import com.success.security.exception.UserAlreadyExistsException;
import com.success.security.registration.RegistrationRequest;
import com.success.security.registration.token.VerificationToken;
import com.success.security.registration.token.VerificationTokenRepository;



@Component@Primary
/**
* The service layer is there to provide logic to operate on the data sent to and from the DAO and the client.
*/
public class UserService implements SuccessForAllService, IUserService {

	@Autowired
	private ConcernsDAO concernsDAO;
	@Autowired
	private UserDAO userDAO;
	private UserSFARepository userRepository;
	private PasswordEncoder passwordEncoder;
    private VerificationTokenRepository tokenRepository;
	
	
	@Override
	public UserDTO getSuccessForAllId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean newSuccessForAllUser(UserDTO userDTO) throws Exception {
			Long num = generateRandomPositiveLong();
			userDTO.setId(num);
			userDAO.save(userDTO);
		return true;
	}
	
	@Override
	public boolean save(UserDTO userDTO) throws Exception {
		userDAO.save(userDTO);
		return true;
	}

	@Override
	public List<ConcernsDTO> fetchConcerns(String searchTerm) throws Exception{
		// get the ConcernsDTO from the service layer (controller/html) to the ConcernsDAO
		List<ConcernsDTO> allConcerns = concernsDAO.fetchAll(searchTerm); 
		
		/*  This performs filtering based on the search term.
		 *  Explanation:
		 *  .stream() represents a sequence of data that one can perform various operations like filtering, 
		 *  mapping, sorting, and reducing. In this case it is being used to filter. Thus, the filter() 
		 *  is used which is an intermediate operation that takes a  method predicate, which is the
		 *  lambda expression 'concern -> concern.getConcernsName().equalsIgnoreCase(searchTerm)' below.
		 *  This predicate is checking if the ConcernsDTO object's concernsName property, obtained using concern.getConcernsName(),
		 *  and seeing if it is equal to the searchTerm. It ignores upper and lower case letters with equalsIgnoreCase() method.
		 *  Then the '.collect(Collectors.toList())' gets the elements of the stream and gathers them into, in this instance a list.
		 *  The reason for the list is that is what this method is returning.
		 */
	    List<ConcernsDTO> filteredConcerns = allConcerns.stream()
	            .filter(concern -> concern.getConcernsName().equalsIgnoreCase(searchTerm))
	            .collect(Collectors.toList());

	    return filteredConcerns;
	}
	



	/*
	 * boolean userExists = sfaRepository.findByEmail(userDTO.getEmail()).isPresent();
		
		if (userExists) {
	        throw new IllegalStateException("Email already taken");
	    }
	    userDAO.save(userDTO);
	    return userDTO;
	}

	
	 */
	
	 public static long generateRandomPositiveLong() {
	        Random random = new Random();
	        long randomLong = Math.abs(random.nextLong());
	        
	        // Ensure the generated number is positive and not zero
	        while (randomLong <= 0) {
	            randomLong = Math.abs(random.nextLong());
	        }
	        
	        return randomLong;
	    }

	@Override
	public void setTestDAO(TestDAO successForAllDAO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TestDAO getTestDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public Iterable<UserDTO> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO registerUser(RegistrationRequest request) {
        Optional<UserDTO> user = this.findByEmail(request.getEmail());
        if (user.isPresent()) {
            throw new UserAlreadyExistsException("User with email " + request.getEmail() + " already exists");
        }
        UserDTO newUser = new UserDTO();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(request.getRole());
        return userRepository.save(newUser);
    }

    @Override
    public Optional<UserDTO> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUserVerificationToken(UserDTO user, String verificationToken) {
        VerificationToken token = new VerificationToken(verificationToken, user);
        tokenRepository.save(token);
    }

    @Override
    public String validateToken(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            return "Invalid verification token";
        }
        UserDTO user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((verificationToken.getTokenExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            tokenRepository.delete(verificationToken);
            return "Token already expired";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }
}