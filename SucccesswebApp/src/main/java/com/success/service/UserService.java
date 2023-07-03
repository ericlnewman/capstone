package com.success.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.success.dao.ConcernsDAO;
import com.success.dao.SuccessForAllDAO;
import com.success.dao.UserDAO;
import com.success.dto.ConcernsDTO;
import com.success.dto.UserDTO;
import com.success.repository.UserSFARepository;


@Component@Primary
/**
* The service layer is there to provide logic to operate on the data sent to and from the DAO and the client.
*/
public class UserService implements SuccessForAllService, UserDetailsService{

	@Autowired
	private ConcernsDAO concernsDAO;
	@Autowired
	private UserDAO userDAO;
	private UserSFARepository sfaRepository;
	private final static String USERNOTFOUND = "Email with %s not found"; // for security
	@Override
	public UserDTO getSuccessForAllId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO newSuccessForAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save(UserDTO userDTO) throws Exception {
		userDAO.save(userDTO);
		return false;
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

	@Override
	public void setSuccessForAllDAO(SuccessForAllDAO successForAllDAO) {
		// TODO Auto-generated method stub

	}

	@Override
	public SuccessForAllDAO getSuccessForAllDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    return sfaRepository.findByEmail(email)
	            .orElseThrow(() -> new UsernameNotFoundException(String.format(USERNOTFOUND, email)));
	}

}
