package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.proxies.RatingProxy;

// TODO: Auto-generated Javadoc
/**
 * The Class RatingService.
 */
@Transactional
@Service
public class RatingService {

	/** The proxy. */
	@Autowired
	private RatingProxy proxy;

	/**
	 * Gets the rating list.
	 *
	 * @return the rating list
	 */
	public List<Rating> getRatingList() {
		return proxy.findAll();
	}

	/**
	 * Save rating.
	 *
	 * @param rating the rating
	 * @return the optional
	 */
	public Optional<Rating> saveRating(final Rating rating) {
		return Optional.of(proxy.save(rating));
	}

	/**
	 * Gets the rating for update.
	 *
	 * @param id the id
	 * @return the rating for update
	 */
	public Rating getRatingForUpdate(final int id) {
		Rating rating = proxy.findById(id);
		
		if(rating == null)	throw(new IllegalArgumentException("Invalid rating Id:" + id));

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
		proxy.deleteById(id);
	}

}
