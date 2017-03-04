package pl.lodz.p.it.naspontanaapp.domain;

import javax.validation.constraints.NotNull;

/**
 * Created by jdziworski on 04.03.17.
 */
public interface BaseActivityInputDto {
    String getStartDate();
    long getCategoryId();
    String getDescription();
    String getName();
    String getFacebookId();
}
