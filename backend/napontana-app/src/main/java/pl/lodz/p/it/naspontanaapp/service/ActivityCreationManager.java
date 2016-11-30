package pl.lodz.p.it.naspontanaapp.service;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.naspontanaapp.domain.ActivityDto;
import pl.lodz.p.it.naspontanaapp.entities.Activity;
import pl.lodz.p.it.naspontanaapp.entities.Category;
import pl.lodz.p.it.naspontanaapp.entities.User;
import pl.lodz.p.it.naspontanaapp.repository.ActivityRepository;
import pl.lodz.p.it.naspontanaapp.repository.CategoryRepository;
import pl.lodz.p.it.naspontanaapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

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

    public void addActivity(ActivityDto activityDto) {
        Category category = categoryRepository.findOne(activityDto.getCategory());
        User user = userRepository.findUserByFacebookId(activityDto.getFacebookId());
        List<User> users = new ArrayList<>();
        users.add(user);

        Activity activity = new Activity();
        activity.setDescription(activityDto.getDescription());
        activity.setName(activityDto.getName());
        activity.setStartDate(activityDto.getStartDate());
        activity.setUsers(users);
        activity.setPublicationDate(LocalDateTime.now());
        activity.setCategory(category);
        activity.setPublished(false);

        activityRepository.save(activity);
    }

}
