package com.nnk.springboot.domain;

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
 * Instantiates a new rule.
 */
@NoArgsConstructor

/**
 * Instantiates a new rule.
 *
 * @param id the id
 * @param name the name
 * @param description the description
 * @param json the json
 * @param template the template
 * @param sqlStr the sql str
 * @param sqlPart the sql part
 */
@AllArgsConstructor
@Entity
@Table(name = "rule")
public class Rule {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/** The name. */
	@NotBlank
	@Size(max = 125)
	private String name;

	/** The description. */
	@NotBlank
	@Size(max = 125)
	private String description;

	/** The json. */
	@NotBlank
	@Size(max = 125)
	private String json;

	/** The template. */
	@NotBlank
	@Size(max = 512)
	private String template;

	/** The sql str. */
	@Column(name = "sql_str")
	@NotBlank
	@Size(max = 125)
	private String sqlStr;

	/** The sql part. */
	@Column(name = "sql_part")
	@NotBlank
	@Size(max = 125)
	private String sqlPart;

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "[ " +
			"id = " + id + ", " +
			"name = " + name + ", " +
			"description = " + description + ", " +
			"json = " + json + ", " +
			"template = " + template + ", " +
			"sqlStr = " + sqlStr + ", " +
			"sqlPart = " + sqlPart +
			" ]";
	}
}
