package com.juliett.api.SampleController;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.api.SampleProcess.SampleProcess;
import com.juliett.commons.servlet.AbstractServlet;

/**
 * Servlet implementation class SampleController
 */
@WebServlet("/SampleController")
public class SampleController extends AbstractServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
	private final SampleProcess sampleProcess;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SampleController() {
        super();
        this.sampleProcess =  new SampleProcess(getSampleService(), getLogger());
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		sampleProcess.getSample(request, response);
		
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
