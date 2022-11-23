package com.juliett.commons.init;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.commons.servlet.AbstractServlet;
import com.xurpas.development.core.commons.ProjectProperties;

/**
 * Servlet implementation class InitServlet
 */
@WebServlet("/InitServlet")
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub

		String propertyFile = (String) config.getInitParameter("init-property-file");
		System.out.println("propertyFile=" + propertyFile);
		ProjectProperties.initialize(config.getServletContext().getResourceAsStream(propertyFile));
		AbstractServlet.getInstance();

	}

}
