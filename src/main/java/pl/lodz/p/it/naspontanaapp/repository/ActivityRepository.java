package pl.lodz.p.it.naspontanaapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.lodz.p.it.naspontanaapp.entities.Activity;

/**
 * Repozytorium aktywności
 */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>  {

	/**
	 * Pobiera wszystkie aktywności dla podanego identyfikatora facebook
	 * @param facebookId
	 * @return
	 */
	List<Activity> findActivityByOwner_facebookId(String facebookId);

}
