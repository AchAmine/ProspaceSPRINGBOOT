package com.prospace.spring.service;

import java.util.List;

import com.prospace.spring.entity.Offer;
import com.prospace.spring.entity.Partner;
import com.prospace.spring.entity.User;

public interface IServicePartner {
  public List<User> AddAndRetrievePartners();
}
