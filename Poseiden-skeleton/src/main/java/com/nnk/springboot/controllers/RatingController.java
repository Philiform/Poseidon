package com.nnk.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;
import com.nnk.springboot.utilities.Utilities;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/** The Constant log. */
@Slf4j
@Controller
public class RatingController {

	/** The service. */
	@Autowired
	private RatingService service;

	/** The utilities. */
	@Autowired
	private Utilities utilities;

	/**
	 * Home.
	 *
	 * @param model the model
	 * @return HTML page
	 */
	@GetMapping("/rating/list")
	public String home(Model model) {
		log.info("home");

		try {
			model.addAttribute("user_logged", utilities.getUserLogged());
			model.addAttribute("ratingList", service.getRatingList());
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "rating/list";
	}

	/**
	 * Adds the rating form.
	 *
	 * @param rating the rating
	 * @return HTML page
	 */
	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		log.info("addRatingForm");

		return "rating/add";
	}

	/**
	 * Validate.
	 *
	 * @param rating the rating
	 * @param result the result
	 * @param model the model
	 * @return HTML page
	 */
	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {
		log.info("validate");

		log.debug("rating = " + rating.toString());

		try {
			if (!result.hasErrors()) {
				if (!service.saveRating(rating).isEmpty()) {
					model.addAttribute("ratingList", service.getRatingList());

					return "redirect:/rating/list";
				}
			}
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "rating/add";
	}

	/**
	 * Show update form.
	 *
	 * @param id the id
	 * @param model the model
	 * @return HTML page
	 */
	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		log.info("showUpdateForm");

		try {
			model.addAttribute("rating", service.getRatingForUpdate(id));

			return "rating/update";
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "redirect:/rating/list";
	}

	/**
	 * Update rating.
	 *
	 * @param id the id
	 * @param rating the rating
	 * @param result the result
	 * @param model the model
	 * @return HTML page
	 */
	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result,
			Model model) {
		log.info("updateRating");
		log.debug("id = " + id);
		log.debug("rating = " + rating.toString());

		try {
			if (result.hasErrors()) {
				return "rating/update";
			}
			service.updateRating(id, rating);
			model.addAttribute("ratingList", service.getRatingList());
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "redirect:/rating/list";
	}

	/**
	 * Delete rating.
	 *
	 * @param id the id
	 * @param model the model
	 * @return HTML page
	 */
	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {
		log.info("deleteRating");
		log.debug("id = " + id);

		try {
			service.deleteRating(id);
			model.addAttribute("ratingList", service.getRatingList());
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "redirect:/rating/list";
	}
}
