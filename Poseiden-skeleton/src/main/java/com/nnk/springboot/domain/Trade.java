package com.nnk.springboot.domain;

import java.sql.Timestamp;

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
@Entity
@Table(name = "trade")
public class Trade {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trade_id")
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
	@Column(name = "buy_quantity", nullable = true)
	private Double buyQuantity;


	/** The sell quantity. */
	@Column(name = "sell_quantity")
	private Double sellQuantity;

	/** The buy price. */
	@Column(name = "buy_price")
	private Double buyPrice;

	/** The sell price. */
	@Column(name = "sell_price")
	private Double sellPrice;

	/** The benchmark. */
	private String benchmark;

	/** The trade date. */
	@Column(name = "trade_ate")
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
	@Column(name = "creation_name")
	private String creationName;

	/** The creation date. */
	@Column(name = "creation_date")
	private Timestamp creationDate;

	/** The revision name. */
	@Column(name = "revision_name")
	private String revisionName;

	/** The revision date. */
	@Column(name = "revision_date")
	private Timestamp revisionDate;

	/** The deal name. */
	@Column(name = "deal_name")
	private String dealName;

	/** The deal type. */
	@Column(name = "deal_type")
	private String dealType;

	/** The source list id. */
	@Column(name = "source_list_id")
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
