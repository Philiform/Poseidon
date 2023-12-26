package com.nnk.springboot.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.api.domain.Trade;
import com.nnk.springboot.api.repositories.TradeRepository;

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

	/**
	 * Gets the trade list.
	 *
	 * @return the trade list
	 */
	public List<Trade> getTradeList() {
		return repository.findAll();
	}

	/**
	 * Save trade .
	 *
	 * @param trade the trade 
	 * @return the optional
	 */
	public Optional<Trade> saveTrade(final Trade trade) {
		try {
			return Optional.of(repository.save(trade));
		} catch (Exception e) {
			log.error(e.toString());
		}

		return Optional.empty();
	}

	/**
	 * Gets the trade by id.
	 *
	 * @param id the id
	 * @return the trade  for update
	 */
	public Trade getTradeById(final Integer id) {
		Trade trade = repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));

		return trade;
	}

	/**
	 * Update trade .
	 *
	 * @param id the id
	 * @param trade the trade 
	 * @return the optional
	 */
	public Optional<Trade> updateTrade(final Integer id, final Trade trade) {
		trade.setId(id);

		return saveTrade(trade);
	}

	/**
	 * Delete trade.
	 *
	 * @param id the id
	 */
	public void deleteTrade(Integer id) {
		repository.deleteById(id);
	}
}
