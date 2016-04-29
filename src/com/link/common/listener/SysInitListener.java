package com.link.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.link.common.util.FileUtil;

public class SysInitListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	public void contextInitialized(ServletContextEvent event) {
		FileUtil.setWebRootPath(event.getServletContext().getRealPath(""));
		  
	}

}
