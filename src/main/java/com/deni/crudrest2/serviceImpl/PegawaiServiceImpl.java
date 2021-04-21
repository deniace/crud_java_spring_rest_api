package com.deni.crudrest2.serviceImpl;

//import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.deni.crudrest2.dao.PegawaiDao;
import com.deni.crudrest2.model.Pegawai;
import com.deni.crudrest2.service.PegawaiService;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {

	@Autowired
	PegawaiDao dao;

	@Override
	public List<Pegawai> getAll() {
		Iterable<Pegawai> allData = dao.findAll();
		List<Pegawai> peg = new ArrayList<Pegawai>();
		if (allData != null) {
			for (Pegawai p : allData) {
				peg.add(p);
			}
			return peg;
		} else {
			return null;
		}
	}

	@Override
	public Pegawai getByNip(String nip) {
		Optional<Pegawai> op = dao.findById(nip);
		if (op.isPresent()) {
			return op.get();
		} else {
			return null;
		}
	}

	@Override
	public void create(Pegawai pegawai) {
		dao.save(pegawai);
	}

	@Override
	public void update(String id, Pegawai pegawai) {
	}

	@Override
	public void delete(String nip) {
		dao.deleteById(nip);
	}

	@Override
	public List<Pegawai> getPage(Pageable pageable) {
		Iterable<Pegawai> peg = dao.findAll(pageable);
		List<Pegawai> p = new ArrayList<Pegawai>();

		if (peg != null) {
			for (Pegawai pp : peg) {
				p.add(pp);
			}
			return p;
		} else {
			return null;
		}
	}

	@Override
	public Page<Pegawai> getPaging(Pageable pageable) {
		Page<Pegawai> peg = dao.findAll(pageable);

		if (peg != null) {
			return peg;
		} else {
			return null;
		}
	}

}
