package com.nnk.springboot.service.DTO;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
 * Instantiates a new curve point DTO.
 */
@NoArgsConstructor

/**
 * Instantiates a new curve point DTO.
 *
 * @param id the id
 * @param curveId the curve id
 * @param term the term
 * @param value the value
 */
@AllArgsConstructor
public class CurvePointDTO {

	/** The id. */
	private int id;

	/** The curve id. */
	@NotNull
	@Positive
	private int curveId;

	/** The term. */
	@NotNull
	@DecimalMin("-9999.9")
	@DecimalMax("9999.9")
	private Double term;

	/** The value. */
	@NotNull
	@DecimalMin("-9999.9")
	@DecimalMax("9999.9")
	private Double value;

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "[ " +
			"id = " + id + ", " +
			"curveId = " + curveId + ", " +
			"term = " + String.format("%.1f", term) + ", " +
			"value = " + String.format("%.1f", value) +
			" ]";
	}
}
