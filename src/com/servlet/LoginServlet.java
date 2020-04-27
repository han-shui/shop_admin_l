package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.AdminInfo;
import com.dao.AdminDao;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AdminDao admindao = new AdminDao();
    
    public LoginServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminName = request.getParameter("adminName");
		String password = request.getParameter("password");
		
		AdminInfo admin = admindao.login(adminName, password);
		if(admin != null) {
			if("1".equals(admin.getState())) {
				request.getSession().setAttribute("now_admin", admin);
				request.getRequestDispatcher("/main.html").forward(request, response);	
			}
			else if("2".equals(admin.getState())) {
				request.setAttribute("msg", "���û��ѱ�����");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
			else {
				request.setAttribute("msg", "�û������������");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				
			}
		}
		else {
			request.setAttribute("msg", "�û������������");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			
		}
	}

}
