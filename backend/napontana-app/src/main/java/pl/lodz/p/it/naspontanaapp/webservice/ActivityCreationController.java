package pl.lodz.p.it.naspontanaapp.webservice;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.lodz.p.it.naspontanaapp.domain.ActivityInputDto;
import pl.lodz.p.it.naspontanaapp.domain.SimilarActivityInputDto;
import pl.lodz.p.it.naspontanaapp.domain.ActivityOutputDto;
import pl.lodz.p.it.naspontanaapp.service.ActivityCreationManager;

import java.util.List;

/**
 * Created by 'Jakub Dziworski' on 30.11.16
 */
@Transactional
@RestController
@RequestMapping("/activity")
public class ActivityCreationController {

	private static final Logger logger = LoggerFactory.getLogger(ActivityCreationController.class);

	@Autowired
	ActivityCreationManager activityCreationManager;

	@RequestMapping(value = "/addActivity", method = RequestMethod.POST)
	public Long addActivity(@RequestBody ActivityInputDto activityInputDto) {
		logger.info("addActivity - START{}", activityInputDto);
		Long activityId = activityCreationManager.addActivity(activityInputDto);
		logger.info("addActivity - END{}", activityId.toString());
		return activityId;
	}

	@RequestMapping(value = "/addUserToActivity", method = RequestMethod.POST)
	public void addUserToActivity(@RequestParam("facebookId") String facebookId, @RequestParam("activityId") long activityId) {
		logger.info("addUserToActivity - START {} {}", facebookId, activityId);
		activityCreationManager.addUserToActivity(facebookId, activityId);
		logger.info("addUserToActivity - END");
	}

	@RequestMapping(value = "/similarActivities", method = RequestMethod.POST)
	public List<ActivityOutputDto> similarActivities(@RequestBody SimilarActivityInputDto inputDTO,
																@RequestParam("minutes") long minutes) {
		logger.info("similarActivities - START{} {}", inputDTO, minutes);
		List<ActivityOutputDto> similarActivities = activityCreationManager.similarActivities(inputDTO, minutes);
		logger.info("similarActivities - STOP{}", similarActivities);
		return similarActivities;
	}
}