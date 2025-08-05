package com.tap.servlets;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Time;

import com.tap.Dao.RestaurantDao;
import com.tap.DaoImpl.RestaurantDaoImpl;
import com.tap.models.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/addRestaurantServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
                 maxFileSize = 5 * 1024 * 1024,    // 5MB
                 maxRequestSize = 10 * 1024 * 1024) // 10MB
public class AddRestaurantServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phonenumber = request.getParameter("phonenumber");
        String cuisineType = request.getParameter("cuisineType");
        String deliveryTimeStr = request.getParameter("deliveryTime");
        Time deliveryTime = Time.valueOf(deliveryTimeStr);
        int adminUserId = Integer.parseInt(request.getParameter("adminUserId"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        boolean isActive = Boolean.parseBoolean(request.getParameter("isActive"));

        // Handle file upload
        Part filePart = request.getPart("imageFile");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uploadPath = getServletContext().getRealPath("") + "uploads";

        // Create uploads directory if not exists
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        // The URL/path to save in the DB (relative path example)
        String imagePath = "uploads/" + fileName;

        // Now create Restaurant object
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setAddress(address);
        restaurant.setPhonenumber(phonenumber);
        restaurant.setCuisineType(cuisineType);
        restaurant.setDeliveryTime(deliveryTime);
        restaurant.setAdminUserId(adminUserId);
        restaurant.setRating(rating);
        restaurant.setActive(isActive);
        restaurant.setImagePath(imagePath);

        RestaurantDao rd=new RestaurantDaoImpl();
         rd.insertRestaurant(restaurant);

        response.sendRedirect("AdminRestaurantlist.html");
    }
}
