package com.nnk.springboot.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.api.domain.Rating;

/**
 * The Interface RatingRepository.
 */
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
