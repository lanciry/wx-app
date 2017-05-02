package com.wx.common.paginate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 分页类
 * @author meiiy
 * @version Apr 3, 2016
 * @param <T>
 */
public class Pagination<T>
{
	/** 当前页数据记录 */
	private List<T> recordList = new ArrayList<T>();
	/** 数据总条数 */
	private int recordCount = 0;
	/** 每页显示数据条数 */
	private int pageSize = 10;
	/** 当前页数 */
	private int currentPage = 1;

	public Pagination()
	{
		
	}
	
	/**
	 * 默认每页10条
	 * @param currentPage 当前页
	 */
	public Pagination(String currentPage)
	{
		if (StringUtils.isNotBlank(currentPage)) 
		{
			if(currentPage.matches("\\d*")){
				this.currentPage=Integer.parseInt(currentPage);
			}
		}
	}
	/**
	 * 自定义每页条数
	 * @param pageSize 每页显示条数
	 * @param currentPage 当前页
	 */
	public Pagination(int pageSize,String currentPage)
	{
		this.pageSize=pageSize;
		if (StringUtils.isNotBlank(currentPage)) {
			if(currentPage.matches("\\d*")){
				this.currentPage=Integer.parseInt(currentPage);
			}
		}
	}
	
	public List<T> getRecordList() {
		return recordList;
	}
	public void setRecordList(List<T> recordList) {
		this.recordList = recordList;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * 获取总页数
	 */
	public int getPageCount() {
		if (recordCount!=0){
			int count = recordCount / pageSize;
			if (recordCount % pageSize > 0) {
				count++;
			}
			return count;
		}
		return 0;
	}

	/**
	 * 根据当前页和每页显示条数获取当前页开始的序号
	 */
	public int getPageStart() 
	{
		if(this.currentPage>this.getPageCount()){
			this.currentPage=1;
		}
		return (this.currentPage - 1) * this.pageSize ;
	}
}
