package com.the;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Login")
public class Login extends HttpServlet {
	
       


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname=request.getParameter("uname");
		String pass=request.getParameter("pass");
		String p ;
		HttpSession session =  request.getSession(true);
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","db","123");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from officer where uname='"+uname+"' and pass='"+pass+"'");
            if(rs.next())
            {
            	p = (String) rs.getString(1);
            	
            	session.setAttribute("dis", p);
            	
               	response.sendRedirect("Welcome.jsp");
            }
            
		}
		catch(Exception e)
		{
			session.invalidate();
		}
	}

}
