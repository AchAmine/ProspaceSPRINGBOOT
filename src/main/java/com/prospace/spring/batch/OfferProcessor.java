package com.prospace.spring.batch;

import org.springframework.batch.item.ItemProcessor;

import com.prospace.spring.entity.Offer;

public class OfferProcessor implements ItemProcessor<Offer, Offer>{

	@Override
	public Offer process(Offer offer) throws Exception {
		// TODO Auto-generated method stub
		return offer;
	}

}
