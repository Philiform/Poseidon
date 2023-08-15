package com.nnk.springboot.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.controllers.HomeController;

// TODO: Auto-generated Javadoc
/**
 * The Class HomeControllerTest.
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class HomeControllerTest {

	/** The controller. */
	@Autowired
	private HomeController controller;

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
	 * Test when get home then return html page home.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetHome_ThenReturnHtmlPageHome() throws Exception {
		mockMvc
			.perform(get("/home"))
			.andExpect(view().name("home"))
			.andExpect(status().isOk())
			;
//			.andDo(print());
	}

	/**
	 * Test given user when get path root then redirect to html page bid list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser
	public void test_GivenUser_WhenGetPathRoot_ThenRedirectToHtmlPageBidList() throws Exception {
		mockMvc
			.perform(get("/"))
			.andExpect(view().name("redirect:/bid/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());
	}

}
