package com.suproject.supj.contoller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suproject.supj.dao.MemberDAO;
import com.suproject.supj.dto.MemberDTO;

import jakarta.servlet.http.HttpSession;

@Controller

public class HomeController {
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/")
	 public String index() {
		
		
		return "Home/index";
		
	}
	
	@RequestMapping("idCheck")
	@ResponseBody
	 public boolean idCheck(String id) {
			MemberDAO dao = new MemberDAO();
			System.out.println("컨트롤러"+id);
			boolean result = dao.idCheck(id);
			
			System.out.println(result+"나왔니?");
			session.setAttribute(id, result);
			
		
		return result;
	}
	
	@RequestMapping("register")
	 public String register() {
		
		
		return "Home/register";
		
	}
	
	@RequestMapping("registMember")
	 public String registMember(String id,String pw) {
		MemberDTO mem = new MemberDTO();	
		MemberDAO dao = new MemberDAO();
		
		mem.setId(id);
		mem.setPw(pw);
		
	//	boolean result = dao.registMember(mem);
		
		//System.out.println(result);
			
		return "Home/index";
	}
	
	
	
}
