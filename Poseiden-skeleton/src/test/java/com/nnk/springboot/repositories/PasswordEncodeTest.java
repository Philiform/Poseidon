package com.nnk.springboot.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/**
 * Created by Khang Nguyen.
 * Email: khang.nguyen@banvien.com
 * Date: 09/03/2019
 * Time: 11:26 AM
 */

/** The Constant log. */
@Slf4j
@SpringBootTest
public class PasswordEncodeTest {

	/** The password. */
	String password = "123456";

	/**
	 * Test given password when encoder then return encoded password.
	 */
	@Test
	public void test_GivenPassword_WenEncoder_ThenReturnEncodedPassword() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String response = encoder.encode(password);

		log.info("password = " + password);
		log.info("password encoded = " + response);

		assertThat(response).isNotEqualTo(password);
	}
}
