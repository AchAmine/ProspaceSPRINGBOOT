package com.prospace.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospace.spring.entity.Activity;
import com.prospace.spring.entity.Badge;
import com.prospace.spring.entity.Event;
import com.prospace.spring.entity.Grade;
import com.prospace.spring.entity.User;
import com.prospace.spring.repository.BadgeRepository;
import com.prospace.spring.repository.UserRepository;

@Service
public class BadgeService implements IBadgeService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	BadgeRepository badgeRepository;

	public List<Badge> GetAllBAdges() {

		return badgeRepository.findAll();
	}

	public long AjoutBadge(Badge badge) {

		badgeRepository.save(badge);

		return badge.getIdBadge();
	}

	public void DeleteBadge(long id) {
		badgeRepository.deleteById(id);

	}

	public long UpdateBadge(Badge badge) {
		badgeRepository.save(badge);
		return badge.getIdBadge();
	}

	public Badge FindBadgeById(long id) {

		return badgeRepository.findById(id).get();
	}

	public void AffectBadgeToUser(long idBadge, long idUser) {
		Badge b = FindBadgeById(idBadge);
		User u = userRepository.findById(idUser).get();
		List<Badge> listBadges = u.getBadges();
		listBadges.add(b);
		u.setBadges(listBadges);
		UpdateBadge(b);

	}

	public void CalculScoreEventParticipationBadge(long idUser) {

		User user = userRepository.findById(idUser).get();
		List<Event> eventsParticipation = new ArrayList <> (user.getEventsParticipation());
		int nbrEvents = eventsParticipation.size();
		int nbrEventActivity = 0;

		if (nbrEvents != 0) {
			for (Event event : eventsParticipation) {
				// int test = nbrEventActivity > 0 ? 2 : 5;
				List<Activity> listActvities =  new ArrayList <> ( event.getActivities());
				nbrEventActivity += listActvities.size();
			}
		}

		int eventsParticipationScore = nbrEvents == 0 ? nbrEvents
				: nbrEvents > 0 && nbrEvents < 10 ? 20 : nbrEvents > 9 && nbrEvents < 26 ? 50 : 100;
		int activityScore = nbrEventActivity == 0 ? nbrEventActivity
				: nbrEventActivity > 0 && nbrEventActivity < 30 ? 25
						: nbrEventActivity > 29 && nbrEventActivity < 49 ? 75 : 150;

		int eventsParticipationGlobalScore = eventsParticipationScore * 4 + activityScore * 2;

		if (eventsParticipationGlobalScore > 0) {
		
			long idbadgeEvent = 1;

			List<Badge> listBadgeEvent = user.getBadges();

			listBadgeEvent.stream().filter(e -> e.getIdBadge() == idbadgeEvent);
			if (listBadgeEvent.isEmpty()) {

				
				Badge badge = badgeRepository.findById(idbadgeEvent).get();
				badge.setTitle(eventsParticipationGlobalScore >= 700 ? Grade.Master
						: eventsParticipationGlobalScore < 700 && eventsParticipationGlobalScore >= 500 ? Grade.Senior
								: eventsParticipationGlobalScore < 500 && eventsParticipationGlobalScore >= 250
										? Grade.Junior : Grade.Newbie);
				List<Badge> listBadge = user.getBadges();
				listBadge.add(badge);
				user.setBadges(listBadge);
				userRepository.save(user);
				
			} else {

				Badge badge = listBadgeEvent.get(0); 
				badge.setTitle(eventsParticipationGlobalScore >= 700 ? Grade.Master
						: eventsParticipationGlobalScore < 700 && eventsParticipationGlobalScore >= 500 ? Grade.Senior
								: eventsParticipationGlobalScore < 500 && eventsParticipationGlobalScore >= 250
										? Grade.Junior : Grade.Newbie);
			
				List<Badge> listBadge = user.getBadges();
				listBadge.add(badge);
				user.setBadges(listBadge);
				userRepository.save(user);
			}

		}

	}
}
