package com.nnk.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.nnk.springboot.utilities.Utilities;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/** The Constant log. */
@Slf4j
@Controller
public class LoginController {

	/** The utilities. */
	@Autowired
	Utilities utilities;

	/**
	 * Login.
	 *
	 * @return HTML page
	 */
	@GetMapping("/login")
	public String login() {
		log.info("login");

		return "login";
	}

	/**
	 * Logout.
	 *
	 * @param request the request
	 * @param response the response
	 * @return HTML page
	 */
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		log.info("logout");

		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			if (auth != null) {
				log.debug("auth = " + auth.toString());
				log.debug("name = " + auth.getName());

				new SecurityContextLogoutHandler().logout(request, response, auth);
			} else {
				log.debug("auth IS NULL");
			}
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "home";
	}

}
