package com.prospace.spring.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.prospace.spring.entity.Image;
import com.prospace.spring.entity.Offer;
import com.prospace.spring.entity.OfferState;
import com.prospace.spring.entity.Partner;
import com.prospace.spring.entity.Question;
import com.prospace.spring.entity.Rating;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.OfferRepository;
import com.prospace.spring.repository.PartnerRepository;
import com.prospace.spring.repository.RatingRepository;
import com.prospace.spring.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ServiceOffer implements IServiceOffer{
	@Autowired
	OfferRepository offerRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	PartnerRepository partnerRepository;
	@Autowired
	IServiceImage imageService;
	@Autowired 
	RatingRepository ratingRepository;
	

	 private final Path root = Paths.get("C:\\xampp\\htdocs\\FileUploads");
	 

	@Override
	public List<Offer> retrieveAllOffers() {
		return (List<Offer>) offerRepository.findAll();
	}

	@Override
	@Transactional
	public Offer addOffer(Offer o, Long idUser,MultipartFile file) {
		User partner =userRepository.findById(idUser).orElse(null);
		
		o.setPartner(partner);
		//Image
				Image image = new Image(file.getOriginalFilename());
				o.setImage(image);
				imageService.save(file);
						//EndImage
				Rating r = new Rating();
				r.setRate((float) 0);
	 List<Rating> ratings = new ArrayList<Rating>();
	 			ratings.add(r);
	 			r.setOffer(o);
	 			ratingRepository.saveAll(ratings);
	 			for (Rating rating : ratings) {
	 				rating.setOffer(o);;
	 				
	 				ratingRepository.save(rating);
	 			}
	 			o.setMoyRatings(0);
		return offerRepository.save(o);
	}

	@Override
	public void deleteOffer(Long id) {
		offerRepository.deleteById(id);
		
	}

	@Override
	public Offer updateOffer(Offer o) {
		
		return offerRepository.save(o);
	}
	
	
	@Override
	public Offer retrieveOffer(Long id) {
		return offerRepository.findById(id).orElse(null);

	}
	
	@Override
	public List<Offer> TriOffers() {
		long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        List<Offer> listOffers = offerRepository.TodaysOffers();
		return offerRepository.RatingTri();
	}

	@Override
	public void addOffers(List<Offer> Offers) {
		List<Offer> listOffers = new ArrayList<Offer>();
		
		for(Offer offer : Offers) {
			User partner = userRepository.findByEmail(offer.getPartnerEmail());
			offer.setPartner(partner);
			offer.setState(OfferState.Waiting);
			log.info("-------------------------OFFER"+offer.getStartsAt() );
			listOffers.add(offer);
		}
		offerRepository.saveAll(listOffers);
	}
	
	
	
	@Override
	public void uploadFile(MultipartFile file) {
		String filename = file.getOriginalFilename();
		getFile(file);
		
		
	}
	
	public void getFile(MultipartFile file){
		if (!root.toFile().exists()) {
			try {
			      Files.createDirectory(root);
			    } catch (IOException e) {
			      throw new RuntimeException("Could not initialize folder for upload!");
			    }
			 }
			try {
			      Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
			    } catch (Exception e) {
			      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
			    }
	}
	
	

}
