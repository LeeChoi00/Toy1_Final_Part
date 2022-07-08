package toy.shopping.pay.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import toy.shopping.pay.member.model.vo.Admin;
import toy.shopping.pay.member.model.vo.Member;

@Repository
public class MemberDAO {
	
	public Admin adminId(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("memberMapper.adminId");
	}

	public Member login(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.selectOne("memberMapper.login", m);
	}



}
