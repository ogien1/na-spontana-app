package pl.lodz.p.it.naspontanaapp.domain;

import org.joda.time.DateTime;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by piotr on 30.11.16.
 */
public class ActivityDto {

    @NotNull
    private String facebookId;

    @NotNull
    private String description;

    @NotNull
    private String name;

    @NotNull
    private DateTime startDate;

    @NotNull
    private long category;

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
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

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ActivityDto{" +
                "facebookId='" + facebookId + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", category=" + category +
                '}';
    }
}
