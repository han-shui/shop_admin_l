package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.AdminInfo;
import com.beans.RoleInfo;
import com.dao.AdminDao;
import com.utils.PageInfo;
import com.utils.PageUtil;



public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDao admindao = new AdminDao();
    
    public AdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opt = request.getParameter("flag");
		if("add".equals(opt)) {
			AdminAdd(request, response);
		}
		else if("cnki".equals(opt)) {
			selectByName(request,response);
		}
		else if("manage".equals(opt)) {
			manager(request,response);
		}
		else if("sd".equals(opt)) {
			sd(request,response);
		}
		else if("del".equals(opt)) {
			AdminDel(request,response);
		}
		else if("js".equals(opt)) {
			js(request,response);
		}
		else if("select".equals(opt)) {
			selectById(request,response);
		}
		else if("res".equals(opt)) {
			AdminRes(request,response);
		}
		else if("delMore".equals(opt)) {
			AdminDelMore(request,response);
		}
		else if("logOut".equals(opt)) {
			logOut(request,response);
		}
	}

	private void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("login.jsp");
	}

	private void AdminDelMore(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] ids = request.getParameterValues("ids");
		for(String id:ids) {
			int aid = Integer.valueOf(id);
			admindao.adminResState("0", aid);
		}
		response.getWriter().print("ɾ���ɹ�");
		
	}

	private void AdminRes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		String adminName = request.getParameter("adminName");
		String password = request.getParameter("password");
		String note = request.getParameter("note");
		int roleId = Integer.valueOf(request.getParameter("roleId"));
		System.out.println(roleId);
		AdminInfo admin = new AdminInfo();
		admin.setId(id);
		admin.setAdminName(adminName);
		admin.setPassword(password);
		admin.setNote(note);
		admin.setRoleId(roleId);
		int result = admindao.adminRes(admin);
		request.setAttribute("admin", admin);
		List<RoleInfo> roles = admindao.getAllRoleInfo();
		request.setAttribute("roles", roles);
		if(result == 1) {
			request.setAttribute("msg", "�޸ĳɹ�");
			request.getRequestDispatcher("/admin/admin_res.jsp").forward(request, response);
		}
		else {
			request.setAttribute("msg", "�޸�ʧ��");
			request.getRequestDispatcher("/admin/admin_res.jsp").forward(request, response);
		}
		
	
		
	}

	private void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		AdminInfo admin = admindao.getAdminById(id);
		AdminInfo now_admin = (AdminInfo)request.getSession().getAttribute("now_admin");
		
		if(admin.getId()==now_admin.getId()) {
			System.out.println(now_admin);
			request.setAttribute("msg", "�����޸ĵ�ǰ��¼���û�");
			manager(request,response);
		}
		else {
			List<RoleInfo> roles = admindao.getAllRoleInfo();
			request.setAttribute("admin", admin);
			request.setAttribute("roles", roles);
			request.getRequestDispatcher("/admin/admin_res.jsp").forward(request, response);
		
		}
		
	}

	private void selectByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String adminName = request.getParameter("adminName");
		AdminInfo admin = admindao.getAdminByName(adminName);
		if(admin!=null) {
			response.getWriter().print("�û����Ѵ��ڣ�");
		}
		else {
			String regex="^\\w{4,15}$"; 
			if(adminName.matches(regex)) {
				response.getWriter().print("��");
			}
			else {
				response.getWriter().print("�û�����ʽ�Ƿ���");
			}
		}
	}

	private void js(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		admindao.adminResState("1", id);
		manager(request,response);
		
	}

	private void AdminDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		AdminInfo now_admin = (AdminInfo)request.getSession().getAttribute("now_admin");
		if(id==now_admin.getId()) {
			request.setAttribute("msg", "����ɾ����ǰ��¼���û�");
			manager(request,response);
		}
		else {
			admindao.adminResState("0", id);
			manager(request,response);
		}
		
	}

	private void sd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		AdminInfo now_admin = (AdminInfo)request.getSession().getAttribute("now_admin");
		if(id==now_admin.getId()) {
			request.setAttribute("msg", "����������ǰ��¼���û�");
			manager(request,response);
		}
		else {
			admindao.adminResState("2", id);
			manager(request,response);
		}
		
	}

	private void manager(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int pageSize = 5;
		int rowCount = admindao.getAdminCount();
		int pageIndex = 1;
		
		String indexStr = request.getParameter("pageIndex");
		
		if(indexStr != null) {
			pageIndex = Integer.valueOf(indexStr);
		}
		PageInfo page = PageUtil.getPageInfo(pageSize, rowCount, pageIndex);
		List<AdminInfo> list = admindao.getAdminInfos(page);
		
		request.setAttribute("list", list);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/admin_manage.jsp").forward(request, response);
		
	}
	private void AdminAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminName = request.getParameter("adminName");
		String password = request.getParameter("password");
		String note = request.getParameter("note");
		AdminInfo admin = new AdminInfo();
		admin.setAdminName(adminName);
		admin.setPassword(password);
		admin.setNote(note);
		admin.setState("1");
		int result = admindao.adminAdd(admin);
		if(result == 1) {
			request.setAttribute("msg", "��ӳɹ�");
			request.getRequestDispatcher("/admin/admin_add.jsp").forward(request, response);
		}
		else {
			request.setAttribute("msg", "���ʧ��");
			request.getRequestDispatcher("/admin/admin_add.jsp").forward(request, response);
		}
	}

}
