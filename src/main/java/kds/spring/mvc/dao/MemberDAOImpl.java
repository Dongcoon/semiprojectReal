package kds.spring.mvc.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import kds.spring.mvc.vo.MemberVO;
import kds.spring.mvc.vo.Zipcode;

@Repository("mdao")
public class MemberDAOImpl implements MemberDAO{

	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertMember(MemberVO mvo) {
		
		return sqlSession.insert("member.insertMember", mvo);
	}

	@Override
	public MemberVO selectOneMember(String uid) {
		
		return sqlSession.selectOne("member.selectOneMember", uid);
	}

	@Override	// 회원수 체크
	public int selectOneMember(MemberVO mvo) {
		
		return sqlSession.selectOne("member.selectCountMember", mvo);
	}

	@Override
	public int selectCountUserid(String uid) {

		return sqlSession.selectOne("member.selectCountUserid", uid);
	}

	@Override
	public List<Zipcode> selectZipcode(String dong) {

		return sqlSession.selectList("member.selectZipcode", dong);
	}
	
	
}

