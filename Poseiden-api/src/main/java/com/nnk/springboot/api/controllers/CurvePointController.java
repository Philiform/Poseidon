package com.nnk.springboot.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nnk.springboot.api.domain.CurvePoint;
import com.nnk.springboot.api.service.CurvePointService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CurvePointController {

	@Autowired
	private CurvePointService service;

	@GetMapping("/curvePoint/list")
	public List<CurvePoint> curvePointList() {
		log.info("curvePointList");

		return service.getCurvePointList();
	}

	@PostMapping("/curvePoint/save")
	public Optional<CurvePoint> curvePointSave(@RequestBody @Valid CurvePoint curvePoint) {
		log.info("validate");
		log.debug("curvePoint = " + curvePoint.toString());

		return service.saveCurvePoint(curvePoint);
	}

	@GetMapping("/curvePoint/{id}")
	public CurvePoint getCurvePointById(@PathVariable("id") Integer id) {
		log.info("getCurvePointById");

		return service.getCurvePointById(id);
	}

	@PutMapping("/curvePoint/update/{id}")
	public void curvePointUpdateById(@PathVariable("id") Integer id, @RequestBody @Valid CurvePoint curvePoint) {
		log.info("curvePointUpdateById");
		log.debug("id = " + id);
		log.debug("curvePoint = " + curvePoint.toString());

		service.updateCurvePoint(id, curvePoint);
	}

	@DeleteMapping("/curvePoint/delete/{id}")
	public void curvePointDeleteById(@PathVariable("id") Integer id, Model model) {
		log.info("curvePointDeleteById");
		log.debug("id = " + id);

		service.deleteCurvePoint(id);
	}
}
