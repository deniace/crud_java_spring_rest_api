package com.deni.crudrest2.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.deni.crudrest2.model.Pegawai;

public interface PegawaiService {
	List<Pegawai> getAll();

	Pegawai getByNip(String nip);

	void create(Pegawai pegawai);

	void update(String id, Pegawai pegawai);

	void delete(String nip);

	List<Pegawai> getPage(Pageable pageable);

	Page<Pegawai> getPaging(Pageable pageable);

}
