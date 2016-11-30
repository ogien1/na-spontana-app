package pl.lodz.p.it.naspontanaapp.utils;

import pl.lodz.p.it.naspontanaapp.domain.ActivityOutputDto;
import pl.lodz.p.it.naspontanaapp.entities.Activity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by piotr on 30.11.16.
 */
public class DtoUtils {

    public static ActivityOutputDto fromActivity(Activity activity) {
        ActivityOutputDto activityOutputDto = new ActivityOutputDto();
        List<String> usersIds = activity.getUsers().stream()
                .map(u -> u.getFacebookId()).collect(Collectors.toList());
        activityOutputDto.setParticipantsID(usersIds);
        activityOutputDto.setDescription(activity.getDescription());
        activityOutputDto.setName(activity.getName());
        activityOutputDto.setStartDate(activity.getStartDate());
        activityOutputDto.setCategoryId(activity.getCategory().getId());
        activityOutputDto.setActivityId(activity.getId());
        return activityOutputDto;
    }

}
