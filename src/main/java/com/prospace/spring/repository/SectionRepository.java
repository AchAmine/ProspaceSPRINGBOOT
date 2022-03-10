package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

	List<Section> findByName(String name);

	@Query(value = "SELECT S FROM Section S INNER JOIN Post_Reaction R ON R.section.idSection=S.idSection WHERE R.type='Like' ")
	List<Section> findLikedSections();
	@Query(value = "SELECT S FROM Section S INNER JOIN Post_Reaction R ON R.section.idSection=S.idSection WHERE R.type='Like' ")
	List<Section> findDislikedSections();
}
