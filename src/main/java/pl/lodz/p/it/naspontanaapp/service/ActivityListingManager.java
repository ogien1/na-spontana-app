package pl.lodz.p.it.naspontanaapp.service;

import java.util.List;

import pl.lodz.p.it.naspontanaapp.entities.Activity;
import pl.lodz.p.it.naspontanaapp.entities.Category;

public interface ActivityListingManager {

	List<Activity> getActivities(List<String> friendsIds);
	
	List<Activity> getUserActivities(String facebookId);
	
	List<Category> getCategories();
}
