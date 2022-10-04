package kds.spring.mvc.service;

import kds.spring.mvc.vo.MemberVO;

public interface MemberService {

	boolean newMember(MemberVO mvo);

	MemberVO readOneMember();

	boolean checkLogin(MemberVO mvo);

}
