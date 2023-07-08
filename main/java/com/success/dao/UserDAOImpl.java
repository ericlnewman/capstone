package com.success.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.success.dto.UserDTO;
import com.success.repository.UserSFARepository;


@Component
/**
 * A direct access object provides access to an underlying database or any other persistence storage.
 * This requires access to the repository, as it does the persistence, while the service layer
 * requires access to a DAO.
 */
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private UserSFARepository userRepository;

	@Override
	public boolean save(UserDTO userDTO) throws Exception {
		userRepository.save(userDTO);
		return false;
	}



}
