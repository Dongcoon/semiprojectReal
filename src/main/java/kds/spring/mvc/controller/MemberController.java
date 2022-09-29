package kds.spring.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kds.spring.mvc.service.MemberService;
import kds.spring.mvc.vo.MemberVO;

@Controller
public class MemberController {
		
		@Autowired
		private MemberService msrv;
	
		// 로그 유형 : trace, debug, info, warn, error
		protected Logger LOGGER = LoggerFactory.getLogger(getClass());
		
		@GetMapping("/join")
		public String join() {
			LOGGER.info("join호출!");
			
			return "join/join";
		}
		@PostMapping("/join")
		public String joinok(MemberVO mvo) {
			
			LOGGER.info("joinok 호출! {}", mvo);
			
			// 회원정보 저장
			if(msrv.newMember(mvo))
				LOGGER.info("회원가입 성공");
			
			return "redirect:/login";
		}
		
		@GetMapping("/login")
		public String login() {
			return "join/login";
		}
		@PostMapping("/login")
		public String loginok() {
			
			return "redirect:/myinfo";
		}
		
		@GetMapping("/myinfo")
		public String myinfo() {
			return "join/myinfo";
		}
}
