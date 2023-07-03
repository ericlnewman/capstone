package com.success.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.success.dto.UserDTO;

@Repository
@Transactional(readOnly = true)
/**
 * this makes a mirror of the CrudRepository interface with all of its methods
 * such as create, read, update, delete
 */
public interface UserSFARepository extends CrudRepository <UserDTO, Long> {

	Optional<UserDTO> findByEmail(String email);
}
