package com.deni.crudrest2.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deni.crudrest2.dto.TestCustomDto;
import com.deni.crudrest2.model.Tutorial;
import com.deni.crudrest2.repository.TutorialRepository;
import com.deni.crudrest2.util.ResponseUtil;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	TutorialRepository tutorialRepository;

	@GetMapping("/a")
	public ResponseEntity<?> getAll(@RequestParam(required = false) String judul) {
		List<TestCustomDto> custom = new ArrayList<TestCustomDto>();
		if (judul == null) {
			tutorialRepository.findAll().forEach(
					a -> custom.add(new TestCustomDto(a.getId(), a.getTitle(), a.getDescription(), a.isPublished())));

			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, custom, "oke"), HttpStatus.OK);
		} else {
//			TestCustomDto testCustomDto;
//			List<TestCustomDto> custom = new ArrayList<TestCustomDto>();
			tutorialRepository.findByTitleContaining(judul).forEach(tutor -> custom.add(
					new TestCustomDto(tutor.getId(), tutor.getTitle(), tutor.getDescription(), tutor.isPublished())));

			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, custom, "oke"), HttpStatus.OK);
		}
	}

	@PostMapping("/a")
	public ResponseEntity<?> save(@RequestBody TestCustomDto testCustomDto) {
		try {
			Tutorial tutorial = tutorialRepository.save(
					new Tutorial(testCustomDto.getJudul(), testCustomDto.getDeskripsi(), testCustomDto.isTerbit()));

			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, tutorial, "data saved successfully"),
					HttpStatus.ACCEPTED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(ResponseUtil.responseConstruct(false, null, "save data failed"),
					HttpStatus.NOT_IMPLEMENTED);
		}
	}
}
