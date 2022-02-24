package com.prospace.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
	List<Section> findByName(String name);
}
