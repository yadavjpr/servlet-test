package in.kailashyadav.servlets;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class LoginServletTest {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ServletConfig config;

	@Before
	public void setUp() {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		config = mock(ServletConfig.class);
	}

	@Test
	public void testLoginWithParam() throws Exception {
		when(config.getInitParameter("prefixParam")).thenReturn("Hi");
		when(request.getParameter("userName")).thenReturn("@yadavjpr");
		PrintWriter writer = new PrintWriter("LoginWithParam.txt");
		when(response.getWriter()).thenReturn(writer);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession(true)).thenReturn(session);

		LoginServlet testServlet = new LoginServlet();
		testServlet.init(config);
		testServlet.doGet(request, response);
		assertTrue(FileUtils.readFileToString(new File("LoginWithParam.txt"),
				"UTF-8").contains("Hi, @yadavjpr. You have loggedin."));

		when(request.getParameter("userName")).thenReturn("Kailash Yadav");
		writer = new PrintWriter("LoginWithParam.txt");
		when(response.getWriter()).thenReturn(writer);
		testServlet.doGet(request, response);
		assertTrue(FileUtils.readFileToString(new File("LoginWithParam.txt"),
				"UTF-8").contains("Hi, Kailash Yadav. You have loggedin."));

		when(config.getInitParameter("prefixParam")).thenReturn("Hello");
		when(request.getParameter("userName")).thenReturn("Mr Kailash");
		writer = new PrintWriter("LoginWithParam.txt");
		when(response.getWriter()).thenReturn(writer);
		testServlet.doGet(request, response);
		assertTrue(FileUtils.readFileToString(new File("LoginWithParam.txt"),
				"UTF-8").contains("Hello, Mr Kailash. You have loggedin."));
	}

	@Test
	public void testLoginWithoutParam() throws Exception {
		when(config.getInitParameter("prefixParam")).thenReturn("Hi");
		PrintWriter writer = new PrintWriter("LoginWithoutParam.txt");
		when(response.getWriter()).thenReturn(writer);

		LoginServlet testServlet = new LoginServlet();
		testServlet.init(config);
		testServlet.doGet(request, response);
		assertTrue(FileUtils.readFileToString(
				new File("LoginWithoutParam.txt"), "UTF-8").contains(
				"Please use userName param in url for login."));
	}

	@AfterClass
	public static void deleteFiles() {
		try {
			new File("LoginWithParam.txt").delete();
			new File("LoginWithoutParam.txt").delete();
		} catch (Exception e) {
		}
	}

}
