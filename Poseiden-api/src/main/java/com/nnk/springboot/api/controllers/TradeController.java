package com.nnk.springboot.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nnk.springboot.api.domain.Trade;
import com.nnk.springboot.api.service.TradeService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TradeController {

	@Autowired
	private TradeService service;

	@GetMapping("/trade/list")
	public List<Trade> tradeList() {
		log.info("tradeList");

		return service.getTradeList();
	}

	@PostMapping("/trade/save")
	public Optional<Trade> tradeSave(@RequestBody @Valid Trade trade) {
		log.info("tradeSave");
		log.debug("trade = " + trade.toString());

		return service.saveTrade(trade);
	}

	@GetMapping("/trade/{id}")
	public Trade getTradeByIdForUpdate(@PathVariable("id") Integer id) {
		log.info("getTradeByIdForUpdate");

		return service.getTradeById(id);
	}

	@PutMapping("/trade/update/{id}")
	public void tradeUpdateById(@PathVariable("id") Integer id, @RequestBody @Valid Trade trade) {
		log.info("tradeUpdateById");
		log.debug("id = " + id);
		log.debug("trade = " + trade.toString());

		service.updateTrade(id, trade);
	}

	@DeleteMapping("/trade/delete/{id}")
	public void tradeDeleteById(@PathVariable("id") Integer id) {
		log.info("deleteTrade");
		log.debug("id = " + id);

		service.deleteTrade(id);
	}
}
