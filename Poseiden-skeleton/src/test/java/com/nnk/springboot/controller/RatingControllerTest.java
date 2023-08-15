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

import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;

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
	 * Test given user when get rating list then return html page rating list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser(username = "user", authorities = "USER")
	public void test_GivenUser_WhenGetRatingList_ThenReturnHtmlPageRatingList() throws Exception {
		mockMvc
			.perform(get("/rating/list"))
			.andExpect(model().attributeExists("ratingList"))
			.andExpect(view().name("rating/list"))
			.andExpect(status().isOk())
			;
//			.andDo(print());

		verify(service, times(1)).getRatingList();
	}

	/**
	 * Test when get rating add then return html page rating add.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetRatingAdd_ThenReturnHtmlPageRatingAdd() throws Exception {
		mockMvc
			.perform(get("/rating/add"))
			.andExpect(view().name("rating/add"))
			.andExpect(status().isOk())
			;
//			.andDo(print());
	}

	/**
	 * Test when get rating validate then return html page rating list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetRatingValidate_ThenReturnHtmlPageRatingList() throws Exception {
		given(service.saveRating(any())).willReturn(Optional.of(rating1));

		mockMvc
			.perform(post("/rating/validate")
				.flashAttr("rating", rating1))
			.andExpect(view().name("redirect:/rating/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).saveRating(any());
		verify(service, times(1)).getRatingList();
	}

	/**
	 * Test when get rating validate then return html page rating add.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetRatingValidate_ThenReturnHtmlPageRatingAdd() throws Exception {
		mockMvc
			.perform(post("/rating/validate"))
			.andExpect(model().hasErrors())
			.andExpect(view().name("rating/add"))
			.andExpect(status().isOk())
			;
//			.andDo(print());
	}

	/**
	 * Test given id when get rating update then return html page rating update.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenGetRatingUpdate_ThenReturnHtmlPageRatingUpdate() throws Exception {
		given(service.getRatingForUpdate(any(Integer.class))).willReturn(rating1);

		mockMvc
			.perform(get("/rating/update/{id}", 1))
			.andExpect(model().attributeExists("rating"))
			.andExpect(view().name("rating/update"))
			.andExpect(status().isOk())
			;
//			.andDo(print());

		verify(service, times(1)).getRatingForUpdate(any(Integer.class));
	}

	/**
	 * Test given bad id when get rating update then redirect to html page rating list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenBadId_WhenGetRatingUpdate_ThenRedirectToHtmlPageRatingList() throws Exception {
		given(service.getRatingForUpdate(any(Integer.class))).willThrow(new IllegalArgumentException());

		mockMvc
			.perform(get("/rating/update/{id}", 1))
			.andExpect(view().name("redirect:/rating/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).getRatingForUpdate(any(Integer.class));
	}

	/**
	 * Test given good id when get rating update then return updated rating.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenGoodId_WhenGetRatingUpdate_ThenReturnUpdatedRating() throws Exception {
		mockMvc
			.perform(post("/rating/update/{id}", 1)
				.flashAttr("rating", rating1))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/rating/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).updateRating(any(Integer.class), any());
		verify(service, times(1)).getRatingList();
	}

	/**
	 * Test given empty rating when get rating update then return html page rating update.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenEmptyRating_WhenGetRatingUpdate_ThenReturnHtmlPageRatingUpdate() throws Exception {
		mockMvc
			.perform(post("/rating/update/{id}", 1)
				.flashAttr("rating", new Rating()))
			.andExpect(model().hasErrors())
			.andExpect(view().name("rating/update"))
			.andExpect(status().isOk())
			;
//			.andDo(print());

		verify(service, times(0)).updateRating(any(Integer.class), any());
		verify(service, times(0)).getRatingList();
	}

	/**
	 * Test given illegal argument exception when get rating update then redirect to html page rating list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenIllegalArgumentException_WhenGetRatingUpdate_ThenRedirectToHtmlPageRatingList() throws Exception {
		given(service.updateRating(any(Integer.class), any())).willThrow(new IllegalArgumentException());

		mockMvc
			.perform(post("/rating/update/{id}", 1)
				.flashAttr("rating", rating1))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/rating/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).updateRating(any(Integer.class), any());
		verify(service, times(0)).getRatingList();
	}

	/**
	 * Test given id when get rating delete then return html page deleted rating.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenGetRatingDelete_ThenReturnHtmlPageDeletedRating() throws Exception {
		mockMvc
			.perform(get("/rating/delete/{id}", 1))
			.andExpect(view().name("redirect:/rating/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).deleteRating(any(Integer.class));
		verify(service, times(1)).getRatingList();
	}
/*
	@Test
	public void test_GivenIllegalArgumentException_WhenGetRatingDelete_ThenRedirectToHtmlPageRatingList() throws Exception {
//		given(service.deleteRating(any(Integer.class))).willThrow(new IllegalArgumentException());
		given(service.deleteRating(any(Integer.class))).willThrow(new IllegalArgumentException());

		mockMvc
			.perform(get("/rating/delete/{id}", 1))
			.andExpect(view().name("redirect:/rating/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).deleteRating(any(Integer.class));
		verify(service, times(0)).getRatingList();
	}
*/
}
