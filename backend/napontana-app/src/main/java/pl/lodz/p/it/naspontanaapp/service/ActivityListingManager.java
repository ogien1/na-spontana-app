package pl.lodz.p.it.naspontanaapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.naspontanaapp.entities.Activity;
import pl.lodz.p.it.naspontanaapp.entities.User;
import pl.lodz.p.it.naspontanaapp.repository.ActivityRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 'Jakub Dziworski' on 30.11.16
 */
@Service
public class ActivityListingManager {

    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityListingManager(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> getActivities(List<String> friendsIds) {
        List<Activity> allActivities = activityRepository.findAll();
        return allActivities.stream()
                .filter(a -> activityHasAtLeastOneUser(a,friendsIds))
                .collect(Collectors.toList());
    }

    private boolean activityHasAtLeastOneUser(Activity activity, List<String> friendsIds) {
        return activity.getUsers().stream()
                .map(User::getFacebookId)
                .anyMatch(friendsIds::contains);
    }
}
