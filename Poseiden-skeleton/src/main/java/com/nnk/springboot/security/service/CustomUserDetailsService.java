package com.nnk.springboot.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.security.model.CustomUserDetails;

import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/** The Constant log. */

/** The Constant log. */

/** The Constant log. */
@Slf4j
@Component
public class CustomUserDetailsService implements UserDetailsService {

	/** The repository. */
	@Autowired
	private UserRepository repository;

	/**
	 * Load user by username.
	 *
	 * @param username the username
	 * @return the user details
	 * @throws UsernameNotFoundException the username not found exception
	 */

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("loadUserByUsername");

		try {
			// rechercher la présence de l'utilisateur dans la base de données
			Optional<User> user = repository.findByUsername(username);

			// SI l'utilisateur n'existe pas ALORS lever une exception
			if (user.isEmpty()) {
				throw new UsernameNotFoundException("User not found");
			}
			log.debug(user.get().toString());

			// renvoyer un UserDetails adapté aux informations de l'utilisateur
			// enregistré dans la base de données
			return new CustomUserDetails(user.get());
		} catch (Exception e) {
			log.error("loadUserByUsername");
		}
		throw new UsernameNotFoundException("User not found");
	}

}
