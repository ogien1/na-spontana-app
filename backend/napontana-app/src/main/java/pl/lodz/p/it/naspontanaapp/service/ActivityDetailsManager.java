package pl.lodz.p.it.naspontanaapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.lodz.p.it.naspontanaapp.entities.Activity;
import pl.lodz.p.it.naspontanaapp.repository.ActivityRepository;

@Service
public class ActivityDetailsManager {

    @Autowired
    private ActivityRepository activityRepository;

    public Activity getActivity(long activtyId) {
        return activityRepository.findById(activtyId);
    }
}
