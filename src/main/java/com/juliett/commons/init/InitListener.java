package com.juliett.commons.init;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.juliett.commons.servlet.AbstractServlet;
import com.juliett.core.commons.ThreadMultiplier.ThreadCheckTermination;
import com.juliett.core.commons.ThreadMultiplier.ThreadMultiplier;
import com.juliett.core.commons.ThreadMultiplier.ThreadSumAssurance;
import com.xurpas.development.core.commons.ProjectProperties;
import com.xurpas.development.core.logger.Logger;

/**
 * Servlet implementation class InitServlet
 */
public class InitListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

	private ThreadMultiplier threadMultiplier;
	private ThreadCheckTermination threadCheckTermination;
	private ThreadSumAssurance threadSumAssurance;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitListener() {
		super();

		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub

		ServletContext servletContext = sce.getServletContext();
		String initParameter = servletContext.getInitParameter("init-property-file");
		System.out.println(initParameter);
		ProjectProperties.initialize(servletContext.getResourceAsStream(initParameter));
		AbstractServlet.getInstance();
		if (threadMultiplier == null) {
			threadMultiplier = new ThreadMultiplier(AbstractServlet.getFinanceEntityService(),
					AbstractServlet.getLogger());
//			threadMultiplier.start();
		}
		if (threadCheckTermination == null) {
			threadCheckTermination = new ThreadCheckTermination(AbstractServlet.getTransactionsService(),
					AbstractServlet.getAutoEmailService(), AbstractServlet.getLogger());
//			threadCheckTermination.start();
		}	
		if (threadSumAssurance == null) {
			threadSumAssurance = new ThreadSumAssurance(AbstractServlet.getTransactionsService(),
					AbstractServlet.getFinanceEntityService(), AbstractServlet.getLogger());
//			threadSumAssurance.start();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
//		threadMultiplier.stop();
//		threadCheckTermination.stop();
//		threadSumAssurance.stop();

	}

}
