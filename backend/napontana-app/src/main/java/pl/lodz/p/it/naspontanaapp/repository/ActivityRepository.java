package pl.lodz.p.it.naspontanaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.lodz.p.it.naspontanaapp.entities.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>  {
    Activity findById(long id);
}
