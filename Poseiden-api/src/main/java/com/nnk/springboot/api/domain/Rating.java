package com.nnk.springboot.api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "rating")
public class Rating {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/** The moodys rating. */
	@Column(name = "moodys_rating")
	@NotBlank
	@Size(max = 125)
	private String moodysRating;

	/** The sand P rating. */
	@Column(name = "sand_p_rating")
	@NotBlank
	@Size(max = 125)
	private String sandPRating;

	/** The fitch rating. */
	@Column(name = "fitch_rating")
	@NotBlank
	@Size(max = 125)
	private String fitchRating;

	/** The order number. */
	@Column(name = "order_number", nullable = true)
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
