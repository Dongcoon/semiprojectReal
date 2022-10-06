package kds.spring.mvc.dao;

import java.util.List;

import kds.spring.mvc.vo.MemberVO;
import kds.spring.mvc.vo.Zipcode;

public interface MemberDAO {

	int insertMember(MemberVO mvo);

	MemberVO selectOneMember(String uid);

	int selectOneMember(MemberVO mvo);

	int selectCountUserid(String uid);

	List<Zipcode> selectZipcode(String dong);

	

}
