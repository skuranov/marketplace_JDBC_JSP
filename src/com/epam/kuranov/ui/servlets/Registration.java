package com.epam.kuranov.ui.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.epam.kuranov.dao.DAOFactory;
import com.epam.kuranov.dao.daofamily.UserDAO;
import com.epam.kuranov.domain.entities.impl.User;

@WebServlet("/Registration")
public class Registration extends HttpServlet {

	private static final long serialVersionUID = -7136814992563992834L;

	public Registration() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletResponse httpResponse = (HttpServletResponse) response; 
		response.setContentType("text / html;charset=UTF-8");
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        UserDAO userDAO = daoFactory.getUserDAO();
        User user = new User(
        		request.getParameter("fullname"),
        		request.getParameter("billingAdress"),
        		request.getParameter("login"),
        		request.getParameter("pswd"),
        		"simpleUser");
        userDAO.addUser(user);
		httpResponse.sendRedirect(request.getServletContext().getContextPath() + "/ShowItemsGuest");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
