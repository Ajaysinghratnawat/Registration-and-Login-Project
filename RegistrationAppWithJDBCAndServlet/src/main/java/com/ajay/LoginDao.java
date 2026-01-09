package com.ajay;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {
	public boolean validate(String name, String pass, String email) {
		boolean check = false;
		String URL = "jdbc:mysql://localhost:3306/java100";
		String username = "root";
		String userpass = "Ajay@9977";
		final String READDATA = "select * from personinformation where name = ? and email=? and password=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(URL, username, userpass);
			PreparedStatement pstmt = con.prepareStatement(READDATA);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, pass);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				check = true;
			} else {
				check = false;
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return check;
	}
}
