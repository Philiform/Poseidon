package com.nnk.springboot.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.controllers.LoginController;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginControllerTest.
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class LoginControllerTest {

	/** The controller. */
	@Autowired
	private LoginController controller;

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/**
	 * Test given controller then return not null.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenController_ThenReturnNotNull() throws Exception {
		assertThat(controller).isNotNull();
	}

	/**
	 * Test when get login then return html page login.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetLogin_ThenReturnHtmlPageLogin() throws Exception {
		mockMvc
			.perform(get("/login"))
			.andExpect(view().name("login"))
			.andExpect(status().isOk())
			;
//			.andDo(print());
	}

	/**
	 * Test given user when get logout then return html page login.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenUser_WhenGetLogout_ThenReturnHtmlPageLogin() throws Exception {
		mockMvc
			.perform(get("/logout"))
			.andExpect(view().name("home"))
			.andExpect(status().isOk())
			;
//			.andDo(print());
	}

}
