package kds.spring.mvc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kds.spring.mvc.vo.BoardVO;

@Repository("bdao")
public class BoardDAOImpl implements BoardDAO{
	
	@Autowired
	private SqlSession sqlSession;	
	
	@Override
	public int insertBoard(BoardVO bvo) {
		
		return sqlSession.insert("insertBoard", bvo);
	}

	@Override
	public List<BoardVO> selectBoard(String fkey, String fval,int snum) {
		
		Map<String,Object> param = new HashMap<>();
		param.put("fkey", fkey);
		param.put("fval", fval + "%");
		param.put("snum", snum);
				
		return sqlSession.selectList("selectBoard", param);
	}

	@Override
	public BoardVO selectOneBoard(String bno) {
		
		sqlSession.update("viewboard", bno);
		
		return sqlSession.selectOne("selectOneBoard", bno);
	}

	@Override
	public int selectCountBoard(String fkey, String fval) {
		Map<String,Object> param = new HashMap<>();
		param.put("fkey", fkey);
		param.put("fval", fval + "%");
		
		return sqlSession.selectOne("selectCountBoard", param);
	}

	@Override
	public int deleteBoard(String bno) {
		
		return sqlSession.delete("deleteBoard", bno);
	}

	@Override
	public int modifyBoard(BoardVO bvo) {
				
		return sqlSession.update("modifyBoard", bvo);
	}
}
