package com.epam.kuranov.ui.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter("/GuestFilter")
public class AuthFilter implements Filter {
    public AuthFilter() {

    }
    
    @Override
	public void destroy() {
	}
    
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        
        String uriPath = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        
        if (session == null || session.getAttribute("login") == null) {        
            HttpServletResponse httpResponse = (HttpServletResponse) response; 
            
            httpResponse.sendRedirect(request.getServletContext().getContextPath()+"/jsp/login.jsp?destination=" + uriPath);
            
            return;
        }                
		chain.doFilter(request, response);
	}

    @Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
