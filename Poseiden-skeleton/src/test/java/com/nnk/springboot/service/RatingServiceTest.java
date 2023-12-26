package com.nnk.springboot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.proxies.RatingProxy;
import com.nnk.springboot.utilities.Utilities;

// TODO: Auto-generated Javadoc
/**
 * The Class RatingServiceTest.
 */
@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {

	/** The service. */
	@InjectMocks
	private RatingService service;

	/** The rating connection proxy. */
	@Mock
	private RatingProxy proxy;

	/** The utilities. */
	@Mock
	private Utilities utilities;

	/** The list rating. */
	List<Rating> listRating = new ArrayList<>();
	
	/** The rating 1. */
	private Rating rating1;
	
	/** The rating 2. */
	private Rating rating2;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		rating1 = new Rating();
		rating1.setId(1);
		rating1.setMoodysRating("MoodysRating");
		rating1.setSandPRating("SandPRating");
		rating1.setFitchRating("FitchRating");
		rating1.setOrderNumber(41);

		rating2 = new Rating();

		listRating.add(rating1);
		listRating.add(rating2);

	}

	/**
	 * Test when get rating list then return list rating not empty.
	 */
	@Test
	void test_WhenGetRatingList_ThenReturnListRatingNotEmpty() {
		given(proxy.findAll()).willReturn(listRating);

		List<Rating> response = service.getRatingList();

		verify(proxy, times(1)).findAll();
		assertThat(response).isEqualTo(listRating);
	}

	/**
	 * Test given good new rating when save rating then return saved rating.
	 */
	@Test
	void test_GivenGoodNewRating_WhenSaveRating_ThenReturnSavedRating() {
		given(proxy.save(any())).willReturn(rating1);

		Optional<Rating> response = service.saveRating(rating1);

		verify(proxy, times(1)).save(any());
		assertThat(response).isEqualTo(Optional.of(rating1));
	}

	/**
	 * Test given good rating id when get rating for update then return rating.
	 */
	@Test
	void test_GivenGoodRatingId_WhenGetRatingForUpdate_ThenReturnRating() {
		given(proxy.findById(any(Integer.class))).willReturn(rating1);

		Rating response = service.getRatingForUpdate(1);

		verify(proxy, times(1)).findById(any(Integer.class));
		assertThat(response).isEqualTo(rating1);
	}

	/**
	 * Test given bad rating id when get rating for update then throw illegal argument exception.
	 */
	@Test
	void test_GivenBadRatingId_WhenGetRatingForUpdate_ThenThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> service.getRatingForUpdate(10));
		verify(proxy, times(1)).findById(any(Integer.class));
	}

	/**
	 * Test given good update rating when get rating for update then return updated rating.
	 */
	@Test
	void test_GivenGoodUpdateRating_WhenGetRatingForUpdate_ThenReturnUpdatedRating() {
		given(proxy.findById(any())).willReturn(rating1);

		Rating response = service.getRatingForUpdate(1);

		verify(proxy, times(1)).findById(any());
		assertThat(response).isEqualTo(rating1);
	}

	/**
	 * Test given bad update rating when get rating for update then throw illegal argument exception.
	 */
	@Test
	void test_GivenBadUpdateRating_WhenGetRatingForUpdate_ThenThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> service.getRatingForUpdate(10));
		verify(proxy, times(1)).findById(any(Integer.class));
	}

	/**
	 * Test given good update rating when update rating then return updated rating.
	 */
	@Test
	void test_GivenGoodUpdateRating_WhenUpdateRating_ThenReturnUpdatedRating() {
		given(proxy.save(any())).willReturn(rating1);

		Optional<Rating> response = service.updateRating(1, rating1);

		verify(proxy, times(1)).save(any());
		assertThat(response).isEqualTo(Optional.of(rating1));
	}

	/**
	 * Test given good rating id when delere rating then delete rating.
	 */
	@Test
	void test_GivenGoodRatingId_WhenDelereRating_ThenDeleteRating() {
		service.deleteRating(1);

		verify(proxy, times(1)).deleteById(any(Integer.class));
	}

}
