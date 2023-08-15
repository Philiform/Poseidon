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
 * Instantiates a new trade DTO.
 */
@NoArgsConstructor

/**
 * Instantiates a new trade DTO.
 *
 * @param id the id
 * @param account the account
 * @param type the type
 * @param buyQuantity the buy quantity
 */
@AllArgsConstructor
public class TradeDTO {

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

	/** The buy quantity. */
	private Double buyQuantity;

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
			"buyQuantity = " + String.format("%.1f", buyQuantity) +
			" ]";
	}
}
