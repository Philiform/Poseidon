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
 * Instantiates a new trade.
 */
@NoArgsConstructor

/**
 * Instantiates a new trade.
 *
 * @param id the id
 * @param account the account
 * @param type the type
 * @param buyQuantity the buy quantity
 * @param sellQuantity the sell quantity
 * @param buyPrice the buy price
 * @param sellPrice the sell price
 * @param benchmark the benchmark
 * @param tradeDate the trade date
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
public class Trade {

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

	/** The sell quantity. */
	private Double sellQuantity;

	/** The buy price. */
	private Double buyPrice;

	/** The sell price. */
	private Double sellPrice;

	/** The benchmark. */
	private String benchmark;

	/** The trade date. */
	private Timestamp tradeDate;

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
			"buyQuantity = " + String.format("%.1f", buyQuantity) +
			"sellQuantity = " + String.format("%.1f", sellQuantity) +
			"buyPrice = " + String.format("%.1f", buyPrice) +
			"sellPrice = " + String.format("%.1f", sellPrice) +
			"benchmark = " + benchmark + ", " +
			"tradeDate = " + tradeDate + ", " +
			"security = " + security + ", " +
			"status = " + status + ", " +
			"trader = " + trader + ", " +
			"book = " + book + ", " +
			"creationName = " + creationName + ", " +
			"creationDate = " + creationDate + ", " +
			"revisionName = " + revisionName + ", " +
			"revisionDate = " + revisionDate + ", " +
			"dealName = " + dealName + ", " +
			"dealType = " + dealType + ", " +
			"sourceListId = " + sourceListId + ", " +
			"side = " + side + ", " +
			" ]";
	}
}
