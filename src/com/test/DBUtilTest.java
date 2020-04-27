package com.test;

import java.util.List;

import com.beans.*;
import com.dao.*;
import com.jdbc.*;

public class DBUtilTest {
	public static void main(String args[]) {
		MenuDao dao = new MenuDao();
		List<MenuInfo> list = dao.getMenuList(0,2);
		for(MenuInfo l:list) {
			System.out.println(l);
		}
	}
}
