package com.nnk.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.proxies.BidProxy;
import com.nnk.springboot.service.DTO.BidDTO;
import com.nnk.springboot.utilities.Utilities;

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

	/** The proxy. */
	@Autowired
	private BidProxy proxy;

	/** The utilities. */
	@Autowired
	private Utilities utilities;

	/**
	 * Gets the bid DTO list.
	 *
	 * @return the bid DTO list
	 */
	public List<BidDTO> getBidDTOList() {
		List<BidDTO> listDto = new ArrayList<>();
		List<Bid> list = proxy.findAll();

		try {
			for(Bid bid : list) {
				listDto.add(utilities.convertBidToDto(bid));
			}
		} catch (Exception e) {
			log.error(e.toString());
		}

		return listDto;
	}

	/**
	 * Save bid DTO.
	 *
	 * @param bidDTO the bid DTO
	 * @return the optional
	 */
	public Optional<Bid> saveBidDTO(final BidDTO bidDTO) {
		try {
			return Optional.of(proxy.save(utilities.convertDtoToBid(bidDTO)));
		} catch (Exception e) {
			log.error(e.toString());
		}

		return Optional.empty();
	}

	/**
	 * Gets the bid DTO for update.
	 *
	 * @param id the id
	 * @return the bid DTO for update
	 */
	public BidDTO getBidDTOForUpdate(final Integer id) {
		Bid bid = proxy.findById(id);
		
		if(bid == null)	throw(new IllegalArgumentException("Invalid bid Id:" + id));

		return utilities.convertBidToDto(bid);
	}

	/**
	 * Update bid DTO.
	 *
	 * @param id the id
	 * @param bidDTO the bid DTO
	 * @return the optional
	 */
	public Optional<Bid> updateBidDTO(final Integer id, final BidDTO bidDTO) {
		bidDTO.setId(id);

		return saveBidDTO(bidDTO);
	}

	/**
	 * Delete bid.
	 *
	 * @param id the id
	 */
	public void deleteBid(Integer id) {
		proxy.deleteById(id);
	}
}
