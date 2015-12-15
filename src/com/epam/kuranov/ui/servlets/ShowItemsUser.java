package com.epam.kuranov.ui.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import com.epam.kuranov.dao.DAOFactory;
import com.epam.kuranov.dao.daofamily.BidDAO;
import com.epam.kuranov.dao.daofamily.ComplexDAO;
import com.epam.kuranov.domain.entities.impl.Bid;
import com.epam.kuranov.domain.entities.impl.ComplexEntity;
import com.epam.kuranov.domain.searchSequence.SearchByDescription;
import com.epam.kuranov.domain.searchSequence.SearchByDiffParams;
import com.epam.kuranov.domain.utils.DataSetUtils;


@WebServlet("/ShowItems")
public class ShowItemsUser extends HttpServlet{
	private static final long serialVersionUID = 8108258220936763488L;
	private DataSource dataSource;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text / html;charset=UTF-8");
	
		boolean onlyMyItems = false;
		request.setAttribute("flEditItem", "0");
		if (request.getParameter("addItem")!=null){
			getServletContext().getRequestDispatcher("/jsp/editItem.jsp").forward(request, response);
			return;
		}
		
		if (request.getParameter("editItem")!=null){
			request.setAttribute("flEditItem", "1");
			request.setAttribute("itemId", request.getParameter("itemId"));
			request.setAttribute("title", request.getParameter		("title"));
			request.setAttribute("descr", request.getParameter("descr"));
			request.setAttribute("timeLeft", request.getParameter("timeLeft"));
			request.setAttribute("startBid", request.getParameter("startBid"));
			request.setAttribute("flBuyItNow", request.getParameter("flBuyItNow"));
			request.setAttribute("bidInc", request.getParameter("bidInc"));
			request.setAttribute("itemId", request.getParameter("itemId"));
			getServletContext().getRequestDispatcher("/jsp/editItem.jsp").forward(request, response);
			return;
		}
        
        HttpSession session = request.getSession();
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
		
		String currentBid = request.getParameter("currentBid");
		String bidInc =  request.getParameter("bidInc");
		String newBidValue = request.getParameter("newBidValue");
		String startBid = request.getParameter("startBid");
		
		if (request.getParameter("makeBid")!=null){
			BidDAO bidDAO = daoFactory.getBidDAO();	
			
			
			if(testBiddingError(startBid,
					currentBid,
					bidInc,
					newBidValue)){
				request.setAttribute("errorMessage", "New bid is incorrect");
	        	getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
	        	return;
			}
			
			Bid bid = new Bid((Integer)session.getAttribute("userId"),
				Integer.parseInt(request.getParameter("itemId")),
				Double.parseDouble(newBidValue));
			bidDAO.addBid(bid);
		}
		
		if (request.getParameter("buyItNow")!=null){
			BidDAO bidDAO = daoFactory.getBidDAO();
			
			Bid bid = new Bid((Integer)session.getAttribute("userId"),
				Integer.parseInt(request.getParameter("itemId")),
				Double.parseDouble(startBid));
			
			bidDAO.addBid(bid);
		}
		
		
        ComplexDAO complexDAO = daoFactory.getComplexDAO();
        ArrayList<ComplexEntity> answer = new ArrayList<>();
        
        
        if (request.getParameter("searchByDescr")!=null){
        	SearchByDescription sDescr = new SearchByDescription(request.getParameter("keywords"));
        	answer = complexDAO.getRSBySearchSequence(sDescr.getSequence(0), dataSource);
        }
        else {
			SearchByDiffParams sSequence = new SearchByDiffParams();
			if (request.getParameter("showMy")!=null){
				sSequence.setUserId((Integer)session.getAttribute("userId"));
				onlyMyItems = true;
			}
			answer = complexDAO.getRSBySearchSequence(sSequence.getSequence(), dataSource);
		}
        request.setAttribute("answer",  DataSetUtils.exceptUnactualItems(DataSetUtils.exceptOldBids(answer)));
		request.setAttribute("onlyMyItems", onlyMyItems);
		
        getServletContext().getRequestDispatcher("/jsp/showItems.jsp").forward(request, response);
        
    }
	
	public boolean testBiddingError(String startBid, String currentBid, String bidInc, String newBid){
		try{
			if ((Double.parseDouble(newBid) < (Double.parseDouble(currentBid)
					+ Double.parseDouble(bidInc))||
					Double.parseDouble(newBid) < Double.parseDouble(startBid))){
				return true;
			}
		}
		catch(Exception e){
			return true;
		}
		return false;
	} 

}
