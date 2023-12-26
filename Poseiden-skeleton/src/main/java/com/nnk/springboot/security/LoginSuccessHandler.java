package com.nnk.springboot.security;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.nnk.springboot.service.JWTService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	private final JWTService jwtService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.debug("==> F:onAuthenticationSuccess");

		jwtService.generateToken(authentication);

		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			log.debug("Response has already been committed. Unable to redirect to /");
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	/**
	 * Determine target url.
	 *
	 * @param authentication the authentication
	 * @return the string
	 */
	protected String determineTargetUrl(final Authentication authentication) {
		log.debug("==> F:determineTargetUrl");

		Map<String, String> roleTargetUrlMap = new HashMap<>();
		roleTargetUrlMap.put("ROLE_ADMIN", "/user/list");
		roleTargetUrlMap.put("ROLE_USER", "/bid/list");

		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (final GrantedAuthority grantedAuthority : authorities) {
			String authorityName = grantedAuthority.getAuthority();
			if (roleTargetUrlMap.containsKey(authorityName)) {
				return roleTargetUrlMap.get(authorityName);
			}
		}

		throw new IllegalStateException();
	}

	/**
	 * Clear authentication attributes.
	 *
	 * @param request the request
	 */
	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		log.debug("==> F:clearAuthenticationAttributes");

		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}

