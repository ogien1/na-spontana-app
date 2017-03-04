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

import lombok.Data;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;


/**
 * The persistent class for the activity database table.
 *
 */
@Data
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
	
	@ManyToOne
	@JoinColumn(name="owner_id", nullable=false)
	private User owner;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="activities", cascade = CascadeType.ALL)
	private List<User> users = new ArrayList<>();

}