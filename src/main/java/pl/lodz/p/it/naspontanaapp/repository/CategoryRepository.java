package pl.lodz.p.it.naspontanaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lodz.p.it.naspontanaapp.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>  {
	
}
