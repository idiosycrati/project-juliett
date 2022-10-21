package com.juliett.api.ProductsController;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.api.ProductsProcess.ProductsProcess;
import com.juliett.commons.servlet.AbstractServlet;

/**
 * Servlet implementation class ProductsController
 */
@WebServlet("/ProductsController")
public class ProductsController extends AbstractServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	
	private final ProductsProcess productsProcess;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductsController() {
        super();
        this.productsProcess = new ProductsProcess(getProductsService(), getLogger());
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		productsProcess.getMethod(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
