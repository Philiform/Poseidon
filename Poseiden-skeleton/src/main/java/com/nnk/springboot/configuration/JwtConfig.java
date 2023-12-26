package com.nnk.springboot.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nnk.springboot.domain.JwtToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class JwtConfig {

	@Value("${jwtKey}")
	private String jwtKey;

	@Bean
	JwtEncoder jwtEncoder() {
		log.info("jwtEncoder");

		return new NimbusJwtEncoder(new ImmutableSecret<>(this.jwtKey.getBytes()));
	}

	@Bean
	JwtToken jwtToken() {
		log.info("jwtToken");

		return new JwtToken();
	}
}