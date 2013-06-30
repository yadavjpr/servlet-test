package in.kailashyadav.servlets;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class UserInfoServletTest {
	private HttpServletRequest request;
	private HttpServletResponse response;

	@Before
	public void setUp() {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
	}

	@Test
	public void testWithSessionAttribute() throws Exception {
		PrintWriter writer = new PrintWriter("UserWithSession.txt");
		when(response.getWriter()).thenReturn(writer);
		HttpSession session = mock(HttpSession.class);
		when(request.getSession(false)).thenReturn(session);

		UserInfoServlet userInfoServlet = new UserInfoServlet();

		when(session.getAttribute("userName")).thenReturn("@yadavjpr");
		userInfoServlet.doGet(request, response);
		assertTrue(FileUtils.readFileToString(new File("UserWithSession.txt"),
				"UTF-8").contains("Hello, @yadavjpr"));

		writer = new PrintWriter("UserWithSession.txt");
		when(response.getWriter()).thenReturn(writer);
		when(session.getAttribute("userName")).thenReturn("Mr Yadav");
		userInfoServlet.doGet(request, response);
		assertTrue(FileUtils.readFileToString(new File("UserWithSession.txt"),
				"UTF-8").contains("Hello, Mr Yadav"));

		writer = new PrintWriter("UserWithSession.txt");
		when(response.getWriter()).thenReturn(writer);
		when(session.getAttribute("userName")).thenReturn("Kailash Yadav");
		userInfoServlet.doGet(request, response);
		assertTrue(FileUtils.readFileToString(new File("UserWithSession.txt"),
				"UTF-8").contains("Hello, Kailash Yadav"));
	}

	@Test
	public void testWithoutSessionAttribute() throws Exception {
		PrintWriter writer = new PrintWriter("UserWithoutSession.txt");
		when(response.getWriter()).thenReturn(writer);

		UserInfoServlet userInfoServlet = new UserInfoServlet();
		userInfoServlet.doGet(request, response);
		assertTrue(FileUtils.readFileToString(
				new File("UserWithoutSession.txt"), "UTF-8").contains(
				"You are not logged in, Please login first."));
	}

	@AfterClass
	public static void deleteFiles() {
		try {
			new File("UserWithSession.txt").delete();
			new File("UserWithoutSession.txt").delete();
		} catch (Exception e) {
		}
	}

}
