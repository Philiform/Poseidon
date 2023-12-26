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

import com.nnk.springboot.api.domain.Rule;
import com.nnk.springboot.api.service.RuleService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RuleController {

	@Autowired
	private RuleService service;

	@GetMapping("/rule/list")
	public List<Rule> ruleList() {
		log.info("ruleList");

		return service.getRuleList();
	}

	@PostMapping("/rule/save")
	public Optional<Rule> ruleSave(@RequestBody @Valid Rule rule) {
		log.info("ruleSave");
		log.debug("rule = " + rule.toString());

		return service.saveRule(rule);
	}

	@GetMapping("/rule/{id}")
	public Rule getRuleByIdForUpdate(@PathVariable("id") Integer id) {
		log.info("getRuleByIdForUpdate");

		return service.getRuleById(id);
	}

	@PutMapping("/rule/update/{id}")
	public void ruleUpdateById(@PathVariable("id") Integer id, @RequestBody @Valid Rule rule) {
		log.info("ruleUpdateById");
		log.debug("id = " + id);
		log.debug("rule = " + rule.toString());

		service.updateRule(id, rule);
	}

	@DeleteMapping("/rule/delete/{id}")
	public void ruleDeleteById(@PathVariable("id") Integer id) {
		log.info("deleteBid");
		log.debug("id = " + id);

		service.deleteRule(id);
	}
}
