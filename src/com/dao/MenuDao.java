package com.dao;

import java.util.List;

import com.beans.MenuInfo;
import com.jdbc.DBUtil;

public class MenuDao {
	public List<MenuInfo>getMenuList(int parentId,int roleId){
		List<MenuInfo> menuList=DBUtil.getList("select m.id,menuName,target,url,parentId,icon from menuInfo m join rolemenu on m.id=menuid where parentId=? and roleid = ?", MenuInfo.class, parentId,roleId);
		 
		for(MenuInfo m: menuList) {
			if(m.getParentId()==0) {
				
				m.setSubMenuList( getMenuList( m.getId(),roleId));
			}
		}
		return menuList;
	}
}
