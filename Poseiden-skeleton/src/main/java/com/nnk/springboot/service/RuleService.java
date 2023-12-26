package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.Rule;
import com.nnk.springboot.proxies.RuleProxy;

// TODO: Auto-generated Javadoc
/**
 * The Class RuleService.
 */
@Transactional
@Service
public class RuleService {

	/** The proxy. */
	@Autowired
	private RuleProxy proxy;

	/**
	 * Gets the rule list.
	 *
	 * @return the rule list
	 */
	public List<Rule> getRuleList() {
		return proxy.findAll();
	}

	/**
	 * Save rule.
	 *
	 * @param rule the rule
	 * @return the optional
	 */
	public Optional<Rule> saveRule(final Rule rule) {
		return Optional.of(proxy.save(rule));
	}

	/**
	 * Gets the rule for update.
	 *
	 * @param id the id
	 * @return the rule for update
	 */
	public Rule getRuleForUpdate(final int id) {
		Rule rule = proxy.findById(id);
		
		if(rule == null)	throw(new IllegalArgumentException("Invalid rule Id:" + id));

		return rule;
	}

	/**
	 * Update rule.
	 *
	 * @param id the id
	 * @param rule the rule
	 * @return the optional
	 */
	public Optional<Rule> updateRule(final int id, final Rule rule) {
		rule.setId(id);

		return saveRule(rule);
	}

	/**
	 * Delete rule.
	 *
	 * @param id the id
	 */
	public void deleteRule(final int id) {
		proxy.deleteById(id);
	}

}
