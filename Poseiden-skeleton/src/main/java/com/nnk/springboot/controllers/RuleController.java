package com.nnk.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.domain.Rule;
import com.nnk.springboot.service.RuleService;
import com.nnk.springboot.utilities.Utilities;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/** The Constant log. */
@Slf4j
@Controller
public class RuleController {

	/** The service. */
	@Autowired
	private RuleService service;

	/** The utilities. */
	@Autowired
	private Utilities utilities;

	/**
	 * Home.
	 *
	 * @param model the model
	 * @return HTML page
	 */
	@GetMapping("/rule/list")
	public String home(Model model) {
		log.info("home");

		try {
			model.addAttribute("user_logged", utilities.getUserLogged());
			model.addAttribute("ruleList", service.getRuleList());
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "rule/list";
	}

	/**
	 * Adds the rule form.
	 *
	 * @param bid the bid
	 * @return HTML page
	 */
	@GetMapping("/rule/add")
	public String addRuleForm(Rule bid) {
		log.info("addBidForm");

		return "rule/add";
	}

	/**
	 * Validate.
	 *
	 * @param rule the rule
	 * @param result the result
	 * @param model the model
	 * @return HTML page
	 */
	@PostMapping("/rule/validate")
	public String validate(@Valid Rule rule, BindingResult result, Model model) {
		log.info("validate");
		log.debug("rule = " + rule.toString());

		try {
			if (!result.hasErrors()) {
				if (!service.saveRule(rule).isEmpty()) {
					model.addAttribute("ruleList", service.getRuleList());

					return "redirect:/rule/list";
				}
			}
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "rule/add";
	}

	/**
	 * Show update form.
	 *
	 * @param id the id
	 * @param model the model
	 * @return HTML page
	 */
	@GetMapping("/rule/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		log.info("showUpdateForm");

		try {
			model.addAttribute("rule", service.getRuleForUpdate(id));

			return "rule/update";
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "redirect:/rule/list";
	}

	/**
	 * Update rule.
	 *
	 * @param id the id
	 * @param rule the rule
	 * @param result the result
	 * @param model the model
	 * @return HTML page
	 */
	@PostMapping("/rule/update/{id}")
	public String updateRule(@PathVariable("id") Integer id, @Valid Rule rule, BindingResult result, Model model) {
		log.info("updateBid");
		log.debug("id = " + id);
		log.debug("rule = " + rule.toString());

		try {
			if (result.hasErrors()) {
				return "rule/update";
			}
			service.updateRule(id, rule);
			model.addAttribute("ruleList", service.getRuleList());
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "redirect:/rule/list";
	}

	/**
	 * Delete rule.
	 *
	 * @param id the id
	 * @param model the model
	 * @return HTML page
	 */
	@GetMapping("/rule/delete/{id}")
	public String deleteRule(@PathVariable("id") Integer id, Model model) {
		log.info("deleteBid");
		log.debug("id = " + id);

		try {
			service.deleteRule(id);
			model.addAttribute("ruleList", service.getRuleList());
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "redirect:/rule/list";
	}
}
