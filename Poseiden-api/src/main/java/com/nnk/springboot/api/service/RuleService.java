package com.nnk.springboot.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.api.domain.Rule;
import com.nnk.springboot.api.repositories.RuleRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class RuleService.
 */
@Transactional
@Service
public class RuleService {

	/** The repository. */
	@Autowired
	private RuleRepository repository;

	/**
	 * Gets the rule list.
	 *
	 * @return the rule list
	 */
	public List<Rule> getRuleList() {
		return repository.findAll();
	}

	/**
	 * Save rule.
	 *
	 * @param rule the rule
	 * @return the optional
	 */
	public Optional<Rule> saveRule(final Rule rule) {
		return Optional.of(repository.save(rule));
	}

	/**
	 * Gets the rule by id.
	 *
	 * @param id the id
	 * @return the rule
	 */
	public Rule getRuleById(final int id) {
		Rule rule = repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid rule Id:" + id));

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
		repository.deleteById(id);
	}

}
