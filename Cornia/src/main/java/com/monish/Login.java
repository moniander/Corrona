package com.monish;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.monish.Dao.UserDao;
import com.monish.beans.UserBean;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			UserBean user = new UserBean(); 
			user.setUname(request.getParameter("uname")); 
			user.setPass(request.getParameter("pass")); 
			user = UserDao.login(user);
			
			if(user.isValid()) { 
			
			HttpSession session = request.getSession(true); 
			session.setAttribute("currentSessionUser",user); 
			response.sendRedirect("userLogged.jsp"); //logged-in page 
			} 
			else response.sendRedirect("index.jsp"); //error page 
		} catch (Throwable theException) {
			System.out.println(theException); } 
		
		}
	
}
