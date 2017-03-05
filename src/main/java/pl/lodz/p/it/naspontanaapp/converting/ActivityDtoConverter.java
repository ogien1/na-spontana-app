package pl.lodz.p.it.naspontanaapp.converting;

import org.joda.time.LocalDateTime;
import pl.lodz.p.it.naspontanaapp.domain.ActivityOutputDto;
import pl.lodz.p.it.naspontanaapp.domain.BaseActivityInputDto;
import pl.lodz.p.it.naspontanaapp.entities.Activity;
import pl.lodz.p.it.naspontanaapp.entities.Category;
import pl.lodz.p.it.naspontanaapp.entities.User;
import pl.lodz.p.it.naspontanaapp.utils.DateFormater;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jdziworski on 04.03.17.
 */
public class ActivityDtoConverter {
    public static ActivityOutputDto toDto(Activity activity) {
        List<String> usersIds = activity.getUsers().stream()
                .map(u -> u.getFacebookId()).collect(Collectors.toList());
        return ActivityOutputDto.builder()
                .participantsID(usersIds)
                .description(activity.getDescription())
                .name(activity.getName())
                .startDate(DateFormater.convert(activity.getStartDate()))
                .categoryId(activity.getCategory().getId())
                .activityId(activity.getId())
                .ownerFbId(activity.getOwner().getFacebookId())
                .build();
    }

    public static Activity toActivity(BaseActivityInputDto activityInputDto, Category category, User owner) {
        return Activity.builder()
                .description(activityInputDto.getDescription())
                .name(activityInputDto.getName())
                .startDate(DateFormater.convert(activityInputDto.getStartDate()))
                .publicationDate(LocalDateTime.now())
                .category(category)
                .published(false)
                .owner(owner)
                .users(Collections.singletonList(owner))
                .build();
    }
}
