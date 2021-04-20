package com.deni.crudrest2.controller;

import java.util.List;

import javax.websocket.server.PathParam;

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
import org.springframework.web.bind.annotation.RestController;

import com.deni.crudrest2.model.Mahasiswa;
import com.deni.crudrest2.service.MahasiswaService;
import com.deni.crudrest2.util.ResponseUtil;

@RestController
@RequestMapping("/mahasiswa")
public class MahasiswaController {

	@Autowired
	MahasiswaService mahasiswaService;

	@GetMapping("")
	public ResponseEntity<?> getAll() {
		List<Mahasiswa> allData = mahasiswaService.getAll();
		if (allData.isEmpty()) {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(false, null, "no data"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, allData, "oke"), HttpStatus.OK);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		Mahasiswa mahasiswa = mahasiswaService.getById(id);
		if (mahasiswa != null) {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, mahasiswa, "oke"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(false, null, "data not found"), HttpStatus.OK);
		}
	}

	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody Mahasiswa mhs) {
		mahasiswaService.save(mhs);
		return new ResponseEntity<>(ResponseUtil.responseConstruct(true, null, "data saved"), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Mahasiswa mhs) {
		Mahasiswa mahasiswa = mahasiswaService.getById(id);
		if (mahasiswa != null) {
			mahasiswa.setNama(mhs.getNama());
			mahasiswa.setEmail(mhs.getEmail());
			mahasiswa.setJurusan(mhs.getJurusan());
			mahasiswaService.save(mahasiswa);
			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, mahasiswa, "data updated"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(false, null, "Mahasiswa not found"),
					HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {
		Mahasiswa mahasiswa = mahasiswaService.getById(id);

		if (mahasiswa != null) {
			mahasiswaService.delete(id);
			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, null, "data deleted"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(false, null, "mahasiswa not found"),
					HttpStatus.OK);
		}
	}
}
