package com.success.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.success.dto.UserDTO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(readOnly = true)
/**
 * this makes a mirror of the CrudRepository interface with all of its methods
 * such as create, read, update, delete
 */
public interface UserSFARepository extends CrudRepository <UserDTO, Long> {
	
	Optional<UserDTO> findByEmail(String email);
	
	 @Transactional
	    @Modifying
	    @Query("UPDATE user a " +
	            "SET a.enabled = TRUE WHERE a.email = ?1")
	    int enableAccess(String email);

	
}
