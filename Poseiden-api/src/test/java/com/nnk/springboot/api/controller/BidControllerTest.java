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
import com.nnk.springboot.api.controllers.BidController;
import com.nnk.springboot.api.domain.Bid;
import com.nnk.springboot.api.service.BidService;

// TODO: Auto-generated Javadoc
/**
 * The Class BidControllerTest.
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class BidControllerTest {

	/** The controller. */
	@Autowired
	private BidController controller;

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The service. */
	@MockBean
	public BidService service;

	/** The bid 1. */
	private static Bid bid1;
	
	/**
	 * Sets the up before class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		bid1 = new Bid();
		bid1.setId(1);
		bid1.setAccount("Account A1");
		bid1.setType("Type T1");
		bid1.setBidQuantity(11.1);
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
	 * Test when get bid list then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetBidList_ThenReturnStatusOk() throws Exception {
		mockMvc
			.perform(get("/bid/list"))
			.andExpect(status().isOk());

		verify(service, times(1)).getBidList();
	}

	/**
	 * Test when save bid then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenSaveBid_ThenReturnStatusOk() throws Exception {
		given(service.saveBid(any())).willReturn(Optional.of(bid1));

		mockMvc
			.perform(post("/bid/save")
				.content(asJsonString(bid1))
			.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

		verify(service, times(1)).saveBid(any());
	}

	/**
	 * Test given id when get bid by id then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenGetBidById_ThenReturnStatusOk() throws Exception {
		given(service.getBidById(any(Integer.class))).willReturn(bid1);

		mockMvc
			.perform(get("/bid/{id}", 0))
			.andExpect(status().isOk());

		verify(service, times(1)).getBidById(any(Integer.class));
	}

	/**
	 * Test given id when bid update by id then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenGetBidUpdateById_ThenReturnStatusOk() throws Exception {
		given(service.updateBid(any(Integer.class), any(Bid.class))).willReturn(Optional.of(bid1));

		mockMvc
			.perform(put("/bid/update/{id}", 1)
					.content(asJsonString(bid1))
				.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

		verify(service, times(1)).updateBid(any(Integer.class), any(Bid.class));
	}

	/**
	 * Test given bad id when bid update by id then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenBadId_WhenGetBidUpdateById_ThenReturnStatusOk() throws Exception {
		given(service.getBidById(any(Integer.class))).willThrow(new IllegalArgumentException());

		mockMvc
		.perform(put("/bid/update/{id}", 1)
				.content(asJsonString(bid1))
			.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

		verify(service, times(1)).updateBid(any(Integer.class), any(Bid.class));
	}

	/**
	 * Test given id when bid delete then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenBidDelete_ThenReturnStatusOk() throws Exception {
		mockMvc
			.perform(delete("/bid/delete/{id}", 1))
			.andExpect(status().isOk());

		verify(service, times(1)).deleteBid(any(Integer.class));
	}

	private static String asJsonString(final Object obj) {
		try {
		    return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
		    throw new RuntimeException(e);
		}
	}
}
