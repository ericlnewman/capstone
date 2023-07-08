package com.success.dao;

import java.util.List;

import com.success.dto.ConcernsDTO;

public interface ConcernsDAO {

	List<ConcernsDTO> fetchAll(String searchFilter) throws Exception;

}