package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.utilities.Utilities;

// TODO: Auto-generated Javadoc
/**
 * The Class UserService.
 */
@Transactional
@Service
public class UserService {

	/** The encoder. */
	@Autowired
	private PasswordEncoder encoder;

	/** The repository. */
	@Autowired
	private UserRepository repository;

	/** The utilities. */
	@Autowired
	private Utilities utilities;

	/**
	 * Gets the user list.
	 *
	 * @return the user list
	 */
	public List<User> getUserList() {
		return repository.findAll();
	}

	/**
	 * Save user.
	 *
	 * @param user the user
	 * @return the optional
	 */
	public Optional<User> saveUser(final User user) {
		if(utilities.isValidPassword(user.getPassword())) {
			user.setPassword(encoder.encode(user.getPassword()));

			return Optional.of(repository.save(user));
		}

		return Optional.empty();
	}

	/**
	 * Gets the user for update.
	 *
	 * @param id the id
	 * @return the user for update
	 */
	public User getUserForUpdate(final int id) {
		User user = repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

		return user;
	}

	/**
	 * Update user.
	 *
	 * @param id the id
	 * @param user the user
	 * @return the optional
	 */
	public Optional<User> updateUser(final int id, final User user) {
		user.setId(id);

		return saveUser(user);
	}

	/**
	 * Delete user.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public boolean deleteUser(final int id) {
		User user = repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		repository.delete(user);

		return true;
	}

	/**
	 * Checks if is username exist.
	 *
	 * @param username the username
	 * @return true, if is username exist
	 */
	public boolean isUsernameExist(final String username) {
		return repository.findByUsername(username).isEmpty() ? false : true;
	}

}
