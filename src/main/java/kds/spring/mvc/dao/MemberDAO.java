package kds.spring.mvc.dao;

import kds.spring.mvc.vo.MemberVO;

public interface MemberDAO {

	int insertMember(MemberVO mvo);

	MemberVO selectOneMember();

	int selectOneMember(MemberVO mvo);

	

}
