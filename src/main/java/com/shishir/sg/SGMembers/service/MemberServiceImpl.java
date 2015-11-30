package com.shishir.sg.SGMembers.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shishir.sg.SGMembers.dao.MemberDao;
import com.shishir.sg.SGMembers.model.Member;

@Service("memberService")
@Transactional
public class MemberServiceImpl implements MemberService {
	Logger logger = Logger.getLogger(MemberServiceImpl.class);
	
	@Autowired
	MemberDao dao;
	
	public Member findById(long id) {
		return dao.findById(id);
	}

	public List<Member> findByStatus(String status) {
		return dao.findByStatus(status);
	}

	public List<Member> findAllMembers() {
		return dao.findAllMembers();
	}

	public boolean saveMember(Member member) {
		return dao.saveMember(member);
		
	}

	public int saveMembers(List<Member> members) {
		logger.info("DAO is : " + dao);
		return dao.saveMembers(members);
	}
	
	public List<Member> searchForMember(String searchText) throws Exception{
		return dao.searchForMember(searchText);
	}

}
