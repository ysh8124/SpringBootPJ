package com.suproject.supj.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suproject.supj.dto.MemberDTO;
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
		
		public List<PostDTO> selectPost(){
			return mybatis.selectList("selectPost");
		}
		
}
