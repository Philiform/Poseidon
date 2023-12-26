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
import org.modelmapper.ModelMapper;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.proxies.CurvePointProxy;
import com.nnk.springboot.service.DTO.CurvePointDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class CurvePointServiceTest.
 */
@ExtendWith(MockitoExtension.class)
public class CurvePointServiceTest {

	/** The service. */
	@InjectMocks
	private CurvePointService service;

	/** The curve point connection proxy. */
	@Mock
	private CurvePointProxy proxy;

	/** The model mapper. */
	@Mock
	private ModelMapper modelMapper;

	/** The list curve point. */
	List<CurvePoint> listCurvePoint = new ArrayList<>();
	
	/** The list curve point DTO. */
	List<CurvePointDTO> listCurvePointDTO = new ArrayList<>();
	
	/** The curve point 1. */
	private CurvePoint curvePoint1;
	
	/** The curve point 2. */
	private CurvePoint curvePoint2;
	
	/** The curve point DTO 1. */
	private CurvePointDTO curvePointDTO1;
	
	/** The curve point DTO 2. */
	private CurvePointDTO curvePointDTO2;

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

		curvePointDTO1 = new CurvePointDTO();
		curvePointDTO1.setId(1);
		curvePointDTO1.setCurveId(1);
		curvePointDTO1.setTerm(11.1);
		curvePointDTO1.setValue(22.2);

		curvePointDTO2 = new CurvePointDTO();

		listCurvePointDTO.add(curvePointDTO1);
		listCurvePointDTO.add(curvePointDTO2);
	}

	/**
	 * Test when get curve point DTO list then return list curve point DTO not empty.
	 */
	@Test
	void test_WhenGetCurvePointDTOList_ThenReturnListCurvePointDTONotEmpty() {
		given(proxy.findAll()).willReturn(listCurvePoint);

		List<CurvePointDTO> response = service.getCurvePointDTOList();

		verify(proxy, times(1)).findAll();
		assertThat(response).isEqualTo(listCurvePointDTO);
	}

	/**
	 * Test given good new curve point DTO when save curve point DTO then return saved curve point.
	 */
	@Test
	void test_GivenGoodNewCurvePointDTO_WhenSaveCurvePointDTO_ThenReturnSavedCurvePoint() {
		given(modelMapper.map(any(), any())).willReturn(curvePoint1);
		given(proxy.save(any())).willReturn(curvePoint1);

		Optional<CurvePoint> response = service.saveCurvePointDTO(curvePointDTO1);

		verify(modelMapper, times(1)).map(any(), any());
		verify(proxy, times(1)).save(any());
		assertThat(response).isEqualTo(Optional.of(curvePoint1));
	}

	/**
	 * Test given good curve point DTO id when get curve point DTO for update then return curve point DTO.
	 */
	@Test
	void test_GivenGoodCurvePointDTOId_WhenGetCurvePointDTOForUpdate_ThenReturnCurvePointDTO() {
		given(proxy.findById(any(Integer.class))).willReturn(curvePoint1);
		given(modelMapper.map(any(), any())).willReturn(curvePointDTO1);

		CurvePointDTO response = service.getCurvePointDTOForUpdate(1);

		verify(proxy, times(1)).findById(any(Integer.class));
		verify(modelMapper, times(1)).map(any(), any());
		assertThat(response).isEqualTo(curvePointDTO1);
	}

	/**
	 * Test given bad curve point DTO id when get curve point DTO for update then throw illegal argument exception.
	 */
	@Test
	void test_GivenBadCurvePointDTOId_WhenGetCurvePointDTOForUpdate_ThenThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> service.getCurvePointDTOForUpdate(10));
		verify(proxy, times(1)).findById(any(Integer.class));
		verify(modelMapper, times(0)).map(any(), any());
	}

	/**
	 * Test given good update curve point DTO when get curve point DTO for update then return updated curve point DTO.
	 */
	@Test
	void test_GivenGoodUpdateCurvePointDTO_WhenGetCurvePointDTOForUpdate_ThenReturnUpdatedCurvePointDTO() {
		given(proxy.findById(any())).willReturn(curvePoint1);
		given(modelMapper.map(any(), any())).willReturn(curvePointDTO1);

		CurvePointDTO response = service.getCurvePointDTOForUpdate(1);

		verify(proxy, times(1)).findById(any());
		verify(modelMapper, times(1)).map(any(), any());
		assertThat(response).isEqualTo(curvePointDTO1);
	}

	/**
	 * Test given bad update curve point DTO when get curve point DTO for update then throw illegal argument exception.
	 */
	@Test
	void test_GivenBadUpdateCurvePointDTO_WhenGetCurvePointDTOForUpdate_ThenThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> service.getCurvePointDTOForUpdate(10));
		verify(proxy, times(1)).findById(any(Integer.class));
	}

	/**
	 * Test given good update curve point DTO when update curve point DTO then return updated curve point DTO.
	 */
	@Test
	void test_GivenGoodUpdateCurvePointDTO_WhenUpdateCurvePointDTO_ThenReturnUpdatedCurvePointDTO() {
		given(modelMapper.map(any(), any())).willReturn(curvePoint1);
		given(proxy.save(any())).willReturn(curvePoint1);

		Optional<CurvePoint> response = service.updateCurvePointDTO(1, curvePointDTO1);

		verify(modelMapper, times(1)).map(any(), any());
		verify(proxy, times(1)).save(any());
		assertThat(response).isEqualTo(Optional.of(curvePoint1));
	}

	/**
	 * Test given good curve point DTO id when delere curve point DTO then delete curve point DTO.
	 */
	@Test
	void test_GivenGoodCurvePointDTOId_WhenDelereCurvePointDTO_ThenDeleteCurvePointDTO() {
		service.deleteCurvePoint(1);

		verify(proxy, times(1)).deleteById(any(Integer.class));
	}

}
