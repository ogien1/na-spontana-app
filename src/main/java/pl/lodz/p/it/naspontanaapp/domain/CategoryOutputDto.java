package pl.lodz.p.it.naspontanaapp.domain;

import lombok.*;

/**
 * Kategoria aktywności
 */
@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class CategoryOutputDto {
    /**
     * Identyfikator kategorii
     */
    private Long id;

    /**
     * Nazwa kategorii
     */
    private String name;

    /**
     * Nazwa czynności jaką można wykonywać w ramach aktywności
     */
    private String verb;
}
