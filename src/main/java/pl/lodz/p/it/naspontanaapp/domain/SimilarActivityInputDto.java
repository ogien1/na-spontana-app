package pl.lodz.p.it.naspontanaapp.domain;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * Zawiera dane na podstawie, których aktywność powinna zostać złączona
 */
@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class SimilarActivityInputDto implements BaseActivityInputDto{

    /**
     * Toleracja czasu wyrażona w minutach
     */
	private long minutesDiff;

    /**
     * Lista identyfikatorów facebook przyjaciół
     */
	private String[] friends;

    /**
     * Data początkowa
     */
    private String startDate;

    /**
     * Identyfikator kategorii
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
