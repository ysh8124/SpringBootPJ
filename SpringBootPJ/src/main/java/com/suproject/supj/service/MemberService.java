package com.suproject.supj.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suproject.supj.dao.MemberDAO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO dao;
	
	
	public boolean idCheck(String id) {
		try {
			return dao.idCheck(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
