package com.deni.crudrest2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.deni.crudrest2.dto.PageDto;
import com.deni.crudrest2.model.Pegawai;
import com.deni.crudrest2.service.PegawaiService;
import com.deni.crudrest2.util.ResponseUtil;

@RestController
@RequestMapping("pegawai")
public class PegawaiController {

	@Autowired
	PegawaiService service;

	@GetMapping("")
	public ResponseEntity<?> index() {
		List<Pegawai> allData = service.getAll();
		if (allData.isEmpty()) {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(false, null, "no data"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, allData, "ok"), HttpStatus.OK);
		}
	}

	@PostMapping("/paging")
	public ResponseEntity<?> getPage(@RequestBody PageDto pageDto) {
		Pageable pageable = PageRequest.of(pageDto.getPage(), pageDto.getPageSize());
		List<Pegawai> all = service.getPage(pageable);
		if (all.isEmpty()) {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(false, null, "nodata"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, all, "ok"), HttpStatus.OK);
		}
	}

	@PostMapping("/paging2")
	public ResponseEntity<?> getPaging(@RequestBody PageDto pageDto) {
		Pageable pageable = PageRequest.of(pageDto.getPage(), pageDto.getPageSize());
		Page<Pegawai> allPage = service.getPaging(pageable);
		if (allPage.isEmpty()) {
			// page dimulai dari 0
			List<Pegawai> list = allPage.getContent();
			int currentPage = allPage.getNumber();
			int totalPage = allPage.getTotalPages();
			long totalElement = allPage.getTotalElements();
			return new ResponseEntity<>(ResponseUtil.responseConstruct(false, null, "nodata"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, allPage, "ok"), HttpStatus.OK);
		}
	}

	@GetMapping("/{nip}")
	public ResponseEntity<?> getByNip(@PathVariable("nip") String nip) {
		Pegawai peg = service.getByNip(nip);
		if (peg != null) {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(true, peg, "ok"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(false, null, "nip not found"), HttpStatus.OK);
		}
	}

	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody(required = false) Pegawai p) {
		if (p != null) {
			Map<String, String> req = new HashMap<String, String>();
			if (p.getNip() == null) {
				req.put("nip", "nip is required");
			}
			if (p.getNama() == null) {
				req.put("nama", "nama is required");
			}
			if (p.getJabatan() == null) {
				req.put("jabatan", "jabatan is required");
			}
			if (p.getNoHp() == null) {
				req.put("noHp", "no hp is required");
			}
			if (req.isEmpty()) {
				service.create(p);
				return new ResponseEntity<>(ResponseUtil.responseConstruct(true, p, "saved"), HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(ResponseUtil.responseConstruct(false, req, "required data is missing"),
						HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(false, null, "no data"), HttpStatus.OK);
		}

	}

	@PutMapping("/{nip}")
	public ResponseEntity<?> update(@PathVariable("nip") String nip, @RequestBody Pegawai p) {
		Map<String, String> req = new HashMap<String, String>();
		if (nip == null) {
			req.put("nip", "nip is required");
		}
		if (p.getNama() == null) {
			req.put("nama", "nama is required");
		}
		if (p.getJabatan() == null) {
			req.put("jabatan", "jabatan is required");
		}
		if (p.getNoHp() == null) {
			req.put("noHp", "no hp is required");
		}
		if (p.getDepartemen() == null) {
			req.put("departemen", "departemen is required");
		}

		if (req.isEmpty()) {
			Pegawai peg = service.getByNip(nip);
			if (peg != null) {
				peg.setNama(p.getNama());
				peg.setJabatan(p.getJabatan());
				peg.setNoHp(p.getNoHp());
				peg.setDepartemen(p.getDepartemen());

				service.create(peg);
				return new ResponseEntity<>(ResponseUtil.responseConstruct(true, peg, "data updated"),
						HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(ResponseUtil.responseConstruct(true, null, "no data"), HttpStatus.OK);
			}
		} else {
			return new ResponseEntity<>(ResponseUtil.responseConstruct(false, req, "required data is missing"),
					HttpStatus.OK);

		}
	}

	@DeleteMapping("/{nip}")
	public ResponseEntity<?> delete(@PathVariable("nip") String nip) {
		if (nip == null) {
			Map<String, String> req = new HashMap<String, String>();
			req.put("nip", "nip is required");
			return new ResponseEntity<>(ResponseUtil.responseConstruct(false, req, "required data is missing"),
					HttpStatus.OK);
		} else {
			Pegawai pgw = service.getByNip(nip);
			if (pgw != null) {
				service.delete(nip);
				return new ResponseEntity<>(ResponseUtil.responseConstruct(true, null, "data deleted"), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(ResponseUtil.responseConstruct(false, null, "nip not found"),
						HttpStatus.OK);
			}
		}
	}

}
