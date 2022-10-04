package kds.spring.mvc.dao;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kds.spring.mvc.vo.BoardVO;

@Repository("bdao")
public class BoardDAOImpl implements BoardDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplete;
	private SimpleJdbcInsert simpleInsert;
	private NamedParameterJdbcTemplate jdbcNamedTemplate;
	
	private RowMapper<BoardVO> boardMapper = BeanPropertyRowMapper.newInstance(BoardVO.class);
	
	public BoardDAOImpl(DataSource datasource) {
		simpleInsert = new SimpleJdbcInsert(datasource)
				.withTableName("board")
				.usingColumns("title","userid","contents");
		
		jdbcNamedTemplate = new NamedParameterJdbcTemplate(datasource);
	}
	
	
//	@Override
//	public int insertBoard(BoardVO bvo) {
//		//보안상 끊어서
//		String sql = " insert into board"
//				+"(title, userid, contents)"
//				+ " values(?,?,?)";
//		Object[] params = new Object[] {
//				bvo.getTitle(), bvo.getUserid(),
//				bvo.getContents()
//		};
//		
//		return jdbcTemplete.update(sql,params);
//	}
	@Override
	public int insertBoard(BoardVO bvo) {
		SqlParameterSource params = 
				new BeanPropertySqlParameterSource(bvo);
		
		return simpleInsert.execute(params);
	}


	@Override
	public List<BoardVO> selectBoard() {
		String sql = "select bno, title, userid, regdate, views from board "
				+ " order by bno desc ";
		
		return jdbcNamedTemplate.query(sql, Collections.emptyMap(),boardMapper);
	}


	@Override
	public BoardVO selectOneBoard(String bno) {
		
		// 본문글에 대한 조회수 증가시키기
		String sql = " update board set views = views +1 "
				+ " where bno = ? ";
		Object[] param = { bno };
		jdbcTemplete.update(sql,param);
		
		// 본문글 가져오기
		sql = "select title, userid, regdate, views, contents from board "
				+ " where bno = ? ";
		
		return jdbcTemplete
				.queryForObject(sql, param, boardMapper);
	}
}
