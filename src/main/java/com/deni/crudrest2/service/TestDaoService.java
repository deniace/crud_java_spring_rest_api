package com.deni.crudrest2.service;

import java.util.List;

import com.deni.crudrest2.model.Tutorial;

public interface TestDaoService {

	public List<Tutorial> ambilSemua();

	public Tutorial ambilId(long id);
}
