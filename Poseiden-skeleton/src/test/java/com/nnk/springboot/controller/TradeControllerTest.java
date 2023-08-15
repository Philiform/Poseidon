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

import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
import com.nnk.springboot.service.DTO.TradeDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class TradeControllerTest.
 */
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TradeControllerTest {

	/** The controller. */
	@Autowired
	private TradeController controller;

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The service. */
	@MockBean
	public TradeService service;

	/** The trade 1. */
	private static Trade trade1;
	
	/** The trade DTO 1. */
	private static TradeDTO tradeDTO1;

	/**
	 * Sets the up before class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		trade1 = new Trade();
		trade1.setId(1);
		trade1.setAccount("Account A1");
		trade1.setType("Type T1");
		trade1.setBuyQuantity(11.1);

		tradeDTO1 = new TradeDTO();
		tradeDTO1.setId(1);
		tradeDTO1.setAccount("Account A1");
		tradeDTO1.setType("Type T1");
		tradeDTO1.setBuyQuantity(11.1);

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
	 * Test given user when get trade DTO list then return html page trade DTO list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser(username = "user", authorities = "USER")
	public void test_GivenUser_WhenGetTradeDTOList_ThenReturnHtmlPageTradeDTOList() throws Exception {
		mockMvc
			.perform(get("/trade/list"))
			.andExpect(model().attributeExists("tradeDTOList"))
			.andExpect(view().name("trade/list"))
			.andExpect(status().isOk())
			;
//			.andDo(print());

		verify(service, times(1)).getTradeDTOList();
	}

	/**
	 * Test when get trade DTO add then return html page trade DTO add.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetTradeDTOAdd_ThenReturnHtmlPageTradeDTOAdd() throws Exception {
		mockMvc
			.perform(get("/trade/add"))
			.andExpect(view().name("trade/add"))
			.andExpect(status().isOk())
			;
//			.andDo(print());
	}

	/**
	 * Test when get trade DTO validate then return html page trade DTO list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetTradeDTOValidate_ThenReturnHtmlPageTradeDTOList() throws Exception {
		given(service.saveTradeDTO(any())).willReturn(Optional.of(trade1));

		mockMvc
			.perform(post("/trade/validate")
				.flashAttr("tradeDTO", tradeDTO1))
			.andExpect(view().name("redirect:/trade/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).saveTradeDTO(any());
		verify(service, times(1)).getTradeDTOList();
	}

	/**
	 * Test when get trade DTO validate then return html page trade DTO add.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_WhenGetTradeDTOValidate_ThenReturnHtmlPageTradeDTOAdd() throws Exception {
		mockMvc
			.perform(post("/trade/validate"))
			.andExpect(model().hasErrors())
			.andExpect(view().name("trade/add"))
			.andExpect(status().isOk())
			;
//			.andDo(print());
	}

	/**
	 * Test given id when get trade DTO update then return html page trade DTO update.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenGetTradeDTOUpdate_ThenReturnHtmlPageTradeDTOUpdate() throws Exception {
		given(service.getTradeDTOForUpdate(any(Integer.class))).willReturn(tradeDTO1);

		mockMvc
			.perform(get("/trade/update/{id}", 1))
			.andExpect(model().attributeExists("tradeDTO"))
			.andExpect(view().name("trade/update"))
			.andExpect(status().isOk())
			;
//			.andDo(print());

		verify(service, times(1)).getTradeDTOForUpdate(any(Integer.class));
	}

	/**
	 * Test given bad id when get trade DTO update then redirect to html page trade DTO list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenBadId_WhenGetTradeDTOUpdate_ThenRedirectToHtmlPageTradeDTOList() throws Exception {
		given(service.getTradeDTOForUpdate(any(Integer.class))).willThrow(new IllegalArgumentException());

		mockMvc
			.perform(get("/trade/update/{id}", 1))
			.andExpect(view().name("redirect:/trade/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).getTradeDTOForUpdate(any(Integer.class));
	}

	/**
	 * Test given good id when get trade DTO update then return updated trade DTO.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenGoodId_WhenGetTradeDTOUpdate_ThenReturnUpdatedTradeDTO() throws Exception {
		mockMvc
			.perform(post("/trade/update/{id}", 1)
				.flashAttr("tradeDTO", tradeDTO1))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/trade/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).updateTradeDTO(any(Integer.class), any());
		verify(service, times(1)).getTradeDTOList();
	}

	/**
	 * Test given empty trade DTO when get trade DTO update then return html page trade DTO update.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenEmptyTradeDTO_WhenGetTradeDTOUpdate_ThenReturnHtmlPageTradeDTOUpdate() throws Exception {
		mockMvc
			.perform(post("/trade/update/{id}", 1)
				.flashAttr("tradeDTO", new TradeDTO()))
			.andExpect(model().hasErrors())
			.andExpect(view().name("trade/update"))
			.andExpect(status().isOk())
			;
//			.andDo(print());

		verify(service, times(0)).updateTradeDTO(any(Integer.class), any());
		verify(service, times(0)).getTradeDTOList();
	}

	/**
	 * Test given illegal argument exception when get trade DTO update then redirect to html page trade DTO list.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenIllegalArgumentException_WhenGetTradeDTOUpdate_ThenRedirectToHtmlPageTradeDTOList() throws Exception {
		given(service.updateTradeDTO(any(Integer.class), any())).willThrow(new IllegalArgumentException());

		mockMvc
			.perform(post("/trade/update/{id}", 1)
				.flashAttr("tradeDTO", tradeDTO1))
			.andExpect(model().hasNoErrors())
			.andExpect(view().name("redirect:/trade/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).updateTradeDTO(any(Integer.class), any());
		verify(service, times(0)).getTradeDTOList();
	}

	/**
	 * Test given id when get trade DTO delete then return html page deleted trade DTO.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test_GivenId_WhenGetTradeDTODelete_ThenReturnHtmlPageDeletedTradeDTO() throws Exception {
		mockMvc
			.perform(get("/trade/delete/{id}", 1))
			.andExpect(view().name("redirect:/trade/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).deleteTrade(any(Integer.class));
		verify(service, times(1)).getTradeDTOList();
	}
/*
	@Test
	public void test_GivenIllegalArgumentException_WhenGetTradeDTODelete_ThenRedirectToHtmlPageTradeDTOList() throws Exception {
//		given(service.deleteTradeDTO(any(Integer.class))).willThrow(new IllegalArgumentException());
		given(service.deleteTradeDTO(any(Integer.class))).willThrow(new IllegalArgumentException());

		mockMvc
			.perform(get("/trade/delete/{id}", 1))
			.andExpect(view().name("redirect:/trade/list"))
			.andExpect(status().is3xxRedirection())
			;
//			.andDo(print());

		verify(service, times(1)).deleteTradeDTO(any(Integer.class));
		verify(service, times(0)).getTradeDTOList();
	}
*/
}
