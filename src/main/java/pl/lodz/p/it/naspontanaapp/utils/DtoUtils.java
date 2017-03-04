package pl.lodz.p.it.naspontanaapp.utils;

import java.util.List;
import java.util.stream.Collectors;

import pl.lodz.p.it.naspontanaapp.domain.ActivityOutputDto;
import pl.lodz.p.it.naspontanaapp.domain.CategoryOutputDto;
import pl.lodz.p.it.naspontanaapp.entities.Activity;
import pl.lodz.p.it.naspontanaapp.entities.Category;

/**
 * Created by piotr on 30.11.16.
 */
public class DtoUtils {

    public static ActivityOutputDto fromActivity(Activity activity) {
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

    public static CategoryOutputDto categoryTocategoryOutputDto(Category category) {
        return CategoryOutputDto.builder()
            .id(category.getId())
            .name(category.getName())
            .verb(category.getVerb())
            .build();
    }

}
