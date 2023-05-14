package com.suproject.supj.dao;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public class MemberDAO {
	
	@Autowired
	SqlSessionTemplate mybatis;

		public boolean idCheck(String id) {
			int result = mybatis.selectOne("Member.idCheck",id);
			
			if(result > 0) {
				return false;
			}else {return true;}
			
		}
	
}
