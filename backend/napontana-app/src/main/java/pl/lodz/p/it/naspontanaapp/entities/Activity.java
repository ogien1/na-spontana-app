package pl.lodz.p.it.naspontanaapp.entities;

import org.hibernate.annotations.*;
import org.joda.time.LocalDateTime;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;


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

}