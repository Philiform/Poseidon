package com.nnk.springboot.utilities;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.service.DTO.BidDTO;

import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/** The Constant log. */
@Slf4j
@Component
public class Utilities {

	/**
	 * Checks if is valid password.
	 *
	 * The password must have 8 characters, at least 1 lowercase, at least 1 uppercase, at least 1 number and 1 symbol.
	 *
	 * @param password the password
	 * @return true, if is valid password
	 */
	public boolean isValidPassword(String password) {
		boolean isPassWordValid = Pattern.matches("^(?=(.*[a-z]){1,})(?=(.*[A-Z]){1,})(?=(.*[0-9]){1,})(?=(.*[!:;,@#$%^&*()/\\\\_\\-+.\\{\\}\\[\\]]){1,}).{8,}$", password);

		if(isPassWordValid) {
			log.debug("password is valid");

			return true;
		}
		log.debug("password is not valid");

		return false;
	}

	/**
	 * Gets the user logged.
	 *
	 * @return the user logged
	 */
	public String getUserLogged() {
		log.debug("getUserLogged");

		return "user";
	}

	/**
	 * Convert bid to dto.
	 *
	 * @param bid the bid
	 * @return the bid DTO
	 */
	public BidDTO convertBidToDto(Bid bid) {
		BidDTO bidDto = new BidDTO();

		bidDto.setId(bid.getId());
		bidDto.setAccount(bid.getAccount());
		bidDto.setType(bid.getType());
		bidDto.setBidQuantity(bid.getBidQuantity());

		return bidDto;
	}

	/**
	 * Convert dto to bid.
	 *
	 * @param bidDto the bid dto
	 * @return the bid
	 */
	public Bid convertDtoToBid(BidDTO bidDto) {
		Bid bid = new Bid();

		bid.setId(bidDto.getId());
		bid.setAccount(bidDto.getAccount());
		bid.setType(bidDto.getType());
		bid.setBidQuantity(bidDto.getBidQuantity());

		return bid;
	}

}
