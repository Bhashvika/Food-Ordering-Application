package com.tap.servlets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.tap.Dao.MenuDao;
import com.tap.DaoImpl.MenuDaoImpl;
import com.tap.models.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/addMenuServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,  // 1MB
                 maxFileSize = 5 * 1024 * 1024,     // 5MB
                 maxRequestSize = 10 * 1024 * 1024) // 10MB
public class AddMenuServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
        String itemName = request.getParameter("itemName");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        boolean isAvailable = Boolean.parseBoolean(request.getParameter("isAvailable"));
        int ratings = Integer.parseInt(request.getParameter("ratings"));

        // Handle image upload
        Part filePart = request.getPart("imageFile");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uploadPath = getServletContext().getRealPath("") + "uploads";

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        String imagePath = "uploads/" + fileName; // This is the path stored in DB

        // Create Menu object
        Menu menu = new Menu();
        menu.setRestaurantId(restaurantId);
        menu.setItemName(itemName);
        menu.setDescription(description);
        menu.setPrice(price);
        menu.setAvailable(isAvailable);
        menu.setRatings(ratings);
        menu.setImagePath(imagePath);

        // Save to DB
        MenuDao menuDao = new MenuDaoImpl();
        menuDao.insertMenu(menu);

        response.sendRedirect("AdminMenu.html?restaurantId=" + restaurantId);
        
    }
}
