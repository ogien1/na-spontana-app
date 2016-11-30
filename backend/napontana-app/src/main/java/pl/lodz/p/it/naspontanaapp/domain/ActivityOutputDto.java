package pl.lodz.p.it.naspontanaapp.domain;

import pl.lodz.p.it.naspontanaapp.entities.Activity;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by piotr on 30.11.16.
 */
public class ActivityOutputDto extends BaseActivityDto {

    @NotNull
    private List<String> participantsID;

    private long activityId;

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
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

}
