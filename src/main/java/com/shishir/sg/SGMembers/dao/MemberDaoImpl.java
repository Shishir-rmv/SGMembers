package com.shishir.sg.SGMembers.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shishir.sg.SGMembers.model.Member;

@Repository("memberDao")
public class MemberDaoImpl extends AbstractDao<Long, Member> implements
		MemberDao {

	public Member findById(long id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	public List<Member> findByStatus(String status) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("status", status));
		return (List<Member>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Member> findAllMembers() {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.asc("id"));
		return (List<Member>) criteria.list();
	}

	public boolean saveMember(Member member) {
		persist(member);
		return true;
	}

	public int saveMembers(List<Member> members) {
		for (Member member : members) {
			persist(member);
		}
		return members.size();
	}

	@Transactional
	public void indexMembers() throws Exception {
		try {
			Session session = getSession();
			FullTextSession fullTextSession = Search
					.getFullTextSession(session);
			fullTextSession.createIndexer().startAndWait();
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Member> searchForMember(String searchText) throws Exception {
		try {
			Session session = getSession();

			FullTextSession fullTextSession = Search
					.getFullTextSession(session);

			QueryBuilder qb = fullTextSession.getSearchFactory()
					.buildQueryBuilder().forEntity(Member.class).get();
			org.apache.lucene.search.Query query = qb.keyword()
					.onFields("status", "height", "weight")
					.matching(searchText).createQuery();

			org.hibernate.Query hibQuery = fullTextSession.createFullTextQuery(
					query, Member.class);

			List<Member> results = hibQuery.list();
			return results;
		} catch (Exception e) {
			throw e;
		}
	}

}
