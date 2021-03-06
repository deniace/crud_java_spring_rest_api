package com.deni.crudrest2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deni.crudrest2.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
	List<Tutorial> findByPublished(boolean published);

	List<Tutorial> findByTitleContaining(String title);
	
//	List<Tutorial> findByJudul(String judul);
}
