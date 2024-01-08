package com.restaurant.res;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Properties;
import javax.servlet.http.Part;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.MultipartConfigElement;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
@MultipartConfig
public class Register extends HttpServlet {
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		
	
		 	String url = "jdbc:postgresql://localhost:5432/Zomato";
		    String dbUsername = "postgres";
		    String dbPassword = "root";
		    String query="INSERT INTO Users(name,password,email,address,dob,user_image) VALUES(?,?,?,?,?,?)";
		    String name=req.getParameter("name");
		    String password=req.getParameter("password");
		    String email=req.getParameter("email");
		    String address=req.getParameter("address");
		    String dob=req.getParameter("dob");
	        Part filePart = req.getPart("file");
	        byte[] imageData = getByteArrayFromInputStream(filePart.getInputStream());
	      
			System.out.println(name);
			
			
			try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection(url, dbUsername, dbPassword);
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, name);
			pst.setString(2, password);
			pst.setString(3, email);
			pst.setString(4, address);
			pst.setString(5, dob);
			pst.setBytes(6, imageData);
			
			pst.executeUpdate();	
			System.out.println("Successfully added");
			res.getWriter().println("success");
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.print("some error");
		}
		
		
	}

	 private byte[] getByteArrayFromInputStream(InputStream inputStream) throws IOException {
		 try {
             byte[] buffer = new byte[inputStream.available()];
             inputStream.read(buffer);
             return buffer;
         } finally {
             inputStream.close();
         }
	}


}
