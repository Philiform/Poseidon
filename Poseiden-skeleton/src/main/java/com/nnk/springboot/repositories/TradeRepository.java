package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.domain.Trade;


/**
 * The Interface TradeRepository.
 */
public interface TradeRepository extends JpaRepository<Trade, Integer> {

}
