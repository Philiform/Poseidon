package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.domain.Rule;


/**
 * The Interface RuleRepository.
 */
public interface RuleRepository extends JpaRepository<Rule, Integer> {
}
