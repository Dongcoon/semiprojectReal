package kds.spring.mvc.dao;

import java.util.Collections;

import javax.sql.DataSource;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kds.spring.mvc.vo.MemberVO;

@Repository("mdao")
public class MemberDAOImpl implements MemberDAO{

//	@Autowired
//	private JdbcTemplate jdbcTemplete;
	private SimpleJdbcInsert simpleInsert;
	private NamedParameterJdbcTemplate jdbcNamedTemplate;
	
	private RowMapper<MemberVO> memberMapper = BeanPropertyRowMapper.newInstance(MemberVO.class);
	
	public MemberDAOImpl(DataSource datasource) {
		simpleInsert = new SimpleJdbcInsert(datasource)
				.withTableName("member")
				.usingColumns("userid","passwd","name","email");
		
		jdbcNamedTemplate = new NamedParameterJdbcTemplate(datasource);
	}
	
	
//	@Override
//	public int insertMember(MemberVO mvo) {
//		//보안상 끊어서
//		String sql = " insert into member"
//				+"(userid, passwd, name, email)"
//				+ " values(?,?,?,?)";
//		Object[] params = new Object[] {
//				mvo.getUserid(), mvo.getPasswd(),
//				mvo.getName(),mvo.getEmail()
//		};
//		
//		return jdbcTemplete.update(sql,params);
//	}
	public int insertMember(MemberVO mvo) {
		SqlParameterSource params = 
				new BeanPropertySqlParameterSource(mvo);
		
		return simpleInsert.execute(params);
	}


	@Override
	public MemberVO selectOneMember() {
		String sql = "select userid,name,email,regdate from member"
				+ " where mno = 1";
		
		return jdbcNamedTemplate.queryForObject(sql, Collections.emptyMap(), memberMapper);
	}
	
}
