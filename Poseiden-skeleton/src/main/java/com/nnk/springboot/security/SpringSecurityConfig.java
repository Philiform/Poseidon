package com.nnk.springboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// TODO: Auto-generated Javadoc
/**
 * The Class SpringSecurityConfig.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

	/**
	 * Security filter chain.
	 *
	 * @param http the http
	 * @return the security filter chain
	 * @throws Exception the exception
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		System.out.println("nouveau mot de passe (123456aA!) = " + passwordEncoder().encode("123456aA!"));

		http.formLogin((form) -> form
				.loginPage("/login")
				.loginProcessingUrl("/login/authentication")
				.permitAll()
				.usernameParameter("username")
				.defaultSuccessUrl("/bid/list")
				.failureUrl("/login?error")
				);

		http.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/css/**").permitAll()
				.requestMatchers("/home").permitAll()
				.requestMatchers("/user/list").hasRole("ADMIN")
				.requestMatchers("/**").hasAnyRole("ADMIN", "USER")
				.anyRequest().authenticated()
				);

		http.logout((logout) -> logout
				.logoutUrl("/home")
				.permitAll()
				.deleteCookies("JSESSIONID")
				);

		return http.build();
	}

	/**
	 * Password encoder.
	 *
	 * @return the password encoder
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
