package com.deni.crudrest2.dao;

import org.springframework.data.repository.CrudRepository;

import com.deni.crudrest2.model.Mahasiswa;

public interface MahasiswaDao extends CrudRepository<Mahasiswa, Integer> {

}
