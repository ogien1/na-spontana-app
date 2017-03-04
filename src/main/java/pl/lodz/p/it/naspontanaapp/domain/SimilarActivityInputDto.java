package pl.lodz.p.it.naspontanaapp.domain;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * Created by 'Jakub Dziworski' on 09.12.16
 */
@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class SimilarActivityInputDto implements BaseActivityInputDto{

	private long minutesDiff;
	
	private String[] friends;

    private String startDate;

    private long categoryId;

    private String description;

    private String name;

    @NotNull
    private String facebookId;
}
