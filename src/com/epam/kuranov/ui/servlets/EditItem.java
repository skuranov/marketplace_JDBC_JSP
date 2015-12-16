package com.epam.kuranov.ui.servlets;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.kuranov.dao.DAOFactory;
import com.epam.kuranov.dao.daofamily.ItemDAO;
import com.epam.kuranov.domain.entities.impl.Item;

@WebServlet("/EditItemProt")
public class EditItem extends HttpServlet {

	private static final long serialVersionUID = -1419632727909218257L;

	public EditItem() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletResponse httpResponse = (HttpServletResponse) response; 
		response.setContentType("text / html;charset=UTF-8");
		HttpSession session = request.getSession();
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
        ItemDAO itemDAO = daoFactory.getItemDAO();
        String buyItNow = null;
        request.setAttribute("flEditItem", "0");
        if (request.getParameter("buyItNow")!=null){
        	buyItNow = "1";
        	}
        else{
        	buyItNow = "";
        }
        Double bidInc = 0.0;
        if (request.getParameter("bidInc")!=null){
        	bidInc = Double.parseDouble(request.getParameter("bidInc"));
        }
           	Item item = new Item(
           		1, //do not impact on anything
				(Integer)session.getAttribute("userId"),
				request.getParameter("titleofitem"),
				request.getParameter("description"),
				Double.parseDouble(request.getParameter("startPrice")),
				Double.parseDouble(request.getParameter("timeLeft")),
				new Timestamp(System.currentTimeMillis()),
				buyItNow,
				bidInc);
    	if(request.getParameter("flEditItem").equals("1")){
    		request.setAttribute("flEditItem", "1");
    		itemDAO.changeItem(item, Integer.parseInt(request.getParameter("itemId")));
    	}
    	else{
    		itemDAO.addItem(item);
    	}
    	
		httpResponse.sendRedirect(request.getServletContext().getContextPath() + "/ShowItems");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
