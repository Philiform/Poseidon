package com.nnk.springboot.domain;

import java.sql.Timestamp;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
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
 * Instantiates a new curve point.
 */
@NoArgsConstructor

/**
 * Instantiates a new curve point.
 *
 * @param id the id
 * @param curveId the curve id
 * @param asOfDate the as of date
 * @param term the term
 * @param value the value
 * @param creationDate the creation date
 */
@AllArgsConstructor
public class CurvePoint {

	/** The id. */
	private int id;

	/** The curve id. */
	@Positive
	private int curveId;

	/** The as of date. */
	private Timestamp asOfDate;

	/** The term. */
	@DecimalMin("-9999.9")
	@DecimalMax("9999.9")
	private Double term;

	/** The value. */
	@DecimalMin("-9999.9")
	@DecimalMax("9999.9")
	private Double value;

	/** The creation date. */
	private Timestamp creationDate;

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
