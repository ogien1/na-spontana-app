package pl.lodz.p.it.naspontanaapp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the user database table.
 */
@Entity
@Table(name = "user")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "facebook_id", nullable = false, length = 256)
    private String facebookId;

    @Column(nullable = false, length = 50)
    private String lastname;

    @Column(nullable = false, length = 50)
    private String name;
    
	@OneToMany(mappedBy="owner")
	private List<Activity> ownerActivities = new ArrayList<>();

    //bi-directional many-to-many association to ActivityInputDto
    @ManyToMany
    @JoinTable(
            name = "user_activity"
            , joinColumns = {
            @JoinColumn(name = "user_id", nullable = false)
    }
            , inverseJoinColumns = {
            @JoinColumn(name = "activity_id", nullable = false)
    }
    )
    private List<Activity> activities = new ArrayList<>();

    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacebookId() {
        return this.facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Activity> getActivities() {
        return Optional.ofNullable(activities).orElse(Collections.emptyList());
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", facebookId='" + facebookId + '\'' +
                ", lastname='" + lastname + '\'' +
                ", name='" + name + '\'' +
                ", activities=" + activities +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        User user = (User) o;

        if (this.id != null ? !this.id.equals(user.id) : user.id != null) return false;
        if (this.facebookId != null ? !this.facebookId.equals(user.facebookId) : user.facebookId != null) return false;
        if (this.lastname != null ? !this.lastname.equals(user.lastname) : user.lastname != null) return false;
        if (this.name != null ? !this.name.equals(user.name) : user.name != null) return false;
        return this.activities != null ? (this.activities.containsAll(user.activities)
                && user.activities.containsAll(this.activities)) : user.activities == null;

    }

    @Override
    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.facebookId != null ? this.facebookId.hashCode() : 0);
        result = 31 * result + (this.lastname != null ? this.lastname.hashCode() : 0);
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        result = 31 * result + (this.activities != null ? this.activities.hashCode() : 0);
        return result;
    }

	public List<Activity> getOwnerActivities() {
		return ownerActivities;
	}

	public void setOwnerActivities(List<Activity> ownerActivities) {
		this.ownerActivities = ownerActivities;
	}
}