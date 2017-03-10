package pl.lodz.p.it.naspontanaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lodz.p.it.naspontanaapp.entities.User;

/**
 * Repozytorium użytkowników
 */
public interface UserRepository extends JpaRepository<User, Long> {
	/**
	 * Pobiera dane użytkonika na podstawie podstawie podanego indetyfikatora facebook
	 * @param facebookId
	 * @return
	 */
	User findUserByFacebookId(String facebookId);
}
