package com.deni.crudrest2.dao;

import org.springframework.data.repository.CrudRepository;

import com.deni.crudrest2.model.Tutorial;

public interface TestDao extends CrudRepository<Tutorial, Long> {

}
