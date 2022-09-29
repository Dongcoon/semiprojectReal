package kds.spring.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kds.spring.mvc.service.BoardService;
import kds.spring.mvc.service.MemberService;
import kds.spring.mvc.vo.BoardVO;
import kds.spring.mvc.vo.MemberVO;

@Controller
public class BoardController {
	@Autowired
	private BoardService bsrv;

	// 로그 유형 : trace, debug, info, warn, error
	protected Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/write")
	public String write() {
		LOGGER.info("write호출!");
		
		return "board/write";
	}
	@PostMapping("/write")
	public String writeok(BoardVO bvo) {
		
		LOGGER.info("writeok 호출! {}", bvo);
		
		// 게시물 저장
		if(bsrv.newBoard(bvo))
			LOGGER.info("글작성 성공");
		
		return "redirect:/write";
	}
	
	@GetMapping("/list")
	public String list() {
		return "board/list";
	}
	
	@GetMapping("/view")
	public String view() {
		return "board/view";
	}
	

}
