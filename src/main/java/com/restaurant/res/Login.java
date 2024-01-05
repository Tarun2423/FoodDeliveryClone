package com.restaurant.res;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jwt.*;
import com.restaurant.model.User;

import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Login extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
		
		   res.setHeader("Access-Control-Allow-Origin", "*");
		    res.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		   
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		
		String username=req.getParameter("name");
		String password=req.getParameter("password");
		
		
		try {
		    Class.forName("org.postgresql.Driver");
		    String url = "jdbc:postgresql://localhost:5432/Zomato";
		    String dbUsername = "postgres";
		    String dbPassword = "root";
		    ArrayList<User> resList=new ArrayList<User>();
		    try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword)) {
		        String query = "SELECT * FROM Users WHERE name=? AND password=?";
		        
		        try (PreparedStatement pst = con.prepareStatement(query)) {
		            pst.setString(1, username);
		            pst.setString(2, password);
		            
		            try (ResultSet rs = pst.executeQuery()) {
		                if (rs.next()) {
		                	String name=rs.getString("name");
		                	String pass=rs.getString("password");
		                	String email=rs.getString("email");
		                	String dob=rs.getString("dob");
		                	String address=rs.getString("address");
		                	resList.add(new User(name,pass,email,address,dob));
		                	  Gson gson = new Gson();
		                      String json = gson.toJson(resList);
		                      res.setContentType("application/json");
		                      res.getWriter().write(json);
		                } else {
		                    out.println("Invalid credentials");
		                }
		            }
		        }
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		    out.println("Error from DB");
		}
	

		
		
		
	
		
		
	}
	
}
