package com.nnk.springboot.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.api.domain.Bid;

/**
 * The Interface BidRepository.
 */
public interface BidRepository extends JpaRepository<Bid, Integer> {

}
