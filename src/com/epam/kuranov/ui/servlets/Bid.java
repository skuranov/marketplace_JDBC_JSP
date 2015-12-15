package com.epam.kuranov.ui.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.epam.kuranov.dao.DAOFactory;
import com.epam.kuranov.dao.daofamily.BidDAO;
import com.epam.kuranov.domain.dictionary.Tables;
import com.epam.kuranov.domain.entities.Entity;
@WebServlet("/bidsProt")
public class Bid  extends HttpServlet{
	
	private static final long serialVersionUID = 2517192959678309905L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text / html;charset=UTF-8");
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        BidDAO bidDAO = daoFactory.getBidDAO();
        bidDAO.deleteById(Tables.BIDS.name(), Tables.BIDS.getIdColumn(), "7");
        bidDAO = daoFactory.getBidDAO();
        try {
			ArrayList<Entity> answer = bidDAO.getAllTable(Tables.BIDS);
			req.setAttribute("answer",answer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    getServletContext().getRequestDispatcher("/jsp/bidsTest.jsp").forward(req, resp);
    }
}

