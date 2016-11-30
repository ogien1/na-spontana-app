package pl.lodz.p.it.naspontanaapp.service;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.naspontanaapp.domain.ActivityInputDto;
import pl.lodz.p.it.naspontanaapp.domain.ActivityOutputDto;
import pl.lodz.p.it.naspontanaapp.domain.BaseActivityDto;
import pl.lodz.p.it.naspontanaapp.entities.Activity;
import pl.lodz.p.it.naspontanaapp.entities.Category;
import pl.lodz.p.it.naspontanaapp.entities.User;
import pl.lodz.p.it.naspontanaapp.repository.ActivityRepository;
import pl.lodz.p.it.naspontanaapp.repository.CategoryRepository;
import pl.lodz.p.it.naspontanaapp.repository.UserRepository;
import pl.lodz.p.it.naspontanaapp.utils.DtoUtils;
import pl.lodz.p.it.naspontanaapp.utils.TimeUtils;

import java.util.List;
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

    public void addActivity(ActivityInputDto activityInputDto) {
        Category category = categoryRepository.findOne((activityInputDto.getCategoryId()));
        User user = userRepository.findUserByFacebookId(activityInputDto.getFacebookId());

        Activity activity = new Activity();
        activity.setDescription(activityInputDto.getDescription());
        activity.setName(activityInputDto.getName());
        activity.setStartDate(activityInputDto.getStartDate());
        activity.setPublicationDate(LocalDateTime.now());
        activity.setCategory(category);
        activity.setPublished(false);
        user.getActivities().add(activity);

        activityRepository.save(activity);
    }

    public void addUserToActivity(String facebookId, long activityId) {
        Activity activity = activityRepository.findOne(activityId);
        User user = userRepository.findUserByFacebookId(facebookId);
        activity.getUsers().add(user);
    }

    public List<ActivityOutputDto> similarActivities(BaseActivityDto baseActivityDto, long minutes) {
        List<Activity> activities = activityRepository.findAll();
        List<Activity> filteredActivities = activities.stream()
                .filter(a -> (TimeUtils.getMinutes(baseActivityDto.getStartDate(), a.getStartDate()) <= minutes))
                .collect(Collectors.toList());
        return filteredActivities.stream().map(DtoUtils::fromActivity).collect(Collectors.toList());
    }
}
