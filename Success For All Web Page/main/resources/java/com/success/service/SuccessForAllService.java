package com.success.service;

import java.util.List;

import com.success.dao.SuccessForAllDAO;
import com.success.dto.ConcernsDTO;
import com.success.dto.UserDTO;

public interface SuccessForAllService {

	/**
	 * Get users from persistence layer.
	 * @param id is a unique lookup
	 * @return is the user with a matching Id
	 */
	UserDTO getSuccessForAllId(Long id);

	/**
	 * Make a new user from the client layer
	 * @param is a Long
	 * @return id is randomly generated
	 * @throws Exception 
	 */
	UserDTO newSuccessForAllUser(UserDTO userDTO) throws Exception;

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
	List<ConcernsDTO> fetchConcerns(String string) throws Exception;

	void setSuccessForAllDAO(SuccessForAllDAO successForAllDAO);

	SuccessForAllDAO getSuccessForAllDAO();


}