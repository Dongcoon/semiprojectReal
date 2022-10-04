package kds.spring.mvc.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		
		//로그인 안했다면 -> redirect:/login
		//로그인 상태면 -> join/myinfo
		@GetMapping("/join")
		public String join(Model m, HttpSession sess) {
			String returnPage = "join/join";
			LOGGER.info("join호출!");
			
			if(sess.getAttribute("m") != null) {
				returnPage = "redirect:/myinfo";
			}
			return returnPage;
			
		}
		@PostMapping("/join")
		public String joinok(MemberVO mvo) {
			
			LOGGER.info("joinok 호출! {}", mvo);
			
			// 회원정보 저장
			if(msrv.newMember(mvo))
				LOGGER.info("회원가입 성공");
			
			return "redirect:/login";
		}
		
		@GetMapping("/login")	//로그인 처리
		public String login() {
			
			String returnPage = "join/login";
			

			return returnPage;
		}
		@PostMapping("/login")
		public String loginok(MemberVO mvo, HttpSession sess) {
			
			String returnPage = "join/lgnfail";
			
			if(msrv.checkLogin(mvo)) {
				sess.setAttribute("m", mvo); //회원정보를 세션에 저장
				returnPage = "redirect:/myinfo";
			}
			return returnPage;
		}
		
		@GetMapping("/logout")
		public String logout(HttpSession sess) {
			
			sess.invalidate(); //모든 세션 제거
			
			return "redirect:/login";
		}
		
		//로그인 안했다면 -> redirect:/login
		//로그인 상태면 -> join/myinfo
		@GetMapping("/myinfo")	
		public String myinfo(Model m, HttpSession sess) {
			
			String returnPage = "join/myinfo";
			
			if(sess.getAttribute("m") != null) {
				MemberVO mvo = (MemberVO) sess.getAttribute("m");
				m.addAttribute("mbr",msrv.readOneMember(mvo.getUserid()));
			} else {
				returnPage = "redirect:/login";
			}
			return returnPage;
		}
}
