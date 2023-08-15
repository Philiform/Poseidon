package com.nnk.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.domain.Rating;

/**
 * The Interface RatingRepository.
 */
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
