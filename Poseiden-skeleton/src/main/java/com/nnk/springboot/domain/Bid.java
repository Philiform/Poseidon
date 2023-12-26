package com.nnk.springboot.domain;

import java.sql.Timestamp;

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
 * Instantiates a new bid.
 */
@NoArgsConstructor

/**
 * Instantiates a new bid.
 *
 * @param id the id
 * @param account the account
 * @param type the type
 * @param bidQuantity the bid quantity
 * @param askQuantity the ask quantity
 * @param bid the bid
 * @param ask the ask
 * @param benchmark the benchmark
 * @param bidListDate the bid list date
 * @param commentary the commentary
 * @param security the security
 * @param status the status
 * @param trader the trader
 * @param book the book
 * @param creationName the creation name
 * @param creationDate the creation date
 * @param revisionName the revision name
 * @param revisionDate the revision date
 * @param dealName the deal name
 * @param dealType the deal type
 * @param sourceListId the source list id
 * @param side the side
 */
@AllArgsConstructor
public class Bid {

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

	/** The ask quantity. */
	private Double askQuantity;

	/** The bid. */
	private Double bid;

	/** The ask. */
	private Double ask;

	/** The benchmark. */
	private String benchmark;

	/** The bid list date. */
	private Timestamp bidListDate;

	/** The commentary. */
	private String commentary;

	/** The security. */
	private String security;

	/** The status. */
	private String status;

	/** The trader. */
	private String trader;

	/** The book. */
	private String book;

	/** The creation name. */
	private String creationName;

	/** The creation date. */
	private Timestamp creationDate;

	/** The revision name. */
	private String revisionName;

	/** The revision date. */
	private Timestamp revisionDate;

	/** The deal name. */
	private String dealName;

	/** The deal type. */
	private String dealType;

	/** The source list id. */
	private String sourceListId;

	/** The side. */
	private String side;

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
