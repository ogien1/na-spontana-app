package pl.lodz.p.it.naspontanaapp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;


/**
 * Encja zawierająca dane aktywności
 */
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name="activity")
@NamedQuery(name="Activity.findAll", query="SELECT a FROM Activity a")
public class Activity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Identyfikator
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique=true, nullable=false)
    private Long id;

    /**
     * Opisa aktywności
     */
    @Column(nullable=false, length=100)
    private String description;

    /**
     * Nazwa aktywności
     */
    @Column(nullable=false, length=50)
    private String name;

    /**
     * Data publikacji
     */
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    @Column(name="publication_date", nullable=false)
    private LocalDateTime publicationDate;

    /**
     * Czy aktywność została już opublikowana
     */
    private Boolean published;

    /**
     * Data początkowa
     */
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    @Column(name="start_date", nullable=false)
    private LocalDateTime startDate;

    /**
     * Identyfikator kategorii
     */
    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category category;

    /**
     * Właściciel aktywności
     */
    @ManyToOne
    @JoinColumn(name="owner_id", nullable=false)
    private User owner;

    /**
     * Lista uczestników aktywności
     */
    @ManyToMany(mappedBy="activities", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", publicationDate=" + publicationDate +
                ", published=" + published +
                ", startDate=" + startDate +
                ", category=" + category.getName() +
                ", owner=" + owner.getFacebookId() +
                ", users=" + users.stream().map(User::getFacebookId).collect(Collectors.toList()) +
                '}';
    }
}