package pl.lodz.p.it.naspontanaapp.converting;

import pl.lodz.p.it.naspontanaapp.domain.CategoryOutputDto;
import pl.lodz.p.it.naspontanaapp.entities.Category;

/**
 * Klasa konwertująca encje do obiektu DTO
 */
public class CategoryDtoConverter {

    /**
     * Konwertuje encję do obiektu DTO
     * @param category
     * @return
     */
    public static CategoryOutputDto toDto(Category category) {
        return CategoryOutputDto.builder()
                .id(category.getId())
                .name(category.getName())
                .verb(category.getVerb())
                .build();
    }

}
