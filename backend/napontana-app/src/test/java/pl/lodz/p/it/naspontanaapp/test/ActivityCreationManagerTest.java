package pl.lodz.p.it.naspontanaapp.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.lodz.p.it.naspontanaapp.domain.ActivityOutputDto;
import pl.lodz.p.it.naspontanaapp.domain.SimilarActivityInputDto;
import pl.lodz.p.it.naspontanaapp.entities.Activity;
import pl.lodz.p.it.naspontanaapp.entities.Category;
import pl.lodz.p.it.naspontanaapp.entities.User;
import pl.lodz.p.it.naspontanaapp.service.ActivityCreationManager;
import pl.lodz.p.it.naspontanaapp.service.ActivityListingManager;
import pl.lodz.p.it.naspontanaapp.utils.DateFormater;
import pl.lodz.p.it.naspontanaapp.utils.TimeYodaUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ActivityCreationManagerTest {

    private static final List<String> FACEBOOK_IDS = Arrays.asList("5872882191377338","5872882ddd191377338");

    private static final long CATEGORY_ID = 1;

    private static final long MINUTES_DIFF = 10;

    private static final String START_DATE1 = "2016-12-18T13:20:00";

    private static final String START_DATE2 = "2016-11-17T13:46:00";

    private static final String START_DATE3 = "2016-11-17T13:16:00";

    private static final String START_DATE4 = "2016-12-18T13:29:00";

    private static final String START_DATE5 = "2016-12-18T13:09:00";

    @Mock
    private ActivityListingManager activityListingManager;

    @InjectMocks
    private ActivityCreationManager activityCreationManager;

    @Test
    public void shouldNotFindSimilarActivities() {
        SimilarActivityInputDto similarActivityInputDto = createSimilarActivityInputDto(
                START_DATE1, CATEGORY_ID, MINUTES_DIFF, FACEBOOK_IDS.toArray(new String[0]));
        List<Activity> activityList = Arrays.asList(
                createActivity(START_DATE2, 1), createActivity(START_DATE3, 1));
        when(activityListingManager.getActivities(FACEBOOK_IDS)).thenReturn(activityList);

        List<ActivityOutputDto> activityOutputDto = activityCreationManager.findSimilarActivities(similarActivityInputDto);

        assertEquals(activityOutputDto.size(), 0);
    }

    @Test
    public void shouldFindSimilarActivities() {
        SimilarActivityInputDto similarActivityInputDto = createSimilarActivityInputDto(
                START_DATE1, CATEGORY_ID, MINUTES_DIFF, FACEBOOK_IDS.toArray(new String[0]));
        List<Activity> activityList = Arrays.asList(
                createActivity(START_DATE4, 1), createActivity(START_DATE3, 1),
                createActivity(START_DATE5, 1));
        when(activityListingManager.getActivities(FACEBOOK_IDS)).thenReturn(activityList);

        List<ActivityOutputDto> activityOutputDtoList =
                activityCreationManager.findSimilarActivities(similarActivityInputDto);

        assertEquals(activityOutputDtoList.size(), 1);
    }

    private SimilarActivityInputDto createSimilarActivityInputDto(String startDate, long categoryId, long minutesDiff,
                                                                  String[] facebookIds) {
        SimilarActivityInputDto similarActivityInputDto = new SimilarActivityInputDto();
        similarActivityInputDto.setStartDate(startDate);
        similarActivityInputDto.setCategoryId(categoryId);
        similarActivityInputDto.setMinutesDiff(minutesDiff);
        similarActivityInputDto.setFriends(facebookIds);

        return similarActivityInputDto;
    }

    private Activity createActivity(String startDate, long categoryId) {
        Category category = new Category();
        category.setId(categoryId);

        Activity activity = new Activity();
        activity.setStartDate(DateFormater.convert(startDate));
        activity.setCategory(category);
        activity.setId(0L);
        activity.setOwner(new User());

        return activity;
    }

}