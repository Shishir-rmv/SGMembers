package com.shishir.sg.SGMembers.service;

import java.util.List;

import com.shishir.sg.SGMembers.model.Member;

public interface MemberService {
	
	Member findById(long id);
	
	List<Member> findByStatus(String status);
	
	List<Member> findAllMembers();
	
	boolean saveMember(Member member);
	
	int saveMembers(List<Member> members);
	
	List<Member> searchForMember(String searchText) throws Exception;
	
}
