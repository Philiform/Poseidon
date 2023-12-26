package com.nnk.springboot.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nnk.springboot.api.domain.Rating;
import com.nnk.springboot.api.service.RatingService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RatingController {

	@Autowired
	private RatingService service;

	@GetMapping("/rating/list")
	public List<Rating> ratingList() {
		log.info("ratingList");

		return service.getRatingList();
	}

	@PostMapping("/rating/save")
	public Optional<Rating> ratingSave(@RequestBody @Valid Rating rating) {
		log.info("ratingSave");
		log.debug("rating = " + rating.toString());

		return service.saveRating(rating);
	}

	@GetMapping("/rating/{id}")
	public Rating getRatingByIdForUpdate(@PathVariable("id") Integer id) {
		log.info("getRatingByIdForUpdate");

		return service.getRatingById(id);
	}

	@PutMapping("/rating/update/{id}")
	public void ratingUpdateById(@PathVariable("id") Integer id, @RequestBody @Valid Rating rating) {
		log.info("ratingUpdateById");
		log.debug("id = " + id);
		log.debug("rating = " + rating.toString());

		service.updateRating(id, rating);
	}

	@DeleteMapping("/rating/delete/{id}")
	public void ratingDeleteById(@PathVariable("id") Integer id) {
		log.info("ratingDeleteById");
		log.debug("id = " + id);

		service.deleteRating(id);
	}
}
