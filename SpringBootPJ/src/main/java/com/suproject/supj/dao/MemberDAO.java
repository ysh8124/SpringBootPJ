package com.suproject.supj.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suproject.supj.dto.MemberDTO;
import com.suproject.supj.dto.PagingDTO;
import com.suproject.supj.dto.PostDTO;


@Repository
public class MemberDAO {
	
	@Autowired
	SqlSessionTemplate mybatis;
	

		public boolean idCheck(String id) {
			int result = mybatis.selectOne("Member.idCheck",id);
			System.out.println(result);
			
			if(result > 0) {
				return false;
			}else {return true;}
			
		}

		public boolean registMember(MemberDTO mem) {
			int result = mybatis.insert("Member.insertMember",mem);
			if(result>0) {return true;}
			else {return false;}
		}
	
		public boolean doLogin(Map<String,String> param) {
			
			int result = mybatis.selectOne("Member.doLogin",param);
			
			if(result > 0) {
				return true;
			}else {return false;}
		}
		
		public boolean addPost(Map<String,String> param) {
			
			int result = mybatis.insert("Member.addPost",param);
			
			if(result > 0) {
				return true;
			}else {return false;}
		}
		
		public int totalPost() {
			int total = mybatis.selectOne("Member.totalPost");
			
			return total;
		}
		
		public List<PostDTO> selectPost(PagingDTO paging){
			List<PostDTO> list = mybatis.selectList("Member.selectPost",paging);
			System.out.println(paging.getKind());			
			
			return list;
		}
		
		public PostDTO postView(int seq) {
			return mybatis.selectOne("Member.postView",seq);
		}
		
		public int postModify(Map<String,String> param) {
		    
			return mybatis.update("Member.postModify",param);
		}
		
		public int postDelete(int seq) {
			return mybatis.delete("postDelete",seq);
		}
		
}
