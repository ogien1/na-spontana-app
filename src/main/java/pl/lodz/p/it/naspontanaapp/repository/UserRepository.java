package pl.lodz.p.it.naspontanaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.lodz.p.it.naspontanaapp.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findUserByFacebookId(String facebookId);
}
