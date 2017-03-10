package pl.lodz.p.it.naspontanaapp.entities;

import lombok.*;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Encja zawierająca dane kategorii aktywności
 */
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name="category")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Identyfikator
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	/**
	 * Nazwa kategorii
	 */
	@Column(nullable=false, length=50)
	private String name;

	/**
	 * Czynność jaką można wykonywać w ramach aktywności
	 */
	@Column(nullable=false, length=50)
	private String verb;

	/**
	 * Lista aktywności zawierająca kategorię
	 */
	@OneToMany(mappedBy="category")
	private List<Activity> activities;
}