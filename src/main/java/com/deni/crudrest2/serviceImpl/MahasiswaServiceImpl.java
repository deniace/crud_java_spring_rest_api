package com.deni.crudrest2.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deni.crudrest2.dao.MahasiswaDao;
import com.deni.crudrest2.model.Mahasiswa;
import com.deni.crudrest2.service.MahasiswaService;

@Service
@Transactional
public class MahasiswaServiceImpl implements MahasiswaService {

	@Autowired
	MahasiswaDao dao;

	@Override
	public List<Mahasiswa> getAll() {
		Iterable<Mahasiswa> all = dao.findAll();
		if (all != null) {
			List<Mahasiswa> mhs = new ArrayList<Mahasiswa>();
			for (Mahasiswa a : all) {
				mhs.add(a);
			}
			return mhs;
		} else {
			return null;
		}
	}

	@Override
	public Mahasiswa getById(Integer id) {
		Optional<Mahasiswa> it = dao.findById(id);
		if (it.isEmpty()) {
			return null;
		} else {
			return it.get();
		}
	}

	@Override
	public void save(Mahasiswa mhs) {
		dao.save(mhs);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

}
