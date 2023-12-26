package com.nnk.springboot.api.service;

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

import com.nnk.springboot.api.domain.CurvePoint;
import com.nnk.springboot.api.repositories.CurvePointRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class CurvePointServiceTest.
 */
@ExtendWith(MockitoExtension.class)
public class CurvePointServiceTest {

	/** The service. */
	@InjectMocks
	private CurvePointService service;

	/** The curve point connection repository. */
	@Mock
	private CurvePointRepository repository;

	/** The list curve point. */
	List<CurvePoint> listCurvePoint = new ArrayList<>();
	
	/** The curve point 1. */
	private CurvePoint curvePoint1;
	
	/** The curve point 2. */
	private CurvePoint curvePoint2;
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		curvePoint1 = new CurvePoint();
		curvePoint1.setId(1);
		curvePoint1.setCurveId(1);
		curvePoint1.setTerm(11.1);
		curvePoint1.setValue(22.2);

		curvePoint2 = new CurvePoint();

		listCurvePoint.add(curvePoint1);
		listCurvePoint.add(curvePoint2);
	}

	/**
	 * Test when get curve point list then return list curve point not empty.
	 */
	@Test
	void test_WhenGetCurvePointList_ThenReturnListCurvePointNotEmpty() {
		given(repository.findAll()).willReturn(listCurvePoint);

		List<CurvePoint> response = service.getCurvePointList();

		verify(repository, times(1)).findAll();
		assertThat(response).isEqualTo(listCurvePoint);
	}

	/**
	 * Test given good new curve point when save curve point then return saved curve point.
	 */
	@Test
	void test_GivenGoodNewCurvePoint_WhenSaveCurvePoint_ThenReturnSavedCurvePoint() {
		given(repository.save(any())).willReturn(curvePoint1);

		Optional<CurvePoint> response = service.saveCurvePoint(curvePoint1);

		verify(repository, times(1)).save(any());
		assertThat(response).isEqualTo(Optional.of(curvePoint1));
	}

	/**
	 * Test given good curve point id when get curve point for update then return curve point .
	 */
	@Test
	void test_GivenGoodCurvePointId_WhenGetCurvePointForUpdate_ThenReturnCurvePoint() {
		given(repository.findById(any(Integer.class))).willReturn(Optional.of(curvePoint1));

		CurvePoint response = service.getCurvePointById(1);

		verify(repository, times(1)).findById(any(Integer.class));
		assertThat(response).isEqualTo(curvePoint1);
	}

	/**
	 * Test given bad curve point id when get curve point for update then throw illegal argument exception.
	 */
	@Test
	void test_GivenBadCurvePointId_WhenGetCurvePointForUpdate_ThenThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> service.getCurvePointById(10));
		verify(repository, times(1)).findById(any(Integer.class));
	}

	/**
	 * Test given good update curve point when get curve point for update then return updated curve point .
	 */
	@Test
	void test_GivenGoodUpdateCurvePoint_WhenGetCurvePointForUpdate_ThenReturnUpdatedCurvePoint() {
		given(repository.findById(any())).willReturn(Optional.of(curvePoint1));

		CurvePoint response = service.getCurvePointById(1);

		verify(repository, times(1)).findById(any());
		assertThat(response).isEqualTo(curvePoint1);
	}

	/**
	 * Test given bad update curve point when get curve point for update then throw illegal argument exception.
	 */
	@Test
	void test_GivenBadUpdateCurvePoint_WhenGetCurvePointForUpdate_ThenThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> service.getCurvePointById(10));
		verify(repository, times(1)).findById(any(Integer.class));
	}

	/**
	 * Test given good update curve point when update curve point then return updated curve point .
	 */
	@Test
	void test_GivenGoodUpdateCurvePoint_WhenUpdateCurvePoint_ThenReturnUpdatedCurvePoint() {
		given(repository.save(any())).willReturn(curvePoint1);

		Optional<CurvePoint> response = service.updateCurvePoint(1, curvePoint1);

		verify(repository, times(1)).save(any());
		assertThat(response).isEqualTo(Optional.of(curvePoint1));
	}

	/**
	 * Test given good curve point id when delere curve point then delete curve point .
	 */
	@Test
	void test_GivenGoodCurvePointId_WhenDelereCurvePoint_ThenDeleteCurvePoint() {
		service.deleteCurvePoint(1);

		verify(repository, times(1)).deleteById(any());
	}

}
