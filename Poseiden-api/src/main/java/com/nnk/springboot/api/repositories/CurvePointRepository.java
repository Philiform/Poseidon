package com.nnk.springboot.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nnk.springboot.api.domain.CurvePoint;

/**
 * The Interface CurvePointRepository.
 */
public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

}
