package com.nnk.springboot.service.DTO;

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
 * Instantiates a new bid DTO.
 */
@NoArgsConstructor

/**
 * Instantiates a new bid DTO.
 *
 * @param id the id
 * @param account the account
 * @param type the type
 * @param bidQuantity the bid quantity
 */
@AllArgsConstructor
public class BidDTO {

	/** The id. */
	private int id;

	/** The account. */
	@NotBlank
	@Size(max = 30)
	private String account;

	/** The type. */
	@NotBlank
	@Size(max = 30)
	private String type;

	/** The bid quantity. */
	private Double bidQuantity;

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "[ " +
			"id = " + id + ", " +
			"account = " + account + ", " +
			"type = " + type + ", " +
			"bidQuantity = " + String.format("%.1f", bidQuantity) +
			" ]";
	}
}
