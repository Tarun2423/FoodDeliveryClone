package com.restaurant.res;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addorder")
public class AddOrder extends HttpServlet {

	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		
		
	 	String url = "jdbc:postgresql://localhost:5432/Zomato";
	    String dbUsername = "postgres";
	    String dbPassword = "root";
	    String query="INSERT INTO Orders(restaurantname,customer,items,amount,status) VALUES(?,?,?,?,?)";
	    String restaurant=req.getParameter("restaurant");
	    String customer=req.getParameter("customer");
	    String items=req.getParameter("items");
	    String amount=req.getParameter("amount");
	    String status="Placed";
	    
		try {
			Class.forName("org.postgresql.Driver");
			Connection con=DriverManager.getConnection(url, dbUsername, dbPassword);
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, restaurant);
			pst.setString(2, customer);
			pst.setString(3, items);
			pst.setString(4, amount);
			pst.setString(5, status);
			pst.executeUpdate();
			System.out.println("Successfully added");
			res.getWriter().println("success");
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.print("some error");
		}
		
	    
	}
}
