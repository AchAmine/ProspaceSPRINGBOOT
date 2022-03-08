package com.prospace.spring.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.prospace.spring.entity.Offer;
import com.prospace.spring.service.IServiceOffer;

public class OfferWriter implements ItemWriter<Offer>{

	@Autowired
	IServiceOffer serviceOffer; 
	@Override
	public void write(List<? extends Offer> Offers) throws Exception {
		serviceOffer.addOffers((List<Offer>) Offers);
		
	}

}
