package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/** The Constant log. */
@Slf4j
@Controller
public class HomeController {

	/**
	 * Home.
	 *
	 * @return HTML page
	 */
	@GetMapping("/home")
	public String home()
	{
		log.info("home");

		return "home";
	}

	/**
	 * Redirect home.
	 *
	 * @return HTML page
	 */
	@GetMapping("/")
	public String redirectHome() {
		log.info("redirectHome");

		return "redirect:/bid/list";
	}

}
