package pl.lodz.p.it.naspontanaapp.domain;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;

/**
 * Created by piotr on 30.11.16.
 */
@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class ActivityInputDto implements BaseActivityInputDto{

	private String startDate;

	private long categoryId;

	private String description;

	private String name;
	
	@NotNull
	private String facebookId;
}
