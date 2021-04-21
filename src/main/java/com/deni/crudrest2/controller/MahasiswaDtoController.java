package com.deni.crudrest2.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.deni.crudrest2.dto.MahasiswaDto;
import com.deni.crudrest2.model.Mahasiswa;
import com.deni.crudrest2.service.MahasiswaService;
import com.deni.crudrest2.util.ResponseUtil;

@RestController
@RequestMapping("mhsdto")
public class MahasiswaDtoController {

	@Autowired
	MahasiswaService mhssrv;

	@GetMapping("")
	public ResponseEntity<?> getAll() {
		List<Mahasiswa> all = mhssrv.getAll();
		if (all.isEmpty()) {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(false, null, "no data"), HttpStatus.OK);
		} else {
			List<MahasiswaDto> mhsdto = new ArrayList<MahasiswaDto>();

			all.forEach(m -> {
				mhsdto.add(new MahasiswaDto(m.getId(), m.getNama(), m.getEmail(), m.getJurusan()));
			});
			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, mhsdto, "oke"), HttpStatus.OK);
		}
	}

	@PostMapping("")
	public ResponseEntity<?> save(@RequestBody MahasiswaDto mhsdto) {
		Mahasiswa mhs = new Mahasiswa();
		mhs.setNama(mhsdto.getName());
		mhs.setEmail(mhsdto.getEmail());
		mhs.setJurusan(mhsdto.getMajors());
		mhssrv.save(mhs);

		return new ResponseEntity<>(ResponseUtil.responseConstruct(true, mhsdto, "save success"), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody MahasiswaDto mhsdto) {
		Mahasiswa mhs = mhssrv.getById(id);
		if (mhs != null) {
			mhs.setEmail(mhsdto.getEmail());
			mhs.setNama(mhsdto.getName());
			mhs.setJurusan(mhsdto.getMajors());
			mhssrv.save(mhs);
			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, mhsdto, "update data success"),
					HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(false, null, "update failed"), HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		Mahasiswa mhs = mhssrv.getById(id);
		if (mhs != null) {
			mhssrv.delete(id);
			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, null, "data deleted"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(false, null, "data not found"), HttpStatus.OK);
		}
	}

}
