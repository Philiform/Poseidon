package com.nnk.springboot.domain;

import java.io.Serializable;

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
 * Instantiates a new user.
 */
@NoArgsConstructor

/**
 * Instantiates a new user.
 *
 * @param id the id
 * @param username the username
 * @param password the password
 * @param fullname the fullname
 * @param role the role
 */
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5963337391014744083L;

	/** The id. */
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

	/** The username. */
	@NotBlank(message = "Username is mandatory")
	@Size(min = 2, max = 125)
	@Column(unique = true)
    private String username;

	/** The password. */
	@NotBlank(message = "Password is mandatory")
	@Size(min = 8, max = 125)
    private String password;

	/** The fullname. */
	@NotBlank(message = "FullName is mandatory")
	@Size(min = 2, max = 125)
    private String fullname;

	/** The role. */
	@NotBlank(message = "Role is mandatory")
	@Size(min = 2, max = 125)
    private String role;

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return
//			"[ "+
			"id = " + id + ", " +
			"username = " + username + ", " +
			"fullname = " + fullname + ", " +
			"role = " + role;
//			"role = " + role +
//			" ]";
	}
}
