package in.kailashyadav.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

/**
 * This servlet take <b>userName</b> parameter from request and show message
 * according to <b>userName</b> parameter. If <b>userName</b> exist in request,
 * it will be stored in session.
 * 
 * @author <a href="mailto:yadavjpr@gmail.com">Kailash Yadav</a>
 */

@WebServlet(name = "testServlet", urlPatterns = { "/login" }, 
			initParams = { @WebInitParam(name = "prefixParam", value = "Hello") })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");
		if (StringUtils.isBlank(userName)) {
			out.println("Please use userName param in url for login.");
		} else {
			HttpSession session = request.getSession(true);
			session.setAttribute("userName", userName);
			String prefixParam = getServletConfig().getInitParameter(
					"prefixParam");
			out.println(prefixParam + ", " + userName + ". You have loggedin.");
		}
		out.close();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
