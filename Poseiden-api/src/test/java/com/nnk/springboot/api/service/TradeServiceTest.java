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

import com.nnk.springboot.api.domain.Trade;
import com.nnk.springboot.api.repositories.TradeRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class TradeServiceTest.
 */
@ExtendWith(MockitoExtension.class)
public class TradeServiceTest {

	/** The service. */
	@InjectMocks
	private TradeService service;

	/** The trade connection repository. */
	@Mock
	private TradeRepository repository;

	/** The list trade. */
	List<Trade> listTrade = new ArrayList<>();
	
	/** The trade 1. */
	private Trade trade1;
	
	/** The trade 2. */
	private Trade trade2;
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		trade1 = new Trade();
		trade1.setId(1);
		trade1.setAccount("Account A1");
		trade1.setType("Type T1");
		trade1.setBuyQuantity(11.1);

		trade2 = new Trade();

		listTrade.add(trade1);
		listTrade.add(trade2);
	}

	/**
	 * Test when get trade list then return list trade not empty.
	 */
	@Test
	void test_WhenGetTradeList_ThenReturnListTradeNotEmpty() {
		given(repository.findAll()).willReturn(listTrade);

		List<Trade> response = service.getTradeList();

		verify(repository, times(1)).findAll();
		assertThat(response).isEqualTo(listTrade);
	}

	/**
	 * Test given good new trade when save trade then return saved trade.
	 */
	@Test
	void test_GivenGoodNewTrade_WhenSaveTrade_ThenReturnSavedTrade() {
		given(repository.save(any())).willReturn(trade1);

		Optional<Trade> response = service.saveTrade(trade1);

		verify(repository, times(1)).save(any());
		assertThat(response).isEqualTo(Optional.of(trade1));
	}

	/**
	 * Test given good trade id when get trade for update then return trade .
	 */
	@Test
	void test_GivenGoodTradeId_WhenGetTradeForUpdate_ThenReturnTrade() {
		given(repository.findById(any(Integer.class))).willReturn(Optional.of(trade1));

		Trade response = service.getTradeById(1);

		verify(repository, times(1)).findById(any(Integer.class));
		assertThat(response).isEqualTo(trade1);
	}

	/**
	 * Test given bad trade id when get trade for update then throw illegal argument exception.
	 */
	@Test
	void test_GivenBadTradeId_WhenGetTradeForUpdate_ThenThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> service.getTradeById(10));
		verify(repository, times(1)).findById(any(Integer.class));
	}

	/**
	 * Test given good update trade when get trade for update then return updated trade .
	 */
	@Test
	void test_GivenGoodUpdateTrade_WhenGetTradeForUpdate_ThenReturnUpdatedTrade() {
		given(repository.findById(any())).willReturn(Optional.of(trade1));

		Trade response = service.getTradeById(1);

		verify(repository, times(1)).findById(any());
		assertThat(response).isEqualTo(trade1);
	}

	/**
	 * Test given bad update trade when get trade for update then throw illegal argument exception.
	 */
	@Test
	void test_GivenBadUpdateTrade_WhenGetTradeForUpdate_ThenThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> service.getTradeById(10));
		verify(repository, times(1)).findById(any(Integer.class));
	}

	/**
	 * Test given good update trade when update trade then return updated trade .
	 */
	@Test
	void test_GivenGoodUpdateTrade_WhenUpdateTrade_ThenReturnUpdatedTrade() {
		given(repository.save(any())).willReturn(trade1);

		Optional<Trade> response = service.updateTrade(1, trade1);

		verify(repository, times(1)).save(any());
		assertThat(response).isEqualTo(Optional.of(trade1));
	}

	/**
	 * Test given good trade id when delete trade then delete trade .
	 */
	@Test
	void test_GivenGoodTradeId_WhenDeleteTrade_ThenDeleteTrade() {
		service.deleteTrade(1);

		verify(repository, times(1)).deleteById(any());
	}

}
