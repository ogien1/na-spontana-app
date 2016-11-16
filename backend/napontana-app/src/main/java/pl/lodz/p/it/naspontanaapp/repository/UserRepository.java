package pl.lodz.p.it.naspontanaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lodz.p.it.naspontanaapp.entities.User;

public interface UserRepository extends JpaRepository<User, Long>  {
	
}
