package com.nnk.springboot.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.api.domain.Trade;


/**
 * The Interface TradeRepository.
 */
public interface TradeRepository extends JpaRepository<Trade, Integer> {

}
