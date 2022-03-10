package com.prospace.spring.service;
import java.util.List;

import com.prospace.spring.entity.Badge;

public interface IBadgeService {
	public List<Badge> GetAllBAdges();
	public long AjoutBadge(Badge badge);
	public void DeleteBadge(long id);
	public Badge FindBadgeById(long id);
	public void AffectBadgeToUser (long idBadge, long idUser);
	public void CalculScoreEventParticipationBadge(long idUser);
}
