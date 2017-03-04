package pl.lodz.p.it.naspontanaapp.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.lodz.p.it.naspontanaapp.domain.ActivityInputDto;
import pl.lodz.p.it.naspontanaapp.domain.ActivityOutputDto;
import pl.lodz.p.it.naspontanaapp.domain.BaseActivityInputDto;
import pl.lodz.p.it.naspontanaapp.domain.SimilarActivityInputDto;
import pl.lodz.p.it.naspontanaapp.entities.Activity;
import pl.lodz.p.it.naspontanaapp.entities.Category;
import pl.lodz.p.it.naspontanaapp.entities.User;
import pl.lodz.p.it.naspontanaapp.repository.ActivityRepository;
import pl.lodz.p.it.naspontanaapp.repository.CategoryRepository;
import pl.lodz.p.it.naspontanaapp.repository.UserRepository;
import pl.lodz.p.it.naspontanaapp.utils.DateFormater;
import pl.lodz.p.it.naspontanaapp.utils.DtoUtils;
import pl.lodz.p.it.naspontanaapp.utils.TimeYodaUtils;

/**
 * Created by 'Jakub Dziworski' on 30.11.16
 */
@Service
public class ActivityCreationManagerImpl implements ActivityCreationManager {

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ActivityListingManagerImpl activityListingManager;

	@Override
	public Long addActivity(BaseActivityInputDto activityInputDto) {
		Category category = categoryRepository.findOne((activityInputDto.getCategoryId()));
		User user = userRepository.findUserByFacebookId(activityInputDto.getFacebookId());
		if (user == null) {
			user = createNewUser(activityInputDto.getFacebookId());
		}

		Activity activity = new Activity();
		activity.setDescription(activityInputDto.getDescription());
		activity.setName(activityInputDto.getName());
		activity.setStartDate(DateFormater.convert(activityInputDto.getStartDate()));
		activity.setPublicationDate(LocalDateTime.now());
		activity.setCategory(category);
		activity.setPublished(false);
		activity.setOwner(user);

		return activityRepository.save(activity).getId();
	}

	private User createNewUser(String facebookId) {
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
		List<String> friendsIds = Arrays.asList(inputDTO.getFriends());
		return activityListingManager.getActivities(friendsIds).stream()
				.filter(a -> areSimilarActivities(inputDTO, a))
				.map(DtoUtils::fromActivity)
				.collect(Collectors.toList());
	}

	private boolean areSimilarActivities(SimilarActivityInputDto similarActivityInputDto, Activity activity) {
		long minutes = TimeYodaUtils.getMinutes(DateFormater.convert(similarActivityInputDto.getStartDate()),
				activity.getStartDate());

		boolean isCorrectTimeDiff = minutes <= similarActivityInputDto.getMinutesDiff();
		boolean isCorrectCategory = similarActivityInputDto.getCategoryId() == activity.getCategory().getId();

		return isCorrectTimeDiff & isCorrectCategory;
	}

}
