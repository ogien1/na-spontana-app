package pl.lodz.p.it.naspontanaapp.webservice;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pl.lodz.p.it.naspontanaapp.domain.GetActivitiesDto;
import pl.lodz.p.it.naspontanaapp.service.ActivityListingManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 'Jakub Dziworski' on 30.11.16
 */
@Transactional(value = TxType.REQUIRES_NEW)
@RestController
@RequestMapping("/activity")
public class ActivityListingController {
	
	@Autowired
	ActivityListingManager activityListingManager;

	@RequestMapping(value = "/friendsActivities",method = RequestMethod.GET)
	public List<GetActivitiesDto> getFriendsActivities(@RequestParam("friendId") String[] friends){
        return activityListingManager.getActivities(Arrays.asList(friends))
                .stream()
                .map(GetActivitiesDto::fromActivity)
                .collect(Collectors.toList());
    }
	
	@RequestMapping(value = "/userActivities",method = RequestMethod.GET)
	public List<GetActivitiesDto> getUserActivities(@RequestParam("facebookId") String facebookId){
		return activityListingManager.getUserActivities(facebookId)
				.stream()
				.map(GetActivitiesDto::fromActivity)
				.collect(Collectors.toList());
	}
}
