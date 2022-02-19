package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{

}
