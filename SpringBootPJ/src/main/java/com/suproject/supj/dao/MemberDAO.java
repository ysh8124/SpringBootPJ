package com.suproject.supj.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	
	@Autowired(required = false)
	private SqlSessionTemplate mybatis;
	
	public boolean idCheck(String id){
		System.out.println(id);
		int result = 0;
		
		   result = mybatis.selectOne("member.idcheck",id);
		   System.out.println(result);
		   
		   if(result>0) {return false;}
		   else {return true;}
	}
	
}
