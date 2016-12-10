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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SimilarActivityInputDto that = (SimilarActivityInputDto) o;

        if (minutesDiff != that.minutesDiff) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(friends, that.friends);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (minutesDiff ^ (minutesDiff >>> 32));
        result = 31 * result + Arrays.hashCode(friends);
        return result;
    }
}
