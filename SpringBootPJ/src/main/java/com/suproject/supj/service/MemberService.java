package com.suproject.supj.service;



import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suproject.supj.dao.MemberDAO;

@Service
public class MemberService {
	
	public String sha512(String input){

		String toReturn = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			digest.update(input.getBytes("utf8"));
			toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return toReturn;
	}
	
	@Autowired
	private MemberDAO dao;
	
	
	public boolean idCheck(String id) {
		try {
			return dao.IdCheck(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	

	
}
