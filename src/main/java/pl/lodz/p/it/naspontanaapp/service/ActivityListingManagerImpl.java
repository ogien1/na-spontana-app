package pl.lodz.p.it.naspontanaapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.lodz.p.it.naspontanaapp.entities.Activity;
import pl.lodz.p.it.naspontanaapp.entities.Category;
import pl.lodz.p.it.naspontanaapp.repository.ActivityRepository;
import pl.lodz.p.it.naspontanaapp.repository.CategoryRepository;

/**
 * Created by 'Jakub Dziworski' on 30.11.16
 */
@Service
public class ActivityListingManagerImpl implements ActivityListingManager{

	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	public List<Activity> getActivities(List<String> friendsIds) {
		List<Activity> allActivities = activityRepository.findAll();
		return allActivities.stream()
				.filter(a -> activityOwnedByAnyFriend(a, friendsIds))
				.collect(Collectors.toList());
	}

	private boolean activityOwnedByAnyFriend(Activity activity, List<String> friendsIds) {
		String ownerFbId = activity.getOwner().getFacebookId();
		return friendsIds.contains(ownerFbId);
	}

	public List<Activity> getUserActivities(String facebookId) {
		return activityRepository.findActivityByOwner_facebookId(facebookId);
	}

	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}
}
