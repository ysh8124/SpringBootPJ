package com.suproject.supj;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;

public class JDBCTest {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		
		try(Connection con = 
				DriverManager.getConnection(
                // Oracle19 ������ ��� => "jdbc:oracle:thin:@localhost:1521:orcl"
                // Oracle11 ������ ��� => "jdbc:oracle:thin:@localhost:1521:XE"
						"jdbc:oracle:thin:@localhost:1521:xe",
						"su",
						"su")){
			System.out.println(con);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
	}
}
