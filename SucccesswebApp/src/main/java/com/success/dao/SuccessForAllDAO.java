package com.success.dao;

import com.success.dto.UserDTO;

public interface SuccessForAllDAO {

	boolean save(UserDTO userDTO) throws Exception;
}
