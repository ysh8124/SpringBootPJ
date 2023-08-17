package com.suproject.supj.contoller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suproject.supj.dao.MemberDAO;
import com.suproject.supj.dto.MemberDTO;
import com.suproject.supj.dto.PagingDTO;
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
		mem.setId(loginId);
		mem.setPw(mservice.sha512(loginPw));
		mem.setNickname(nickname);
		
		
		boolean result = dao.registMember(mem);
		
		System.out.println(result);
			
		return "Home/index";
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
	
	@GetMapping("goBoard")
	public String goBoard(PagingDTO paging,Model model, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage,
			@RequestParam(value="kind", required=false)String kind,
			@RequestParam(value="need", required=false)String need){
		
		System.out.println("들어옴?");
		
		int total = 1;
		total = dao.totalPost();
		

		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "10";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "10";
		} else if(kind == null) {
			kind = "";
		} else if(need == null) {
			need = "";
		}
		
		paging = new PagingDTO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage),kind,need);
		model.addAttribute("paging", paging);
		List<PostDTO> list = dao.selectPost(paging);
		model.addAttribute("list",list);
		
		if(session.getAttribute("id") == null) {return "Home/Login";}
		return "Home/board";
	}
	
	@GetMapping("goWrite")
	public String goWrite() {
		
		return "Home/write";
	}
	
	@PostMapping("addPost")
	public String addPost(String title, String content) {
		Map<String,String> param = new HashMap<>();
		
		param.put("title", title);
		param.put("content", content);
		param.put("writer", (String) session.getAttribute("id"));
		
		boolean check = dao.addPost(param);
		if(check == true) {return "redirect:goBoard";}
		else{return "Home/write";}
	}
	
	@GetMapping("postView")
	public String postView(Model model, int seq) {
			PostDTO post=dao.postView(seq);
			model.addAttribute("post",post);
			model.addAttribute("user",session.getAttribute("id"));
			
		return "Home/postView";
	}
	
	@GetMapping("goModify")
	public String goModify(Model model,int seq){
			PostDTO post = dao.postView(seq);
			model.addAttribute("post",post);
			
		return "Home/modify";
	}
	
	@PostMapping("postModify")
	public String postModify(Model model,String seq, String title, String content) {
		Map<String,String> param = new HashMap<>();
		param.put("seq", seq);
		param.put("title",title);
		param.put("content", content);
		System.out.println(seq+title+content);
		
		dao.postModify(param);
		
		return "redirect:goBoard";
	}
	
	@GetMapping("deletePost")
	public String postDelete(int seq) {
		dao.postDelete(seq);
		
		return "redirect:goBoard";
	}
	
	@GetMapping("logout")
	public String logout() {
		System.out.println("?");
		session.invalidate();
		
		return "Home/index";
	}
	
}
