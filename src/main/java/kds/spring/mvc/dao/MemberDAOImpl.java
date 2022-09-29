package kds.spring.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kds.spring.mvc.vo.MemberVO;

@Repository("mdao")
public class MemberDAOImpl implements MemberDAO{

	@Autowired
	private JdbcTemplate jdbcTemplete;
	
	
	@Override
	public int insertMember(MemberVO mvo) {
		//보안상 끊어서
		String sql = " insert into member"
				+"(userid, passwd, name, email)"
				+ " values(?,?,?,?)";
		Object[] params = new Object[] {
				mvo.getUserid(), mvo.getPasswd(),
				mvo.getName(),mvo.getEmail()
		};
		
		return jdbcTemplete.update(sql,params);
	}
	
}
