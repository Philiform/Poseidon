package com.nnk.springboot.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.api.domain.Bid;
import com.nnk.springboot.api.repositories.BidRepository;

import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/**
 * The Class BidService.
 */

/** The Constant log. */
@Slf4j
@Transactional
@Service
public class BidService {

	/** The repository. */
	@Autowired
	private BidRepository repository;

	/**
	 * Gets the bid list.
	 *
	 * @return the bid list
	 */
	public List<Bid> getBidList() {
		return repository.findAll();
	}

	/**
	 * Save bid .
	 *
	 * @param bid the bid 
	 * @return the optional
	 */
	public Optional<Bid> saveBid(final Bid bid) {
		try {
			return Optional.of(repository.save(bid));
		} catch (Exception e) {
			log.error(e.toString());
		}

		return Optional.empty();
	}

	/**
	 * Gets the bid by id
	 *
	 * @param id the id
	 * @return the bid
	 */
	public Bid getBidById(final Integer id) {
		Bid bid = repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));

		return bid;
	}

	/**
	 * Update bid .
	 *
	 * @param id the id
	 * @param bid the bid 
	 * @return the optional
	 */
	public Optional<Bid> updateBid(final Integer id, final Bid bid) {
		bid.setId(id);

		return saveBid(bid);
	}

	/**
	 * Delete bid.
	 *
	 * @param id the id
	 */
	public void deleteBid(Integer id) {
		repository.deleteById(id);
	}
}
