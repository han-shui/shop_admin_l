package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.beans.AdminInfo;


@WebFilter("/SessionFiliter")
public class SessionFiliter implements Filter {

    
    public SessionFiliter() {
       
    }

	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		String url = req.getRequestURI();
		HttpSession session =req.getSession();
		AdminInfo admin=(AdminInfo)session.getAttribute("now_admin");
		if(admin!=null||url.contains("ogin")) {//login.jspºÍLoginServlet
			chain.doFilter(request, response);
		}
		else {
			request.setAttribute("msg", "ÇëµÇÂ¼!");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
