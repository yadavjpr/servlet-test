package in.kailashyadav.servlets;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class HttpUnitTestSkip {
	private static String appUrl = "http://localhost:8080/servlet-test/";

	@Test
	public void simpleTest() throws Exception {
		WebConversation wc = new WebConversation();
		WebRequest req = new GetMethodWebRequest(appUrl);
		WebResponse resp = wc.getResponse(req);
		assertTrue(resp.getText().contains("Hello World!"));

		WebLink loginLink = resp.getLinkWith("Login");
		WebLink getMeLink = resp.getLinkWith("Get Me");

		loginLink.click();
		resp = wc.getCurrentPage();
		assertTrue(resp.getText().contains(
				"Please use userName param in url for login."));

		getMeLink.click();
		resp = wc.getCurrentPage();
		assertTrue(resp.getText().contains(
				"You are not logged in, Please login first."));
	}

	@Test
	public void loginTest() throws Exception {
		String userName = "yadavjpr";
		WebConversation wc = new WebConversation();
		WebRequest req = new GetMethodWebRequest(appUrl + "login?userName="
				+ userName);
		WebResponse resp = wc.getResponse(req);
		assertTrue(resp.getText().contains(
				"Signature: "
						+ StringUtils.reverse(StringUtils.reverseDelimited(
								userName, ' '))));
		assertTrue(resp.getText().contains(
				"Hello, yadavjpr. You have loggedin."));
	}

}
