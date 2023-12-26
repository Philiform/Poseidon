package com.nnk.springboot.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.api.domain.Rule;


/**
 * The Interface RuleRepository.
 */
public interface RuleRepository extends JpaRepository<Rule, Integer> {
}
