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

import com.nnk.springboot.api.domain.Bid;
import com.nnk.springboot.api.service.BidService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BidController {

	@Autowired
	private BidService service;

	@GetMapping("/bid/list")
	public List<Bid> getBidList() {
		log.info("getBidList");

		return service.getBidList();
	}

	@PostMapping("/bid/save")
	public Optional<Bid> bidSave(@RequestBody @Valid Bid bid) {
		log.info("bidSave");
		log.debug("bid = " + bid.toString());

		return service.saveBid(bid);
	}

	@GetMapping("/bid/{id}")
	public Bid getBidById(@PathVariable("id") Integer id) {
		log.info("getBidById");

		return service.getBidById(id);
	}

	@PutMapping("/bid/update/{id}")
	public void bidUpdateById(@PathVariable("id") Integer id, @RequestBody @Valid Bid bid) {
		log.info("bidUpdateById");
		log.debug("id = " + id);
		log.debug("bid = " + bid.toString());

		service.updateBid(id, bid);
	}

	@DeleteMapping("/bid/delete/{id}")
	public void deleteBidById(@PathVariable("id") Integer id) {
		log.info("deleteBid");
		log.debug("id = " + id);

		service.deleteBid(id);
	}
}
