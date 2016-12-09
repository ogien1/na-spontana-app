package pl.lodz.p.it.naspontanaapp.webservice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.lodz.p.it.naspontanaapp.domain.ActivityOutputDto;
import pl.lodz.p.it.naspontanaapp.service.ActivityListingManager;
import pl.lodz.p.it.naspontanaapp.utils.DtoUtils;

/**
 * Created by 'Jakub Dziworski' on 30.11.16
 */
@Transactional
@RestController
@RequestMapping("/activity")
public class ActivityListingController {
	
	private static final Logger logger = LoggerFactory.getLogger(ActivityListingController.class);
	
	@Autowired
	private ActivityListingManager activityListingManager;

	@RequestMapping(value = "/friendsActivities",method = RequestMethod.GET)
	public List<ActivityOutputDto> getFriendsActivities(@RequestParam("friendId") String[] friends){
		logger.info("getFriendsActivities {}", Arrays.asList(friends));
		return activityListingManager.getActivities(Arrays.asList(friends))
                .stream()
                .map(DtoUtils::fromActivity)
                .collect(Collectors.toList());
    }
	
	@RequestMapping(value = "/userActivities",method = RequestMethod.GET)
	public List<ActivityOutputDto> getUserActivities(@RequestParam("facebookId") String facebookId){
		logger.info("getUserActivities {}", facebookId);
		return activityListingManager.getUserActivities(facebookId)
			.stream().map(DtoUtils::fromActivity).collect(Collectors.toList());
	}
}
