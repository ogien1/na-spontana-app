package pl.lodz.p.it.naspontanaapp.domain;

import org.joda.time.LocalDateTime;

/**
 * Created by 'Jakub Dziworski' on 09.12.16
 */
public class SimilarActivityInputDto {
    private LocalDateTime start;

    public LocalDateTime getStart() {
        return this.start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        SimilarActivityInputDto that = (SimilarActivityInputDto) o;

        return this.start != null ? this.start.equals(that.start) : that.start == null;

    }

    @Override
    public int hashCode() {
        return this.start != null ? this.start.hashCode() : 0;
    }
}
