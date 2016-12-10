package pl.lodz.p.it.naspontanaapp.domain;

import java.util.Arrays;

/**
 * Created by 'Jakub Dziworski' on 09.12.16
 */
public class SimilarActivityInputDto extends ActivityInputDto{
   
	private long minutesDiff;
	private String[] friends;

	public long getMinutesDiff() {
		return minutesDiff;
	}

	public void setMinutesDiff(long minutesDiff) {
		this.minutesDiff = minutesDiff;
	}

	public String[] getFriends() {
		return friends;
	}

	public void setFriends(String[] friends) {
		this.friends = friends;
	}

	@Override
	public String toString() {
		return "SimilarActivityInputDto [minutesDiff=" + minutesDiff + ", friends=" + Arrays.toString(friends)
				+ ", startDate=" + startDate + ", categoryId=" + categoryId + ", description=" + description + ", name="
				+ name + "]";
	}
	
	
	
}
