package pl.lodz.p.it.naspontanaapp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;


/**
 * The persistent class for the activity database table.
 *
 */
@Entity
@Table(name="activity")
@NamedQuery(name="Activity.findAll", query="SELECT a FROM Activity a")
public class Activity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false, length=100)
	private String description;

	@Column(nullable=false, length=50)
	private String name;

	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	@Column(name="publication_date", nullable=false)
	private LocalDateTime publicationDate;

	private Boolean published;

	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	@Column(name="start_date", nullable=false)
	private LocalDateTime startDate;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="category_id", nullable=false)
	private Category category;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="activities", cascade = CascadeType.ALL)
	private List<User> users = new ArrayList<>();

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDateTime getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(LocalDateTime publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || this.getClass() != o.getClass()) return false;

		Activity activity = (Activity) o;

		if (this.id != null ? !this.id.equals(activity.id) : activity.id != null) return false;
		if (this.description != null ? !this.description.equals(activity.description) : activity.description != null)
			return false;
		if (this.name != null ? !this.name.equals(activity.name) : activity.name != null) return false;
		if (this.publicationDate != null ? !this.publicationDate.equals(activity.publicationDate) : activity.publicationDate != null)
			return false;
		if (this.published != null ? !this.published.equals(activity.published) : activity.published != null) return false;
		if (this.startDate != null ? !this.startDate.equals(activity.startDate) : activity.startDate != null) return false;
		if (this.category != null ? !this.category.equals(activity.category) : activity.category != null) return false;
		return this.users != null ? this.users.equals(activity.users) : activity.users == null;

	}

	@Override
	public int hashCode() {
		int result = this.id != null ? this.id.hashCode() : 0;
		result = 31 * result + (this.description != null ? this.description.hashCode() : 0);
		result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
		result = 31 * result + (this.publicationDate != null ? this.publicationDate.hashCode() : 0);
		result = 31 * result + (this.published != null ? this.published.hashCode() : 0);
		result = 31 * result + (this.startDate != null ? this.startDate.hashCode() : 0);
		result = 31 * result + (this.category != null ? this.category.hashCode() : 0);
		result = 31 * result + (this.users != null ? this.users.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Activity{" +
				"id=" + id +
				", description='" + description + '\'' +
				", name='" + name + '\'' +
				", publicationDate=" + publicationDate +
				", published=" + published +
				", startDate=" + startDate +
				", category=" + category +
				", users=" + users +
				'}';
	}
}