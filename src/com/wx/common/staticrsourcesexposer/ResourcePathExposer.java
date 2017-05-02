package com.wx.common.staticrsourcesexposer;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

/**
 * 静态资源版本控制(js,css,images)</br>
 * 当从新发布版本时让浏览器能读取新的资源</br>
 * @author meiiy
 * @version Apr 3, 2016
 */
public class ResourcePathExposer implements ServletContextAware
{
	private ServletContext servletContext;
	private String resourceRoot;
	
	/**
	 * 获取版本信息
	 */
	public void init()
	{
		try {
			InputStream in=this.getClass().getClassLoader().getResourceAsStream("/application.properties");
			Properties p=new Properties();
			p.load(in);
			String version=p.getProperty("resourceVersion");
			resourceRoot="resources-"+version;
			this.getServletContext().setAttribute("resourceRoot", this.getServletContext().getContextPath()+"/"+resourceRoot);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		}
	}

	public String getResourceRoot() {
		return resourceRoot;
	}
	public void setResourceRoot(String resourceRoot) {
		this.resourceRoot = resourceRoot;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}
	public void setServletContext(ServletContext servletContext) {
		this.servletContext=servletContext;
		
	}
}
