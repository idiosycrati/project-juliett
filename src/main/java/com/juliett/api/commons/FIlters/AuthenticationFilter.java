package com.juliett.api.commons.FIlters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.api.model.enums.ResponseCode;
import com.juliett.commons.process.AbstractProcess;
import com.juliett.commons.servlet.AbstractServlet;
import com.juliett.core.Users.model.UsersModel;
import com.juliett.core.UsersService.UsersService;
import com.xurpas.development.core.logger.Logger;

@WebFilter()
public class AuthenticationFilter extends AbstractProcess implements Filter {

	private UsersService usersService;
	final Logger logger;

	public AuthenticationFilter() {
		super(AbstractServlet.getLogger());
		this.usersService = AbstractServlet.getUsersService();
		this.logger = AbstractServlet.getLogger();
	

//		
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

				final String token = getToken(req);

				if (token == null) {

					sendError(res, ResponseCode.BAD_REQUEST, "Token is null");

					return;
				}
				Boolean foundUser = usersService.foundAccount(token);
				System.out.println(foundUser);
				if (foundUser) {

					request.setAttribute("tokenInput", token);
					chain.doFilter(request, response);

				}
				if (!foundUser) {

					sendResponse(res, ResponseCode.UNAUTHORIZED, "Invalid token :>");

				}

				return;
			} catch (Throwable e) {

				System.out.println(e.getMessage());
				res.setStatus(500);
				sendError(res, ResponseCode.INTERNAL_SERVER_ERROR, "Internal server error "+e.getMessage());
			}
		} else {
			chain.doFilter(request, response);
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
