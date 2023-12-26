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
import com.nnk.springboot.api.controllers.RatingController;
import com.nnk.springboot.api.domain.Rating;
import com.nnk.springboot.api.service.RatingService;

// TODO: Auto-generated Javadoc
/**
 * The Class RatingControllerTest.
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class RatingControllerTest {

	/** The controller. */
	@Autowired
	private RatingController controller;

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The service. */
	@MockBean
	public RatingService service;

	/** The rating 1. */
	private static Rating rating1;

	/**
	 * Sets the up before class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		rating1 = new Rating();
		rating1.setId(1);
		rating1.setMoodysRating("MoodysRating");
		rating1.setSandPRating("SandPRating");
		rating1.setFitchRating("FitchRating");
		rating1.setOrderNumber(41);

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
	 * Test when get rating list then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetRatingList_ThenReturnStatusOk() throws Exception {
		mockMvc
			.perform(get("/rating/list"))
			.andExpect(status().isOk());

		verify(service, times(1)).getRatingList();
	}

	/**
	 * Test when save rating then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenSaveRating_ThenReturnStatusOk() throws Exception {
		given(service.saveRating(any())).willReturn(Optional.of(rating1));

		mockMvc
			.perform(post("/rating/save")
					.content(asJsonString(rating1))
				.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

		verify(service, times(1)).saveRating(any());
	}

	/**
	 * Test when get rating by id then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenGetRatingById_ThenReturnStatusOk() throws Exception {
		given(service.getRatingById(any(Integer.class))).willReturn(rating1);

		mockMvc
			.perform(get("/rating/{id}", 0))
			.andExpect(status().isOk());

		verify(service, times(1)).getRatingById(any(Integer.class));
	}

	/**
	 * Test given id when rating update by id then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenRatingUpdateById_ThenReturnStatusOk() throws Exception {
		given(service.updateRating(any(Integer.class), any(Rating.class))).willReturn(Optional.of(rating1));

		mockMvc
			.perform(put("/rating/update/{id}", 1)
				.content(asJsonString(rating1))
			.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

		verify(service, times(1)).updateRating(any(Integer.class), any(Rating.class));
	}

	/**
	 * Test given bad id when rating update by id then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenBadId_WhenGetRatingUpdateById_ThenReturnStatusOk() throws Exception {
		given(service.getRatingById(any(Integer.class))).willThrow(new IllegalArgumentException());

		mockMvc
			.perform(put("/rating/update/{id}", 1)
					.content(asJsonString(rating1))
				.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

		verify(service, times(1)).updateRating(any(Integer.class), any(Rating.class));
	}

	/**
	 * Test given id when rating delete then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenRatingDelete_ThenReturnStatusOk() throws Exception {
		mockMvc
			.perform(delete("/rating/delete/{id}", 1))
			.andExpect(status().isOk());

		verify(service, times(1)).deleteRating(any(Integer.class));
	}

	private static String asJsonString(final Object obj) {
		try {
		    return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
		    throw new RuntimeException(e);
		}
	}
}
