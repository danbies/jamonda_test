package com.example.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.login.service.LoginService;
import com.example.member.dto.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller("loginController")
public class LoginControllerImpl implements LoginController{
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MemberDTO memberDTO;
	
	@Override
	@GetMapping("/login/guestloginForm.do")
	public ModelAndView guestloginForm(@ModelAttribute("member") MemberDTO member,
			@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "result", required = false) String result,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

			HttpSession session=request.getSession(); //세션이 있다면 세션 정보를 가져오고, 없다면 세션을 생성하는 거다.
			session.setAttribute("action",action);
			
			ModelAndView mav=new ModelAndView();
			mav.addObject("result", result);
			mav.setViewName("/login/guestloginForm");
			return mav;
	}

	@Override
	@PostMapping("/login/guestlogin.do")
	public ModelAndView guestlogin(@ModelAttribute("member") MemberDTO member, RedirectAttributes rAttr, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		memberDTO=loginService.login(member);	//로그인한 사람의 email과 pwd 가져와서 memberDTO 변수에 넣기
		
		ModelAndView mav=new ModelAndView();
		
		if(memberDTO != null) {	//
			if(memberDTO.getEmail().equals("admin")) {
				//관리자로 로그인할 경우
				HttpSession session=request.getSession();
				session.setAttribute("admin", memberDTO);
				session.setAttribute("isLogOn", true);
				String action=(String)session.getAttribute("action");
				if(action != null) {
					mav.setViewName("redirect:/admin/mainAdmin.do");	//관리자 메인페이지 이동
				}else {
					mav.setViewName("redirect:/main.do");
				}
			} else {
				//일반회원으로 로그인할 경우
				HttpSession session=request.getSession();
				session.setAttribute("guest", memberDTO);
				session.setAttribute("isLogOn", true);
				String action=(String)session.getAttribute("action");
				if(action != null) {
					mav.setViewName("redirect:" + action);
				}else {
					mav.setViewName("redirect:/main.do");
				}
			}
		}else {
			//회원 정보가 없다.
			rAttr.addAttribute("result","아이디나 비밀번호가 틀립니다. 다시 로그인해주세요.");
			mav.setViewName("redirect:/login/guestloginForm.do");
		}
		return mav;
	}

	@Override
	@GetMapping("/login/guestlogout.do")
	public ModelAndView guestlogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		session.removeAttribute("guest");
		session.removeAttribute("isLogOn");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/main.do");
		return mav;
	}


	@Override
	@GetMapping("/login/hostloginForm.do")
	public ModelAndView hostloginForm(@ModelAttribute("member") MemberDTO member,
			@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "result2", required = false) String result2,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(); //세션이 있다면 세션 정보를 가져오고, 없다면 세션을 생성하는 거다.
		session.setAttribute("action",action);
		ModelAndView mav=new ModelAndView();
		mav.addObject("result2", result2);
		mav.setViewName("/login/hostloginForm");
		return mav;
	}
	

	@Override
	@PostMapping("/login/hostlogin.do")
	public ModelAndView hostlogin(@ModelAttribute("member") MemberDTO member, RedirectAttributes rAttr, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		memberDTO=loginService.login(member);
		
		ModelAndView mav=new ModelAndView();
		if(memberDTO != null) {
			//회원이 있다.
			HttpSession session=request.getSession();
			session.setAttribute("host", member);
			session.setAttribute("isLogon", true);
			String email=memberDTO.getEmail();
			session.setAttribute("email", member);
			String action=(String)session.getAttribute("action");
			if(action != null) {
				mav.setViewName("redirect:" + action);
			}else {
				mav.setViewName("redirect:/mainhost.do");
				//mav.setViewName("redirect:/accommodation/regAccommodation.do");
			}
		}else {
			//회원 정보가 없다.
			rAttr.addAttribute("result2","아이디나 비밀번호가 틀립니다. 다시 로그인해주세요.");
			mav.setViewName("redirect:/login/hostloginForm.do");
		}
		return mav;
	}

	@Override
	@GetMapping("/login/hostlogout.do")
	public ModelAndView hostlogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		session.removeAttribute("host");
		session.removeAttribute("isLogon");
		session.removeAttribute("email");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/mainhost.do");
		return mav;
	}
}
