package com.shishir.sg.SGMembers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shishir.sg.SGMembers.dao.EthnicityDao;
import com.shishir.sg.SGMembers.model.Ethnicity;

@Service("ethnicityService")
@Transactional
public class EthnicityServiceImpl implements EthnicityService{
	
	@Autowired
	EthnicityDao dao;

	public Ethnicity findById(int id) {
		return dao.findById(id);
	}

}
