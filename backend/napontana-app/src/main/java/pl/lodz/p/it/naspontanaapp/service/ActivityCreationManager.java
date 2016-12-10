package pl.lodz.p.it.naspontanaapp.service;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.naspontanaapp.domain.ActivityInputDto;
import pl.lodz.p.it.naspontanaapp.domain.ActivityOutputDto;
import pl.lodz.p.it.naspontanaapp.domain.SimilarActivityInputDto;
import pl.lodz.p.it.naspontanaapp.entities.Activity;
import pl.lodz.p.it.naspontanaapp.entities.Category;
import pl.lodz.p.it.naspontanaapp.entities.User;
import pl.lodz.p.it.naspontanaapp.repository.ActivityRepository;
import pl.lodz.p.it.naspontanaapp.repository.CategoryRepository;
import pl.lodz.p.it.naspontanaapp.repository.UserRepository;
import pl.lodz.p.it.naspontanaapp.utils.DateFormater;
import pl.lodz.p.it.naspontanaapp.utils.DtoUtils;
import pl.lodz.p.it.naspontanaapp.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



/**
 * Created by 'Jakub Dziworski' on 30.11.16
 */
@Service
public class ActivityCreationManager {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Long addActivity(ActivityInputDto activityInputDto) {
        Category category = categoryRepository.findOne((activityInputDto.getCategoryId()));
        User user = userRepository.findUserByFacebookId(activityInputDto.getFacebookId());
        if(user == null){
        	user = createNewUser(activityInputDto);
        }

        Activity activity = new Activity();
        activity.setDescription(activityInputDto.getDescription());
        activity.setName(activityInputDto.getName());
        activity.setStartDate(DateFormater.convert(activityInputDto.getStartDate()));
        activity.setPublicationDate(LocalDateTime.now());
        activity.setCategory(category);
        activity.setPublished(false);
        user.getActivities().add(activity);

        return activityRepository.save(activity).getId();
    }

    private User createNewUser(ActivityInputDto activityInputDto) {
    	String facebookId = activityInputDto.getFacebookId();
    	User user = new User();
    	user.setName("");
    	user.setLastname("");
    	user.setFacebookId(facebookId);
    	return userRepository.save(user);
    }

	public void addUserToActivity(String facebookId, long activityId) {
        Activity activity = activityRepository.findOne(activityId);
        User user = userRepository.findUserByFacebookId(facebookId);
        activity.getUsers().add(user);
        user.getActivities().add(activity);
    }

    public List<ActivityOutputDto> findSimilarActivities(SimilarActivityInputDto inputDTO) {

    	List<String> friends = Arrays.asList(inputDTO.getFriends());
    	Map<Long, Activity> friendsActivities = new HashMap<Long, Activity>();
    	for (String friendId : friends) {
    		List<Activity> activities = activityRepository.findActivityByUsers_FacebookId(friendId);
    		Map<Long, Activity> mapActivities = activities.stream().collect(Collectors.toMap(Activity::getId, a -> a));
    		friendsActivities.putAll(mapActivities);
		}
    	List<Activity> friendsActivitiesList = new ArrayList<Activity>(friendsActivities.values());
        List<Activity> filteredActivities = friendsActivitiesList.stream()
                .filter(a -> (TimeUtils.getMinutes(DateFormater.convert(inputDTO.getStartDate()), a.getStartDate())
                		<= inputDTO.getMinutesDiff()))
                .filter(a -> inputDTO.getCategoryId() == a.getCategory().getId())
                .collect(Collectors.toList());
        return filteredActivities.stream().map(DtoUtils::fromActivity).collect(Collectors.toList());
    }
}
