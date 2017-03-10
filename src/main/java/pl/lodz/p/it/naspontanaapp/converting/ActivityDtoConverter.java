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
 * Klasa konwertująca encje do obiektu DTO
 */
public class ActivityDtoConverter {

    /**
     * Konwertuje encję do obiektu DTO
     * @param activity
     * @return
     */
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

    /**
     * Konwertuje obiekt DTO do encji
     * @param activityInputDto
     * @param category
     * @param owner
     * @return
     */
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
