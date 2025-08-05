package com.tap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.tap.DaoImpl.UserDaoImpl;
import com.tap.models.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
   @Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	boolean userfound=false;
	boolean passwordfound=false;
	resp.setContentType("text/html");
	 PrintWriter out=resp.getWriter();
	 UserDaoImpl udi=new UserDaoImpl();
	 List<UserModel> userlist=udi.getAllUsers();
	 UserModel loggedInUser = null;
	 for(UserModel u:userlist) {
		 if(u.getEmail().equals(email)) {
			 userfound=true;
			 if(u.getPassword().equals(password)) {
				 passwordfound=true;
				 loggedInUser=u;
			 }
		 }
	 }
	 if(userfound && passwordfound) {
		 HttpSession session = req.getSession();
         session.setAttribute("userId", loggedInUser.getUserid());
         session.setAttribute("role", loggedInUser.getRole()); 
         String role = loggedInUser.getRole().toLowerCase();
         if (role.equals("admin")) {
             resp.sendRedirect("AdminDashBoard.html");  // Redirect to Add Restaurant page
         } else if (role.equals("customer")) {
             resp.sendRedirect("UserDashboard.html");     // Redirect to Restaurants list
         } else {
             out.print("Unknown role. Cannot redirect.");
         }
	 }
	 else if(!userfound){
		 out.print("email does not exist");
	 }
	 else if(userfound && !passwordfound) {
		 out.print("enter corrcet password");
	 }
}
}
