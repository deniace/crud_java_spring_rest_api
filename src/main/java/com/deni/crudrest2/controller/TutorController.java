package com.deni.crudrest2.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deni.crudrest2.model.Tutorial;
import com.deni.crudrest2.repository.TutorialRepository;
import com.deni.crudrest2.util.ResponseUtil;

@RestController
@RequestMapping("api")
public class TutorController {
	@Autowired
	TutorialRepository tutorialRepository;

	@GetMapping("/tutor")
	public ResponseEntity<?> getAllTutor(@RequestParam(required = false) String title) {
		try {
			List<Tutorial> tutorials = new ArrayList<Tutorial>();

			if (title == null) {
				tutorialRepository.findAll().forEach(tutorials::add);
			} else {
				tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
			}

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(ResponseUtil.responseConstruct(false, null, "No data"), HttpStatus.OK);
			}

			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, tutorials, "oke"), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(ResponseUtil.responseConstruct(false, null, "internal server error"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	response nya bisa di manipulasi pake object yang dibuat sendiri
	@GetMapping("/tutor/{id}")
	public ResponseEntity<?> getTutorById(@PathVariable("id") long id) {
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, tutorialData.get(), "oke"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(false, null, "not found"), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/tutor")
	public ResponseEntity<?> createTutor(@RequestBody Tutorial tutorial) {
		try {
			Tutorial _tutorial = tutorialRepository
					.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, _tutorial, "save success"),
					HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(ResponseUtil.responseConstruct(false, null, "save failed"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/tutor/{id}")
	public ResponseEntity<?> updateTutor(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			Tutorial _tutorial = tutorialData.get();
			_tutorial.setTitle(tutorial.getTitle());
			_tutorial.setDescription(tutorial.getDescription());
			_tutorial.setPublished(tutorial.isPublished());
			tutorialRepository.save(_tutorial);
			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, tutorialData, "update success"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(false, null, "update data failed"),
					HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/tutor/{id}")
	public ResponseEntity<?> deleteTutor(@PathVariable("id") long id) {
		try {
			tutorialRepository.deleteById(id);
			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, null, "delete success"), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(ResponseUtil.responseConstruct(false, null, "delete failed"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/tutor")
	public ResponseEntity<?> deleteAllTutor() {
		try {
			tutorialRepository.deleteAll();
			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, null, "delete all data success"),
					HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(ResponseUtil.responseConstruct(false, null, "delete failed"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/tutor/published")
	public ResponseEntity<?> findByPublish() {
		try {
			List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(ResponseUtil.responseConstruct(true, null, "data not found"),
						HttpStatus.OK);
			}
			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, tutorials, "oke"), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, null, "internal server error"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
