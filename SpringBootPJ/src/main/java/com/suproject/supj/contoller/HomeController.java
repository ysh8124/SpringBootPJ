package com.suproject.supj.contoller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suproject.supj.dao.MemberDAO;
import com.suproject.supj.dto.MemberDTO;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private MemberDAO dao;
	
	@GetMapping("/")
	 public String index() {
		
		
		return "Home/index";
		
	}
	
	@PostMapping("idCheck")
	@ResponseBody
	 public boolean idCheck(String id) throws Exception{
			//MemberDAO dao = new MemberDAO();
			System.out.println("컨트롤러"+id);
			boolean result = dao.idCheck(id);
			
			System.out.println(result+"나왔니?");
			session.setAttribute(id, result);
			
		
		return result;
	}
	
	@GetMapping("register")
	 public String register() {
		
		
		return "Home/register";
		
	}
	
	@GetMapping("registMember")
	 public String registMember(String id,String pw) {
		MemberDTO mem = new MemberDTO();	
		
		mem.setId(id);
		mem.setPw(pw);
		
	//	boolean result = dao.registMember(mem);
		
		//System.out.println(result);
			
		return "Home/index";
	}
	
	
	
}
