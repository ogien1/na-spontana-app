package pl.lodz.p.it.naspontanaapp.webservice;

import java.util.List;

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
import pl.lodz.p.it.naspontanaapp.domain.ActivityOutputDto;
import pl.lodz.p.it.naspontanaapp.domain.SimilarActivityInputDto;
import pl.lodz.p.it.naspontanaapp.service.ActivityCreationManager;

/**
 * Endpoint aktywności
 */
@Transactional
@RestController
@RequestMapping("/activity")
public class ActivityCreationController {

	private static final Logger logger = LoggerFactory.getLogger(ActivityCreationController.class);

	@Autowired
	ActivityCreationManager activityCreationManager;

	/**
	 * Umożliwia dodanie aktywności
	 * @param activityInputDto
	 * @return
	 */
	@RequestMapping(value = "/addActivity", method = RequestMethod.POST)
	public Long addActivity(@RequestBody ActivityInputDto activityInputDto) {
		logger.info("addActivity - START {}", activityInputDto);
		Long activityId = activityCreationManager.addActivity(activityInputDto);
		logger.info("addActivity - END {}", activityId.toString());
		return activityId;
	}

	/**
	 * Umożliwia dodanie użytkownika do aktywności
	 * @param facebookId
	 * @param activityId
	 */
	@RequestMapping(value = "/addUserToActivity", method = RequestMethod.POST)
	public void addUserToActivity(@RequestParam("facebookId") String facebookId, @RequestParam("activityId") long activityId) {
		logger.info("addUserToActivity - START {} {}", facebookId, activityId);
		activityCreationManager.addUserToActivity(facebookId, activityId);
		logger.info("addUserToActivity - END");
	}

	/**
	 * Umożliwia dodanie podobnych aktywności
	 * @param similarActivityInputDto
	 * @return
	 */
	@RequestMapping(value = "/addSimilarActivity", method = RequestMethod.POST)
	public List<ActivityOutputDto> addSimilarActivity(@RequestBody SimilarActivityInputDto similarActivityInputDto) {
		logger.info("similarActivities - START {} {}", similarActivityInputDto);
		List<ActivityOutputDto> similarActivities = activityCreationManager.findSimilarActivities(similarActivityInputDto);
		if(similarActivities.isEmpty()){
			activityCreationManager.addActivity(similarActivityInputDto);
		}
		logger.info("similarActivities - STOP {}", similarActivities);
		return similarActivities;
	}
}