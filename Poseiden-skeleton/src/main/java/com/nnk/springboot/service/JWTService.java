package com.nnk.springboot.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.JwtToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JWTService {

	private JwtEncoder jwtEncoder;
	private JwtToken jwtToken;
	
	public JWTService(JwtEncoder jwtEncoder, JwtToken jwtToken) {
		this.jwtEncoder = jwtEncoder;
		this.jwtToken = jwtToken;
	}

	public void generateToken(Authentication authentication) {
		log.info("generateToken");

		Instant now = Instant.now();
		JwtClaimsSet claims = JwtClaimsSet.builder().issuer("self").issuedAt(now)
				.expiresAt(now.plus(1, ChronoUnit.DAYS)).subject(authentication.getName()).build();
		JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters
				.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);

		jwtToken.setToken(this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue());
	}
}
