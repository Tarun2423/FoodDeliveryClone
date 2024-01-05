package com.restaurant.res;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nimbusds.jose.shaded.gson.Gson;
import com.restaurant.model.Order;




@WebServlet("/getorder")
public class Getorder extends HttpServlet{
	
	protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		
		ArrayList<Order> resList=new ArrayList<Order>();
	 	String url = "jdbc:postgresql://localhost:5432/Zomato";
	    String dbUsername = "postgres";
	    String dbPassword = "root";
	    String query="SELECT * FROM Orders where restaurantname=? and status=?";
	    String name=req.getParameter("resname");
	    String status="Placed";
	   
	try {
		Class.forName("org.postgresql.Driver");
		Connection con=DriverManager.getConnection(url, dbUsername, dbPassword);
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1,name);
		pst.setString(2, status);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			int id=rs.getInt("id");
			String resname=rs.getString("restaurantname");
			String customer=rs.getString("customer");
			String items=rs.getString("items");
			String amount=rs.getString("amount");
			resList.add((new Order(id,resname,customer,items,amount)));
		}
		   Gson gson = new Gson();
           String json = gson.toJson(resList);
           res.setContentType("application/json");
            res.getWriter().write(json);
       
	}catch (Exception e) {
		// TODO: handle exception
		System.out.print("some error");
	}
	
		
		
	}

}
