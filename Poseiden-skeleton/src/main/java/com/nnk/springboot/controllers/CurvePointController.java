package com.nnk.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nnk.springboot.service.CurvePointService;
import com.nnk.springboot.service.DTO.CurvePointDTO;
import com.nnk.springboot.utilities.Utilities;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

// TODO: Auto-generated Javadoc
/** The Constant log. */
@Slf4j
@Controller
public class CurvePointController {

	/** The service. */
	@Autowired
	private CurvePointService service;

	/** The utilities. */
	@Autowired
	private Utilities utilities;

	/**
	 * Home.
	 *
	 * @param model the model
	 * @return HTML page
	 */
	@GetMapping("/curvePoint/list")
	public String home(Model model) {
		log.info("home");

		try {
			model.addAttribute("user_logged", utilities.getUserLogged());
			model.addAttribute("curvePointDTOList", service.getCurvePointDTOList());
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "curvePoint/list";
	}

	/**
	 * Adds the curve point form.
	 *
	 * @param curvePointDTO the curve point DTO
	 * @return HTML page
	 */
	@GetMapping("/curvePoint/add")
	public String addCurvePointForm(CurvePointDTO curvePointDTO) {
		log.info("addCurvePointForm");

		return "curvePoint/add";
	}

	/**
	 * Validate.
	 *
	 * @param curvePointDTO the curve point DTO
	 * @param result the result
	 * @param model the model
	 * @return HTML page
	 */
	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePointDTO curvePointDTO, BindingResult result, Model model) {
		log.info("validate");
		log.debug("curvePointDTO = " + curvePointDTO.toString());

		try {
			if (!result.hasErrors()) {

				if (!service.saveCurvePointDTO(curvePointDTO).isEmpty()) {
					model.addAttribute("curvePointDTOList", service.getCurvePointDTOList());
				}

				return "redirect:/curvePoint/list";
			}
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "curvePoint/add";
	}

	/**
	 * Show update form.
	 *
	 * @param id the id
	 * @param model the model
	 * @return HTML page
	 */
	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		log.info("showUpdateForm");

		try {
			model.addAttribute("curvePointDTO", service.getCurvePointDTOForUpdate(id));

			return "curvePoint/update";
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "redirect:/curvePoint/list";
	}

	/**
	 * Update curve point.
	 *
	 * @param id the id
	 * @param curvePointDTO the curve point DTO
	 * @param result the result
	 * @param model the model
	 * @return HTML page
	 */
	@PostMapping("/curvePoint/update/{id}")
	public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePointDTO curvePointDTO,
			BindingResult result, Model model) {
		log.info("updateCurvePoint");
		log.debug("id = " + id);
		log.debug("curvePointDTO = " + curvePointDTO.toString());

		try {
			if (result.hasErrors()) {
				return "curvePoint/update";
			}
			service.updateCurvePointDTO(id, curvePointDTO);
			model.addAttribute("curvePointDTOList", service.getCurvePointDTOList());
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "redirect:/curvePoint/list";
	}

	/**
	 * Delete curve point.
	 *
	 * @param id the id
	 * @param model the model
	 * @return HTML page
	 */
	@GetMapping("/curvePoint/delete/{id}")
	public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
		log.info("deleteCurvePoint");
		log.debug("id = " + id);

		try {
			service.deleteCurvePoint(id);
			model.addAttribute("curvePointDTOList", service.getCurvePointDTOList());
		} catch (Exception e) {
			log.error(e.toString());
		}

		return "redirect:/curvePoint/list";
	}
}
