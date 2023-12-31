package com.nnk.springboot.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.api.domain.Rating;
import com.nnk.springboot.api.repositories.RatingRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class RatingService.
 */
@Transactional
@Service
public class RatingService {

	/** The repository. */
	@Autowired
	private RatingRepository repository;

	/**
	 * Gets the rating list.
	 *
	 * @return the rating list
	 */
	public List<Rating> getRatingList() {
		return repository.findAll();
	}

	/**
	 * Save rating.
	 *
	 * @param rating the rating
	 * @return the optional
	 */
	public Optional<Rating> saveRating(final Rating rating) {
		return Optional.of(repository.save(rating));
	}

	/**
	 * Gets the rating by id.
	 *
	 * @param id the id
	 * @return the rating
	 */
	public Rating getRatingById(final int id) {
		Rating rating = repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));

		return rating;
	}

	/**
	 * Update rating.
	 *
	 * @param id the id
	 * @param rating the rating
	 * @return the optional
	 */
	public Optional<Rating> updateRating(final int id, final Rating rating) {
		rating.setId(id);

		return saveRating(rating);
	}

	/**
	 * Delete rating.
	 *
	 * @param id the id
	 */
	public void deleteRating(final int id) {
		repository.deleteById(id);
	}

}
