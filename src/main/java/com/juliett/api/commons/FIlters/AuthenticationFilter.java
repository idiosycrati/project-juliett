package com.juliett.api.commons.FIlters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.api.model.enums.ResponseCode;
import com.juliett.commons.process.AbstractProcess;
import com.juliett.commons.servlet.AbstractServlet;
import com.juliett.core.Users.model.UsersModel;
import com.juliett.core.UsersService.UsersService;
import com.xurpas.development.core.logger.Logger;

@WebFilter()
public class AuthenticationFilter implements Filter {
	
	private UsersService usersService;
//	final Logger logger;
	
	public AuthenticationFilter() {
		
//		this.logger = AbstractServlet.getLogger();
		System.out.println((AbstractServlet.getUsersService()==null) +" service is null");
		System.out.println("filter logger " + AbstractServlet.getUsersService() );
		
//		
	}
	
	public String getToken(HttpServletRequest request) {
		System.out.println("Getting token");
		String authorizationHeader = request.getHeader("Authorization");
//		logger.debug("authorizationHeader : " + authorizationHeader);
		System.out.println(authorizationHeader);
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
//			logger.error(authorizationHeader + "error Atuhorization");
			return null;
		}

//		logger.debug("token: " + authorizationHeader);

		String bearerToken = authorizationHeader.substring(7);

		return bearerToken;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		if (!req.getRequestURI().substring(req.getContextPath().length()).equals("/users/register")
				&& !req.getRequestURI().substring(req.getContextPath().length()).equals("/users/login")) {

			try {
				System.out.println("Filter is running");
				final String token = getToken(req);
				System.out.println(token);
				System.out.println();
				if (token == null) {

					res.getWriter().append("token is nullr");
					System.out.println("null");
					return;
				}
				Boolean foundUser = usersService.findUserByToken(token);
				if (foundUser) {

					request.setAttribute("tokenInput", token);
					chain.doFilter(request, response);
				}
				
				return;
			} catch (Throwable e) {
				System.out.println("Internal error");
			System.out.println(e.getMessage());
				res.setStatus(500);
				res.getWriter().append("Internal server error");
			}
		}else {
		chain.doFilter(request, response);
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
