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
import com.restaurant.model.Restaurant;



@WebServlet("/getsingleres")
public class GetSingleRestaurant extends HttpServlet{
	
	protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
		
		ArrayList<Restaurant> resList=new ArrayList<Restaurant>();
	 	String url = "jdbc:postgresql://localhost:5432/Zomato";
	    String dbUsername = "postgres";
	    String dbPassword = "root";
	    String query="SELECT * FROM Restaurant where name=?";
	    String resname=req.getParameter("name");
	try {
		Class.forName("org.postgresql.Driver");
		Connection con=DriverManager.getConnection(url, dbUsername, dbPassword);
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1, resname);
		ResultSet rs=pst.executeQuery();
		if(rs.next()) {
			int id=rs.getInt("id");
			String name=rs.getString("name");
			String location=rs.getString("location");
			String contact=rs.getString("contact");
			String r=rs.getString("rating");
			String image=rs.getString("image");
			String h=rs.getString("highlight");
			String images=rs.getString("displayimages");
			resList.add((new Restaurant(id,name,location,contact,r,image,h,images)));
		   Gson gson = new Gson();
           String json = gson.toJson(resList);
           res.setContentType("application/json");
            res.getWriter().write(json);
		}
		else {
			res.getWriter().write("some error");
		}
	}catch (Exception e) {
		// TODO: handle exception
		System.out.print("some error");
	}
	
		
		
	}

}
