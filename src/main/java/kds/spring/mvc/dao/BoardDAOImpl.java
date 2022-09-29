package kds.spring.mvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kds.spring.mvc.vo.BoardVO;
import kds.spring.mvc.vo.MemberVO;

@Repository("bdao")
public class BoardDAOImpl implements BoardDAO{

	@Autowired
	private JdbcTemplate jdbcTemplete;
	
	
	@Override
	public int insertBoard(BoardVO bvo) {
		//보안상 끊어서
		String sql = " insert into board"
				+"(title, userid, contents)"
				+ " values(?,?,?)";
		Object[] params = new Object[] {
				bvo.getTitle(), bvo.getUserid(),
				bvo.getContents()
		};
		
		return jdbcTemplete.update(sql,params);
	}
}
