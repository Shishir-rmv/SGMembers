package com.shishir.sg.SGMembers.dao;

import org.springframework.stereotype.Repository;

import com.shishir.sg.SGMembers.model.Ethnicity;

@Repository("ethnicityDao")
public class EthnicityDaoImpl extends AbstractDao<Integer, Ethnicity> implements EthnicityDao {

	public Ethnicity findById(int id) {
		return getByKey(id);
	}
	

}
