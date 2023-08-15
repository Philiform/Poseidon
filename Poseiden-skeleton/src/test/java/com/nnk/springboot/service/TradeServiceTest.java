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

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.DTO.TradeDTO;

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

	/** The model mapper. */
	@Mock
	private ModelMapper modelMapper;

	/** The list trade. */
	List<Trade> listTrade = new ArrayList<>();
	
	/** The list trade DTO. */
	List<TradeDTO> listTradeDTO = new ArrayList<>();
	
	/** The trade 1. */
	private Trade trade1;
	
	/** The trade 2. */
	private Trade trade2;
	
	/** The trade DTO 1. */
	private TradeDTO tradeDTO1;
	
	/** The trade DTO 2. */
	private TradeDTO tradeDTO2;

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

		tradeDTO1 = new TradeDTO();
		tradeDTO1.setId(1);
		tradeDTO1.setAccount("Account A1");
		tradeDTO1.setType("Type T1");
		tradeDTO1.setBuyQuantity(11.1);

		tradeDTO2 = new TradeDTO();

		listTradeDTO.add(tradeDTO1);
		listTradeDTO.add(tradeDTO2);
	}

	/**
	 * Test when get trade DTO list then return list trade DTO not empty.
	 */
	@Test
	void test_WhenGetTradeDTOList_ThenReturnListTradeDTONotEmpty() {
		given(repository.findAll()).willReturn(listTrade);

		List<TradeDTO> response = service.getTradeDTOList();

		verify(repository, times(1)).findAll();
		assertThat(response).isEqualTo(listTradeDTO);
	}

	/**
	 * Test given good new trade DTO when save trade DTO then return saved trade.
	 */
	@Test
	void test_GivenGoodNewTradeDTO_WhenSaveTradeDTO_ThenReturnSavedTrade() {
		given(modelMapper.map(any(), any())).willReturn(trade1);
		given(repository.save(any())).willReturn(trade1);

		Optional<Trade> response = service.saveTradeDTO(tradeDTO1);

		verify(modelMapper, times(1)).map(any(), any());
		verify(repository, times(1)).save(any());
		assertThat(response).isEqualTo(Optional.of(trade1));
	}

	/**
	 * Test given good trade DTO id when get trade DTO for update then return trade DTO.
	 */
	@Test
	void test_GivenGoodTradeDTOId_WhenGetTradeDTOForUpdate_ThenReturnTradeDTO() {
		given(repository.findById(any(Integer.class))).willReturn(Optional.of(trade1));
		given(modelMapper.map(any(), any())).willReturn(tradeDTO1);

		TradeDTO response = service.getTradeDTOForUpdate(1);

		verify(repository, times(1)).findById(any(Integer.class));
		verify(modelMapper, times(1)).map(any(), any());
		assertThat(response).isEqualTo(tradeDTO1);
	}

	/**
	 * Test given bad trade DTO id when get trade DTO for update then throw illegal argument exception.
	 */
	@Test
	void test_GivenBadTradeDTOId_WhenGetTradeDTOForUpdate_ThenThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> service.getTradeDTOForUpdate(10));
		verify(repository, times(1)).findById(any(Integer.class));
		verify(modelMapper, times(0)).map(any(), any());
	}

	/**
	 * Test given good update trade DTO when get trade DTO for update then return updated trade DTO.
	 */
	@Test
	void test_GivenGoodUpdateTradeDTO_WhenGetTradeDTOForUpdate_ThenReturnUpdatedTradeDTO() {
		given(repository.findById(any())).willReturn(Optional.of(trade1));
		given(modelMapper.map(any(), any())).willReturn(tradeDTO1);

		TradeDTO response = service.getTradeDTOForUpdate(1);

		verify(repository, times(1)).findById(any());
		verify(modelMapper, times(1)).map(any(), any());
		assertThat(response).isEqualTo(tradeDTO1);
	}

	/**
	 * Test given bad update trade DTO when get trade DTO for update then throw illegal argument exception.
	 */
	@Test
	void test_GivenBadUpdateTradeDTO_WhenGetTradeDTOForUpdate_ThenThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> service.getTradeDTOForUpdate(10));
		verify(repository, times(1)).findById(any(Integer.class));
	}

	/**
	 * Test given good update trade DTO when update trade DTO then return updated trade DTO.
	 */
	@Test
	void test_GivenGoodUpdateTradeDTO_WhenUpdateTradeDTO_ThenReturnUpdatedTradeDTO() {
		given(modelMapper.map(any(), any())).willReturn(trade1);
		given(repository.save(any())).willReturn(trade1);

		Optional<Trade> response = service.updateTradeDTO(1, tradeDTO1);

		verify(modelMapper, times(1)).map(any(), any());
		verify(repository, times(1)).save(any());
		assertThat(response).isEqualTo(Optional.of(trade1));
	}

	/**
	 * Test given good trade DTO id when delere trade DTO then delete trade DTO.
	 */
	@Test
	void test_GivenGoodTradeDTOId_WhenDelereTradeDTO_ThenDeleteTradeDTO() {
		given(repository.findById(any(Integer.class))).willReturn(Optional.of(trade1));

		service.deleteTrade(1);

		verify(repository, times(1)).findById(any(Integer.class));
		verify(repository, times(1)).delete(any(Trade.class));
	}

	/**
	 * Test given bad trade DTO id when delere trade DTO then throw illegal argument exception.
	 */
	@Test
	void test_GivenBadTradeDTOId_WhenDelereTradeDTO_ThenThrowIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> service.deleteTrade(10));
		verify(repository, times(1)).findById(any(Integer.class));
		verify(repository, times(0)).delete(any(Trade.class));
	}

}
