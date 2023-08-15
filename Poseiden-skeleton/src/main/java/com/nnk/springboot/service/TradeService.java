package com.nnk.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.DTO.TradeDTO;

import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/**
 * The Class TradeService.
 */

/** The Constant log. */
@Slf4j
@Transactional
@Service
public class TradeService {

	/** The repository. */
	@Autowired
	private TradeRepository repository;

	/** The model mapper. */
	private ModelMapper modelMapper;

	/**
	 * Gets the trade DTO list.
	 *
	 * @return the trade DTO list
	 */
	public List<TradeDTO> getTradeDTOList() {
		List<TradeDTO> listDto = new ArrayList<>();
		List<Trade> list = repository.findAll();
		modelMapper = new ModelMapper();

		try {
			for(Trade trade : list) {
				listDto.add(modelMapper.map(trade, TradeDTO.class));
			}
		} catch (Exception e) {
			log.error(e.toString());
		}

		return listDto;
	}

	/**
	 * Save trade DTO.
	 *
	 * @param tradeDTO the trade DTO
	 * @return the optional
	 */
	public Optional<Trade> saveTradeDTO(final TradeDTO tradeDTO) {
		try {
			return Optional.of(repository.save(modelMapper.map(tradeDTO, Trade.class)));
		} catch (Exception e) {
			log.error(e.toString());
		}

		return Optional.empty();
	}

	/**
	 * Gets the trade DTO for update.
	 *
	 * @param id the id
	 * @return the trade DTO for update
	 */
	public TradeDTO getTradeDTOForUpdate(final Integer id) {
		Trade trade = repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));

		return modelMapper.map(trade, TradeDTO.class);
	}

	/**
	 * Update trade DTO.
	 *
	 * @param id the id
	 * @param tradeDTO the trade DTO
	 * @return the optional
	 */
	public Optional<Trade> updateTradeDTO(final Integer id, final TradeDTO tradeDTO) {
		tradeDTO.setId(id);

		return saveTradeDTO(tradeDTO);
	}

	/**
	 * Delete trade.
	 *
	 * @param id the id
	 */
	public void deleteTrade(Integer id) {
		Trade trade = repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));

		repository.delete(trade);
	}
}
