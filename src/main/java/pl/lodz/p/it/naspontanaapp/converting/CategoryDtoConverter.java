package pl.lodz.p.it.naspontanaapp.converting;

import pl.lodz.p.it.naspontanaapp.domain.CategoryOutputDto;
import pl.lodz.p.it.naspontanaapp.entities.Category;

/**
 * Created by jdziworski on 04.03.17.
 */
public class CategoryDtoConverter {
    public static CategoryOutputDto toDto(Category category) {
        return CategoryOutputDto.builder()
                .id(category.getId())
                .name(category.getName())
                .verb(category.getVerb())
                .build();
    }

}
