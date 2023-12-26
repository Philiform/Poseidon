package com.nnk.springboot.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * Hash code.
 *
 * @return the int
 */
@Data

/**
 * Instantiates a new rating.
 */
@NoArgsConstructor

/**
 * Instantiates a new rating.
 *
 * @param id the id
 * @param moodysRating the moodys rating
 * @param sandPRating the sand P rating
 * @param fitchRating the fitch rating
 * @param orderNumber the order number
 */
@AllArgsConstructor
public class Rating {

	/** The id. */
	private int id;

	/** The moodys rating. */
	@NotBlank
	@Size(max = 125)
	private String moodysRating;

	/** The sand P rating. */
	@NotBlank
	@Size(max = 125)
	private String sandPRating;

	/** The fitch rating. */
	@NotBlank
	@Size(max = 125)
	private String fitchRating;

	/** The order number. */
	private int orderNumber;

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "[ " +
			"id = " + id + ", " +
			"moodysRating = " + moodysRating + ", " +
			"sandPRating = " + sandPRating + ", " +
			"fitchRating = " + fitchRating + ", " +
			"orderNumber = " + orderNumber +
			" ]";
	}
}
