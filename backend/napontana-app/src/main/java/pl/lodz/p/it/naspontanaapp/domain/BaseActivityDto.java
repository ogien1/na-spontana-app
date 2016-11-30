package pl.lodz.p.it.naspontanaapp.domain;

import org.joda.time.LocalDateTime;

import javax.validation.constraints.NotNull;

/**
 * Created by piotr on 30.11.16.
 */
public class BaseActivityDto {

    protected LocalDateTime startDate;

    protected long categoryId;

    protected String description;

    protected String name;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BaseActivityDto{" +
                "startDate=" + startDate +
                ", categoryId=" + categoryId +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
