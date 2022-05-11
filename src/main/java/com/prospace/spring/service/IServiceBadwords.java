package com.prospace.spring.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.prospace.spring.entity.Badwords;

public interface IServiceBadwords {

	void addBadwords(MultipartFile file);
}
