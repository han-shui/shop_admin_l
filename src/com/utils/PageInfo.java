package com.utils;

public class PageInfo {
	private int pageSize;//��ǰҳ���С
	private int rowCount;//ȫ����������
	private int pageIndex;//��ǰҳ��
	private int pageCount;//ȫ��ҳ��
	private int beginRow;//�����п�ʼ
	
	private boolean hasBefore;//�Ƿ�����һҳ
	private boolean hasNext;//�Ƿ�����һҳ
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getBeginRow() {
		return beginRow;
	}
	public void setBeginRow(int beginRow) {
		this.beginRow = beginRow;
	}
	public boolean isHasBefore() {
		return hasBefore;
	}
	public void setHasBefore(boolean hasBefore) {
		this.hasBefore = hasBefore;
	}
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	
	
	
}
