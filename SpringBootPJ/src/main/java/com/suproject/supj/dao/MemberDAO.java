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

		public boolean IdCheck(String id) {
			int result = mybatis.selectOne("Member.idCheck",id);

			
			if(result > 0) {
				return false;
			}else {return true;}
			
		}

		public boolean RegistMember(MemberDTO mem) {
			int result = mybatis.insert("Member.insertMember",mem);
			if(result>0) {return true;}
			else {return false;}
		}
	
		public boolean DoLogin(Map<String,String> param) {
			
			int result = mybatis.selectOne("Member.doLogin",param);
			
			if(result > 0) {
				return true;
			}else {return false;}
		}
		
		public boolean AddPost(Map<String,String> param) {
			
			int result = mybatis.insert("Member.addPost",param);
			
			if(result > 0) {
				return true;
			}else {return false;}
		}
		
		public int TotalPost() {
			int total = mybatis.selectOne("Member.totalPost");
			
			return total;
		}
		
		public List<PostDTO> SelectPost(PagingDTO paging){
			List<PostDTO> list = mybatis.selectList("Member.selectPost",paging);
		
			
			return list;
		}
		
		public PostDTO PostView(int seq) {
			return mybatis.selectOne("Member.postView",seq);
		}
		
		public int PostModify(Map<String,String> param) {
		    
			return mybatis.update("Member.postModify",param);
		}
		
		public int PostDelete(int seq) {
			return mybatis.delete("postDelete",seq);
		}
		
}
