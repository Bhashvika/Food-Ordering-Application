package com.tap.servlets;

import java.io.IOException;

import com.tap.DaoImpl.UserDaoImpl;
import com.tap.models.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet{
  @Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   int userid=Integer.parseInt(req.getParameter("userid"));
	   String name=req.getParameter("name");
	   String username=req.getParameter("username");
	   String password=req.getParameter("password");
	   String email=req.getParameter("email");
	   String phone=req.getParameter("phone");
	   String address=req.getParameter("address");
	   String role=req.getParameter("role");
	   UserModel u=new UserModel(userid,name,username,password,email,phone,address,role);
	   UserDaoImpl udi=new UserDaoImpl();
	   udi.insertUser(u);
	   resp.sendRedirect("signin.html");
}
}
