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
import com.nnk.springboot.api.controllers.TradeController;
import com.nnk.springboot.api.domain.Trade;
import com.nnk.springboot.api.service.TradeService;

// TODO: Auto-generated Javadoc
/**
 * The Class TradeControllerTest.
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TradeControllerTest {

	/** The controller. */
	@Autowired
	private TradeController controller;

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The service. */
	@MockBean
	public TradeService service;

	/** The trade 1. */
	private static Trade trade1;
	
	/**
	 * Sets the up before class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		trade1 = new Trade();
		trade1.setId(1);
		trade1.setAccount("Account A1");
		trade1.setType("Type T1");
		trade1.setBuyQuantity(11.1);

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
	 * Test when get trade list then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetTradeList_ThenReturnStatusOk() throws Exception {
		mockMvc
			.perform(get("/trade/list"))
			.andExpect(status().isOk());

		verify(service, times(1)).getTradeList();
	}

	/**
	 * Test when save trade then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenSaveTrade_ThenReturnStatusOk() throws Exception {
		given(service.saveTrade(any())).willReturn(Optional.of(trade1));

		mockMvc
			.perform(post("/trade/save")
					.content(asJsonString(trade1))
				.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

		verify(service, times(1)).saveTrade(any());
	}

	/**
	 * Test when get trade by id then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenGetTradeById_ThenReturnStatusOk() throws Exception {
		given(service.getTradeById(any(Integer.class))).willReturn(trade1);

		mockMvc
			.perform(get("/trade/{id}", 0))
			.andExpect(status().isOk());

		verify(service, times(1)).getTradeById(any(Integer.class));
	}

	/**
	 * Test given id when trade update by id then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenTradeUpdateById_ThenReturnStatusOk() throws Exception {
		given(service.updateTrade(any(Integer.class), any(Trade.class))).willReturn(Optional.of(trade1));

		mockMvc
			.perform(put("/trade/update/{id}", 1)
					.content(asJsonString(trade1))
				.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

		verify(service, times(1)).updateTrade(any(Integer.class), any(Trade.class));
	}

	/**
	 * Test given bad id when trade update by id then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenBadId_WhenGetTradeUpdateById_ThenReturnStatusOk() throws Exception {
		given(service.getTradeById(any(Integer.class))).willThrow(new IllegalArgumentException());

		mockMvc
			.perform(put("/trade/update/{id}", 1)
				.content(asJsonString(trade1))
			.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

		verify(service, times(1)).updateTrade(any(Integer.class), any(Trade.class));
	}

	/**
	 * Test given id when trade delete then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenTradeDelete_ThenReturnStatusOk() throws Exception {
		mockMvc
			.perform(delete("/trade/delete/{id}", 1))
			.andExpect(status().isOk());

		verify(service, times(1)).deleteTrade(any(Integer.class));
	}

	private static String asJsonString(final Object obj) {
		try {
		    return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
		    throw new RuntimeException(e);
		}
	}
}
