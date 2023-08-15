package com.nnk.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.DTO.CurvePointDTO;

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

	/** The model mapper. */
	private ModelMapper modelMapper;

	/**
	 * Gets the curve point DTO list.
	 *
	 * @return the curve point DTO list
	 */
	public List<CurvePointDTO> getCurvePointDTOList() {
		List<CurvePointDTO> listDto = new ArrayList<>();
		List<CurvePoint> list = repository.findAll();
		modelMapper = new ModelMapper();

		try {
			for(CurvePoint curvePoint : list) {
				log.info("curvePoint = " + curvePoint);
				listDto.add(modelMapper.map(curvePoint, CurvePointDTO.class));
			}
		} catch (Exception e) {
			log.error(e.toString());
		}

		return listDto;
	}

	/**
	 * Save curve point DTO.
	 *
	 * @param curvePointDTO the curve point DTO
	 * @return the optional
	 */
	public Optional<CurvePoint> saveCurvePointDTO(final CurvePointDTO curvePointDTO) {
		try {
			return Optional.of(repository.save(modelMapper.map(curvePointDTO, CurvePoint.class)));
		} catch (Exception e) {
			log.error(e.toString());
		}

		return Optional.empty();
	}

	/**
	 * Gets the curve point DTO for update.
	 *
	 * @param id the id
	 * @return the curve point DTO for update
	 */
	public CurvePointDTO getCurvePointDTOForUpdate(final Integer id) {
		CurvePoint curvePoint = repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));

		return modelMapper.map(curvePoint, CurvePointDTO.class);
	}

	/**
	 * Update curve point DTO.
	 *
	 * @param id the id
	 * @param curvePointDTO the curve point DTO
	 * @return the optional
	 */
	public Optional<CurvePoint> updateCurvePointDTO(final Integer id, final CurvePointDTO curvePointDTO) {
		curvePointDTO.setId(id);

		return saveCurvePointDTO(curvePointDTO);
	}

	/**
	 * Delete curve point.
	 *
	 * @param id the id
	 */
	public void deleteCurvePoint(Integer id) {
		CurvePoint curvePoint = repository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));

		repository.delete(curvePoint);
	}
}
