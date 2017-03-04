package pl.lodz.p.it.naspontanaapp.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.lodz.p.it.naspontanaapp.converting.ActivityDtoConverter;
import pl.lodz.p.it.naspontanaapp.domain.ActivityOutputDto;
import pl.lodz.p.it.naspontanaapp.entities.Activity;
import pl.lodz.p.it.naspontanaapp.service.ActivityDetailsManager;

/**
 * Created by 'Jakub Dziworski' on 30.11.16
// */
@RestController
public class ActivityDetailsController {
	
	private static final Logger logger = LoggerFactory.getLogger(ActivityDetailsController.class);

    @Autowired
    private ActivityDetailsManager activityDetailsManager;

    @RequestMapping(value = "/activity/details/{activityId}",method = RequestMethod.GET)
    public ActivityOutputDto activityDetails(@PathVariable long activityId) {
        logger.info("activityDetails - START {}", activityId);
        Activity activity = activityDetailsManager.getActivity(activityId);
        ActivityOutputDto fromActivity = ActivityDtoConverter.toDto(activity);
        logger.info("activityDetails - STOP {}", fromActivity);
        return fromActivity;
    }
}
