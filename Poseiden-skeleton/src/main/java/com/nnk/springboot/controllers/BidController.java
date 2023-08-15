package com.nnk.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.service.BidService;
import com.nnk.springboot.service.DTO.BidDTO;
import com.nnk.springboot.utilities.Utilities;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/** The Constant log. */
@Slf4j
@Controller
public class BidController {

	/** The service. */
	@Autowired
	private BidService service;

	/** The utilities. */
	@Autowired
	private Utilities utilities;

	/**
	 * Home.
	 *
	 * @param model the model
	 * @return HTML page
	 */
	@GetMapping("/bid/list")
	public String home(Model model) {
		log.info("home");

		try {
			model.addAttribute("user_logged", utilities.getUserLogged());
			model.addAttribute("bidDTOList", service.getBidDTOList());
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "bid/list";
	}

	/**
	 * Adds the bid form.
	 *
	 * @param bidDTO the bid DTO
	 * @return HTML page
	 */
	@GetMapping("/bid/add")
	public String addBidForm(BidDTO bidDTO) {
		log.info("addBidForm");

		return "bid/add";
	}

	/**
	 * Validate.
	 *
	 * @param bidDTO the bid DTO
	 * @param result the result
	 * @param model the model
	 * @return HTML page
	 */
	@PostMapping("/bid/validate")
	public String validate(@Valid BidDTO bidDTO, BindingResult result, Model model) {
		log.info("validate");
		log.debug("bidDTO = " + bidDTO.toString());

		try {
			if (!result.hasErrors()) {

				if(!service.saveBidDTO(bidDTO).isEmpty()) {
					model.addAttribute("bidDTOList", service.getBidDTOList());

					return "redirect:/bid/list";
				}

				return "redirect:/bid/list";
			}
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "bid/add";
	}

	/**
	 * Show update form.
	 *
	 * @param id the id
	 * @param model the model
	 * @return HTML page
	 */
	@GetMapping("/bid/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		log.info("showUpdateForm");

		try {
			model.addAttribute("bidDTO", service.getBidDTOForUpdate(id));

			return "bid/update";
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "redirect:/bid/list";
	}

	/**
	 * Update bid.
	 *
	 * @param id the id
	 * @param bidDTO the bid DTO
	 * @param result the result
	 * @param model the model
	 * @return HTML page
	 */
	@PostMapping("/bid/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid BidDTO bidDTO, BindingResult result, Model model) {
		log.info("updateBid");
		log.debug("id = " + id);
		log.debug("bidDTO = " + bidDTO.toString());

		try {
			if (result.hasErrors()) {
				return "bid/update";
			}
			service.updateBidDTO(id, bidDTO);
			model.addAttribute("bidDTOList", service.getBidDTOList());
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "redirect:/bid/list";
	}

	/**
	 * Delete bid.
	 *
	 * @param id the id
	 * @param model the model
	 * @return HTML page
	 */
	@GetMapping("/bid/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		log.info("deleteBid");
		log.debug("id = " + id);

		try {
			service.deleteBid(id);
			model.addAttribute("bidDTOList", service.getBidDTOList());
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "redirect:/bid/list";
	}
}
