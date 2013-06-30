package in.kailashyadav.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

/**
 * This servlet get <b>userName</b> attribute from session and show message
 * according to <b>userName</b> attribute. If <b>userName</b> exist in session,
 * it will be removed from there.
 * 
 * @author <a href="mailto:yadavjpr@gmail.com">Kailash Yadav</a>
 */

@WebServlet("/getme")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse responce) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = responce.getWriter();
		if (session == null) {
			out.println("You are not logged in, Please login first.");
		} else {
			String userName = (String) session.getAttribute("userName");
			if (StringUtils.isBlank(userName)) {
				out.println("You are not logged in, Please login first.");
			} else {
				out.println("Hello, " + userName);
				session.removeAttribute("userName");
			}
		}
		out.close();
	}

}
