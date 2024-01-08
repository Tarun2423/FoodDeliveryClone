package com.restaurant.res;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/AddRestaurant")
@MultipartConfig
public class AddRestaurant extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String contact = request.getParameter("contact");
        String highlight = request.getParameter("highlight");

        Part thumbnailPart = request.getPart("file");
        String thumbnailFileName = saveFile(thumbnailPart);

        List<Part> imageParts = request.getParts().stream()
                .filter(part -> part.getName().equals("images"))
                .collect(Collectors.toList());

        ArrayList<String> imageFileNames = new ArrayList<>();
        for (Part imagePart : imageParts) {
            String imageFileName = saveFile(imagePart);
            imageFileNames.add(imageFileName);
        }

        insertRestaurant(name, highlight, address, contact, thumbnailFileName, imageFileNames);

        response.getWriter().write("success");
    }

    private String saveFile(Part filePart) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + new File(filePart.getSubmittedFileName()).getName();

        String filePath = "C:/Users/ADMIN/eclipse-workspace/ZomatoClone/src/main/webapp/" + fileName;

        try (InputStream fileContent = filePart.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(new File(filePath))) {

            // Save the file to the local storage
            int read;
            byte[] bytes = new byte[1024];
            while ((read = fileContent.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }

        return fileName;
    }

    private void insertRestaurant(String name, String highlight, String address, String contact, String thumbnailFileName, List<String> imageFileNames) {

        String url = "jdbc:postgresql://localhost:5432/Zomato";
        String dbUsername = "postgres";
        String dbPassword = "root";
        try {
    		Class.forName("org.postgresql.Driver");
    		Connection con=DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO restaurant (name, location, contact, image, highlight, displayimages) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, contact);
            preparedStatement.setString(4, thumbnailFileName);
            preparedStatement.setString(5, highlight);

            preparedStatement.setString(6, String.join("/", imageFileNames));

            preparedStatement.executeUpdate();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
