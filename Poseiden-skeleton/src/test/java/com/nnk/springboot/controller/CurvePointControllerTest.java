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

import com.nnk.springboot.controllers.CurvePointController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
import com.nnk.springboot.service.DTO.CurvePointDTO;

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
	
	/** The curve point DTO 1. */
	private static CurvePointDTO curvePointDTO1;

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

		curvePointDTO1 = new CurvePointDTO();
		curvePointDTO1.setId(1);
		curvePointDTO1.setCurveId(1);
		curvePointDTO1.setTerm(11.1);
		curvePointDTO1.setValue(22.2);

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
	 * Test given user when get curve point DTO list then return html page curve point DTO list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser(username = "user", authorities = "USER")
	public void test_GivenUser_WhenGetCurvePointDTOList_ThenReturnHtmlPageCurvePointDTOList() throws Exception {
		mockMvc
			.perform(get("/curvePoint/list"))
			.andExpect(model().attributeExists("curvePointDTOList"))
			.andExpect(view().name("curvePoint/list"))
			.andExpect(status().isOk())
			;
//			.andDo(print());

		verify(service, times(1)).getCurvePointDTOList();
	}

	/**
	 * Test when get curve point DTO add then return html page curve point DTO add.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetCurvePointDTOAdd_ThenReturnHtmlPageCurvePointDTOAdd() throws Exception {
		mockMvc
			.perform(get("/curvePoint/add"))
			.andExpect(view().name("curvePoint/add"))
			.andExpect(status().isOk())
			;
//			.andDo(print());
	}

	/**
	 * Test when get curve point DTO validate then return html page curve point DTO list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetCurvePointDTOValidate_ThenReturnHtmlPageCurvePointDTOList() throws Exception {
		given(service.saveCurvePointDTO(any())).willReturn(Optional.of(curvePoint1));

		mockMvc
			.perform(post("/curvePoint/validate")
				.flashAttr("curvePointDTO", curvePointDTO1))
			.andExpect(view().name("redirect:/curvePoint/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).saveCurvePointDTO(any());
		verify(service, times(1)).getCurvePointDTOList();
	}

	/**
	 * Test when get curve point DTO validate then return html page curve point DTO add.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetCurvePointDTOValidate_ThenReturnHtmlPageCurvePointDTOAdd() throws Exception {
		mockMvc
			.perform(post("/curvePoint/validate"))
			.andExpect(model().hasErrors())
			.andExpect(view().name("curvePoint/add"))
			.andExpect(status().isOk())
			;
//			.andDo(print());
	}

	/**
	 * Test given id when get curve point DTO update then return html page curve point DTO update.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenGetCurvePointDTOUpdate_ThenReturnHtmlPageCurvePointDTOUpdate() throws Exception {
		given(service.getCurvePointDTOForUpdate(any(Integer.class))).willReturn(curvePointDTO1);

		mockMvc
			.perform(get("/curvePoint/update/{id}", 1))
			.andExpect(model().attributeExists("curvePointDTO"))
			.andExpect(view().name("curvePoint/update"))
			.andExpect(status().isOk())
			;
//			.andDo(print());

		verify(service, times(1)).getCurvePointDTOForUpdate(any(Integer.class));
	}

	/**
	 * Test given bad id when get curve point DTO update then redirect to html page curve point DTO list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenBadId_WhenGetCurvePointDTOUpdate_ThenRedirectToHtmlPageCurvePointDTOList() throws Exception {
		given(service.getCurvePointDTOForUpdate(any(Integer.class))).willThrow(new IllegalArgumentException());

		mockMvc
			.perform(get("/curvePoint/update/{id}", 1))
			.andExpect(view().name("redirect:/curvePoint/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).getCurvePointDTOForUpdate(any(Integer.class));
	}

	/**
	 * Test given good id when get curve point DTO update then return updated curve point DTO.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenGoodId_WhenGetCurvePointDTOUpdate_ThenReturnUpdatedCurvePointDTO() throws Exception {
		mockMvc
			.perform(post("/curvePoint/update/{id}", 1)
				.flashAttr("curvePointDTO", curvePointDTO1))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/curvePoint/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).updateCurvePointDTO(any(Integer.class), any());
		verify(service, times(1)).getCurvePointDTOList();
	}

	/**
	 * Test given empty curve point DTO when get curve point DTO update then return html page curve point DTO update.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenEmptyCurvePointDTO_WhenGetCurvePointDTOUpdate_ThenReturnHtmlPageCurvePointDTOUpdate() throws Exception {
		mockMvc
			.perform(post("/curvePoint/update/{id}", 1)
				.flashAttr("curvePointDTO", new CurvePointDTO()))
			.andExpect(model().hasErrors())
			.andExpect(view().name("curvePoint/update"))
			.andExpect(status().isOk())
			;
//			.andDo(print());

		verify(service, times(0)).updateCurvePointDTO(any(Integer.class), any());
		verify(service, times(0)).getCurvePointDTOList();
	}

	/**
	 * Test given illegal argument exception when get curve point DTO update then redirect to html page curve point DTO list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenIllegalArgumentException_WhenGetCurvePointDTOUpdate_ThenRedirectToHtmlPageCurvePointDTOList() throws Exception {
		given(service.updateCurvePointDTO(any(Integer.class), any())).willThrow(new IllegalArgumentException());

		mockMvc
			.perform(post("/curvePoint/update/{id}", 1)
				.flashAttr("curvePointDTO", curvePointDTO1))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/curvePoint/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).updateCurvePointDTO(any(Integer.class), any());
		verify(service, times(0)).getCurvePointDTOList();
	}

	/**
	 * Test given id when get curve point DTO delete then return html page deleted curve point DTO.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenGetCurvePointDTODelete_ThenReturnHtmlPageDeletedCurvePointDTO() throws Exception {
		mockMvc
			.perform(get("/curvePoint/delete/{id}", 1))
			.andExpect(view().name("redirect:/curvePoint/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).deleteCurvePoint(any(Integer.class));
		verify(service, times(1)).getCurvePointDTOList();
	}

}
