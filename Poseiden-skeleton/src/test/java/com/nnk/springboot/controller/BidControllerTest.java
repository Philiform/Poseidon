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

import com.nnk.springboot.controllers.BidController;
import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.service.BidService;
import com.nnk.springboot.service.DTO.BidDTO;

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
	
	/** The bid DTO 1. */
	private static BidDTO bidDTO1;

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

		bidDTO1 = new BidDTO();
		bidDTO1.setId(1);
		bidDTO1.setAccount("Account A1");
		bidDTO1.setType("Type T1");
		bidDTO1.setBidQuantity(11.1);

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
	 * Test given user when get bid DTO list then return html page bid DTO list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser(username = "user", authorities = "USER")
	public void test_GivenUser_WhenGetBidDTOList_ThenReturnHtmlPageBidDTOList() throws Exception {
		mockMvc
			.perform(get("/bid/list"))
			.andExpect(model().attributeExists("bidDTOList"))
			.andExpect(view().name("bid/list"))
			.andExpect(status().isOk())
			;
//			.andDo(print());

		verify(service, times(1)).getBidDTOList();
	}

	/**
	 * Test when get bid DTO add then return html page bid DTO add.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetBidDTOAdd_ThenReturnHtmlPageBidDTOAdd() throws Exception {
		mockMvc
			.perform(get("/bid/add"))
			.andExpect(view().name("bid/add"))
			.andExpect(status().isOk())
			;
//			.andDo(print());
	}

	/**
	 * Test when get bid DTO validate then return html page bid DTO list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetBidDTOValidate_ThenReturnHtmlPageBidDTOList() throws Exception {
		given(service.saveBidDTO(any())).willReturn(Optional.of(bid1));

		mockMvc
			.perform(post("/bid/validate")
				.flashAttr("bidDTO", bidDTO1))
			.andExpect(view().name("redirect:/bid/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).saveBidDTO(any());
		verify(service, times(1)).getBidDTOList();
	}

	/**
	 * Test when get bid DTO validate then return html page bid DTO add.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetBidDTOValidate_ThenReturnHtmlPageBidDTOAdd() throws Exception {
		mockMvc
			.perform(post("/bid/validate"))
			.andExpect(model().hasErrors())
			.andExpect(view().name("bid/add"))
			.andExpect(status().isOk())
			;
//			.andDo(print());
	}

	/**
	 * Test given id when get bid DTO update then return html page bid DTO update.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenGetBidDTOUpdate_ThenReturnHtmlPageBidDTOUpdate() throws Exception {
		given(service.getBidDTOForUpdate(any(Integer.class))).willReturn(bidDTO1);

		mockMvc
			.perform(get("/bid/update/{id}", 1))
			.andExpect(model().attributeExists("bidDTO"))
			.andExpect(view().name("bid/update"))
			.andExpect(status().isOk())
			;
//			.andDo(print());

		verify(service, times(1)).getBidDTOForUpdate(any(Integer.class));
	}

	/**
	 * Test given bad id when get bid DTO update then redirect to html page bid DTO list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenBadId_WhenGetBidDTOUpdate_ThenRedirectToHtmlPageBidDTOList() throws Exception {
		given(service.getBidDTOForUpdate(any(Integer.class))).willThrow(new IllegalArgumentException());

		mockMvc
			.perform(get("/bid/update/{id}", 1))
			.andExpect(view().name("redirect:/bid/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).getBidDTOForUpdate(any(Integer.class));
	}

	/**
	 * Test given good id when get bid DTO update then return updated bid DTO.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenGoodId_WhenGetBidDTOUpdate_ThenReturnUpdatedBidDTO() throws Exception {
		mockMvc
			.perform(post("/bid/update/{id}", 1)
				.flashAttr("bidDTO", bidDTO1))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/bid/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).updateBidDTO(any(Integer.class), any());
		verify(service, times(1)).getBidDTOList();
	}

	/**
	 * Test given empty bid DTO when get bid DTO update then return html page bid DTO update.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenEmptyBidDTO_WhenGetBidDTOUpdate_ThenReturnHtmlPageBidDTOUpdate() throws Exception {
		mockMvc
			.perform(post("/bid/update/{id}", 1)
				.flashAttr("bidDTO", new BidDTO()))
			.andExpect(model().hasErrors())
			.andExpect(view().name("bid/update"))
			.andExpect(status().isOk())
			;
//			.andDo(print());

		verify(service, times(0)).updateBidDTO(any(Integer.class), any());
		verify(service, times(0)).getBidDTOList();
	}

	/**
	 * Test given illegal argument exception when get bid DTO update then redirect to html page bid DTO list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenIllegalArgumentException_WhenGetBidDTOUpdate_ThenRedirectToHtmlPageBidDTOList() throws Exception {
		given(service.updateBidDTO(any(Integer.class), any())).willThrow(new IllegalArgumentException());

		mockMvc
			.perform(post("/bid/update/{id}", 1)
				.flashAttr("bidDTO", bidDTO1))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/bid/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).updateBidDTO(any(Integer.class), any());
		verify(service, times(0)).getBidDTOList();
	}

	/**
	 * Test given id when get bid DTO delete then return html page deleted bid DTO.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenGetBidDTODelete_ThenReturnHtmlPageDeletedBidDTO() throws Exception {
		mockMvc
			.perform(get("/bid/delete/{id}", 1))
			.andExpect(view().name("redirect:/bid/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).deleteBid(any(Integer.class));
		verify(service, times(1)).getBidDTOList();
	}
/*
	@Test
	public void test_GivenIllegalArgumentException_WhenGetBidDTODelete_ThenRedirectToHtmlPageBidDTOList() throws Exception {
//		given(service.deleteBidDTO(any(Integer.class))).willThrow(new IllegalArgumentException());
		given(service.deleteBidDTO(any(Integer.class))).willThrow(new IllegalArgumentException());

		mockMvc
			.perform(get("/bid/delete/{id}", 1))
			.andExpect(view().name("redirect:/bid/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).deleteBidDTO(any(Integer.class));
		verify(service, times(0)).getBidDTOList();
	}
*/
}
