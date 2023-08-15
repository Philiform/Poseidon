package com.nnk.springboot.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.controllers.RuleController;
import com.nnk.springboot.domain.Rule;
import com.nnk.springboot.service.RuleService;

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
	 * Test given user when get rule list then return html page rule list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser(username = "user", authorities = "USER")
	public void test_GivenUser_WhenGetRuleList_ThenReturnHtmlPageRuleList() throws Exception {
		mockMvc
			.perform(get("/rule/list"))
			.andExpect(model().attributeExists("ruleList"))
			.andExpect(view().name("rule/list"))
			.andExpect(status().isOk())
			;
//			.andDo(print());

		verify(service, times(1)).getRuleList();
	}

	/**
	 * Test when get rule add then return html page rule add.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetRuleAdd_ThenReturnHtmlPageRuleAdd() throws Exception {
		mockMvc
			.perform(get("/rule/add"))
			.andExpect(view().name("rule/add"))
			.andExpect(status().isOk())
			;
//			.andDo(print());
	}

	/**
	 * Test when get rule validate then return html page rule list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetRuleValidate_ThenReturnHtmlPageRuleList() throws Exception {
		given(service.saveRule(any())).willReturn(Optional.of(rule1));

		mockMvc
			.perform(post("/rule/validate")
				.flashAttr("rule", rule1))
			.andExpect(view().name("redirect:/rule/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).saveRule(any());
		verify(service, times(1)).getRuleList();
	}

	/**
	 * Test when get rule validate then return html page rule add.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetRuleValidate_ThenReturnHtmlPageRuleAdd() throws Exception {
		mockMvc
			.perform(post("/rule/validate"))
			.andExpect(model().hasErrors())
			.andExpect(view().name("rule/add"))
			.andExpect(status().isOk())
			;
//			.andDo(print());
	}

	/**
	 * Test given id when get rule update then return html page rule update.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenGetRuleUpdate_ThenReturnHtmlPageRuleUpdate() throws Exception {
		given(service.getRuleForUpdate(any(Integer.class))).willReturn(rule1);

		mockMvc
			.perform(get("/rule/update/{id}", 1))
			.andExpect(model().attributeExists("rule"))
			.andExpect(view().name("rule/update"))
			.andExpect(status().isOk())
			;
//			.andDo(print());

		verify(service, times(1)).getRuleForUpdate(any(Integer.class));
	}

	/**
	 * Test given bad id when get rule update then redirect to html page rule list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenBadId_WhenGetRuleUpdate_ThenRedirectToHtmlPageRuleList() throws Exception {
		given(service.getRuleForUpdate(any(Integer.class))).willThrow(new IllegalArgumentException());

		mockMvc
			.perform(get("/rule/update/{id}", 1))
			.andExpect(view().name("redirect:/rule/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).getRuleForUpdate(any(Integer.class));
	}

	/**
	 * Test given good id when get rule update then return updated rule.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenGoodId_WhenGetRuleUpdate_ThenReturnUpdatedRule() throws Exception {
		mockMvc
			.perform(post("/rule/update/{id}", 1)
				.flashAttr("rule", rule1))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/rule/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).updateRule(any(Integer.class), any());
		verify(service, times(1)).getRuleList();
	}

	/**
	 * Test given empty rule when get rule update then return html page rule update.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenEmptyRule_WhenGetRuleUpdate_ThenReturnHtmlPageRuleUpdate() throws Exception {
		mockMvc
			.perform(post("/rule/update/{id}", 1)
				.flashAttr("rule", new Rule()))
			.andExpect(model().hasErrors())
			.andExpect(view().name("rule/update"))
			.andExpect(status().isOk())
			;
//			.andDo(print());

		verify(service, times(0)).updateRule(any(Integer.class), any());
		verify(service, times(0)).getRuleList();
	}

	/**
	 * Test given illegal argument exception when get rule update then redirect to html page rule list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenIllegalArgumentException_WhenGetRuleUpdate_ThenRedirectToHtmlPageRuleList() throws Exception {
		given(service.updateRule(any(Integer.class), any())).willThrow(new IllegalArgumentException());

		mockMvc
			.perform(post("/rule/update/{id}", 1)
				.flashAttr("rule", rule1))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/rule/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).updateRule(any(Integer.class), any());
		verify(service, times(0)).getRuleList();
	}

	/**
	 * Test given id when get rule delete then return html page deleted rule.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenGetRuleDelete_ThenReturnHtmlPageDeletedRule() throws Exception {
		mockMvc
			.perform(get("/rule/delete/{id}", 1))
			.andExpect(view().name("redirect:/rule/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).deleteRule(any(Integer.class));
		verify(service, times(1)).getRuleList();
	}
/*
	@Test
	public void test_GivenIllegalArgumentException_WhenGetRuleDelete_ThenRedirectToHtmlPageRuleList() throws Exception {
//		given(service.deleteRule(any(Integer.class))).willThrow(new IllegalArgumentException());
		given(service.deleteRule(any(Integer.class))).willThrow(new IllegalArgumentException());

		mockMvc
			.perform(get("/rule/delete/{id}", 1))
			.andExpect(view().name("redirect:/rule/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).deleteRule(any(Integer.class));
		verify(service, times(0)).getRuleList();
	}
*/
}
