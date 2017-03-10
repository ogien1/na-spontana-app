package pl.lodz.p.it.naspontanaapp.service;

import java.util.List;

import pl.lodz.p.it.naspontanaapp.domain.ActivityInputDto;
import pl.lodz.p.it.naspontanaapp.domain.ActivityOutputDto;
import pl.lodz.p.it.naspontanaapp.domain.BaseActivityInputDto;
import pl.lodz.p.it.naspontanaapp.domain.SimilarActivityInputDto;

/**
 * Zawiera obłusgę aktywności
 */
public interface ActivityCreationManager {

	/**
	 * Dodaje nową aktywność
	 * @param activityInputDto
	 * @return
	 */
	Long addActivity(BaseActivityInputDto activityInputDto);

	/**
	 * Dodaneje użytkownika do aktywności
	 * @param facebookId
	 * @param activityId
	 */
	void addUserToActivity(String facebookId, long activityId);

	/**
	 * Znajduje podobne aktywności na podstawie przekazanych parametrów
	 * @param inputDTO
	 * @return
	 */
	List<ActivityOutputDto> findSimilarActivities(SimilarActivityInputDto inputDTO);

}
