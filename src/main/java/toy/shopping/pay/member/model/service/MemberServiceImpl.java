package toy.shopping.pay.member.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import toy.shopping.pay.member.model.dao.MemberDAO;
import toy.shopping.pay.member.model.vo.Admin;
import toy.shopping.pay.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private MemberDAO mDAO;

	@Override
	public Admin adminId() {
		return mDAO.adminId(sqlSession);
	}
	
	
	@Override
	public Member login(Member m) {
		return mDAO.login(sqlSession, m);
	}





}
