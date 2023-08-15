package com.nnk.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;
import com.nnk.springboot.utilities.Utilities;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/** The Constant log. */
@Slf4j
@Controller
public class UserController {

	/** The service. */
	@Autowired
	private UserService service;

	/** The utilities. */
	@Autowired
	private Utilities utilities;

	/**
	 * Home.
	 *
	 * @param model the model
	 * @return HTML page
	 */
	@GetMapping("/user/list")
	public String home(Model model) {
		log.info("home");

		try {
			model.addAttribute("user_logged", utilities.getUserLogged());
			model.addAttribute("userList", service.getUserList());
		} catch (Exception e) {
			log.error(e.toString());
		}
		return "user/list";
	}

	/**
	 * Adds the user form.
	 *
	 * @param user the user
	 * @return HTML page
	 */
	@GetMapping("/user/add")
	public String addUserForm(User user) {
		log.info("addUserForm");

		return "user/add";
	}

	/**
	 * Validate.
	 *
	 * @param user the user
	 * @param result the result
	 * @param model the model
	 * @return HTML page
	 */
	@PostMapping("/user/validate")
	public String validate(@Valid User user, BindingResult result, Model model) {
		log.info("validate");
		log.debug("user = " + user.toString());

		try {
			if (!result.hasErrors()) {
				if(service.isUsernameExist(user.getUsername())) {
					result.rejectValue("username", "error.username", "The username '" + user.getUsername()+ "' already exists.");
				} else if(!service.saveUser(user).isEmpty()) {
					model.addAttribute("userList", service.getUserList());

					return "redirect:/user/list";
				} else {
					result.rejectValue("password", "error.password", "The password must have 8 characters, at least 1 lowercase, at least 1 uppercase, at least 1 number and 1 symbol.");
				}
			}
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "user/add";
	}

	/**
	 * Show update form.
	 *
	 * @param id the id
	 * @param model the model
	 * @return HTML page
	 */
	@GetMapping("/user/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		log.info("showUpdateForm");

		try {
			model.addAttribute("user", service.getUserForUpdate(id));

			return "user/update";
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "redirect:/user/list";
	}

	/**
	 * Update user.
	 *
	 * @param id the id
	 * @param user the user
	 * @param result the result
	 * @param model the model
	 * @return HTML page
	 */
	@PostMapping("/user/update/{id}")
	public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model) {
		log.info("updateUser");
		log.debug("id = " + id);
		log.debug("user = " + user.toString());

		try {
			if (!result.hasErrors()) {
				if(!service.updateUser(id, user).isEmpty()) {
					model.addAttribute("userList", service.getUserList());

					return "redirect:/user/list";
				} else {
					result.rejectValue("password", "error.password", "The password must have 8 characters, at least 1 lowercase, at least 1 uppercase, at least 1 number and 1 symbol.");
				}
			}
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "user/update";
	}

	/**
	 * Delete user.
	 *
	 * @param id the id
	 * @param model the model
	 * @return HTML page
	 */
	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id, Model model) {
		log.info("deleteUser");
		log.debug("id = " + id);

		try {
			service.deleteUser(id);
			model.addAttribute("userList", service.getUserList());
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "redirect:/user/list";
	}

}
