package pl.lodz.p.it.naspontanaapp.webservice;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

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
import pl.lodz.p.it.naspontanaapp.domain.BaseActivityDto;
import pl.lodz.p.it.naspontanaapp.domain.SimpleType;
import pl.lodz.p.it.naspontanaapp.service.ActivityCreationManager;

import java.util.List;

/**
 * Created by 'Jakub Dziworski' on 30.11.16
 */
@Transactional(value = TxType.REQUIRES_NEW)
@RestController
@RequestMapping("/activity")
public class ActivityCreationController {

	private static final Logger logger = LoggerFactory.getLogger(ActivityCreationController.class);

	@Autowired
	ActivityCreationManager activityCreationManager;

	@RequestMapping(value = "/addActivity", method = RequestMethod.POST)
	public void addActivity(@RequestBody ActivityInputDto activityInputDto) {
		logger.info("addActivity {}", activityInputDto);
		activityCreationManager.addActivity(activityInputDto);
	}

	@RequestMapping(value = "/addUserToActivity", method = RequestMethod.POST)
	public void addUserToActivity(@RequestParam("facebookId") String facebookId, @RequestParam("activityId") long activityId) {
		logger.info("addUserToActivity {} {}", facebookId, activityId);
		activityCreationManager.addUserToActivity(facebookId, activityId);
	}

	@RequestMapping(value = "/similarActivities", method = RequestMethod.POST)
	public SimpleType<List<ActivityOutputDto>> similarActivities(@RequestBody BaseActivityDto baseActivityDto,
																@RequestParam("minutes") long minutes) {
		logger.info("similarActivities {} {}", baseActivityDto, minutes);
		return new SimpleType<>(activityCreationManager.similarActivities(baseActivityDto, minutes));
	}
}