package pl.lodz.p.it.naspontanaapp.entities;

import lombok.*;

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
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
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

}