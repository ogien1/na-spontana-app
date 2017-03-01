package pl.lodz.p.it.naspontanaapp.domain;

import javax.validation.constraints.NotNull;

/**
 * Created by piotr on 30.11.16.
 */
public class ActivityInputDto {

	protected String startDate;
	
	protected long categoryId;
	
	protected String description;
	
	protected String name;
	
	@NotNull
	private String facebookId;

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
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

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;

		ActivityInputDto that = (ActivityInputDto) o;

		if (this.categoryId != that.categoryId)
			return false;
		if (this.startDate != null ? !this.startDate.equals(that.startDate) : that.startDate != null)
			return false;
		if (this.description != null ? !this.description.equals(that.description) : that.description != null)
			return false;
		if (this.name != null ? !this.name.equals(that.name) : that.name != null)
			return false;
		return this.facebookId != null ? this.facebookId.equals(that.facebookId) : that.facebookId == null;

	}

	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (this.startDate != null ? this.startDate.hashCode() : 0);
		result = 31 * result + (int) (this.categoryId ^ this.categoryId >>> 32);
		result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
		result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
		result = 31 * result + (this.facebookId != null ? this.facebookId.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "ActivityInputDto{" + "startDate=" + startDate + ", categoryId=" + categoryId + ", description='"
				+ description + '\'' + ", name='" + name + '\'' + ", facebookId='" + facebookId + '\'' + '}';
	}
}
