package pl.lodz.p.it.naspontanaapp.domain;

import pl.lodz.p.it.naspontanaapp.entities.Activity;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by piotr on 30.11.16.
 */
public class GetActivitiesDto {

    @NotNull
    private List<String> participantsID;

    @NotNull
    private String description;

    @NotNull
    private String name;

    @NotNull
    private Timestamp startDate;

    @NotNull
    private long category;

    public static GetActivitiesDto fromActivity(Activity activity) {
        GetActivitiesDto getActivitiesDto = new GetActivitiesDto();
        List<String> particpajntsIds = activity.getUsers().stream()
                .map(u -> u.getFacebookId()).collect(Collectors.toList());
        getActivitiesDto.setParticipantsID(particpajntsIds);
        getActivitiesDto.setDescription(activity.getDescription());
        getActivitiesDto.setName(activity.getName());
        getActivitiesDto.setStartDate(activity.getStartDate());
        getActivitiesDto.setCategory(activity.getCategory().getId());
        return getActivitiesDto;
    }

    public List<String> getParticipantsID() {
        return this.participantsID;
    }

    public void setParticipantsID(List<String> participantsID) {
        this.participantsID = participantsID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }
}
