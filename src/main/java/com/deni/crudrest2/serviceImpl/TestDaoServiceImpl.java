package com.deni.crudrest2.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deni.crudrest2.dao.TestDao;
import com.deni.crudrest2.model.Tutorial;
import com.deni.crudrest2.service.TestDaoService;

@Service
@Transactional
public class TestDaoServiceImpl implements TestDaoService {

	@Autowired
	TestDao testDao;

	@Override
	public List<Tutorial> ambilSemua() {
		List<Tutorial> all = new ArrayList<Tutorial>();

		testDao.findAll().forEach(all::add);
		return all;
	}

	@Override
	public Tutorial ambilId(long id) {
		Optional<Tutorial> tut = testDao.findById(id);
		if (tut.isPresent()) {
			return tut.get();
		} else {
			return null;
		}
	}

}
