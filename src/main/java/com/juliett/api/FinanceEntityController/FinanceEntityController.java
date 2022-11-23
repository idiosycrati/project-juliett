
package com.juliett.api.FinanceEntityController;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;

import com.juliett.api.FinanceEntityProcess.FinanceEntityProcess;
import com.juliett.commons.servlet.AbstractServlet;

/**
 * Servlet implementation class ProductsController
 */
@WebServlet("/FinanceController")
public class FinanceEntityController extends AbstractServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	private final FinanceEntityProcess financeEntityProcess;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FinanceEntityController() {
		super();
		this.financeEntityProcess = new FinanceEntityProcess(getFinanceEntityService(), getLogger());

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		financeEntityProcess.getMethod(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			while(true) {
				
			financeEntityProcess.postMethod(request, response);
			Thread.sleep(3000);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
