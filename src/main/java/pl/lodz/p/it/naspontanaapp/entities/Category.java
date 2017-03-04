package pl.lodz.p.it.naspontanaapp.entities;

import lombok.Data;

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
 * The persistent class for the category database table.
 * 
 */
@Data
@Entity
@Table(name="category")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(nullable=false, length=50)
	private String name;

	@Column(nullable=false, length=50)
	private String verb;

	//bi-directional many-to-one association to ActivityInputDto
	@OneToMany(mappedBy="category")
	private List<Activity> activities;
}