package main.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class FeedSuccessHandler implements AuthenticationSuccessHandler {

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {
		try {
			String backUrl = request.getHeader("Referer");
			if (backUrl == null) {
				response.sendRedirect("/");
			}
			response.sendRedirect(request.getContextPath()+backUrl);
		} catch (Exception e) {
			response.sendRedirect("/");
		}

	}
}
