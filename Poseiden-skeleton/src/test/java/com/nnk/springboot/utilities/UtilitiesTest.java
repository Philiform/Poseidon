package com.nnk.springboot.utilities;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

// TODO: Auto-generated Javadoc
/**
 * The Class UtilitiesTest.
 */
@ExtendWith(MockitoExtension.class)
class UtilitiesTest {

	/** The utilities. */
	private Utilities utilities;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		utilities = new Utilities();
	}

	/**
	 * Test given good password when is valid password then return true.
	 */
	@Test
	void test_GivenGoodPassword_WhenIsValidPassword_ThenReturnTrue() {
		boolean response = utilities.isValidPassword("11aaAA&&");

		assertThat(response).isTrue();
	}

	/**
	 * Test given password with 7 characters when is valid password then return false.
	 */
	@Test
	void test_GivenPasswordWith7Characters_WhenIsValidPassword_ThenReturnFalse() {
		boolean response = utilities.isValidPassword("11aaAA&");

		assertThat(response).isFalse();
	}

	/**
	 * Test given password with 0 lowercase when is valid password then return false.
	 */
	@Test
	void test_GivenPasswordWith0Lowercase_WhenIsValidPassword_ThenReturnFalse() {
		boolean response = utilities.isValidPassword("1122AA&&");

		assertThat(response).isFalse();
	}

	/**
	 * Test given password with 0 uppercase when is valid password then return false.
	 */
	@Test
	void test_GivenPasswordWith0Uppercase_WhenIsValidPassword_ThenReturnFalse() {
		boolean response = utilities.isValidPassword("1122aa&&");

		assertThat(response).isFalse();
	}

	/**
	 * Test given password with 0 number when is valid password then return false.
	 */
	@Test
	void test_GivenPasswordWith0Number_WhenIsValidPassword_ThenReturnFalse() {
		boolean response = utilities.isValidPassword("aabbAA&&");

		assertThat(response).isFalse();
	}

	/**
	 * Test given password with 0 symbol when is valid password then return false.
	 */
	@Test
	void test_GivenPasswordWith0Symbol_WhenIsValidPassword_ThenReturnFalse() {
		boolean response = utilities.isValidPassword("11aaAABB");

		assertThat(response).isFalse();
	}

}
