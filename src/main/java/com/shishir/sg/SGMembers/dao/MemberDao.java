package com.shishir.sg.SGMembers.dao;

import java.util.List;

import com.shishir.sg.SGMembers.model.Member;

public interface MemberDao {
	
	void indexMembers() throws Exception;
	
	Member findById(long id);
	
	List<Member> findByStatus(String status);
	
	List<Member> findAllMembers();

	boolean saveMember(Member member);

	int saveMembers(List<Member> members);
	
	public List<Member> searchForMember(String searchText) throws Exception;

}
