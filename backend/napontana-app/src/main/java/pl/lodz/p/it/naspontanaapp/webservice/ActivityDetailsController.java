package pl.lodz.p.it.naspontanaapp.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.lodz.p.it.naspontanaapp.domain.GetActivitiesDto;
import pl.lodz.p.it.naspontanaapp.entities.Activity;
import pl.lodz.p.it.naspontanaapp.service.ActivityDetailsManager;

/**
 * Created by 'Jakub Dziworski' on 30.11.16
// */
@RestController("/activity")
public class ActivityDetailsController {

    @Autowired
    private ActivityDetailsManager activityDetailsManager;

    @RequestMapping(value = "/details/{activityId}",method = RequestMethod.GET)
    public GetActivitiesDto activityDetails(@PathVariable long activityId) {
        Activity activity = activityDetailsManager.getActivity(activityId);
        return GetActivitiesDto.fromActivity(activity);
    }
}
