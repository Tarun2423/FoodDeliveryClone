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

@WebServlet("/updateItems")
public class UpdateFoodItem extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        String url = "jdbc:postgresql://localhost:5432/Zomato";
        String dbUsername = "postgres";
        String dbPassword = "root";
        String query = "UPDATE Fooditems SET items=? WHERE restaurantname=?";
        String items = req.getParameter("items");
        String resname = req.getParameter("name");
        System.out.print("insidee "+resname);
        System.out.println("Items: " + items);

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
                 PreparedStatement pst = con.prepareStatement(query)) {

                pst.setString(1, items);
                pst.setString(2, resname);

                int affectedRows = pst.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Successfully updated");
                    res.getWriter().println("success");
                } else {
                    System.out.println("Update failed");
                    res.getWriter().println("failed");
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // or System.out.println("Error: " + e.getMessage());
            res.getWriter().println("failed");
        }
    }
}
