package com.epam.kuranov.ui.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import com.epam.kuranov.dao.DAOFactory;
import com.epam.kuranov.dao.daofamily.ComplexDAO;
import com.epam.kuranov.domain.entities.impl.ComplexEntity;
import com.epam.kuranov.domain.searchSequence.SearchByDescription;
import com.epam.kuranov.domain.searchSequence.SearchByDiffParams;
import com.epam.kuranov.domain.utils.DataSetUtils;


@WebServlet("/ShowItemsGuest")
public class ShowItemsGuest extends HttpServlet{

	private static final long serialVersionUID = -9155243909472641252L;
	private DataSource dataSource;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text / html;charset=UTF-8");

		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        ComplexDAO complexDAO = daoFactory.getComplexDAO();
        ArrayList<ComplexEntity> answer = new ArrayList<>();
        
        
        if (request.getParameter("searchByDescr")!=null){
        	SearchByDescription sDescr = new SearchByDescription(request.getParameter("keywords"));
        	answer = complexDAO.getRSBySearchSequence(sDescr.getSequence(0), dataSource);
        }
        else {
			SearchByDiffParams sSequence = new SearchByDiffParams();
			answer = complexDAO.getRSBySearchSequence(sSequence.getSequence(), dataSource);
		}
        request.setAttribute("answer",  DataSetUtils.exceptUnactualItems(DataSetUtils.exceptOldBids(answer)));

		getServletContext().getRequestDispatcher("/jsp/showItemsGuest.jsp").forward(request, response);
        
    }

}
