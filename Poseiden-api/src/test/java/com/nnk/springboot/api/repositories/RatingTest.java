package com.nnk.springboot.api.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.api.domain.Rating;

// TODO: Auto-generated Javadoc
/**
 * The Class RatingTest.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RatingTest {

	/** The repository. */
	@Autowired
	private RatingRepository repository;

	/** The rating 1. */
	private Rating rating1;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		rating1 = new Rating();
		rating1.setId(0);
		rating1.setMoodysRating("MoodysRating");
		rating1.setSandPRating("SandPRating");
		rating1.setFitchRating("FitchRating");
		rating1.setOrderNumber(41);
	}

	/**
	 * Test when find all then return rating list.
	 */
	@Test
	void test_WhenFindAll_ThenReturnRatingList() {
		List<Rating> response = repository.findAll();

		assertThat(response.size()).isNotNull();
	}

	/**
	 * Test given good new rating when save rating then return saved rating.
	 */
	@Test
	void test_GivenGoodNewRating_WhenSaveRating_ThenReturnSavedRating() {
		Rating response = repository.save(rating1);

		//ratingList size = 2
		rating1.setId(3);

		assertThat(response).isEqualTo(rating1);
	}

	/**
	 * Test given modified rating when update rating then return updated rating.
	 */
	@Test
	void test_GivenModifiedRating_WhenUpdateRating_ThenReturnUpdatedRating() {
		Rating newRating = repository.save(rating1);

		newRating.setMoodysRating("MoodysRating modified");

		Rating response = repository.save(newRating);

		assertThat(response.getMoodysRating()).isEqualTo("MoodysRating modified");
	}

	/**
	 * Test given rating id when delete rating then delete rating.
	 */
	@Test
	void test_GivenRatingId_WhenDeleteRating_ThenDeleteRating() {
		Integer id = rating1.getId();
		repository.delete(rating1);
		Optional<Rating> response = repository.findById(id);

		assertThat(response).isEmpty();
	}

}
