package pl.lodz.p.it.naspontanaapp.domain;

/**
 * Bazowy interfejs aktywności
 */
public interface BaseActivityInputDto {
    String getStartDate();
    long getCategoryId();
    String getDescription();
    String getName();
    String getFacebookId();
}
