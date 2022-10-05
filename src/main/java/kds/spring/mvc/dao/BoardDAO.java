package kds.spring.mvc.dao;

import java.util.List;

import kds.spring.mvc.vo.BoardVO;

public interface BoardDAO {

	int insertBoard(BoardVO bvo);

	List<BoardVO> selectBoard(int snum);

	BoardVO selectOneBoard(String bno);

	int selectCountBoard();

}
