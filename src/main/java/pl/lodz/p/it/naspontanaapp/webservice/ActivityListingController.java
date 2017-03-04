package pl.lodz.p.it.naspontanaapp.webservice;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.lodz.p.it.naspontanaapp.converting.ActivityDtoConverter;
import pl.lodz.p.it.naspontanaapp.converting.CategoryDtoConverter;
import pl.lodz.p.it.naspontanaapp.domain.ActivityOutputDto;
import pl.lodz.p.it.naspontanaapp.domain.CategoryOutputDto;
import pl.lodz.p.it.naspontanaapp.entities.Category;
import pl.lodz.p.it.naspontanaapp.service.ActivityListingManager;

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
		logger.info("getFriendsActivities - START {}", Arrays.asList(friends));
		List<ActivityOutputDto> list = activityListingManager.getActivities(Arrays.asList(friends))
        .stream()
        .map(ActivityDtoConverter::toDto)
        .collect(toList());
		logger.info("getFriendsActivities - STOP {}", list);
		return list;
    }
	
	@RequestMapping(value = "/userActivities",method = RequestMethod.GET)
	public List<ActivityOutputDto> getUserActivities(@RequestParam("facebookId") String facebookId){
		logger.info("getUserActivities - START {}", facebookId);
		List<ActivityOutputDto> collect = activityListingManager.getUserActivities(facebookId)
			.stream().map(ActivityDtoConverter::toDto).collect(toList());
		logger.info("getUserActivities - STOP {}", collect);
		return collect;
	}
	
	@RequestMapping(value = "/categories",method = RequestMethod.GET)
	public List<CategoryOutputDto> getCategories(){
		logger.info("getCategories - START");
		List<Category> categories = activityListingManager.getCategories();
		logger.info("getCategories - STOP {}", categories);
		return categories.stream().map(CategoryDtoConverter::toDto).collect(toList());
	}
}
