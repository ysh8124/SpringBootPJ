package com.suproject.supj.contoller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suproject.supj.dao.MemberDAO;
import com.suproject.supj.dto.MemberDTO;
import com.suproject.supj.dto.PostDTO;
import com.suproject.supj.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private MemberService mservice;
	
	@GetMapping("/")
	 public String index() {
		
		
		return "Home/index";
		
	}
	
	@PostMapping("idCheck")
	@ResponseBody
	 public boolean idCheck(String id) throws Exception{
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
	
	@PostMapping("registMember")
	 public String registMember(String loginId,String loginPw,String nickname) {
		MemberDTO mem = new MemberDTO();
		if(nickname.contentEquals("")){nickname = loginId;}
		System.out.println("오긴옴?");
		mem.setId(loginId);
		mem.setPw(mservice.sha512(loginPw));
		mem.setNickname(nickname);
		
		
		boolean result = dao.registMember(mem);
		
		System.out.println(result);
			
		return "Home/index";
	}
	
	@GetMapping("goBoard")
	public String goBoard(Model model) {
		List<PostDTO> list = dao.selectPost();
		model.addAttribute("list",list);
		
		if(session.getAttribute("id") == null) {return "Home/Login";}
		return "Home/board";
	}
	
	@PostMapping("login")
	public String login(Model model,String id,String pw) {
		return "Home/login";
	}
	
	@PostMapping("doLogin")
	@ResponseBody
	public boolean doLogin(Model model,String id,String pw) {
		Map<String,String> param = new HashMap<>();
		String pw2 = mservice.sha512(pw);
		
		param.put("id",id);
		param.put("pw",pw2);
		boolean check = dao.doLogin(param);
		
		if(check == true) {session.setAttribute("id", id);}
		
		return check;
	}
	
	@GetMapping("goWrite")
	public String goWrite() {
		
		return "Home/write";
	}
	
	@PostMapping("addPost")
	@ResponseBody
	public String addPost(String title, String content) {
		Map<String,String> param = new HashMap<>();
		
		param.put("title", title);
		param.put("content", content);
		param.put("writer", (String) session.getAttribute("id"));
		
		boolean check = dao.addPost(param);
		if(check == true) {return "Home/goBoard";}
		else{return "Home/goWrite";}
	}
	
}
