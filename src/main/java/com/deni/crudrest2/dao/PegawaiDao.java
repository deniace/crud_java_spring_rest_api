package com.deni.crudrest2.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deni.crudrest2.model.Pegawai;

public interface PegawaiDao extends JpaRepository<Pegawai, String> {

}
