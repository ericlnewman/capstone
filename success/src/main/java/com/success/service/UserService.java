package com.success.service;

import java.util.List;

import com.success.dao.UserDAO;
import com.success.dto.ConcernsDTO;
import com.success.dto.UserDTO;

public interface UserService {

	/**
	 * Get users from persistence layer.
	 * @param id is a unique lookup
	 * @return is the user with a matching Id
	 */
	UserDTO getUserById(Long id);

	/**
	 * Make a new user from the client layer
	 * @param is a Long
	 * @return id is randomly generated
	 */
	UserDTO createNewUser();

	/**
	 * Persist the given DTO
	 * @param userDTO
	 * @return a boolean
	 */
	boolean save(UserDTO userDTO) throws Exception;
	
	/**
	 * Return a list of possible user information that contains this string
	 * @param string of criteria while signing up, first name, email address and password
	 * @return a list of possible matching 
	 */
	List<ConcernsDTO> fetchConcerns(String string);

	void setUserDAO(UserDAO userDAO);

	UserDAO getUserDAO();


}