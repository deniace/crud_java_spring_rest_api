package com.deni.crudrest2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deni.crudrest2.model.Tutorial;
import com.deni.crudrest2.service.TestDaoService;
import com.deni.crudrest2.serviceImpl.TestDaoServiceImpl;
import com.deni.crudrest2.util.ResponseUtil;

@RestController
@RequestMapping("/dao")
public class TestDaoController {

//	@Autowired
//	TestDao testDao;

//	@Autowired
//	TestDaoServiceImpl testDaoServiceImpl;

	@Autowired
	TestDaoService testDaoService;

	@GetMapping("/test")
	public ResponseEntity<?> getAll() {
//		List<Tutorial> all = testDaoServiceImpl.ambilSemua();
		List<Tutorial> all = testDaoService.ambilSemua();
		return new ResponseEntity<>(ResponseUtil.responseConstruct(true, all, "asdf"), HttpStatus.OK);
	}

	@GetMapping("/test/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") long id) {
		Tutorial tutById = testDaoService.ambilId(id);
		if (tutById != null) {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, tutById, "Oke"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(false, null, "not found"), HttpStatus.OK);
		}
//		return null;
	}
}
