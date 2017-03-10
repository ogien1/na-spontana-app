package pl.lodz.p.it.naspontanaapp.domain;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;

/**
 * Dane aktywności podawane przez użytkownika
 */
@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class ActivityInputDto implements BaseActivityInputDto{

	/**
	 * Data startowa aktywności
	 */
	private String startDate;

	/**
	 * Typ aktywności
	 */
	private long categoryId;

	/**
	 * Opis aktywności
	 */
	private String description;

	/**
	 * Nazwa aktywności
	 */
	private String name;

	/**
	 * Identyfikator facebook użytkownika
	 */
	@NotNull
	private String facebookId;
}
