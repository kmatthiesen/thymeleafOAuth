package catalyst.thymeleaf.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler{

	/**
	 * Deletes the user's token from the token repository and redirects the user to the login page.
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {

		System.out.println("I logged out and did stuff");
		
		response.sendRedirect("/login");
		response.setStatus(HttpServletResponse.SC_OK);
		
	}

}
