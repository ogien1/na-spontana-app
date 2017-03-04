package pl.lodz.p.it.naspontanaapp.domain;

import lombok.*;

/**
 * Created by 'Jakub Dziworski' on 10.12.16
 */
@Builder
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class CategoryOutputDto {
    private Long id;
    private String name;
    private String verb;
}
