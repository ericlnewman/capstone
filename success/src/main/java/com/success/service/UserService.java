package com.success.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.success.dao.ConcernsDAO;
import com.success.dao.SuccessForAllDAO;
import com.success.dto.ConcernsDTO;
import com.success.dto.UserDTO;

@Component@Primary
public class UserService implements SuccessForAllService {

	@Autowired
	ConcernsDAO concernsDAO;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ConcernsDTO> fetchConcerns(String searchTerm) throws Exception{
		return concernsDAO.fetch(searchTerm);
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

}
