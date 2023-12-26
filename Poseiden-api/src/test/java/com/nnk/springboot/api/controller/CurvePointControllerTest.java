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
import com.nnk.springboot.api.controllers.CurvePointController;
import com.nnk.springboot.api.domain.CurvePoint;
import com.nnk.springboot.api.service.CurvePointService;

// TODO: Auto-generated Javadoc
/**
 * The Class CurvePointControllerTest.
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class CurvePointControllerTest {

	/** The controller. */
	@Autowired
	private CurvePointController controller;

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The service. */
	@MockBean
	public CurvePointService service;

	/** The curve point 1. */
	private static CurvePoint curvePoint1;
	
	/**
	 * Sets the up before class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		curvePoint1 = new CurvePoint();
		curvePoint1.setId(1);
		curvePoint1.setCurveId(1);
		curvePoint1.setTerm(11.1);
		curvePoint1.setValue(22.2);
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
	 * Test when get curve point list then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetCurvePointList_ThenReturnStatusOk() throws Exception {
		mockMvc
			.perform(get("/curvePoint/list"))
			.andExpect(status().isOk());

		verify(service, times(1)).getCurvePointList();
	}

	/**
	 * Test when save curve point then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenSaveCurvePoint_ThenReturnStatusOk() throws Exception {
		given(service.saveCurvePoint(any())).willReturn(Optional.of(curvePoint1));

		mockMvc
			.perform(post("/curvePoint/save")
					.content(asJsonString(curvePoint1))
				.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

		verify(service, times(1)).saveCurvePoint(any());
	}

	/**
	 * Test when get curve point by id then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenGetCurvePointById_ThenReturnStatusOk() throws Exception {
		given(service.getCurvePointById(any())).willReturn(curvePoint1);

		mockMvc
			.perform(get("/curvePoint/{id}", 0))
			.andExpect(status().isOk());

		verify(service, times(1)).getCurvePointById(any());
	}

	/**
	 * Test given id when curve point update by id then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenCurvePointUpdateById_ThenReturnStatusOk() throws Exception {
		given(service.updateCurvePoint(any(Integer.class), any(CurvePoint.class))).willReturn(Optional.of(curvePoint1));

		mockMvc
			.perform(put("/curvePoint/update/{id}", 1)
				.content(asJsonString(curvePoint1))
			.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

		verify(service, times(1)).updateCurvePoint(any(Integer.class), any(CurvePoint.class));
	}

	/**
	 * Test given bad id when curve point update by id then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenBadId_WhenGetCurvePointUpdateById_ThenReturnStatusOk() throws Exception {
		given(service.getCurvePointById(any(Integer.class))).willThrow(new IllegalArgumentException());

		mockMvc
			.perform(put("/curvePoint/update/{id}", 1)
					.content(asJsonString(curvePoint1))
				.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

		verify(service, times(1)).updateCurvePoint(any(Integer.class), any(CurvePoint.class));
	}

	/**
	 * Test given id when curve point delete then return status OK.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenCurvePointDelete_ThenReturnStatusOk() throws Exception {
		mockMvc
			.perform(delete("/curvePoint/delete/{id}", 1))
			.andExpect(status().isOk());

		verify(service, times(1)).deleteCurvePoint(any(Integer.class));
	}

	private static String asJsonString(final Object obj) {
		try {
		    return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
		    throw new RuntimeException(e);
		}
	}
}
