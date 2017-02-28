package pl.lodz.p.it.naspontanaapp.domain;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by piotr on 30.11.16.
 */
public class ActivityOutputDto {

    protected String startDate;
    protected long categoryId;
    protected String description;
    protected String name;
    protected String ownerFbId;
    @NotNull
    private List<String> participantsID;

    private long activityId;

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public List<String> getParticipantsID() {
        return this.participantsID;
    }

    public void setParticipantsID(List<String> participantsID) {
        this.participantsID = participantsID;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        ActivityOutputDto that = (ActivityOutputDto) o;

        if (this.categoryId != that.categoryId) return false;
        if (this.activityId != that.activityId) return false;
        if (this.startDate != null ? !this.startDate.equals(that.startDate) : that.startDate != null) return false;
        if (this.description != null ? !this.description.equals(that.description) : that.description != null) return false;
        if (this.ownerFbId != null ? !this.ownerFbId.equals(that.ownerFbId) : that.ownerFbId != null) return false;
        if (this.name != null ? !this.name.equals(that.name) : that.name != null) return false;
        return this.participantsID != null ? this.participantsID.equals(that.participantsID) : that.participantsID == null;

    }

    @Override
    public int hashCode() {
        int result = this.startDate != null ? this.startDate.hashCode() : 0;
        result = 31 * result + (int) (this.categoryId ^ this.categoryId >>> 32);
        result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
        result = 31 * result + (this.ownerFbId != null ? this.ownerFbId.hashCode() : 0);
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        result = 31 * result + (this.participantsID != null ? this.participantsID.hashCode() : 0);
        result = 31 * result + (int) (this.activityId ^ this.activityId >>> 32);
        return result;
    }

    @Override
    public String toString() {
        return "ActivityOutputDto{" +
                "startDate=" + startDate +
                ", categoryId=" + categoryId +
                ", ownerId=" + ownerFbId +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", participantsID=" + participantsID +
                ", activityId=" + activityId +
                '}';
    }

	public String getOwnerFbId() {
		return ownerFbId;
	}

	public void setOwnerId(String ownerFbId) {
		this.ownerFbId = ownerFbId;
	}
}
