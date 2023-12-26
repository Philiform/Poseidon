package com.nnk.springboot.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.api.controllers.RuleController;
import com.nnk.springboot.api.domain.Rule;
import com.nnk.springboot.api.service.RuleService;

// TODO: Auto-generated Javadoc
/**
 * The Class RuleControllerTest.
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class RuleControllerTest {

	/** The controller. */
	@Autowired
	private RuleController controller;

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The service. */
	@MockBean
	public RuleService service;

	/** The rule 1. */
	private static Rule rule1;

	/**
	 * Sets the up before class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		rule1 = new Rule();
		rule1.setId(1);
		rule1.setName("Name 1");
		rule1.setDescription("Description 1");
		rule1.setJson("Json 1");
		rule1.setTemplate("Template 1");
		rule1.setSqlStr("SqlStr 1");
		rule1.setSqlPart("SqlPart 1");

	}

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
	 * Test when get rule list then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetRuleList_ThenReturnStatusOk() throws Exception {
		mockMvc
			.perform(get("/rule/list"))
			.andExpect(status().isOk());

		verify(service, times(1)).getRuleList();
	}

	/**
	 * Test when save rule then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenSaveRule_ThenReturnStatusOk() throws Exception {
		given(service.saveRule(any())).willReturn(Optional.of(rule1));

		mockMvc
			.perform(post("/rule/save")
					.content(asJsonString(rule1))
				.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

		verify(service, times(1)).saveRule(any());
	}

	/**
	 * Test when get rule by id then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenGetRuleById_ThenReturnStatusOk() throws Exception {
		given(service.getRuleById(any(Integer.class))).willReturn(rule1);

		mockMvc
			.perform(get("/rule/{id}", 0))
			.andExpect(status().isOk());

		verify(service, times(1)).getRuleById(any(Integer.class));
	}

	/**
	 * Test given id when rule update by id then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenRuleUpdateById_ThenReturnStatusOk() throws Exception {
		given(service.updateRule(any(Integer.class), any(Rule.class))).willReturn(Optional.of(rule1));

		mockMvc
			.perform(put("/rule/update/{id}", 1)
					.content(asJsonString(rule1))
				.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

		verify(service, times(1)).updateRule(any(Integer.class), any(Rule.class));
	}

	/**
	 * Test given bad id when rule update by id then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenBadId_WhenGetRuleUpdateById_ThenReturnStatusOk() throws Exception {
		given(service.getRuleById(any(Integer.class))).willThrow(new IllegalArgumentException());

		mockMvc
			.perform(put("/rule/update/{id}", 1)
				.content(asJsonString(rule1))
			.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

		verify(service, times(1)).updateRule(any(Integer.class), any(Rule.class));
	}

	/**
	 * Test given id when rule delete then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenRuleDelete_ThenReturnStatusOk() throws Exception {
		mockMvc
			.perform(delete("/rule/delete/{id}", 1))
			.andExpect(status().isOk());

		verify(service, times(1)).deleteRule(any(Integer.class));
	}

	private static String asJsonString(final Object obj) {
		try {
		    return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
		    throw new RuntimeException(e);
		}
	}

}
