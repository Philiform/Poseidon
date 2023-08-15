package com.nnk.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.service.TradeService;
import com.nnk.springboot.service.DTO.TradeDTO;
import com.nnk.springboot.utilities.Utilities;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/** The Constant log. */
@Slf4j
@Controller
public class TradeController {

	/** The service. */
	@Autowired
	private TradeService service;

	/** The utilities. */
	@Autowired
	private Utilities utilities;

	/**
	 * Home.
	 *
	 * @param model the model
	 * @return HTML page
	 */
	@GetMapping("/trade/list")
	public String home(Model model) {
		log.info("home");

		try {
			model.addAttribute("user_logged", utilities.getUserLogged());
			model.addAttribute("tradeDTOList", service.getTradeDTOList());
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "trade/list";
	}

	/**
	 * Adds the trade form.
	 *
	 * @param tradeDTO the trade DTO
	 * @return HTML page
	 */
	@GetMapping("/trade/add")
	public String addTradeForm(TradeDTO tradeDTO) {
		log.info("addTradeForm");

		return "trade/add";
	}

	/**
	 * Validate.
	 *
	 * @param tradeDTO the trade DTO
	 * @param result the result
	 * @param model the model
	 * @return HTML page
	 */
	@PostMapping("/trade/validate")
	public String validate(@Valid TradeDTO tradeDTO, BindingResult result, Model model) {
		log.info("validate");
		log.debug("tradeDTO = " + tradeDTO.toString());

		try {
			if (!result.hasErrors()) {

				if (!service.saveTradeDTO(tradeDTO).isEmpty()) {
					model.addAttribute("tradeDTOList", service.getTradeDTOList());
				}

				return "redirect:/trade/list";
			}
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "trade/add";
	}

	/**
	 * Show update form.
	 *
	 * @param id the id
	 * @param model the model
	 * @return HTML page
	 */
	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		log.info("showUpdateForm");

		try {
			model.addAttribute("tradeDTO", service.getTradeDTOForUpdate(id));

			return "trade/update";
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "redirect:/trade/list";
	}

	/**
	 * Update trade.
	 *
	 * @param id the id
	 * @param tradeDTO the trade DTO
	 * @param result the result
	 * @param model the model
	 * @return HTML page
	 */
	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable("id") Integer id, @Valid TradeDTO tradeDTO, BindingResult result,
			Model model) {
		log.info("updateTrade");
		log.debug("id = " + id);
		log.debug("tradeDTO = " + tradeDTO.toString());

		try {
			if (result.hasErrors()) {
				return "trade/update";
			}
			service.updateTradeDTO(id, tradeDTO);
			model.addAttribute("tradeDTOList", service.getTradeDTOList());
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "redirect:/trade/list";
	}

	/**
	 * Delete trade.
	 *
	 * @param id the id
	 * @param model the model
	 * @return HTML page
	 */
	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable("id") Integer id, Model model) {
		log.info("deleteTrade");
		log.debug("id = " + id);

		try {
			service.deleteTrade(id);
			model.addAttribute("tradeDTOList", service.getTradeDTOList());
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "redirect:/trade/list";
	}
}
