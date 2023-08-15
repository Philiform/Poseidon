package com.nnk.springboot.repositories;

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

import com.nnk.springboot.domain.CurvePoint;

// TODO: Auto-generated Javadoc
/**
 * The Class CurvePointTest.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CurvePointTest {

	/** The repository. */
	@Autowired
	private CurvePointRepository repository;

	/** The curve point 1. */
	private CurvePoint curvePoint1;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		curvePoint1 = new CurvePoint();
		curvePoint1.setId(0);
		curvePoint1.setCurveId(1);
		curvePoint1.setTerm(11.1);
		curvePoint1.setValue(22.2);
	}

	/**
	 * Test when find all then return curve point list.
	 */
	@Test
	void test_WhenFindAll_ThenReturnCurvePointList() {
		List<CurvePoint> response = repository.findAll();

		assertThat(response.size()).isNotNull();
	}

	/**
	 * Test given good new curve point when save curve point then return saved curve point.
	 */
	@Test
	void test_GivenGoodNewCurvePoint_WhenSaveCurvePoint_ThenReturnSavedCurvePoint() {
		CurvePoint response = repository.save(curvePoint1);

		// curvePointList size = 2
		curvePoint1.setId(3);

		assertThat(response).isEqualTo(curvePoint1);
	}

	/**
	 * Test given modified curve point when update curve point then return updated curve point.
	 */
	@Test
	void test_GivenModifiedCurvePoint_WhenUpdateCurvePoint_ThenReturnUpdatedCurvePoint() {
		CurvePoint newCurvePoint = repository.save(curvePoint1);

		newCurvePoint.setCurveId(3);

		CurvePoint response = repository.save(newCurvePoint);

		assertThat(response.getCurveId()).isEqualTo(3);
	}

	/**
	 * Test given curve point id when delete curve point then delete curve point.
	 */
	@Test
	void test_GivenCurvePointId_WhenDeleteCurvePoint_ThenDeleteCurvePoint() {
		Integer id = curvePoint1.getId();
		repository.delete(curvePoint1);
		Optional<CurvePoint> response = repository.findById(id);

		assertThat(response).isEmpty();
	}

}
