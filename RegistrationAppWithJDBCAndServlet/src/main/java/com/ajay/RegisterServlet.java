package com.ajay;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Accept the data
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String city = request.getParameter("city");
		
		String URL="jdbc:mysql://localhost:3306/java100";
		String username = "root";
		String userpass="Ajay@9977";
		//Database (JDBC)
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection(URL,username,userpass);
			PreparedStatement pstmt = con.prepareStatement("Insert into personinformation values(?,?,?,?)");
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, password);
			pstmt.setString(4, city);
			int rowsaffected = pstmt.executeUpdate();
			if(rowsaffected!=0) {
				System.out.println("Successfully inserted....");
				RequestDispatcher rd = request.getRequestDispatcher("Welcome.html");
				rd.forward(request, response);
//				out.println("<h2 style='color:green;'>Successfully Registered</h2>");
			}
			else {
				System.out.println("Unsuccessful");
				out.print("Try Again");
				RequestDispatcher rd = request.getRequestDispatcher("Registration.html");
				rd.include(request, response);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
