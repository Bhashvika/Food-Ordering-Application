package com.tap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.tap.DaoImpl.RestaurantDaoImpl;
import com.tap.models.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
@WebServlet("/api/restaurants")
public class RestaurantServlet extends HttpServlet{
	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter out=resp.getWriter();
		RestaurantDaoImpl rdi=new RestaurantDaoImpl();
		List<Restaurant> restaurants=rdi.getAllRestaurants();
		Gson gson=new Gson();
		String json=gson.toJson(restaurants);
		out.print(json);
		out.flush();
	}

}
