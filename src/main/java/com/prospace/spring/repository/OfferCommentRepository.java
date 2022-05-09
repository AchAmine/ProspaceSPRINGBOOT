package com.prospace.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prospace.spring.entity.OfferComment;
import com.prospace.spring.entity.Offer;
import java.util.List;

public interface OfferCommentRepository extends JpaRepository<OfferComment, Long> {
List<OfferComment> findByOffer(Offer offer);
}
