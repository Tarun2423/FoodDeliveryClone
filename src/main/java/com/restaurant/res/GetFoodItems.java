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
import com.restaurant.model.Items;
import com.restaurant.model.Restaurant;



@WebServlet("/getfood")
public class GetFoodItems extends HttpServlet{
	
	protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		
		ArrayList<Items> resList=new ArrayList<Items>();
	 	String url = "jdbc:postgresql://localhost:5432/Zomato";
	    String dbUsername = "postgres";
	    String dbPassword = "root";
	    String query="SELECT * FROM Fooditems where restaurantname=?";
	    String name=req.getParameter("resname");
	try {
		Class.forName("org.postgresql.Driver");
		Connection con=DriverManager.getConnection(url, dbUsername, dbPassword);
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1,name);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			int id=rs.getInt("id");
			String resname=rs.getString("restaurantname");
			String items=rs.getString("items");
			resList.add((new Items(resname,items)));
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
