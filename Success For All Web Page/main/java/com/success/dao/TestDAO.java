package com.success.dao;

import com.success.dto.UserDTO;

public interface TestDAO {

	boolean save(UserDTO userDTO) throws Exception;
}