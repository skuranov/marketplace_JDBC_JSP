package com.epam.kuranov.ui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.kuranov.dao.DAOFactory;
import com.epam.kuranov.dao.daofamily.UserDAO;
import com.epam.kuranov.domain.entities.impl.User;

@WebServlet("/Login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = -8660469073764636047L;

	public Login() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("login") == null) {        
			//response.sendRedirect("/protected/showItemsUser");       
		}
	    if (request.getParameter("login") != null && request.getParameter("password") != null) {
	    	DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
	        UserDAO UserDAO = daoFactory.getUserDAO();
	        User user = UserDAO.authentificate(request.getParameter("login"), request.getParameter("password")); 
	        if(user == null){
	        	request.setAttribute("errorMessage", "Authentification error");
	        	getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
	        	return;
	        }
	        
	        session.setAttribute("userId", user.getId());
	        session.setAttribute("userFullName", user.getFullName());
	        session.setAttribute("login", request.getParameter("login"));
	        		    
	       
	        request.setAttribute("userName", session.getAttribute("login"));
	        if (request.getParameter("destination") != null && 
	        		!request.getParameter("destination").equals("") && 
	        		!request.getParameter("destination").equals("/")) { 
	            try {
	                response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + request.getParameter("destination"))); 
	                return;
	            }
	            catch (Exception e) {
	                response.sendRedirect(request.getServletContext().getContextPath() + "/ShowItems");
	                return;
	            }
	        }
	        response.sendRedirect(request.getServletContext().getContextPath() + "/ShowItems");    
	    }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
