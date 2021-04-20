package com.deni.crudrest2.service;

import java.util.List;

import com.deni.crudrest2.model.Mahasiswa;

public interface MahasiswaService {
	List<Mahasiswa> getAll();

	Mahasiswa getById(Integer id);

	void save(Mahasiswa mhs);
	
	void delete(Integer id);
}
