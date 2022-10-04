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
	public String list(Model m) {
		LOGGER.info("글리스트 출력!");
		
		m.addAttribute("bdlist",bsrv.readBoard());
		
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
