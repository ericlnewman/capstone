package com.success.dao;

import com.success.dto.UserDTO;

public interface UserDAO {

	boolean save(UserDTO userDTO) throws Exception;

}