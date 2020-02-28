package com.vivekh;
import java.sql.*;
import java.sql.ResultSet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Addservlet extends HttpServlet{
	
	public void service(HttpServletRequest req , HttpServletResponse res) throws IOException 
	{	
		Connection conn = null;
		Statement stmt =null;
		ResultSet rs = null;
		String TableName;
		String i = req.getParameter("username/email");
		String j = req.getParameter("password");
		PrintWriter out = res.getWriter();
		try {
			String userName = "root";
			String password = "root";
			String url = "jdbc:mysql://localhost:3306/userpass";
			
			// Class.forName("com.mysql.jdbc.Driver").newInstance();//C:\Program Files\Java\jdk1.8.0_231\bin
			//C:\mysql-connector-java-8.0.18\mysql-connector-java-8.0.18.jar
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url ,userName,password);
			//out.println("Database connection established");
			stmt=conn.createStatement();
			
			stmt.execute("select * from usernameandpassworddemo where UserId = "+i+" and UserPassword =MD5("+j+")");
			rs =stmt.getResultSet();
			rs.next();
			out.println("welcome home"+rs.getString("FirstName")+" "+rs.getString("LastName"));
			
		}
		catch(SQLSyntaxErrorException e) {out.println("Sorry please Register to login");}
		catch(Exception e) {out.println("Cannot connect to database server: "+e);}
		finally {
			if(conn != null){try{
				conn.close();
				out.println("Database connection terminated");
			}catch(Exception e){/*ignore close errors*/}
		}
		
		
		
		
	}

}}
