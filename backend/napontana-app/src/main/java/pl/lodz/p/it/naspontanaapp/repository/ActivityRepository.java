package pl.lodz.p.it.naspontanaapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.lodz.p.it.naspontanaapp.entities.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>  {

	List<Activity> findActivityByUsers_FacebookId(String friendId);

}
