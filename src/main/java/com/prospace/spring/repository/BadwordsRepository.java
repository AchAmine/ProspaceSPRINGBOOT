package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Badwords;

@Repository
public interface BadwordsRepository extends JpaRepository<Badwords, Long>{

	Badwords findByWord(String word);
}
