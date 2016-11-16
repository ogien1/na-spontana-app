package pl.lodz.p.it.naspontanaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.lodz.p.it.naspontanaapp.entities.Activity;

public interface ActivityRepository extends JpaRepository<Activity, Long>  {
	
//	List<LinkedRoute> findByLinkedDateGreaterThan(LocalDateTime lastUpdateDate);

//	@Query("select count(e)>0 from LinkedRoute e where e.offeredRoute.routeCode = ?1 and e.searchedRoute.routeCode = ?2")
//	boolean exists(String offeredRouteCode, String searchedRouteCode);
}
