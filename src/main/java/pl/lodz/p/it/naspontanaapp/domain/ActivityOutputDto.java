package pl.lodz.p.it.naspontanaapp.domain;

import lombok.*;

import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * Created by piotr on 30.11.16.
 */
@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class ActivityOutputDto {

    private String startDate;

    private long categoryId;

    private String description;

    private String name;

    private String ownerFbId;
    
    @NotNull
    private List<String> participantsID;

    private long activityId;

}
