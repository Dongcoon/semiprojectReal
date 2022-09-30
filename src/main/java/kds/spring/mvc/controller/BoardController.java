package kds.spring.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kds.spring.mvc.service.BoardService;
import kds.spring.mvc.vo.BoardVO;

@Controller
public class BoardController {
	
	@Autowired // >> 버전에 따라 Spring container가 @Service("bsrv")에 의해 자동생성하긴하지만 가독성 떨어지는단점.
 	private BoardService bsrv;

	// 로그 유형 : trace, debug, info, warn, error
	protected Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/write")
	public String write() {
		LOGGER.info("글쓰기 호출!");
		
		return "board/write";
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
	public String list() {
		return "board/list";
	}
	
	@GetMapping("/view")
	public String view() {
		return "board/view";
	}
	

}
