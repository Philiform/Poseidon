package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.domain.Bid;

/**
 * The Interface BidRepository.
 */
public interface BidRepository extends JpaRepository<Bid, Integer> {

}
