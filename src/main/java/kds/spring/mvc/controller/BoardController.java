package kds.spring.mvc.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import kds.spring.mvc.service.BoardService;
import kds.spring.mvc.vo.BoardVO;

@Controller
public class BoardController {
	
	@Autowired // >> 버전에 따라 Spring container가 @Service("bsrv")에 의해 자동생성하긴하지만 가독성 떨어지는단점.
 	private BoardService bsrv;
	
	/* 페이징 처리
	   페이지당 게시물 수perPage : 25 
	   총페이지수 : 전체게시물 수 / 페이지당 게시물 수 
	   pages : ceil(getTotalPage/perPage) */
	
	/* 페이지별 읽어올 게시글 범위
	   총 게시글이 55건 이라 할 때
	   1 page : 1번째 ~ 25번째 게시글 읽어옴 
	   2 page : 26번째 ~ 50번째 게시글 읽어옴 
	   3 page : 51번째 ~ 75번째 게시글 읽어옴 
	   i page : m번째 ~ n 번째 게시글 읽어옴
	   m = (i - 1) * 25 + 1  */

	// 로그 유형 : trace, debug, info, warn, error
	protected Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	// 로그인 안했다면 -> redirect:/login
	// 로그인 했다면 -> board/write
	@GetMapping("/write")
	public String write(HttpSession sess) {
		String returnPage = "board/write";
		
		
		if(sess.getAttribute("m") == null) {
			
			returnPage = "redirect:/login";
		}
		
		return returnPage;
	}
	
	@PostMapping("/write")
	public String writeok(BoardVO bvo) {
		
		LOGGER.info("작성된 글! {}", bvo);
		
		// 게시물 저장
		if(bsrv.newBoard(bvo))
			LOGGER.info("글작성 성공");
		
		return "redirect:/list";
	}
	
	@GetMapping("/list")
	public String list(Model m, String cpg) {
		
		int perPage = 25;
		int snum = ( Integer.parseInt(cpg) - 1 ) * perPage;
		
		m.addAttribute("bdlist",bsrv.readBoard(snum));
		
		return "board/list";
	}
	
	//ModelAndView 클래스 이용
	@GetMapping("/view")
	public ModelAndView view(ModelAndView mv,String bno) {
		mv.setViewName("board/view");
		mv.addObject("bd",bsrv.readOneBoard(bno));
		return mv;
	}
	

}
