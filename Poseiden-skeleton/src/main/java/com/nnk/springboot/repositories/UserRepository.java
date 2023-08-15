package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserRepository.
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the optional
	 */
	public Optional<User> findByUsername(final String username);
}
