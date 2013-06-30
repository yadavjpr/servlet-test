package in.kailashyadav;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.commons.lang3.StringUtils;

/**
 * This filter append signature if request has <b>userName</b> parameter. The signature is revere text of each words.
 * 
 * <pre>
 * Hello      	 = olleH
 * Hello test    = olleH tset
 * Kailash       = hsaliaK
 * Kailash Yadav = hsaliaK vadaY
 * </pre>
 *
 * @author <a href="mailto:yadavjpr@gmail.com">Kailash Yadav</a>
 */

@WebFilter(filterName = "TestFilter", urlPatterns = { "/*" })
public class TestFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String userName = request.getParameter("userName");
		if (StringUtils.isNotBlank(userName)) {
			userName = StringUtils.reverse(StringUtils.reverseDelimited(userName, ' '));
			PrintWriter out = response.getWriter();
			out.println("Signature: " + userName);
		}
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
