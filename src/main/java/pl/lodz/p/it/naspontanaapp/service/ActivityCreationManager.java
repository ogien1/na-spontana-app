package pl.lodz.p.it.naspontanaapp.service;

import java.util.List;

import pl.lodz.p.it.naspontanaapp.domain.ActivityInputDto;
import pl.lodz.p.it.naspontanaapp.domain.ActivityOutputDto;
import pl.lodz.p.it.naspontanaapp.domain.BaseActivityInputDto;
import pl.lodz.p.it.naspontanaapp.domain.SimilarActivityInputDto;

public interface ActivityCreationManager {
	
	Long addActivity(BaseActivityInputDto activityInputDto);

	void addUserToActivity(String facebookId, long activityId);

	List<ActivityOutputDto> findSimilarActivities(SimilarActivityInputDto inputDTO);

}
