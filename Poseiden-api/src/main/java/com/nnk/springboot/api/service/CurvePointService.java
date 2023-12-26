package com.nnk.springboot.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.api.domain.CurvePoint;
import com.nnk.springboot.api.repositories.CurvePointRepository;

import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/**
 * The Class CurvePointService.
 */

/** The Constant log. */
@Slf4j
@Transactional
@Service
public class CurvePointService {

	/** The repository. */
	@Autowired
	private CurvePointRepository repository;

	/**
	 * Gets the curve point list.
	 *
	 * @return the curve point list
	 */
	public List<CurvePoint > getCurvePointList() {
		return repository.findAll();
	}

	/**
	 * Save curve point  .
	 *
	 * @param curvePoint the curve point  
	 * @return the optional
	 */
	public Optional<CurvePoint> saveCurvePoint(final CurvePoint curvePoint ) {
		try {
			return Optional.of(repository.save(curvePoint));
		} catch (Exception e) {
			log.error(e.toString());
		}

		return Optional.empty();
	}

	/**
	 * Gets the curve point by id
	 *
	 * @param id the id
	 * @return the curve point
	 */
	public CurvePoint getCurvePointById(final Integer id) {
		CurvePoint curvePoint = repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));

		return curvePoint;
	}

	/**
	 * Update curve point  .
	 *
	 * @param id the id
	 * @param curvePoint the curve point  
	 * @return the optional
	 */
	public Optional<CurvePoint> updateCurvePoint(final Integer id, final CurvePoint curvePoint ) {
		curvePoint .setId(id);

		return saveCurvePoint (curvePoint );
	}

	/**
	 * Delete curve point.
	 *
	 * @param id the id
	 */
	public void deleteCurvePoint(Integer id) {
		repository.deleteById(id);
	}
}
