package kds.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@GetMapping("/list")
	public String list() {
		return "list/list";
	}
	
	@GetMapping("/view")
	public String view() {
		return "view/view";
	}
	
	@GetMapping("/write")
	public String join() {
		return "write/write";
	}

}
